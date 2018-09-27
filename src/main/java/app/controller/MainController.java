package app.controller;

import app.dto.*;

import app.exception.ObjectAlreadyInOrder;
import app.service.api.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
@RequestMapping(value = "/jsDonut")
public class MainController {

    private static final Logger logger = LogManager.getLogger(MainController.class);

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

    @RequestMapping(value = "/getProductsByParameters", method = RequestMethod.GET)
    @ResponseBody
    public AjaxDTO getProductsByParameters(
            @RequestParam(value = "categoryId", required = false) Integer categoryId,
            @RequestParam(value = "productName", required = false) String productName,
            @RequestParam(value = "minPrice", required = false) Integer minPrice,
            @RequestParam(value = "maxPrice", required = false) Integer maxPrice) {
        AjaxDTO result = new AjaxDTO();
        result.setData(productService.getProductsByParameters(categoryId, productName, minPrice, maxPrice));
        return result;
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public String filter(ModelMap modelMap, HttpSession session) {
        session.setAttribute("countProductInOrder", productDTOList.size());
        if (session.getAttribute("order") == null) {
            session.setAttribute("order", new OrderDTO());
        }
        modelMap.addAttribute("allProducts",productService.getAll());
        modelMap.addAttribute("categoryList", categoryService.getAll());
        return "main/filter";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String cart(ModelMap modelMap,HttpSession session) {
        UserDTO userDTO = userService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        modelMap.addAttribute("userAddresses", addressService.getAddressesByUserId(userDTO.getId()));
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
                throw new ObjectAlreadyInOrder(String.format("Product with name %s already in your Order", productDTO.getName()));
            }
        }
        productDTO.setQuantity(quantity);
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

        //addressService.create(addressDTO);

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
