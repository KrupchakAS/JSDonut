package app.dto;

import app.entity.Product;
import app.entity.User;

import java.util.List;

public class OrderDTO {

    private Integer id;


    private Byte paymentOption;


    private Byte deliveryOption;


    private Byte paymentStatus;


    private Byte orderStatus;


    private List<Product> productList;


    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(Byte paymentOption) {
        this.paymentOption = paymentOption;
    }

    public Byte getDeliveryOption() {
        return deliveryOption;
    }

    public void setDeliveryOption(Byte deliveryOption) {
        this.deliveryOption = deliveryOption;
    }

    public Byte getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Byte paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
