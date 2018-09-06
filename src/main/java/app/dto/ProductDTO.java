package app.dto;

import app.entity.*;

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

    private Category category;

    private List<Order> orderList;

    private Filling filling;

    private Dough corn;

    private User user;

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

    public User getUser() { return user; }

    public void setUser(User user) {
        this.user = user;
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

    public Category getCategory() {
        return category;
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

    public Filling getFilling() {
        return filling;
    }

    public void setFilling(Filling filling) {
        this.filling = filling;
    }


    public Dough getCorn() {
        return corn;
    }

    public void setCorn(Dough corn) {
        this.corn = corn;
    }


    public List<Sprinkle> getSprinkleList() {
        return sprinkleList;
    }

    public void setSprinkleList(List<Sprinkle> sprinkleList) {
        this.sprinkleList = sprinkleList;
    }
}
