package app.controller;

import app.dto.AjaxDTO;
import app.dto.SprinkleDTO;
import app.exception.MinLengthFieldException;
import app.exception.MinValueException;
import app.service.api.SprinkleService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Sprinkle controller
 */
@Controller
@RequestMapping(value = "/admin")
public class SprinkleController {

    @Autowired
    SprinkleService sprinkleService;

    /**
     * Open admin panel sprinkle view
     * @param modelMap - model for view
     * @return - string view name
     */
    @RequestMapping(value = "/sprinkle", method = RequestMethod.GET)
    public String openPage(ModelMap modelMap) {
        modelMap.addAttribute("sprinkleActive", "active");;
        modelMap.addAttribute("sprinkleList", sprinkleService.getAll());
        return "admin/sprinkle";
    }

    /**
     * Method for ajax CRUD operations
     * @param sprinkleId - param for the CrUD operations
     * @return - object with special response params for the query
     */
    @RequestMapping(value = "/sprinkle/getSprinkleById", method = RequestMethod.GET)
    @ResponseBody
    public AjaxDTO getSprinkle(@RequestParam(value = "id") @Valid @NotEmpty(message = "Sprinkle id cannot be empty") Integer sprinkleId) {
        AjaxDTO result = new AjaxDTO();
        result.setData(sprinkleService.getById(sprinkleId));
        return result;
    }

    /**
     * Method for ajax CRUD operations
     * @param sprinkleDTO - object for the CrUD operations
     * @return - object with special response params for the query
     */
    @ResponseBody
    @RequestMapping(value = "/sprinkle/createSprinkle", method = RequestMethod.POST)
    public AjaxDTO createSprinkle(@RequestBody SprinkleDTO sprinkleDTO){
        AjaxDTO result = new AjaxDTO();
        if(sprinkleDTO != null){
            if(sprinkleDTO.getName().length() < 1 || sprinkleDTO.getCalories() == null || sprinkleDTO.getPrice() == null){
                throw new MinLengthFieldException("Field can not be empty");
            } else if(sprinkleDTO.getPrice() < 10){
                throw new MinValueException("Price can not be less than 10P");
            } else if(sprinkleDTO.getCalories() < 50){
                throw new MinValueException(" Calories can not be less than 50 ");
            }
            sprinkleService.create(sprinkleDTO);
            result.setData(sprinkleDTO);
        }
        return result;
    }

    /**
     * Method for ajax CRUD operations
     * @param sprinkleDTO - object for the CrUD operations
     * @return - object with special response params for the query
     */
    @ResponseBody
    @RequestMapping(value = "/sprinkle/updateSprinkle", method = RequestMethod.POST)
    public AjaxDTO updateSprinkle(@RequestBody SprinkleDTO sprinkleDTO){
        AjaxDTO result = new AjaxDTO();
        if(sprinkleDTO != null){
            if(sprinkleDTO.getName().length() < 1 || sprinkleDTO.getCalories() == null || sprinkleDTO.getPrice() == null){
                throw new MinLengthFieldException("Field can not be empty");
            } else if(sprinkleDTO.getPrice() < 10){
                throw new MinValueException("Price can not be less than 10P");
            } else if(sprinkleDTO.getCalories() < 50){
                throw new MinValueException("Calories can not be less than 50 ");
            }
            sprinkleService.update(sprinkleDTO);
            result.setData(sprinkleDTO);
        }
        return result;
    }

    /**
     * Method for ajax CRUD operations
     * @param id - object for the CrUD operations
     * @return - object with special response params for the query
     */
    @ResponseBody
    @RequestMapping(value = "/sprinkle/deleteSprinkle", method = RequestMethod.DELETE)
    public AjaxDTO deleteSprinkle(@RequestBody Integer id){
        SprinkleDTO sprinkleDTO1 = sprinkleService.getById(id);
        AjaxDTO result = new AjaxDTO();
        if(sprinkleDTO1 != null){
            sprinkleService.checkSprinkleByProducts(id);
            sprinkleService.delete(sprinkleDTO1);
        }
        return result;
    }

}
