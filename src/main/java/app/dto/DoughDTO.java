package app.dto;


import javax.validation.constraints.NotNull;

public class DoughDTO {

    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private Float price;
    @NotNull
    private Short calories;

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

    public Short getCalories() {
        return calories;
    }

    public void setCalories(Short calories) {
        this.calories = calories;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
