package app.controller;

import app.dto.ProductDTO;
import app.service.api.OrderService;
import app.service.api.ProductService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DonutRestController {

    private static final Logger logger = Logger.getLogger(DonutRestController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/getTop10Products")
    public List<ProductDTO> getTop10Products(){
        return orderService.getTop10Products();
    }
}
