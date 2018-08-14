package app.controller;


import app.entity.User;
import app.service.UserService;
import app.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String createModel(ModelMap modelMap){
        modelMap.addAttribute("userForm",new User());
        return "/welcome";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.POST)
    public String registration(@Valid @ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/welcome";
        }
        userService.save(userForm);
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }
        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/welcome";
    }




    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        return "admin";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user() {
        return "user";
    }

    @RequestMapping(value = "/user/findUser", method = RequestMethod.GET)
    @ResponseBody
    public User user(@Valid @RequestParam(value = "uName") String login) {
        return userService.findUserByLogin(login);
    }

    @RequestMapping(value = "/userList2", method = RequestMethod.GET)
    public String userslist() {
        return "userList2";
    }

    @RequestMapping(value = "/userList2/getUserList", method = RequestMethod.GET)
    public @ResponseBody
    List<User> getUserList(@RequestParam("uName") String userName) {

        return userService.getUsersList(userName);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public String usersl() {
        return "deleteUser";
    }

    @RequestMapping(value = "/deleteUser/deleteUser={usId}", method = RequestMethod.GET)
    public String userDelete(@PathVariable("usId") Integer id) {
        userService.deleteUser(id);
        return "redirect:/deleteUser";
    }
}
