package app.controller;

import app.dto.AjaxDTO;
import app.dto.OrderDTO;
import app.dto.UserDTO;
import app.exception.UserNotFound;
import app.service.api.AddressService;
import app.service.api.OrderService;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static app.controller.MainController.productDTOList;


@Controller
@RequestMapping(value = "/jsDonut")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String main(HttpSession session) {
        if (session.getAttribute("order")==null) {
            session.setAttribute("order", new OrderDTO());
        }
        OrderDTO orderDTO = (OrderDTO) session.getAttribute("order");
        orderDTO.setProductList(productDTOList);
        Float totalPrice = 0.0f;
        for (int i = 0; i < productDTOList.size(); i++) {
            totalPrice += productDTOList.get(i).getPrice() * productDTOList.get(i).getQuantity();
        }
        orderDTO.setTotalPrice(totalPrice);
        session.setAttribute("countProductInOrder", productDTOList.size());
        return "main/welcome";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String createModel(ModelMap modelMap, HttpSession session) {
        if (session.getAttribute("order")==null) {
            session.setAttribute("order", new OrderDTO());
        }
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
    public String logout(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/jsDonut/welcome";
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String account(ModelMap modelMap, HttpSession session) {
        UserDTO userDTO = userService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        modelMap.addAttribute("user", userDTO);
        List<OrderDTO> orderDTOList = orderService.getOrdersByUserId(userDTO.getId());
        if(orderDTOList != null) {
            List<OrderDTO> orderDTOListTotal = new ArrayList<>();
            for (int i = orderDTOList.size() - 1; i >= 0; i--) {
                orderDTOListTotal.add(orderDTOList.get(i));
            }
            modelMap.addAttribute("orderList", orderDTOListTotal);
        }
        session.setAttribute("countProductInOrder", productDTOList.size());
        if (session.getAttribute("order") == null) {
            session.setAttribute("order", new OrderDTO());
        }
        return "main/account";
    }

    @RequestMapping(value = "/changeUserPassword", method = RequestMethod.POST)
    @ResponseBody
    public AjaxDTO changeUserPassword(@RequestBody UserDTO userDTO1, HttpSession session) {
        AjaxDTO result = new AjaxDTO();
        UserDTO userDTO = userService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        if (userDTO == null) {
            throw new UserNotFound("User Not Found");
        }
        userDTO.setPassword(userDTO1.getPassword());
        userService.updatePassword(userDTO);
        return result;
    }

    @RequestMapping(value = "/changeUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public AjaxDTO changeUserInfo(@RequestBody UserDTO userDTO1, HttpSession session) {
        AjaxDTO result = new AjaxDTO();
        UserDTO userDTO = userService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        if (userDTO == null) {
            throw new UserNotFound("User Not Found");
        }
        userDTO.setFirstName(userDTO1.getFirstName());
        userDTO.setSurName(userDTO1.getSurName());
        userDTO.setPhoneNumber(userDTO1.getPhoneNumber());
        userDTO.setBirthDate(userDTO1.getBirthDate());
        userService.updateInfo(userDTO);
        return result;
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String page403(){
        return "403";
    }
}
