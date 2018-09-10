package app.controller;

import app.dto.AjaxDTO;
import app.dto.DoughDTO;
import app.dto.ProductDTO;
import app.dto.SprinkleDTO;
import app.service.api.DoughService;
import app.service.api.ProductService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/jsDonut/admin")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String openPage(ModelMap modelMap) {
        modelMap.addAttribute("productActive", "active");
        List<ProductDTO> productDTOList = productService.getAll();
        modelMap.addAttribute("productList", productDTOList);
        return "product";
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

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }


}
