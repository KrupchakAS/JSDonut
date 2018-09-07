package app.controller;

import app.dto.AjaxDTO;
import app.dto.DoughDTO;
import app.service.api.DoughService;
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
public class DoughController {

    @Autowired
    private DoughService doughService;

    @RequestMapping(value = "/dough", method = RequestMethod.GET)
    public String openPage(ModelMap modelMap) {
        modelMap.addAttribute("doughActive", "active");

        List<DoughDTO> doughDTOList = doughService.getAll();
        modelMap.addAttribute("doughList", doughDTOList);
        return "dough";
    }

    @RequestMapping(value = "/dough/getDough", method = RequestMethod.GET)
    @ResponseBody
    public AjaxDTO getDough(@RequestParam(value = "id") @Valid @NotEmpty(message = "Dough id cannot be empty") Integer doughId) {
        AjaxDTO result = new AjaxDTO();
        result.setData(doughService.getById(doughId));
        return result;
    }

    public void setDoughService(DoughService doughService) {
        this.doughService = doughService;
    }


}
