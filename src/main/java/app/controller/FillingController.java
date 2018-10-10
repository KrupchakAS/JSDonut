package app.controller;

import app.dto.AjaxDTO;
import app.dto.FillingDTO;
import app.exception.MinPriceException;
import app.service.api.FillingService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class FillingController {

    @Autowired
    private FillingService fillingService;

    @RequestMapping(value = "/filling", method = RequestMethod.GET)
    public String openPage(ModelMap modelMap) {
        modelMap.addAttribute("fillingActive", "active");
        modelMap.addAttribute("fillingList", fillingService.getAll());
        return "admin/filling";
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
    public AjaxDTO createFilling(@Valid @RequestBody FillingDTO fillingDTO) throws InterruptedException {
        AjaxDTO result = new AjaxDTO();
        if(fillingDTO != null){
            if(fillingDTO.getPrice() <= 10){
                throw new MinPriceException(" Price can not be less than 10P ");
            }
            fillingService.create(fillingDTO);
            result.setData(fillingDTO);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/filling/updateFilling", method = RequestMethod.POST)
    public AjaxDTO updateFilling(@Valid @RequestBody FillingDTO fillingDTO){
        AjaxDTO result = new AjaxDTO();
        if(fillingDTO != null){
            if(fillingDTO.getPrice() <= 10){
                throw new MinPriceException(" Price can not be less than 10P ");
            }
            fillingService.update(fillingDTO);
            result.setData(fillingDTO);
        }
        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/filling/deleteFilling", method = RequestMethod.DELETE)
    public AjaxDTO deleteFilling(@RequestBody Integer id){
        FillingDTO fillingDTO = fillingService.getById(id);
        AjaxDTO result = new AjaxDTO();
        if(fillingDTO != null){
            fillingService.delete(fillingDTO);
        }
        return result;
    }

}
