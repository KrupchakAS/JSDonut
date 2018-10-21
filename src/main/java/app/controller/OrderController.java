package app.controller;

import app.dto.AjaxDTO;
import app.dto.OrderDTO;
import app.service.api.OrderService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin")

public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String openPage(ModelMap modelMap) {
        modelMap.addAttribute("orderActive", "active");
        modelMap.addAttribute("orderList", orderService.getAll());
        return "admin/order";
    }

    @RequestMapping(value = "/order/getOrderById", method = RequestMethod.GET)
    @ResponseBody
    public AjaxDTO getOrder(@RequestParam(value = "id") @Valid @NotEmpty(message = "Order id cannot be empty") Integer orderId) {
        AjaxDTO result = new AjaxDTO();
        result.setData(orderService.getById(orderId));
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/order/createOrder", method = RequestMethod.POST)
    public AjaxDTO createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        AjaxDTO result = new AjaxDTO();
        if (orderDTO != null) {
            //orderService.create(orderDTO);
            result.setData(orderDTO);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/order/updateOrder", method = RequestMethod.POST)
    public AjaxDTO updateOrder(@Valid @RequestBody OrderDTO orderDTO) {
        AjaxDTO result = new AjaxDTO();
        if (orderDTO != null) {
            orderService.updateStatuses(orderDTO);
            result.setData(orderDTO);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/order/deleteOrder", method = RequestMethod.DELETE)
    public AjaxDTO deleteOrder(@RequestBody Integer id) {
        OrderDTO orderDTO = orderService.getById(id);
        AjaxDTO result = new AjaxDTO();
        if (orderDTO != null) {
            orderService.delete(orderDTO);
        }
        return result;
    }
}
