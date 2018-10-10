package app.controller;

import app.dto.*;

import app.exception.MinQuantityException;
import app.exception.MinTotalPriceOrderException;
import app.exception.ObjectAlreadyInOrderException;
import app.service.api.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Controller

public class MainController {

    private static final Logger logger = Logger.getLogger(MainController.class);

    public static final List<ProductDTO> productDTOList = new ArrayList<>();

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private FillingService fillingService;

    @Autowired
    private DoughService doughService;

    @Autowired
    private SprinkleService sprinkleService;

    @RequestMapping(value = "/getProductsByParameters", method = RequestMethod.GET)
    @ResponseBody
    public AjaxDTO getProductsByParameters(
            @RequestParam(value = "categoryId", required = false) Integer categoryId,
            @RequestParam(value = "fillingId", required = false) Integer fillingId,
            @RequestParam(value = "doughId", required = false) Integer doughId,
            @RequestParam(value = "sprinkleIdList", required = false) List<Integer> sprinkleIdList,
            @RequestParam(value = "productName", required = false) String productName,
            @RequestParam(value = "minPrice", required = false) Integer minPrice,
            @RequestParam(value = "maxPrice", required = false) Integer maxPrice) {
        AjaxDTO result = new AjaxDTO();
        result.setData(productService.getProductsByParameters(categoryId, fillingId, doughId, sprinkleIdList, productName, minPrice, maxPrice));
        return result;
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public String filter(ModelMap modelMap, HttpSession session) {
        session.setAttribute("countProductInOrder", productDTOList.size());
        if (session.getAttribute("order") == null) {
            session.setAttribute("order", new OrderDTO());
        }
        modelMap.addAttribute("allProducts", productService.getAll());
        modelMap.addAttribute("categoryList", categoryService.getAll());
        modelMap.addAttribute("fillingList", fillingService.getAll());
        modelMap.addAttribute("doughList", doughService.getAll());
        modelMap.addAttribute("sprinkleList", sprinkleService.getAll());
        return "main/filter";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String cart(ModelMap modelMap, HttpSession session) {
        UserDTO userDTO = userService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        if (userDTO != null) {
            List<AddressDTO> addressDTOList = addressService.getAddressesByUserId(userDTO.getId());
            if (addressDTOList != null) {
                modelMap.addAttribute("userAddresses", addressDTOList);
            }
        }
        session.setAttribute("countProductInOrder", productDTOList.size());
        if (session.getAttribute("order") == null) {
            session.setAttribute("order", new OrderDTO());
        }
        return "main/cart";
    }

    @RequestMapping(value = "/emptyCart", method = RequestMethod.GET)
    @ResponseBody
    public AjaxDTO addToOrder() {
        AjaxDTO result = new AjaxDTO();
        productDTOList.clear();
        result.setData(productDTOList);
        return result;
    }

    @RequestMapping(value = "/addProductToOrder", method = RequestMethod.GET)
    @ResponseBody
    public AjaxDTO addToOrder(@Valid @NotNull @RequestParam(value = "id") Integer productId, @Valid @NotNull @RequestParam(value = "quantity") Short quantity, HttpSession session) {
        AjaxDTO result = new AjaxDTO();
        ProductDTO productDTO = productService.getById(productId);
        for (int i = 0; i < productDTOList.size(); i++) {
            if (productDTOList.get(i).getId().equals(productDTO.getId())) {
                throw new ObjectAlreadyInOrderException(String.format("Product with name %s already in your Order", productDTO.getName()));
            }
        }
        if(quantity >=1){
            productDTO.setQuantity(quantity);
        }else {
            throw new MinQuantityException("Wrong Quantity");
        }
        productDTOList.add(productDTO);
        OrderDTO orderDTO = (OrderDTO) session.getAttribute("order");
        orderDTO.setProductList(productDTOList);
        Float totalPrice = 0.0f;
        for (int i = 0; i < productDTOList.size(); i++) {
            totalPrice += productDTOList.get(i).getPrice() * productDTOList.get(i).getQuantity();
        }
        orderDTO.setTotalPrice(totalPrice);
        result.setData(orderDTO);
        logger.info(String.format("Successfully added Product in Cart: %s", productDTO.getName()));
        logger.info(String.format("ProductCount in Cart: %d", productDTOList.size()));
        return result;
    }

    @RequestMapping(value = "/deleteProductByIdFromOrder", method = RequestMethod.GET)
    @ResponseBody
    public AjaxDTO deleteProductById(@Valid @NotNull @RequestParam(value = "id") Integer productId, HttpSession session) {
        AjaxDTO result = new AjaxDTO();
        for (int i = 0; i < productDTOList.size(); i++) {
            if (productId.equals(productDTOList.get(i).getId())) {
                productDTOList.remove(productDTOList.get(i));
                logger.info("Successfully deleted Product from Cart");
            }
        }
        OrderDTO orderDTO = (OrderDTO) session.getAttribute("order");
        orderDTO.setProductList(productDTOList);
        Float totalPrice = 0.0f;
        for (int i = 0; i < productDTOList.size(); i++) {
            totalPrice += productDTOList.get(i).getPrice() * productDTOList.get(i).getQuantity();
        }
        orderDTO.setTotalPrice(totalPrice);
        result.setData(orderDTO);
        return result;
    }

    @RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
    @ResponseBody
    public AjaxDTO saveOrder(@Valid @RequestBody OrderDTO order, HttpSession session) {
        AjaxDTO result = new AjaxDTO();
        OrderDTO orderDTO = (OrderDTO) session.getAttribute("order");
        UserDTO userDTO = userService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        if (order.getAddress() != null) {
            if (order.getAddress().getId() != null) {
                orderDTO.setAddress(addressService.getById(order.getAddress().getId()));
            } else if (order.getAddress() != null) {
                order.getAddress().setUserDTO(userDTO);
                addressService.create(order.getAddress());
                orderDTO.setAddress(order.getAddress());
            }
        }
        if (orderDTO.getTotalPrice() < 600) {
            throw new MinTotalPriceOrderException("Total Price can't be less than 600P");
        }
        orderDTO.setDeliveryOption(order.getDeliveryOption());
        orderDTO.setPaymentOption(order.getPaymentOption());
        orderDTO.setUserDTO(userDTO);
        orderService.create(orderDTO);
        result.setData(orderDTO);
        session.setAttribute("order", new OrderDTO());
        productDTOList.clear();
        return result;
    }

}
