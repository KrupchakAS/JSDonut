package app.service.api;


import app.dto.OrderProductDTO;

import java.util.List;

public interface OrderProductService {

    void create(OrderProductDTO orderProduct);

    OrderProductDTO getById(Integer id);

    List<OrderProductDTO> getAll();

    List<OrderProductDTO> getOrdersByUserId(Integer userId);
}
