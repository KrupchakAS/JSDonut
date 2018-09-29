package app.controller;

import app.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/jsDonut/admin")
public class StatisticController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    public String openPage(ModelMap modelMap) {
        modelMap.addAttribute("statisticActive", "active");
        modelMap.addAttribute("ProceedsForLastMonth", orderService.getProceedsForLastMonth());
        modelMap.addAttribute("ProceedsForLastWeek", orderService.getProceedsForLastWeek());
        modelMap.addAttribute("top10users",orderService.getTop10Users());

        return "admin/statistic";
    }
}
