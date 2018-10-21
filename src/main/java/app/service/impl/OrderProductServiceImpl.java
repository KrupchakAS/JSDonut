package app.service.impl;

import app.dao.api.OrderProductDao;
import app.dto.OrderProductDTO;
import app.entity.Order;
import app.entity.OrderProduct;
import app.entity.Product;
import app.entity.User;
import app.service.api.OrderProductService;
import app.service.api.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderProductDao orderProductDao;

    @Autowired
    private ProductService productService;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void create(OrderProductDTO orderProduct) {
        if (orderProduct != null) {
            OrderProduct orderProduct1 = new OrderProduct();
            //orderProduct1.setOrder(modelMapper.map(orderProduct.getOrder(), Order.class));
            orderProduct1.setProduct(modelMapper.map(orderProduct.getProduct(), Product.class));
            orderProduct1.setQuantity(orderProduct.getQuantity());
            orderProductDao.create(orderProduct1);
        }
    }

    @Override
    public OrderProductDTO getById(Integer id) {
        return null;
    }

    @Override
    public List<OrderProductDTO> getAll() {
        return null;
    }

    @Override
    public List<OrderProductDTO> getOrdersByUserId(Integer userId) {
        return null;
    }
}
