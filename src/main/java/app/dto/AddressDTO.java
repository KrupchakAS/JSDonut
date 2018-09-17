package app.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddressDTO {

    private Integer id;
    @Size(min = 2, max = 16, message = "Field must be between 2 and 16 characters.")
    private String city;
    @Size(min = 4, max = 16, message = "Field must be between 4 and 16 characters.")
    private Integer postCode;
    @Size(min = 1, max = 10, message = "Field must be between 1 and 10 characters.")
    private Short houseNumber;
    @Size(min = 1, max = 10, message = "Field must be between 1 and 10 characters.")
    private Short apartmentNumber;
    @NotNull
    private UserDTO user;

    public Integer getId() { return id; }

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
