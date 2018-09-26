package app.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Field can not be Null")
    @Column(name = "city")
    private String city;

    @NotNull(message = "Field can not be Null")
    @Column(name = "postCode")
    private String postCode;

    @NotNull(message = "Field can not be Null")
    @Column(name = "houseNumber")
    private Short houseNumber;

    @NotNull(message = "Field can not be Null")
    @Column(name = "apartmentNumber")
    private Short apartmentNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public Short getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Short houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Short getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(Short apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
