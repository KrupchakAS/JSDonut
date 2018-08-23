package app.dto;


import app.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

public class RoleDTO {

    private Integer id;

    private String name;
    @JsonIgnore
    private Set<UserDTO> users;

    public Set<UserDTO> getUsers() {
        return users;
    }

    public RoleDTO() {
    }

    public RoleDTO(String name, Set<UserDTO> users) {
        this.name = name;
        this.users = users;
    }

    public void setUsers(Set<UserDTO> users) {
        this.users = users;
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
