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
    @Column(name = "weight")
    private Short weight;

    @NotNull(message = "Field can not be null")
    @Column(name = "quantity")
    private Short quantity;

    @NotNull(message = "Field can not be null")
    @Column(name = "calories")
    private Short calories;

    @NotNull(message = "Field can not be null")
    @Column(name = "creator")
    private String creator;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(insertable = false, updatable = false)
    private Integer category_id;

    @ManyToMany(mappedBy = "productList")
    private List<Order> orderList;

    @OneToOne
    @JoinColumn(name = "filling_id")
    private Filling filling;

    @Column(insertable = false, updatable = false)
    private Integer filling_id;

    @OneToOne
    @JoinColumn(name = "corn_id")
    private Corn corn;

    @Column(insertable = false, updatable = false)
    private Integer corn_id;

    @OneToMany
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

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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

    public Integer getFilling_id() {
        return filling_id;
    }

    public void setFilling_id(Integer filling_id) {
        this.filling_id = filling_id;
    }

    public Corn getCorn() {
        return corn;
    }

    public void setCorn(Corn corn) {
        this.corn = corn;
    }

    public Integer getCorn_id() {
        return corn_id;
    }

    public void setCorn_id(Integer corn_id) {
        this.corn_id = corn_id;
    }

    public List<Sprinkle> getSprinkleList() {
        return sprinkleList;
    }

    public void setSprinkleList(List<Sprinkle> sprinkleList) {
        this.sprinkleList = sprinkleList;
    }

}
