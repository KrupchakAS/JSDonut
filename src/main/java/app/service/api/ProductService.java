package app.service.api;

import app.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO create(ProductDTO productDTO);

    void update(ProductDTO productDTO);

    void delete(ProductDTO productDTO);

    ProductDTO getById(Integer id);

    ProductDTO getByName(String name);

    List<ProductDTO> getAll();

    ProductDTO getLastProduct();
}
