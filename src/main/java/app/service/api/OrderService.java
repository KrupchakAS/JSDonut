package app.service.api;

import app.dto.OrderDTO;
import app.entity.Product;
import app.entity.User;

import java.util.List;

public interface OrderService {

    void create(OrderDTO orderDTO);

    void update(OrderDTO orderDTO);

    void delete(OrderDTO orderDTO);

    OrderDTO getById(Integer id);

    List<OrderDTO> getAll();

    List<OrderDTO> getOrdersByUserId(Integer userId);

    void updateStatuses(OrderDTO orderDTO);

    Float getProceedsForLastMonth();

    Float getProceedsForLastWeek();

    List<User> getTop10Users();

    List<Product> getTop10Products();
}
