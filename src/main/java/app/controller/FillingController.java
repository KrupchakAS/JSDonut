package app.controller;

import app.dto.AjaxDTO;
import app.dto.FillingDTO;
import app.exception.MinLengthFieldException;
import app.exception.MinValueException;
import app.service.api.FillingService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Filling controller
 */
@Controller
@RequestMapping(value = "/admin")
public class FillingController {

    @Autowired
    private FillingService fillingService;

    /**
     * Open admin panel filling view
     * @param modelMap - model for view
     * @return - string view name
     */
    @RequestMapping(value = "/filling", method = RequestMethod.GET)
    public String openPage(ModelMap modelMap) {
        modelMap.addAttribute("fillingActive", "active");
        modelMap.addAttribute("fillingList", fillingService.getAll());
        return "admin/filling";
    }

    /**
     * Method for ajax CRUD operations
     * @param fillingId - param for the CrUD operations
     * @return - object with special response params for the query
     */
    @RequestMapping(value = "/filling/getFillingById", method = RequestMethod.GET)
    @ResponseBody
    public AjaxDTO getFilling(@RequestParam(value = "id") @Valid @NotEmpty(message = "Filling id cannot be empty") Integer fillingId) {
        AjaxDTO result = new AjaxDTO();
        result.setData(fillingService.getById(fillingId));
        return result;
    }

    /**
     * Method for ajax CRUD operations
     * @param fillingDTO - object for the CrUD operations
     * @return - object with special response params for the query
     */
    @ResponseBody
    @RequestMapping(value = "/filling/createFilling", method = RequestMethod.POST)
    public AjaxDTO createFilling(@RequestBody FillingDTO fillingDTO) throws InterruptedException {
        AjaxDTO result = new AjaxDTO();
        if(fillingDTO != null){
            if(fillingDTO.getName().length() < 1 || fillingDTO.getCalories() == null || fillingDTO.getPrice() == null){
                throw new MinLengthFieldException("Field can not be empty ");
            } else if(fillingDTO.getPrice() < 10){
                throw new MinValueException("Price can not be less than 10P ");
            }  else if(fillingDTO.getCalories() < 50){
                throw new MinValueException(" Calories can not be less than 50");
            }
            fillingService.create(fillingDTO);
            result.setData(fillingDTO);
        }
        return result;
    }

    /**
     * Method for ajax CRUD operations
     * @param fillingDTO - object for the CrUD operations
     * @return - object with special response params for the query
     */
    @ResponseBody
    @RequestMapping(value = "/filling/updateFilling", method = RequestMethod.POST)
    public AjaxDTO updateFilling(@RequestBody FillingDTO fillingDTO){
        AjaxDTO result = new AjaxDTO();
        if(fillingDTO != null){
            if(fillingDTO.getName().length() < 1 || fillingDTO.getCalories() == null || fillingDTO.getPrice() == null){
                throw new MinLengthFieldException("Field can not be empty ");
            } else if(fillingDTO.getPrice() < 10){
                throw new MinValueException("Price can not be less than 10P ");
            } else if(fillingDTO.getCalories() < 50){
                throw new MinValueException(" Calories can not be less than 50 ");
            }
            fillingService.update(fillingDTO);
            result.setData(fillingDTO);
        }
        return result;
    }

    /**
     * Method for ajax CRUD operations
     * @param id - param for the CrUD operations
     * @return - object with special response params for the query
     */
    @ResponseBody
    @RequestMapping(value = "/filling/deleteFilling", method = RequestMethod.DELETE)
    public AjaxDTO deleteFilling(@RequestBody Integer id){
        FillingDTO fillingDTO = fillingService.getById(id);
        AjaxDTO result = new AjaxDTO();
        if(fillingDTO != null){
            fillingService.checkFillingByProducts(id);
            fillingService.delete(fillingDTO);
        }
        return result;
    }

}
