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

/**
 * Main controller
 */
@Controller
public class MainController {

    private static final Logger logger = Logger.getLogger(MainController.class);

    public static final List<ProductDTO> productDTOList = new ArrayList<>();

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AddressService addressService;

    /**
     * Method for ajax CRUD operations
     * @param filterDTO - param for the filter operation
     * @return - list objects with special response params for the query
     */
    @RequestMapping(value = "/getProductsByParameters", method = RequestMethod.POST)
    @ResponseBody
    public AjaxDTO getProductsByParameters(@RequestBody FilterDTO filterDTO) {
        AjaxDTO result = new AjaxDTO();
        result.setData(productService.getProductsByParameters(filterDTO));
        return result;
    }

    /**
     * @param modelMap - model for view
     * @param session - param for keeping and use session attributes
     * @return - string view name
     */
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

    /**
     * Method for ajax clear order product list operation
     * @param session - param for keeping and use session attributes
     * @return - list products with special response params for the query
     */
    @RequestMapping(value = "/emptyCart", method = RequestMethod.GET)
    @ResponseBody
    public AjaxDTO addToOrder(HttpSession session) {
        AjaxDTO result = new AjaxDTO();
        productDTOList.clear();
        if (session.getAttribute("order") == null) {
            session.setAttribute("order", new OrderDTO());
        }else {
            OrderDTO orderDTO = (OrderDTO) session.getAttribute("order");
            orderDTO.setTotalPrice(0.0f);
            session.setAttribute("order",orderDTO);
        }
        result.setData(productDTOList);
        return result;
    }

    /**
     * Method for ajax add product to order product list operation
     * @param productId - param for search product by id
     * @param quantity - param for keep quantity product
     * @param session - param for keeping and use session attributes
     * @return - object with special response params for the query
     */
    @RequestMapping(value = "/addProductToOrder", method = RequestMethod.GET)
    @ResponseBody
    public AjaxDTO addToOrder(@Valid @NotNull @RequestParam(value = "id") Integer productId, @Valid @NotNull @RequestParam(value = "quantity") Short quantity, HttpSession session) {
        AjaxDTO result = new AjaxDTO();
        ProductDTO productDTO = productService.getById(productId);
        for (int i = 0; i < productDTOList.size(); i++) {
            if (productDTOList.get(i).getId().equals(productDTO.getId())) {
                throw new ObjectAlreadyInOrderException(String.format("Product with name %s already in your Order", productDTO.getName())); } }
        if(productDTO.getQuantity() < quantity){ throw new MinQuantityException(String.format("Wrong quantity, select available value. Available quantity: %s",productDTO.getQuantity())); }
        if(quantity >=1){ productDTO.setQuantity(quantity); }
        else { throw new MinQuantityException("Wrong Quantity"); }
        productDTOList.add(productDTO);
        OrderDTO orderDTO = (OrderDTO) session.getAttribute("order");
        orderDTO.setProductList(productDTOList);
        Float totalPrice = 0.0f;
        for (int i = 0; i < productDTOList.size(); i++) {
            totalPrice += productDTOList.get(i).getPrice() * productDTOList.get(i).getQuantity(); }
        orderDTO.setTotalPrice((float) (Math.round(totalPrice*100.0)/100.0));
        result.setData(orderDTO);
        logger.info(String.format("Successfully added Product in Cart: %s", productDTO.getName()));
        logger.info(String.format("ProductCount in Cart: %d", productDTOList.size()));
        return result;
    }

    /**
     * Method for ajax delete product from order product list operation
     * @param productId - param for search product by id
     * @param session - param for keeping and use session attributes
     * @return - object with special response params for the query
     */
    @RequestMapping(value = "/deleteProductByIdFromOrder", method = RequestMethod.GET)
    @ResponseBody
    public AjaxDTO deleteProductById(@Valid @NotNull @RequestParam(value = "id") Integer productId, HttpSession session) {
        AjaxDTO result = new AjaxDTO();
        for (int i = 0; i < productDTOList.size(); i++) {
            if (productId.equals(productDTOList.get(i).getId())) {
                productDTOList.remove(productDTOList.get(i));
                logger.info("Successfully deleted Product from Cart"); } }
        OrderDTO orderDTO = (OrderDTO) session.getAttribute("order");
        orderDTO.setProductList(productDTOList);
        Float totalPrice = 0.0f;
        for (int i = 0; i < productDTOList.size(); i++) {
            totalPrice += productDTOList.get(i).getPrice() * productDTOList.get(i).getQuantity(); }
        orderDTO.setTotalPrice(totalPrice);
        result.setData(orderDTO);
        return result;
    }

    /**
     * Method for ajax save order operation
     * @param order - order object for the save operation
     * @param session - param for keeping and use session attributes
     * @return - object with special response params for the query
     */
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
                addressService.checkAddressFields(order.getAddress());
                order.getAddress().setUserDTO(userDTO);
                addressService.create(order.getAddress());
                orderDTO.setAddress(order.getAddress()); } }
        if (orderDTO.getTotalPrice() < 600) {
            throw new MinTotalPriceOrderException("Total Price can't be less than 600P"); }
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
