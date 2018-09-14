package app.dto;

import app.entity.Product;
import app.entity.User;
import app.entity.enums.DeliveryOption;
import app.entity.enums.OrderStatus;
import app.entity.enums.PaymentOption;
import app.entity.enums.PaymentStatus;

import java.util.List;

public class OrderDTO {

    private Integer id;

    private PaymentOption paymentOption;

    private DeliveryOption deliveryOption;

    private PaymentStatus paymentStatus;

    private OrderStatus orderStatus;

    private List<Product> productList;

    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PaymentOption getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(PaymentOption paymentOption) {
        this.paymentOption = paymentOption;
    }

    public DeliveryOption getDeliveryOption() {
        return deliveryOption;
    }

    public void setDeliveryOption(DeliveryOption deliveryOption) {
        this.deliveryOption = deliveryOption;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public User getUserDTO() {
        return user;
    }

    public void setUserDTO(User userDTO) {
        this.user = userDTO;
    }
}
