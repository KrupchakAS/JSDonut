package app.controller;

import app.dto.AjaxDTO;
import app.dto.DoughDTO;
import app.dto.ProductDTO;
import app.dto.SprinkleDTO;
import app.service.api.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/jsDonut/admin")
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

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String openPage(ModelMap modelMap) {
        modelMap.addAttribute("productActive", "active");
        modelMap.addAttribute("productList", productService.getAll());
        modelMap.addAttribute("fillingList", fillingService.getAll());
        modelMap.addAttribute("sprinkleList", sprinkleService.getAll());
        modelMap.addAttribute("doughList", doughService.getAll());
        modelMap.addAttribute("categoryList", categoryService.getAll());
        return "adminPages/product";
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
    public AjaxDTO createProduct(@RequestBody ProductDTO productDTO){
        AjaxDTO result = new AjaxDTO();
        if(productDTO != null){
            productService.create(productDTO);
            result.setData(productDTO);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/product/updateProduct", method = RequestMethod.POST)
    public AjaxDTO updateProduct(@RequestBody ProductDTO productDTO){
        AjaxDTO result = new AjaxDTO();
        if(productDTO != null){
            productService.update(productDTO);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/product/deleteProduct", method = RequestMethod.DELETE)
    public AjaxDTO deleteProduct(@RequestBody Integer id){
        ProductDTO productDTO = productService.getById(id);
        AjaxDTO result = new AjaxDTO();
        if(productDTO != null){
            productService.delete(productDTO);
        }
        return result;
    }

    @RequestMapping(value = "/product/getLastProduct", method = RequestMethod.GET)
    @ResponseBody
    public AjaxDTO getLastProduct() {
        AjaxDTO result = new AjaxDTO();
        result.setData(productService.getLastProduct());
        return result;
    }



}
