package app.dto;

import app.entity.*;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.List;

public class ProductDTO {


    private Integer id;


    private String name;

    private String description;


    private String image;


    private Float price;


    private Short weight;


    private Short quantity;


    private Short calories;


    private String creator;


    private Category category;


    private Integer category_id;

    private List<Order> orderList;

    private Filling filling;

    private Integer filling_id;

    private Corn corn;

    private Integer corn_id;

    private List<Sprinkle> sprinkleList;

    public Integer getId() { return id; }

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

    public Short getCalories() {
        return calories;
    }

    public void setCalories(Short calories) {
        this.calories = calories;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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
