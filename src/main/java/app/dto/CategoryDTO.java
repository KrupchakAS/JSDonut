package app.dto;

import javax.validation.constraints.Size;

public class CategoryDTO {

    private Integer id;

    @Size(min = 2, max = 16, message = "Field must be between 2 and 16 characters.")
    private String name;

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
