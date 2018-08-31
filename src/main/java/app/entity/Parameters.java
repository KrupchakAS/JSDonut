package app.entity;

import javax.persistence.*;

@Entity
@Table(name = "parameters")
public class Parameters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "calories")
    private Short calories;

    @Column(name = "fat")
    private Short fat;

    @Column(name = "carbohydrate")
    private Short carbohydrate;

    @Column(name = "protein")
    private Short protein;

    @OneToOne(mappedBy = "parameters")
    private Product product;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getCalories() {
        return calories;
    }

    public void setCalories(Short calories) {
        this.calories = calories;
    }

    public Short getFat() {
        return fat;
    }

    public void setFat(Short fat) {
        this.fat = fat;
    }

    public Short getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(Short carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public Short getProtein() {
        return protein;
    }

    public void setProtein(Short protein) {
        this.protein = protein;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
