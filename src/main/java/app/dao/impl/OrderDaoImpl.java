package app.dao.impl;

import app.dao.GenericDaoImpl;
import app.dao.api.OrderDao;
import app.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {
}
