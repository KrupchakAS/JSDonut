package app.dao.api;

import app.dao.GenericDao;
import app.entity.OrderProduct;

import java.util.List;

public interface OrderProductDao extends GenericDao<OrderProduct> {

    List<OrderProduct> getOrderProductsByOrderId(Integer userId);
}
