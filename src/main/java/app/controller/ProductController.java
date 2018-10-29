package app.controller;

import app.dto.AjaxDTO;
import app.dto.ProductDTO;
import app.service.api.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Product controller
 */
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

    /**
     * Open admin panel product view
     * @param modelMap - model for view
     * @return - string view name
     */
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

    /**
     * Method for ajax CRUD operations
     * @param productId - param for the CrUD operations
     * @return - object with special response params for the query
     */
    @RequestMapping(value = "/product/getProductById", method = RequestMethod.GET)
    @ResponseBody
    public AjaxDTO getDough(@RequestParam(value = "id") @Valid @NotEmpty(message = "Product id cannot be empty") Integer productId) {
        AjaxDTO result = new AjaxDTO();
        result.setData(productService.getById(productId));
        return result;
    }

    /**
     * Method for ajax CRUD operations
     * @param productDTO - object for the CrUD operations
     * @return - object with special response params for the query
     */
    @ResponseBody
    @RequestMapping(value = "/product/createProduct", method = RequestMethod.POST)
    public AjaxDTO createProduct(@RequestBody ProductDTO productDTO) {
        AjaxDTO result = new AjaxDTO();
        if (productDTO != null) {
            productService.checkProductFields(productDTO);
            productService.create(productDTO);
            result.setData(productDTO);
        }
        return result;
    }

    /**
     * Method for ajax CRUD operations
     * @param productDTO - object for the CrUD operations
     * @return - object with special response params for the query
     */
    @ResponseBody
    @RequestMapping(value = "/product/updateProduct", method = RequestMethod.POST)
    public AjaxDTO updateProduct(@RequestBody ProductDTO productDTO) {
        AjaxDTO result = new AjaxDTO();
        if (productDTO != null) {
            productService.checkProductFields(productDTO);
            if (productDTO.getFilling().getId() == 0) {
                productDTO.setFilling(null);
            }
            productService.update(productDTO);
            result.setData(productDTO);
        }
        return result;
    }

    /**
     * Method for ajax CRUD operations
     * @param id - param for the CrUD operations
     * @return - object with special response params for the query
     */
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

    /**
     * Method for ajax CRUD operations
     * @param categoryId - param for the CrUD operations
     * @return -  list objects with special response params for the query
     */
    @RequestMapping(value = "/product/getProductByCategory", method = RequestMethod.GET)
    @ResponseBody
    public AjaxDTO getProductByCategory(@RequestParam(value = "id") @Valid @NotEmpty(message = "Product id cannot be empty") Integer categoryId) {
        AjaxDTO result = new AjaxDTO();
        result.setData(productService.getAllByCategory(categoryId));
        return result;
    }

}
