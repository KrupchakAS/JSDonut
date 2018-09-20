package app.controller;

import app.dto.UserDTO;
import app.service.api.UserService;
import app.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static app.controller.MainController.ORDER;
import static app.controller.MainController.productDTOList;


@Controller
@RequestMapping(value = "/jsDonut")
public class RegAndLogController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String main(HttpSession session) {
        session.setAttribute("order", ORDER);
        session.setAttribute("countProductInOrder", productDTOList.size());
        return "main/welcome";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String createModel(ModelMap modelMap, HttpSession session) {
        session.setAttribute("order", ORDER);
        session.setAttribute("countProductInOrder", productDTOList.size());
        modelMap.addAttribute("userForm", new UserDTO());
        return "main/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@Valid @ModelAttribute("userForm") UserDTO userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "main/registration";
        }
        userService.create(userForm);
        return "redirect:/jsDonut/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout, HttpSession session) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }
        return "main/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/jsDonut/welcome";
    }


}
