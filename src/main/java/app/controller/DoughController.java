package app.controller;

import app.dto.AjaxDTO;
import app.dto.DoughDTO;
import app.dto.FillingDTO;
import app.dto.SprinkleDTO;
import app.service.api.DoughService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/jsDonut/admin")
public class DoughController {

    @Autowired
    private DoughService doughService;

    @RequestMapping(value = "/dough", method = RequestMethod.GET)
    public String openPage(ModelMap modelMap) {
        modelMap.addAttribute("doughActive", "active");
        modelMap.addAttribute("doughList", doughService.getAll());
        return "adminPages/dough";
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
            doughService.create(doughDTO);
            result.setData(doughDTO);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/dough/updateDough", method = RequestMethod.POST)
    public AjaxDTO updateDough(@RequestBody DoughDTO doughDTO){
        AjaxDTO result = new AjaxDTO();
        if(doughDTO != null){
            doughService.update(doughDTO);
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

    @RequestMapping(value = "/dough/getLastDough", method = RequestMethod.GET)
    @ResponseBody
    public AjaxDTO getLastDough() {
        AjaxDTO result = new AjaxDTO();
        result.setData(doughService.getLastDough());
        return result;
    }

}
