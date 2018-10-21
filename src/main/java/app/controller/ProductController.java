package app.controller;

import app.dto.AjaxDTO;
import app.dto.ProductDTO;
import app.exception.MinLengthFieldException;
import app.exception.MinValueException;
import app.message.MessageSender;
import app.service.api.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private DoughService doughService;
    @Autowired
    private FillingService fillingService;
    @Autowired
    private SprinkleService sprinkleService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String openPage(ModelMap modelMap) {
        modelMap.addAttribute("productActive", "active");
        modelMap.addAttribute("productList", productService.getAll());
        modelMap.addAttribute("fillingList", fillingService.getAll());
        modelMap.addAttribute("sprinkleList", sprinkleService.getAll());
        modelMap.addAttribute("doughList", doughService.getAll());
        modelMap.addAttribute("categoryList", categoryService.getAll());
        return "admin/product";
    }

    @RequestMapping(value = "/product/getProductById", method = RequestMethod.GET)
    @ResponseBody
    public AjaxDTO getDough(@RequestParam(value = "id") @Valid @NotEmpty(message = "Product id cannot be empty") Integer productId) {
        AjaxDTO result = new AjaxDTO();
        result.setData(productService.getById(productId));
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/product/createProduct", method = RequestMethod.POST)
    public AjaxDTO createProduct(@RequestBody ProductDTO productDTO) {
        AjaxDTO result = new AjaxDTO();
        if (productDTO != null) {
            if (productDTO.getName().length() < 1 || productDTO.getDescription().length() < 1 ||
                    productDTO.getCalories() == null || productDTO.getQuantity() == null ||
                    productDTO.getWeight() == null) {
                throw new MinLengthFieldException(" Field can not be empty");
            } else if (productDTO.getCategory().getId() == null) {
                throw new MinLengthFieldException("Field Category can not be empty");
            } else if (productDTO.getDough().getId() == null) {
                throw new MinLengthFieldException("Field Dough can not be empty");
            } else if (productDTO.getPrice() < 10) {
                throw new MinValueException(" Price can not be less than 10P");
            } else if (productDTO.getCalories() < 50) {
                throw new MinValueException("Calories can not be less than 50 ");
            } else if (productDTO.getWeight() < 30) {
                throw new MinValueException("Weight can not be less than 30 ");
            } else if (productDTO.getQuantity() < 1) {
                throw new MinValueException("Quantity can not be less than 1 ");
            }
            productService.create(productDTO);
            result.setData(productDTO);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/product/updateProduct", method = RequestMethod.POST)
    public AjaxDTO updateProduct(@RequestBody ProductDTO productDTO) {
        AjaxDTO result = new AjaxDTO();
        if (productDTO != null) {
            if (productDTO.getName().length() < 1 || productDTO.getDescription().length() < 1 ||
                    productDTO.getCalories() == null || productDTO.getQuantity() == null || productDTO.getWeight() == null) {
                throw new MinLengthFieldException("Field can not be empty");
            } else if (productDTO.getDough().getId() == null) {
                throw new MinLengthFieldException("Field Dough can not be empty");
            } else if (productDTO.getPrice() < 10) {
                throw new MinValueException(" Price can not be less than 10P");
            } else if (productDTO.getCalories() < 50) {
                throw new MinValueException(" Calories can not be less than 50 ");
            } else if (productDTO.getWeight() < 30) {
                throw new MinValueException("Weight can not be less than 30 ");
            } else if (productDTO.getQuantity() < 1) {
                throw new MinValueException("Quantity can not be less than 1 "); }
            productService.update(productDTO);
            result.setData(productDTO);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/product/deleteProduct", method = RequestMethod.DELETE)
    public AjaxDTO deleteProduct(@Valid @RequestBody Integer id) {
        ProductDTO productDTO = productService.getById(id);
        AjaxDTO result = new AjaxDTO();
        if (productDTO != null) {
            productService.delete(productDTO);
        }
        return result;
    }


    @RequestMapping(value = "/product/getProductByCategory", method = RequestMethod.GET)
    @ResponseBody
    public AjaxDTO getProductByCategory(@RequestParam(value = "id") @Valid @NotEmpty(message = "Product id cannot be empty") Integer categoryId) {
        AjaxDTO result = new AjaxDTO();
        result.setData(productService.getAllByCategory(categoryId));
        return result;
    }

}
