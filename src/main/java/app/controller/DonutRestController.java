package app.controller;

import app.dto.ProductDTO;
import app.dto.ProductRestDTO;
import app.service.api.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Rest controller for web service
 */
@RestController
@RequestMapping(value = "/rest")
public class DonutRestController {

    private static final Logger logger = Logger.getLogger(DonutRestController.class);

    @Autowired
    private OrderService orderService;

    /**
     * Method for ajax CRUD operations
     * @return -  list objects with special response params for the query
     */
    @GetMapping(value = "/getTop10Products")
    public List<ProductRestDTO> getTop10Products(){
        List<ProductDTO> list = orderService.getTop10Products();
        List<ProductRestDTO> listTotal = new ArrayList<>();
        for(int i = 0; i < list.size();i++){
            ProductRestDTO productDTO = new ProductRestDTO();
            productDTO.setId(list.get(i).getId());
            productDTO.setName(list.get(i).getName());
            productDTO.setCalories(list.get(i).getCalories());
            productDTO.setDescription(list.get(i).getDescription());
            productDTO.setPrice(list.get(i).getPrice());
            productDTO.setWorkPrice(list.get(i).getWorkPrice());
            productDTO.setQuantity(list.get(i).getQuantity());
            productDTO.setWeight(list.get(i).getWeight());
            productDTO.setCategory(list.get(i).getCategory().getName());
            listTotal.add(productDTO);
        }
        logger.info("Send list with size " + listTotal.size());
        return listTotal;
    }

}
