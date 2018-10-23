package app.service.api;

import app.dto.CategoryDTO;
import java.util.List;

public interface CategoryService {

    CategoryDTO create(CategoryDTO categoryDTO);

    void update(CategoryDTO categoryDTO);

    void delete(CategoryDTO categoryDTO);

    CategoryDTO getById(Integer id);

    CategoryDTO getByName(String name);

    List<CategoryDTO> getAll();

    void checkCategoryByProduct(Integer id);
}
