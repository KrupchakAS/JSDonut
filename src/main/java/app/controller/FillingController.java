package app.controller;

import app.dto.AjaxDTO;
import app.dto.FillingDTO;
import app.service.api.FillingService;
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

    @RequestMapping(value = "/filling/getFilling", method = RequestMethod.GET)
    @ResponseBody
    public AjaxDTO getFilling(@RequestParam(value = "id") @Valid @NotEmpty(message = "Filling id cannot be empty") Integer fillingId) {
        AjaxDTO result = new AjaxDTO();
        result.setData(fillingService.getById(fillingId));
        return result;
    }

    public void setFillingService(FillingService fillingService) {
        this.fillingService = fillingService;
    }


}
