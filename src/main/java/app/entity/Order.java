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
    private Byte paymentOption;

    @NotNull(message = "Field can not be null")
    private Byte deliveryOption;

    @NotNull(message = "Field can not be null")
    private Byte paymentStatus;

    @NotNull(message = "Field can not be null")
    private Byte orderStatus;

    @NotNull(message = "Field can not be null")
    private Float totalPrice;

    @NotNull(message = "Field can not be null")
    @ManyToMany
    @JoinTable(name = "order_products", joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productList;

    @NotNull(message = "Field can not be null")
    @ManyToOne
    @JoinColumn(name = "user_id")
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

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
