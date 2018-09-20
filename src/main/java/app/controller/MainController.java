package app.controller;

import app.dto.AjaxDTO;
import app.dto.OrderDTO;
import app.dto.ProductDTO;
import app.exception.ObjectAlreadyInOrder;
import app.service.api.CategoryService;
import app.service.api.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/jsDonut")
public class MainController {

    private static final Logger logger = LogManager.getLogger(MainController.class);

    public static final List<ProductDTO> productDTOList = new ArrayList<>();

    public static final OrderDTO ORDER = new OrderDTO();

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

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
        session.setAttribute("order", ORDER);
        modelMap.addAttribute("categoryList", categoryService.getAll());
        return "main/filter";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String order(HttpSession session) {
        session.setAttribute("countProductInOrder", productDTOList.size());
        session.setAttribute("order", ORDER);
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
    public AjaxDTO addToOrder(@RequestParam(value = "id") Integer productId, @RequestParam(value = "quantity") Short quantity, HttpSession session) {
        AjaxDTO result = new AjaxDTO();
        ProductDTO productDTO = productService.getById(productId);
        for (int i = 0; i < productDTOList.size(); i++) {
            if (productDTO.getId() == productDTOList.get(i).getId()) {
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
        logger.info(String.format("Successfully added Product in Cart: " + productDTO.getName()));
        logger.info(String.format("ProductCount in Cart: " + productDTOList.size()));
        return result;
    }

    @RequestMapping(value = "/deleteProductByIdFromOrder",method = RequestMethod.GET)
    @ResponseBody
    public AjaxDTO deleteProductById(@RequestParam(value = "id") Integer productId, HttpSession session){
        AjaxDTO result = new AjaxDTO();
        for (int i = 0; i < productDTOList.size();i++){
            if(productDTOList.get(i).getId() == productId){
                productDTOList.remove(productDTOList.get(i));
                logger.info(String.format("Successfully deleted Product from Cart"));
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
}
