package app.service.api;

import app.dto.ProductDTO;
import app.entity.Product;

import java.util.List;

public interface ProductService {

    ProductDTO create(ProductDTO productDTO);

    void update(ProductDTO productDTO);

    void delete(ProductDTO productDTO);

    ProductDTO getById(Integer id);

    ProductDTO getByName(String name);

    List<ProductDTO> getAll();

    List<ProductDTO> getAllByCategory(Integer categoryId);

    List<ProductDTO> getProductsByParameters(Integer categoryId,Integer fillingId, Integer doughId, String productsName, Integer minPrice, Integer maxPrice);
}
