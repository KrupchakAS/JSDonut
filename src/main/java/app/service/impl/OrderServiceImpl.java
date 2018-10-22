package app.service.impl;

import app.dao.api.OrderDao;
import app.dao.api.OrderProductDao;
import app.dto.*;
import app.entity.*;
import app.entity.enums.OrderStatus;
import app.entity.enums.PaymentOption;
import app.entity.enums.PaymentStatus;
import app.message.MessageSender;

import app.service.api.OrderService;
import app.service.api.ProductService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = Logger.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private OrderProductDao orderProductDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void create(OrderDTO orderDTO) {
        if (orderDTO != null) {
            Order order = new Order();
            order.setPurchaseDate(new Date());
            order.setTotalPrice(orderDTO.getTotalPrice());
            order.setProductList(generateProductList(orderDTO.getProductList()));
            if (orderDTO.getAddress() != null) {
                order.setAddress(modelMapper.map(orderDTO.getAddress(), Address.class));
            }
            order.setUser(modelMapper.map(orderDTO.getUserDTO(), User.class));

            order.setDeliveryOption(orderDTO.getDeliveryOption());
            order.setPaymentOption(orderDTO.getPaymentOption());
            order.setOrderStatus(OrderStatus.AWAITING_PAYMENT);
            if (orderDTO.getPaymentOption() == PaymentOption.CASH) {
                order.setPaymentStatus(PaymentStatus.NOT_PAID);
            } else {
                order.setPaymentStatus(PaymentStatus.PAID);
            }
            orderDao.create(order);

            List<OrderProduct> orderProductList = new ArrayList<>();
            for(int i = 0; i < orderDTO.getProductList().size();i++){
                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setOrder(order);
                orderProduct.setProduct(modelMapper.map(orderDTO.getProductList().get(i),Product.class));
                orderProduct.setQuantity(orderDTO.getProductList().get(i).getQuantity());
                orderProductDao.create(orderProduct);
                orderProductList.add(orderProduct);
            }
            order.setOrderProducts(orderProductList);

            orderDao.update(order);
            logger.info("Successfully saved order");
            messageSender.sendMessage("Update");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateStatuses(OrderDTO orderDTO) {
        Order order = orderDao.getById(orderDTO.getId());
        if (order != null) {
            order.setDeliveryOption(orderDTO.getDeliveryOption());
            order.setPaymentOption(orderDTO.getPaymentOption());
            order.setOrderStatus(orderDTO.getOrderStatus());
            order.setPaymentStatus(orderDTO.getPaymentStatus());
            orderDao.update(order);
        }
        logger.info("Successfully updated order");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(OrderDTO orderDTO) {
        Order order = orderDao.getById(orderDTO.getId());
        if (order != null) {
            order.setTotalPrice(orderDTO.getTotalPrice());
            List<Product> productList = orderDTO.getProductList().stream().map(productDTO -> modelMapper.map(productDTO, Product.class)).collect(Collectors.toList());
            order.setProductList(productList);
            order.setPurchaseDate(orderDTO.getPurchaseDate());
            if (orderDTO.getAddress() != null) {
                order.setAddress(modelMapper.map(orderDTO.getAddress(), Address.class));
            }
            order.setUser(modelMapper.map(orderDTO.getUserDTO(), User.class));

            order.setDeliveryOption(orderDTO.getDeliveryOption());
            order.setPaymentOption(orderDTO.getPaymentOption());
            order.setOrderStatus(OrderStatus.AWAITING_PAYMENT);
            if (orderDTO.getPaymentOption() == PaymentOption.CASH) {
                order.setPaymentStatus(PaymentStatus.NOT_PAID);
            } else {
                order.setPaymentStatus(PaymentStatus.PAID);
            }
            orderDao.update(order);
        }
        logger.info("Successfully updated order");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(OrderDTO orderDTO) {
        if (orderDTO != null)
            orderDao.delete(modelMapper.map(orderDTO, Order.class));
        logger.info("Successfully deleted order");
    }

    @Transactional(readOnly = true)
    @Override
    public OrderDTO getById(Integer id) {
        Order order = orderDao.getById(id);
        OrderDTO orderDTO = new OrderDTO();
        if (order.getAddress() != null) {
            orderDTO.setAddress(modelMapper.map(order.getAddress(), AddressDTO.class));
        }
        orderDTO.setId(order.getId());

        List<OrderProduct> orderProductDTOS = orderProductDao.getOrderProductsByOrderId(order.getId());
        orderDTO.setOrderProducts(orderProductDTOS);

        orderDTO.setPurchaseDate(order.getPurchaseDate());
        orderDTO.setTotalPrice(order.getTotalPrice());

        orderDTO.setProductList(order.getProductList().stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList()));
        orderDTO.setUserDTO(modelMapper.map(order.getUser(), UserDTO.class));

        orderDTO.setPaymentOption(order.getPaymentOption());
        orderDTO.setDeliveryOption(order.getDeliveryOption());
        orderDTO.setOrderStatus(order.getOrderStatus());
        orderDTO.setPaymentStatus(order.getPaymentStatus());

        return orderDTO;
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrderDTO> getAll() {
        List<Order> orderList = orderDao.getAll();
        List<OrderDTO> orderDTOList = new ArrayList<>();
        if (orderList != null) {
            for (int i = 0; i < orderList.size(); i++) {
                OrderDTO orderDTO = new OrderDTO();

                orderDTO.setPaymentOption(orderList.get(i).getPaymentOption());
                orderDTO.setDeliveryOption(orderList.get(i).getDeliveryOption());
                orderDTO.setOrderStatus(orderList.get(i).getOrderStatus());
                orderDTO.setPaymentStatus(orderList.get(i).getPaymentStatus());

                orderDTO.setId(orderList.get(i).getId());

                List<OrderProduct> orderProductDTOS = orderProductDao.getOrderProductsByOrderId(orderList.get(i).getId());
                orderDTO.setOrderProducts(orderProductDTOS);

                List<ProductDTO> productList = orderList.get(i).getProductList().stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
                orderDTO.setProductList(productList);

                orderDTO.setPurchaseDate(orderList.get(i).getPurchaseDate());
                orderDTO.setTotalPrice(orderList.get(i).getTotalPrice());
                if (orderList.get(i).getAddress() != null) {
                    AddressDTO addressDTO = modelMapper.map(orderList.get(i).getAddress(), AddressDTO.class);
                    orderDTO.setAddress(addressDTO);
                }
                UserDTO userDTO = modelMapper.map(orderList.get(i).getUser(), UserDTO.class);
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

                List<OrderProduct> orderProductDTOS = orderProductDao.getOrderProductsByOrderId(orderList.get(i).getId());
                orderDTO.setOrderProducts(orderProductDTOS);

                orderDTO.setPaymentOption(orderList.get(i).getPaymentOption());
                orderDTO.setDeliveryOption(orderList.get(i).getDeliveryOption());
                orderDTO.setOrderStatus(orderList.get(i).getOrderStatus());
                orderDTO.setPaymentStatus(orderList.get(i).getPaymentStatus());

                List<ProductDTO> productList = orderList.get(i).getProductList().stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
                orderDTO.setProductList(productList);
                if (orderList.get(i).getAddress() != null) {
                    AddressDTO addressDTO = modelMapper.map(orderList.get(i).getAddress(), AddressDTO.class);
                    orderDTO.setAddress(addressDTO);
                }

                orderDTO.setPurchaseDate(orderList.get(i).getPurchaseDate());
                orderDTO.setTotalPrice(orderList.get(i).getTotalPrice());
                UserDTO userDTO = modelMapper.map(orderList.get(i).getUser(), UserDTO.class);
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
    public Float getProceedsForLastMonth() {
        List<Order> orderList = orderDao.getOrdersForMonth();
        Float proceeds = 0.0f;
        if (orderList != null) {
            for (int i = 0; i < orderList.size(); i++) {
                proceeds += orderList.get(i).getTotalPrice();
            }
            return proceeds;
        } else
            return 0.0f;

    }

    @Transactional(readOnly = true)
    @Override
    public Float getProceedsForLastWeek() {
        List<Order> orderList = orderDao.getOrdersForWeek();
        Float proceeds = 0.0f;
        if (orderList != null) {
            for (Order anOrderList : orderList) {
                proceeds += anOrderList.getTotalPrice();
            }
            return proceeds;
        } else {
            return 0.0f;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDTO> getTop10Users() {
        List<Order> orderList = orderDao.getAll();
        Map<User, Integer> top10Users = new HashMap<>();
        for (int i = 0; i < orderList.size(); i++) {
            int countOrders = 0;
            for (Order anOrderList : orderList) {
                if (orderList.get(i).getUser().getId().equals(anOrderList.getUser().getId())) {
                    countOrders++;
                }
            }
            top10Users.put(orderList.get(i).getUser(), countOrders);
        }

        Map<User, Integer> sorted = top10Users
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));

        List<User> userList = new ArrayList<>(sorted.keySet());
        List<User> userList2 = new ArrayList<>();
        if (userList.size() > 10) {
            for (int i = 0; i < 10; i++) {
                userList2.add(userList.get(i));
            }
            return userList2.stream().map(user -> modelMapper.map(user,UserDTO.class)).collect(Collectors.toList());
        } else {
            return userList.stream().map(user -> modelMapper.map(user,UserDTO.class)).collect(Collectors.toList());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductDTO> getTop10Products() {
        List<Order> orderList = orderDao.getAll();
        Map<Product, Integer> top10Products = new HashMap<>();
        List<Product> productList = new ArrayList<>();
        for (Order anOrderList : orderList) {
            productList.addAll(anOrderList.getProductList());
        }
        for (int i = 0; i < productList.size(); i++) {
            int countInOrders = 0;
            for (Product aProductList : productList) {
                if (productList.get(i).getId().equals(aProductList.getId())) {
                    countInOrders++;
                }
            }
            top10Products.put(productList.get(i), countInOrders);
        }

        Map<Product, Integer> sorted = top10Products
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));

        List<Product> productList1 = new ArrayList<>(sorted.keySet());
        List<Product> productList2 = new ArrayList<>();
        if (productList1.size() > 10) {
            for (int i = 0; i < 10; i++) {
                productList2.add(productList1.get(i));
            }

            return productList2.stream().map(product -> modelMapper.map(product,ProductDTO.class)).collect(Collectors.toList());
        } else {
            return productList1.stream().map(product -> modelMapper.map(product,ProductDTO.class)).collect(Collectors.toList());
        }
    }


    private List<Product> generateProductList(List<ProductDTO> productDTOList) {
        List<Product> productList = new ArrayList<>();

        for (ProductDTO productDTO : productDTOList) {
            productService.byProduct(productDTO);
            productList.add(modelMapper.map(productDTO, Product.class));
        }
        return productList;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
