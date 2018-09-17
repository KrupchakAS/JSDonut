package app.dto;


import javax.validation.constraints.Size;

public class DoughDTO {

    private Integer id;
    @Size(min = 2, max = 16, message = "Field must be between 2 and 16 characters.")
    private String name;
    @Size(min = 1, max = 5, message = "Field must be between 1 and 5 digits.")
    private Float price;
    @Size(min = 1, max = 6, message = "Field must be between 1 and 5 digits.")
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
