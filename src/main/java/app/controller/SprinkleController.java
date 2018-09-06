package app.controller;

import app.dto.AjaxDTO;
import app.dto.SprinkleDTO;
import app.service.api.SprinkleService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/sprinkle/getSprinkle", method = RequestMethod.GET)
    @ResponseBody
    public AjaxDTO getSprinkle(@RequestParam(value = "id") @Valid @NotEmpty(message = "Sprinkle id cannot be empty") Integer sprinkleId) {
        AjaxDTO result = new AjaxDTO();
        result.setData(sprinkleService.getById(sprinkleId));
        return result;
    }

    public void setSprinkleService(SprinkleService sprinkleService) {
        this.sprinkleService = sprinkleService;
    }
}
