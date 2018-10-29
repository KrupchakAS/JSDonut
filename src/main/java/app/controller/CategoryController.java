package app.controller;

import app.dto.AjaxDTO;
import app.dto.CategoryDTO;
import app.exception.MinLengthFieldException;
import app.service.api.CategoryService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Category controller
 */
@Controller
@RequestMapping(value = "/admin")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Open admin panel category view
     * @param modelMap - model for view
     * @return - string view name
     */
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String openPage(ModelMap modelMap) {
        modelMap.addAttribute("categoryActive", "active");
        modelMap.addAttribute("categoryList", categoryService.getAll());
        return "admin/category";
    }

    /**
     * Method for ajax CRUD operations
     * @param categoryId = param to get category by Id
     * @return - object with special response params for the query
     */
    @RequestMapping(value = "/category/getCategoryById", method = RequestMethod.GET)
    @ResponseBody
    public AjaxDTO getCategory(@RequestParam(value = "id") @Valid @NotEmpty(message = "Category id cannot be empty") Integer categoryId) {
        AjaxDTO result = new AjaxDTO();
        result.setData(categoryService.getById(categoryId));
        return result;
    }

    /**
     * Method for ajax CRUD operations
     * @param categoryDTO - object for the CrUD operations
     * @return - object with special response params for the query
     */
    @ResponseBody
    @RequestMapping(value = "/category/createCategory", method = RequestMethod.POST)
    public AjaxDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
        AjaxDTO result = new AjaxDTO();
        if (categoryDTO != null) {
            if(categoryDTO.getName().length() < 1 || categoryDTO.getName() == null ){
                throw new MinLengthFieldException("Field can not be empty");
            }
            categoryService.create(categoryDTO);
            result.setData(categoryDTO);
        }
        return result;
    }
    /**
     * Method for ajax CRUD operations
     * @param categoryDTO - object for the CrUD operations
     * @return - object with special response params for the query
     */
    @ResponseBody
    @RequestMapping(value = "/category/updateCategory", method = RequestMethod.POST)
    public AjaxDTO updateCategory(@RequestBody CategoryDTO categoryDTO) {
        AjaxDTO result = new AjaxDTO();
        if (categoryDTO != null) {
            if(categoryDTO.getName().length() < 1 || categoryDTO.getName() == null){
                throw new MinLengthFieldException("Field can not be empty");
            }
            categoryService.update(categoryDTO);
        }
        result.setData(categoryDTO);
        return result;
    }
    /**
     * Method for ajax CRUD operations
     * @param id - param for the CrUD operations
     * @return - object with special response params for the query
     */
    @ResponseBody
    @RequestMapping(value = "/category/deleteCategory", method = RequestMethod.DELETE)
    public AjaxDTO deleteCategory(@Valid @NotEmpty(message = "Category id cannot be empty") @RequestBody Integer id) {
        CategoryDTO categoryDTO = categoryService.getById(id);
        AjaxDTO result = new AjaxDTO();
        if (categoryDTO != null) {
            categoryService.checkCategoryByProduct(id);
            categoryService.delete(categoryDTO);
        }
        return result;
    }
}
