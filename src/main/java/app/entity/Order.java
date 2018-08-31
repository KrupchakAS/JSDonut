package app.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "paymentOption")
    private Byte paymentOption;

    @Column(name = "deliveryOption")
    private Byte deliveryOption;

    @Column(name = "paymentStatus")
    private Byte paymentStatus;

    @Column(name = "orderStatus")
    private Byte orderStatus;

    @OneToMany(mappedBy = "order")
    private Set<Product> productList;

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

    public Set<Product> getProductList() {
        return productList;
    }

    public void setProductList(Set<Product> productList) {
        this.productList = productList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
