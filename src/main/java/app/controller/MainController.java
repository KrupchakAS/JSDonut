package app.controller;


import app.dto.AjaxDTO;
import app.dto.OrderDTO;
import app.dto.UserDTO;
import app.service.api.CategoryService;
import app.service.api.ProductService;
import app.service.api.UserService;
import app.validator.UserValidator;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SessionAttributes(value = "order")
@Controller
@RequestMapping(value = "/jsDonut")
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @ModelAttribute("order")
    public OrderDTO createOrder(){
        return new OrderDTO();
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String main(@ModelAttribute ("order") OrderDTO orderDTO, ModelMap modelMap){
        modelMap.addAttribute("order", new OrderDTO());
        modelMap.addAttribute("categoryList",categoryService.getAll());
        return "main/welcome";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String createModel(ModelMap modelMap, HttpSession session){
        modelMap.addAttribute("userForm",new UserDTO());
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
    public String login(Model model, String error, String logout) {
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

    @RequestMapping(value = "/product/getProductsByParameters", method = RequestMethod.GET)
    @ResponseBody
    public AjaxDTO getDough(@RequestParam(value = "categoryName",required = false) String categoryName,
                            @RequestParam(value = "productName",required = false) String productName,
                            @RequestParam(value = "minPrice",required = false) Integer minPrice,
                            @RequestParam(value = "maxPrice",required = false) Integer maxPrice) {
        AjaxDTO result = new AjaxDTO();
        result.setData(productService.getProductsByParameters(categoryName,productName,minPrice,maxPrice));
        return result;
    }



    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String products(){
        return "main/products";
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public String filter(){
        return "main/filter";
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String order(){
        return "main/order";
    }
}
