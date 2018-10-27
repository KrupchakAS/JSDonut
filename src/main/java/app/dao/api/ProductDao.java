package app.dao.api;

import app.dao.GenericDao;
import app.dto.FilterDTO;
import app.entity.Product;

import java.util.List;

public interface ProductDao extends GenericDao<Product>{
    Product getByName(String name);

    List<Product> getAllByCategory(Integer categoryId);

    List<Product> getProductsByParameters(FilterDTO filterDTO);
}
