package app.dto;

import app.entity.Order;
import app.entity.Product;
import app.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class OrderProductDTO {

    private Integer id;
    @JsonIgnore
    private OrderDTO order;

    private ProductDTO product;

    private Short quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public Short getQuantity() {
        return quantity;
    }

    public void setQuantity(Short quantity) {
        this.quantity = quantity;
    }


}
