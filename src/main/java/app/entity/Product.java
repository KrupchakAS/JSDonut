package app.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Field can not be null")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Field can not be null")
    @Column(name = "description")
    private String description;

    @NotNull(message = "Field can not be null")
    @Column(name = "image")
    private String image;

    @NotNull(message = "Field can not be null")
    @Column(name = "price")
    private Float price;

    @NotNull(message = "Field can not be null")
    @Column(name = "workPrice")
    private Float workPrice;

    @NotNull(message = "Field can not be null")
    @Column(name = "weight")
    private Short weight;

    @NotNull(message = "Field can not be null")
    @Column(name = "quantity")
    private Short quantity;

    @NotNull(message = "Field can not be null")
    @Column(name = "calories")
    private Short calories;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "productList")
    private List<Order> orderList;

    @OneToOne
    @JoinColumn(name = "filling_id")
    private Filling filling;

    @OneToOne
    @JoinColumn(name = "dough_id")
    private Dough dough;

    @ManyToMany
    @JoinTable(name = "products_sprinkle", joinColumns = @JoinColumn(name = "product_id"),
    inverseJoinColumns = @JoinColumn(name = "sprinkle_id"))
    private List<Sprinkle> sprinkleList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Short getWeight() {
        return weight;
    }

    public void setWeight(Short weight) {
        this.weight = weight;
    }

    public Short getQuantity() {
        return quantity;
    }

    public void setQuantity(Short quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public Float getWorkPrice() {
        return workPrice;
    }

    public void setWorkPrice(Float workPrice) {
        this.workPrice = workPrice;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public Short getCalories() {
        return calories;
    }

    public void setCalories(Short calories) {
        this.calories = calories;
    }

    public Filling getFilling() {
        return filling;
    }

    public void setFilling(Filling filling) {
        this.filling = filling;
    }

    public Dough getDough() {
        return dough;
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Sprinkle> getSprinkleList() {
        return sprinkleList;
    }

    public void setSprinkleList(List<Sprinkle> sprinkleList) {
        this.sprinkleList = sprinkleList;
    }

}
