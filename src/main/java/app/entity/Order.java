package app.entity;

import app.entity.enums.Converter.DeliveryOptionConverter;
import app.entity.enums.Converter.OrderStatusConverter;
import app.entity.enums.Converter.PaymentOptionConverter;
import app.entity.enums.Converter.PaymentStatusConverter;
import app.entity.enums.DeliveryOption;
import app.entity.enums.OrderStatus;
import app.entity.enums.PaymentOption;
import app.entity.enums.PaymentStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Field can not be null")
    @Column(name = "paymentOption", insertable = false, updatable = false)
    @Convert(converter = PaymentOptionConverter.class)
    private PaymentOption paymentOption;

    @NotNull(message = "Field can not be null")
    @Column(name = "deliveryOption", insertable = false, updatable = false)
    @Convert(converter = DeliveryOptionConverter.class)
    private DeliveryOption deliveryOption;

    @NotNull(message = "Field can not be null")
    @Column(name = "paymentStatus", insertable = false, updatable = false)
    @Convert(converter = PaymentStatusConverter.class)
    private PaymentStatus paymentStatus;

    @NotNull(message = "Field can not be null")
    @Column(name = "OrderStatus", insertable = false, updatable = false)
    @Convert(converter = OrderStatusConverter.class)
    private OrderStatus orderStatus;

    @ManyToMany
    @JoinTable(name = "order_products", joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productList;

    @ManyToOne
    @JoinColumn(name = "user_id")
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

    public DeliveryOption getDeliveryOption() {
        return deliveryOption;
    }

    public void setDeliveryOption(DeliveryOption deliveryOption) {
        this.deliveryOption = deliveryOption;
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
