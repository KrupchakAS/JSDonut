package app.dto;

import app.entity.enums.Role;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

public class UserDTO {

    private Integer id;

    @Size(min = 4, max = 16, message = "Field must be between 4 and 16 characters.")
    private String login;

    @Size(min = 4, max = 16, message = "Field must be between 4 and 16 characters.")
    private String password;

    @Size(min = 1,max = 16, message = "Field must be between 1 and 16 characters.")
    private String firstName;

    @Size(min = 1,max = 16, message = "Field must be between 1 and 16 characters.")
    private String surName;

    @Size(min = 10,max = 10, message = "Field must contain 10 digits.")
    private String phoneNumber;

    @NotNull(message = "Field can not be blank.")
    @Past
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Email
    @Size(min = 4,max = 32, message = "Field must be between 4 and 32 characters.")
    private String email;

    @Size(min = 4, max = 16, message = "Field must be between 4 and 16 characters.")
    @Transient
    private String confirmPassword;

    private Role role = Role.ROLE_USER;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
