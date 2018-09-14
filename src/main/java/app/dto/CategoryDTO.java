package app.dto;

public class CategoryDTO {

    private Integer id;

    private String name;

    private ProductDTO product;

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

    public ProductDTO getProductDTO() {
        return product;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.product = productDTO;
    }
}
