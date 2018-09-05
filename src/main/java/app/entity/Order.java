package app.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Field can not be null")
    @Column(name = "paymentOption")
    private Byte paymentOption;

    @NotNull(message = "Field can not be null")
    @Column(name = "deliveryOption")
    private Byte deliveryOption;

    @NotNull(message = "Field can not be null")
    @Column(name = "paymentStatus")
    private Byte paymentStatus;

    @NotNull(message = "Field can not be null")
    @Column(name = "orderStatus")
    private Byte orderStatus;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "order_products", joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productList;

    @ManyToOne(cascade = CascadeType.ALL)
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
}
