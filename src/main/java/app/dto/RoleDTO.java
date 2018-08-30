package app.dto;


import app.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

public class RoleDTO {

    private Integer id;

    private String name;
    @JsonIgnore
    private UserDTO user;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
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

    public void setName(String name) {
        this.name = name;
    }
}
