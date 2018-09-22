package app.dao.api;

import app.dao.GenericDao;
import app.entity.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Order>{

    List<Order> getOrdersByUserId(Integer userId);
}
