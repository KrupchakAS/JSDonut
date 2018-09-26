package app.controller;

import app.dto.AjaxDTO;
import app.dto.SprinkleDTO;
import app.service.api.SprinkleService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/jsDonut/admin")
public class SprinkleController {

    @Autowired
    SprinkleService sprinkleService;

    @RequestMapping(value = "/sprinkle", method = RequestMethod.GET)
    public String openPage(ModelMap modelMap) {
        modelMap.addAttribute("sprinkleActive", "active");;
        modelMap.addAttribute("sprinkleList", sprinkleService.getAll());
        return "admin/sprinkle";
    }

    @RequestMapping(value = "/sprinkle/getSprinkleById", method = RequestMethod.GET)
    @ResponseBody
    public AjaxDTO getSprinkle(@RequestParam(value = "id") @Valid @NotEmpty(message = "Sprinkle id cannot be empty") Integer sprinkleId) {
        AjaxDTO result = new AjaxDTO();
        result.setData(sprinkleService.getById(sprinkleId));
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/sprinkle/createSprinkle", method = RequestMethod.POST)
    public AjaxDTO createSprinkle(@Valid @RequestBody SprinkleDTO sprinkleDTO){
        AjaxDTO result = new AjaxDTO();
        if(sprinkleDTO != null){
            sprinkleService.create(sprinkleDTO);
            result.setData(sprinkleDTO);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/sprinkle/updateSprinkle", method = RequestMethod.POST)
    public AjaxDTO updateSprinkle(@Valid @RequestBody SprinkleDTO sprinkleDTO){

        AjaxDTO result = new AjaxDTO();
        if(sprinkleDTO != null){
            sprinkleService.update(sprinkleDTO);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/sprinkle/deleteSprinkle", method = RequestMethod.DELETE)
    public AjaxDTO deleteSprinkle(@RequestBody Integer id){
        SprinkleDTO sprinkleDTO1 = sprinkleService.getById(id);
        AjaxDTO result = new AjaxDTO();
        if(sprinkleDTO1 != null){
            sprinkleService.delete(sprinkleDTO1);
        }
        return result;
    }

    @RequestMapping(value = "/sprinkle/getLastSprinkle", method = RequestMethod.GET)
    @ResponseBody
    public AjaxDTO getLastSprinkle() {
        AjaxDTO result = new AjaxDTO();
        result.setData(sprinkleService.getLastSprinkle());
        return result;
    }
}
