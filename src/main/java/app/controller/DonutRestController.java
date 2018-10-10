package app.controller;

import app.service.api.ProductService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DonutRestController {

    private static final Logger logger = Logger.getLogger(DonutRestController.class);

    @Autowired
    private ProductService productService;
}
