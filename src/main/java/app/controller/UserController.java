package app.controller;

import app.dto.AjaxDTO;
import app.dto.OrderDTO;
import app.dto.UserDTO;
import app.exception.MinLengthFieldException;
import app.exception.UserNotFoundException;
import app.service.api.*;
import app.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static app.controller.MainController.productDTOList;

/**
 * User controller
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private FillingService fillingService;

    @Autowired
    private DoughService doughService;

    @Autowired
    private SprinkleService sprinkleService;

    /**
     * Open welcome page view
     * @param modelMap - model for view
     * @param session - param for keeping and use session attributes
     * @return - string view name
     */
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(ModelMap modelMap,HttpSession session) {
        sessionOrderInit(modelMap,session);
        return "main/welcome";
    }

    /**
     * Open registration page view
     * @param modelMap - model for view
     * @param session - param for keeping and use session attributes
     * @return - string view name
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String createModel(ModelMap modelMap, HttpSession session) {
        sessionOrderInit(modelMap,session);
        modelMap.addAttribute("userForm", new UserDTO());
        return "main/registration";
    }

    /**
     * Open registration page view
     * @param userForm - model for registration user
     * @param bindingResult - param for keeping errors
     * @return - string view name
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@Valid @ModelAttribute("userForm") UserDTO userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "main/registration";
        }
        userService.create(userForm);
        return "redirect:/welcome";
    }

    /**
     * Open login page view
     * @param modelMap - model for view information
     * @param session - param for keeping and use session attributes
     * @return - string view name
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap modelMap, String error, String logout, HttpSession session) {
        sessionOrderInit(modelMap,session);
        if (error != null) {
            modelMap.addAttribute("error", "Login or password is incorrect.");
        }
        if (logout != null) {
            modelMap.addAttribute("message", "Logged out successfully.");
        }
        return "main/login";
    }

    /**
     * Method to logout user
     * @param request - request context information
     * @param response - response context information
     * @param session - param for keeping and use session attributes
     * @return - string view name
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/welcome";
    }

    /**
     * Open account page view
     * @param modelMap - model for view
     * @param session - param for keeping and use session attributes
     * @return - string view name
     */
    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String account(ModelMap modelMap, HttpSession session) {
        sessionOrderInit(modelMap,session);
        UserDTO userDTO = userService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        modelMap.addAttribute("user", userDTO);
        List<OrderDTO> orderDTOList = orderService.getOrdersByUserId(userDTO.getId());
        if (orderDTOList != null) {
            List<OrderDTO> orderDTOListTotal = new ArrayList<>();
            for (int i = orderDTOList.size() - 1; i >= 0; i--) {
                orderDTOListTotal.add(orderDTOList.get(i));
            }
            modelMap.addAttribute("orderList", orderDTOListTotal);
        }
        return "main/account";
    }

    /**
     * Open filter page view
     * @param modelMap - model for view
     * @param session - param for keeping and use session attributes
     * @return - string view name
     */
    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public String filter(ModelMap modelMap, HttpSession session) {
        sessionOrderInit(modelMap,session);
        modelMap.addAttribute("allProducts", productService.getAll());
        modelMap.addAttribute("categoryList", categoryService.getAll());
        modelMap.addAttribute("fillingList", fillingService.getAll());
        modelMap.addAttribute("doughList", doughService.getAll());
        modelMap.addAttribute("sprinkleList", sprinkleService.getAll());
        return "main/filter";
    }

    /**
     * Method for ajax change user info operation
     * @param userDTO1 - object for the change user info operation
     * @return - object with special response params for the query
     */
    @RequestMapping(value = "/changeUserPassword", method = RequestMethod.POST)
    @ResponseBody
    public AjaxDTO changeUserPassword(@RequestBody UserDTO userDTO1) {
        AjaxDTO result = new AjaxDTO();
        UserDTO userDTO = userService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        if (userDTO == null) {
            throw new UserNotFoundException("User Not Found"); }
        if (userDTO1.getPassword().length() < 4 && userDTO1.getConfirmPassword().length() < 4) {
            throw new MinLengthFieldException("Field can not be less than 4 characters");
        } else if (!userDTO1.getPassword().equals(userDTO1.getConfirmPassword())) {
            throw new MinLengthFieldException("Password don't match."); }
        userDTO.setPassword(userDTO1.getPassword());
        userService.updatePassword(userDTO);
        return result;
    }

    /**
     * Method for ajax change user password operation
     * @param userDTO1 - object for the change user info operation
     * @return - object with special response params for the query
     */
    @RequestMapping(value = "/changeUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public AjaxDTO changeUserInfo(@RequestBody UserDTO userDTO1) {
        AjaxDTO result = new AjaxDTO();
        UserDTO userDTO = userService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        if (userDTO == null) {
            throw new UserNotFoundException("User Not Found"); }
        if(userDTO1.getFirstName() == null && userDTO1.getSurName() == null && userDTO1.getPhoneNumber() == null && userDTO1.getBirthDate() == null){
            throw new UserNotFoundException("No field(s) to change"); }
        if (userDTO1.getFirstName() != null && userDTO1.getFirstName().length() > 1) {
            userDTO.setFirstName(userDTO1.getFirstName()); }
        if (userDTO1.getSurName() != null && userDTO1.getSurName().length() > 1) {
            userDTO.setSurName(userDTO1.getSurName()); }
        if (userDTO1.getPhoneNumber() != null && userDTO1.getPhoneNumber().length() == 10) {
            userDTO.setPhoneNumber(userDTO1.getPhoneNumber()); }
        if (userDTO1.getBirthDate() != null) {
            userDTO.setBirthDate(userDTO1.getBirthDate()); }
        userService.updateInfo(userDTO);
        return result;
    }

    /**
     * Method for init session attributes
     * @param modelMap - model for view
     * @param session - param for keeping and use session attributes
     */
    public void sessionOrderInit(ModelMap modelMap, HttpSession session){
        if (session.getAttribute("order") == null) {
            session.setAttribute("order", new OrderDTO());
            OrderDTO orderDTO = (OrderDTO) session.getAttribute("order");
            Float totalPrice = 0.0f;
            for (int i = 0; i < productDTOList.size(); i++) {
                totalPrice += productDTOList.get(i).getPrice() * productDTOList.get(i).getQuantity(); }
            orderDTO.setTotalPrice(totalPrice);
            orderDTO.setProductList(productDTOList);
            session.setAttribute("order",orderDTO);
            modelMap.addAttribute("orderTotalPrice",orderDTO);
        }else {
            OrderDTO orderDTO = (OrderDTO) session.getAttribute("order");
            orderDTO.setProductList(productDTOList);
            Float totalPrice = 0.0f;
            for (int i = 0; i < productDTOList.size(); i++) {
                totalPrice += productDTOList.get(i).getPrice() * productDTOList.get(i).getQuantity(); }
            orderDTO.setTotalPrice(totalPrice);
            session.setAttribute("order",orderDTO);
            modelMap.addAttribute("orderTotalPrice",orderDTO);
        }
        session.setAttribute("countProductInOrder", productDTOList.size());
    }

    /**
     * Open 403 error page view
     */
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String page403() {
        return "403";
    }

    /**
     * Open 404 error page view
     */
    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String page404() {
        return "404";
    }

    /**
     * Open 405 error page view
     */
    @RequestMapping(value = "/405", method = RequestMethod.GET)
    public String page405() {
        return "405";
    }

    /**
     * Open 500 error page view
     */
    @RequestMapping(value = "/500", method = RequestMethod.GET)
    public String page500() {
        return "500";
    }

}
