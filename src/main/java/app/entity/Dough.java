package app.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dough")
public class Dough {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Field can not be null")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Field can not be null")
    @Column(name = "price")
    private Short price;

    @NotNull(message = "Field can not be null")
    @Column(name = "calories")
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

    public Short getCalories() {
        return calories;
    }

    public void setCalories(Short calories) {
        this.calories = calories;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getPrice() {
        return price;
    }

    public void setPrice(Short price) {
        this.price = price;
    }
}