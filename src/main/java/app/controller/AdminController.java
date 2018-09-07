package app.controller;

import app.dto.*;
import app.service.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "jsDonut/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private DoughService doughService;

    @Autowired
    private SprinkleService sprinkleService;

    @Autowired
    private FillingService fillingService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/adminPanel",method = RequestMethod.GET)
    public String adminPanel(){
        return "adminPanel";
    }

}
