package app.controller;

import app.dto.AjaxDTO;
import app.dto.CategoryDTO;
import app.service.api.CategoryService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String openPage(ModelMap modelMap) {
        modelMap.addAttribute("categoryActive", "active");
        modelMap.addAttribute("categoryList", categoryService.getAll());
        return "admin/category";
    }

    @RequestMapping(value = "/category/getCategoryById", method = RequestMethod.GET)
    @ResponseBody
    public AjaxDTO getCategory(@RequestParam(value = "id") @Valid @NotEmpty(message = "Category id cannot be empty") Integer categoryId) {
        AjaxDTO result = new AjaxDTO();
        result.setData(categoryService.getById(categoryId));
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/category/createCategory", method = RequestMethod.POST)
    public AjaxDTO createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        AjaxDTO result = new AjaxDTO();
        if (categoryDTO != null) {
            categoryService.create(categoryDTO);
            result.setData(categoryDTO);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/category/updateCategory", method = RequestMethod.POST)
    public AjaxDTO updateCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        AjaxDTO result = new AjaxDTO();
        if (categoryDTO != null) {
            categoryService.update(categoryDTO);
        }
        result.setData(categoryDTO);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/category/deleteCategory", method = RequestMethod.DELETE)
    public AjaxDTO deleteCategory(@Valid @NotEmpty(message = "Category id cannot be empty") @RequestBody Integer id) {
        CategoryDTO categoryDTO = categoryService.getById(id);
        AjaxDTO result = new AjaxDTO();
        if (categoryDTO != null) {
            categoryService.delete(categoryDTO);
        }
        return result;
    }
}
