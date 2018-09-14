package app.dto;

import app.entity.User;

public class AddressDTO {

    private Integer id;


    private String city;


    private Integer postCode;


    private Short houseNumber;

    private Short apartmentNumber;

    private UserDTO user;

    public Integer getId() {        return id; }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPostCode() {
        return postCode;
    }

    public void setPostCode(Integer postCode) {
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

    public UserDTO getUserDTO() {
        return user;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.user = userDTO;
    }
}
