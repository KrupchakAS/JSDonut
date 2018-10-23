package app.controller;

import app.dto.AjaxDTO;
import app.dto.DoughDTO;
import app.exception.MinLengthFieldException;
import app.exception.MinValueException;
import app.service.api.DoughService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin")
public class DoughController {

    @Autowired
    private DoughService doughService;

    @RequestMapping(value = "/dough", method = RequestMethod.GET)
    public String openPage(ModelMap modelMap) {
        modelMap.addAttribute("doughActive", "active");
        modelMap.addAttribute("doughList", doughService.getAll());
        return "admin/dough";
    }

    @RequestMapping(value = "/dough/getDoughById", method = RequestMethod.GET)
    @ResponseBody
    public AjaxDTO getDough(@RequestParam(value = "id") @Valid @NotEmpty(message = "Dough id cannot be empty") Integer doughId) {
        AjaxDTO result = new AjaxDTO();
        result.setData(doughService.getById(doughId));
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/dough/createDough", method = RequestMethod.POST)
    public AjaxDTO createDough(@RequestBody DoughDTO doughDTO){
        AjaxDTO result = new AjaxDTO();
        if(doughDTO != null){
            if(doughDTO.getName().length() < 1 || doughDTO.getCalories() == null || doughDTO.getPrice() == null){
                throw new MinLengthFieldException("Field can not be empty");
            } else if(doughDTO.getPrice() < 10){
                throw new MinValueException("Price can not be less than 10P ");
            } else if(doughDTO.getCalories() < 50){
                throw new MinValueException("Calories can not be less than 50 "); }
            doughService.create(doughDTO);
            result.setData(doughDTO);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/dough/updateDough", method = RequestMethod.POST)
    public AjaxDTO updateDough( @RequestBody DoughDTO doughDTO){
        AjaxDTO result = new AjaxDTO();
        if(doughDTO != null){
            if(doughDTO.getName().length() <= 1 || doughDTO.getCalories() == null || doughDTO.getPrice() == null){
                throw new MinLengthFieldException("Field can not be empty");
            } else if(doughDTO.getPrice() <= 10){
                throw new MinValueException("Price can not be less than 10P ");
            } else if(doughDTO.getCalories() < 50){
                throw new MinValueException("Calories can not be less than 50 "); }
            doughService.update(doughDTO);
            result.setData(doughDTO);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/dough/deleteDough", method = RequestMethod.DELETE)
    public AjaxDTO deleteDough(@RequestBody Integer id){
        DoughDTO doughDTO = doughService.getById(id);
        AjaxDTO result = new AjaxDTO();
        if(doughDTO != null){
            doughService.delete(doughDTO);
        }
        return result;
    }



}
