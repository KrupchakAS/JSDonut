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
        modelMap.addAttribute("sprinkleActive", "active");

        List<SprinkleDTO> sprinkleDTOList = sprinkleService.getAll();
        modelMap.addAttribute("sprinkleList", sprinkleDTOList);
        return "sprinkle";
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
    public AjaxDTO createSprinkle(@RequestBody SprinkleDTO sprinkleDTO){
        AjaxDTO result = new AjaxDTO();
        if(sprinkleDTO != null){
            sprinkleService.create(sprinkleDTO);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/sprinkle/updateSprinkle", method = RequestMethod.POST)
    public AjaxDTO updateSprinkle(@RequestBody SprinkleDTO sprinkleDTO){

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

    public void setSprinkleService(SprinkleService sprinkleService) {
        this.sprinkleService = sprinkleService;
    }
}
