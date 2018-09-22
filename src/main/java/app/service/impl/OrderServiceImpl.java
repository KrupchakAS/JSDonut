package app.service.impl;

import app.dao.api.OrderDao;
import app.dto.OrderDTO;
import app.dto.ProductDTO;
import app.dto.UserDTO;
import app.entity.Order;
import app.entity.Product;
import app.entity.User;
import app.entity.enums.Converter.OrderStatusConverter;
import app.entity.enums.Converter.PaymentStatusConverter;
import app.service.api.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import app.entity.enums.Converter.DeliveryOptionConverter;
import app.entity.enums.Converter.PaymentOptionConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void create(OrderDTO orderDTO) {
        if (orderDTO != null) {
            Order order = new Order();
            order.setTotalPrice(orderDTO.getTotalPrice());
            List<Product> productList = orderDTO.getProductList().stream().map(productDTO -> modelMapper.map(productDTO, Product.class)).collect(Collectors.toList());
            order.setProductList(productList);
            order.setUser(modelMapper.map(orderDTO.getUserDTO(), User.class));
            order.setDeliveryOption(new DeliveryOptionConverter().convertToDatabaseColumn(orderDTO.getDeliveryOption()));
            order.setPaymentOption(new PaymentOptionConverter().convertToDatabaseColumn(orderDTO.getPaymentOption()));
            order.setOrderStatus((byte) 1);
            if (new PaymentOptionConverter().convertToDatabaseColumn(orderDTO.getPaymentOption()) == 1) {
                order.setPaymentStatus((byte) 1);
            } else {
                order.setPaymentStatus((byte) 2);
            }
            orderDao.create(order);
        }
        logger.info(String.format("Successfully saved order"));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(OrderDTO orderDTO) {
        Order order = orderDao.getById(orderDTO.getId());
        if (order != null) {
            order.setTotalPrice(orderDTO.getTotalPrice());
            List<Product> productList = orderDTO.getProductList().stream().map(productDTO -> modelMapper.map(productDTO, Product.class)).collect(Collectors.toList());
            order.setProductList(productList);
            order.setUser(modelMapper.map(orderDTO.getUserDTO(), User.class));
            order.setDeliveryOption(new DeliveryOptionConverter().convertToDatabaseColumn(orderDTO.getDeliveryOption()));
            order.setPaymentOption(new PaymentOptionConverter().convertToDatabaseColumn(orderDTO.getPaymentOption()));
            order.setOrderStatus((byte) 1);
            if (new PaymentOptionConverter().convertToDatabaseColumn(orderDTO.getPaymentOption()) == 1) {
                order.setPaymentStatus((byte) 1);
            } else {
                order.setPaymentStatus((byte) 2);
            }
            orderDao.update(modelMapper.map(orderDTO, Order.class));
        }
        logger.info(String.format("Successfully updated order"));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(OrderDTO orderDTO) {
        if (orderDTO != null)
            orderDao.delete(modelMapper.map(orderDTO, Order.class));
        logger.info(String.format("Successfully deleted order"));
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
        List<OrderDTO> orderDTOList = new ArrayList<>();
        if (orderList != null) {
            for(int i = 0; i < orderList.size();i ++){
                OrderDTO orderDTO = new OrderDTO();

                orderDTO.setPaymentOption(new PaymentOptionConverter().convertToEntityAttribute(orderList.get(i).getPaymentStatus()));
                orderDTO.setDeliveryOption(new DeliveryOptionConverter().convertToEntityAttribute(orderList.get(i).getDeliveryOption()));
                orderDTO.setOrderStatus(new OrderStatusConverter().convertToEntityAttribute(orderList.get(i).getOrderStatus()));
                orderDTO.setPaymentStatus(new PaymentStatusConverter().convertToEntityAttribute(orderList.get(i).getPaymentStatus()));

                orderDTO.setId(orderList.get(i).getId());
                List<ProductDTO> productList = orderList.get(i).getProductList().stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
                orderDTO.setProductList(productList);

                UserDTO userDTO = modelMapper.map(orderList.get(i).getUser(),UserDTO.class);
                orderDTO.setUserDTO(userDTO);
                orderDTOList.add(orderDTO);
            }
            return orderDTOList;
        } else {
            return null;
        }
    }
    @Transactional(readOnly = true)
    @Override
    public List<OrderDTO> getOrdersByUserId(Integer userId) {
        List<Order> orderList = orderDao.getOrdersByUserId(userId);
        List<OrderDTO> orderDTOList = new ArrayList<>();
        if (orderList != null) {
            for (int i = 0; i < orderList.size(); i++) {
                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setId(orderList.get(i).getId());

                orderDTO.setPaymentOption(new PaymentOptionConverter().convertToEntityAttribute(orderList.get(i).getPaymentStatus()));
                orderDTO.setDeliveryOption(new DeliveryOptionConverter().convertToEntityAttribute(orderList.get(i).getDeliveryOption()));
                orderDTO.setOrderStatus(new OrderStatusConverter().convertToEntityAttribute(orderList.get(i).getOrderStatus()));
                orderDTO.setPaymentStatus(new PaymentStatusConverter().convertToEntityAttribute(orderList.get(i).getPaymentStatus()));

                List<ProductDTO> productList = orderList.get(i).getProductList().stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
                orderDTO.setProductList(productList);

                UserDTO userDTO = modelMapper.map(orderList.get(i).getUser(), UserDTO.class);
                orderDTO.setUserDTO(userDTO);

                orderDTOList.add(orderDTO);
            }
            return orderDTOList;
        } else {
            return null;
        }
    }
}
