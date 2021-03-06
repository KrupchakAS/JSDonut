package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Admin controller
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    /**
     * Open adminPanel page
     *
     * @return
     */
    @RequestMapping(value = "/adminPanel",method = RequestMethod.GET)
    public String adminPanel(){
        return "admin/adminPanel";
    }

}
