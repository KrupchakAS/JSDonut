package app.controller;

import app.dto.AjaxDTO;
import app.dto.FillingDTO;
import app.dto.SprinkleDTO;
import app.service.api.FillingService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/jsDonut/admin")
public class FillingController {

    @Autowired
    private FillingService fillingService;

    @RequestMapping(value = "/filling", method = RequestMethod.GET)
    public String openPage(ModelMap modelMap) {
        modelMap.addAttribute("fillingActive", "active");
        List<FillingDTO> fillingDTOList = fillingService.getAll();
        modelMap.addAttribute("fillingList", fillingDTOList);
        return "filling";
    }

    @RequestMapping(value = "/filling/getFillingById", method = RequestMethod.GET)
    @ResponseBody
    public AjaxDTO getFilling(@RequestParam(value = "id") @Valid @NotEmpty(message = "Filling id cannot be empty") Integer fillingId) {
        AjaxDTO result = new AjaxDTO();
        result.setData(fillingService.getById(fillingId));
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/filling/createFilling", method = RequestMethod.POST)
    public AjaxDTO createFilling(@RequestBody FillingDTO fillingDTO){
        AjaxDTO result = new AjaxDTO();
        if(fillingDTO != null){
            fillingService.create(fillingDTO);
        }
        return result;
    }
    @ResponseBody
    @RequestMapping(value = "/filling/updateFilling", method = RequestMethod.POST)
    public AjaxDTO updateFilling(@RequestBody FillingDTO fillingDTO){
        AjaxDTO result = new AjaxDTO();
        if(fillingDTO != null){
            fillingService.update(fillingDTO);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/filling/deleteFilling", method = RequestMethod.POST)
    public AjaxDTO deleteFilling(@RequestParam(value = "id") @Valid @NotEmpty(message = "Filling id cannot be empty") Integer fillingId){
        FillingDTO fillingDTO = fillingService.getById(fillingId);
        AjaxDTO result = new AjaxDTO();
        if(fillingDTO != null){
            fillingService.delete(fillingDTO);
        }
        return result;
    }

}
