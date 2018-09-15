package app.service.impl;

import app.dao.api.OrderDao;
import app.dto.OrderDTO;
import app.entity.Order;
import app.service.api.OrderService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void create(OrderDTO orderDTO) {
        if (orderDTO != null)
            orderDao.create(modelMapper.map(orderDTO, Order.class));
        logger.debug(String.format("Successfully saved order"));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(OrderDTO orderDTO) {
        Order order = orderDao.getById(orderDTO.getId());
        if (order != null)
            orderDao.update(modelMapper.map(orderDTO, Order.class));
        logger.debug(String.format("Successfully updated order"));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(OrderDTO orderDTO) {
        if (orderDTO != null)
            orderDao.delete(modelMapper.map(orderDTO, Order.class));
        logger.debug(String.format("Successfully deleted order"));
    }

    @Transactional(readOnly = true)
    @Override
    public OrderDTO getById(Integer id) {
        return modelMapper.map(orderDao.getById(id), OrderDTO.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrderDTO> getAll() {
        List<Order> orderList = orderDao.getAll();
        if (orderList != null) {
            return orderList.stream().map(order -> modelMapper.map(order, OrderDTO.class)).collect(Collectors.toList());
        } else {
            return null;
        }
    }
}
