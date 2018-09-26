package app.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FillingDTO {

    private Integer id;
    @Size(min = 2, max = 16, message = "Field must be between 4 and 16 characters.")
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Short getCalories() {
        return calories;
    }

    public void setCalories(Short calories) {
        this.calories = calories;
    }
}
