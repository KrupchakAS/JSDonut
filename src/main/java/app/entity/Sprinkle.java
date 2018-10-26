package app.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sprinkle")
public class Sprinkle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Field can not be null")
    @Column(name = "name")
    private String name;

    @Min(value = 0)
    @NotNull(message = "Field can not be null")
    @Column(name = "price")
    private Float price;

    @NotNull(message = "Field can not be null")
    @Column(name = "calories")
    private Short calories;

    public Sprinkle() {
    }

    public Sprinkle(String name, Float price, Short calories) {
        this.name = name;
        this.price = price;
        this.calories = calories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Short getCalories() {
        return calories;
    }

    public void setCalories(Short calories) {
        this.calories = calories;
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
}
