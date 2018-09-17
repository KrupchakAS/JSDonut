package app.dto;

import app.entity.Product;
import app.entity.User;
import app.entity.enums.DeliveryOption;
import app.entity.enums.OrderStatus;
import app.entity.enums.PaymentOption;
import app.entity.enums.PaymentStatus;

import javax.validation.constraints.NotNull;
import java.util.List;

public class OrderDTO {

    private Integer id;
    @NotNull
    private PaymentOption paymentOption;
    @NotNull
    private DeliveryOption deliveryOption;
    @NotNull
    private PaymentStatus paymentStatus;
    @NotNull
    private OrderStatus orderStatus;
    @NotNull
    private List<Product> productList;
    @NotNull
    private UserDTO user;

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

    public UserDTO getUserDTO() {
        return user;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.user = userDTO;
    }
}
