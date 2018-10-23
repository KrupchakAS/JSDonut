package app.service.impl;

import app.dao.api.CategoryDao;
import app.dao.api.ProductDao;
import app.dto.CategoryDTO;
import app.entity.Category;
import app.entity.Product;
import app.exception.CategoryUsedException;
import app.exception.ObjectExistsException;
import app.service.api.CategoryService;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger logger = Logger.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductDao productDao;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public CategoryDTO create(CategoryDTO categoryDTO) {
        if (categoryDTO != null) {
            Category categoryex = categoryDao.getByName(categoryDTO.getName());
            if (categoryex != null) {
                throw new ObjectExistsException(String.format("Category with name %s already exists", categoryDTO.getName()));
            }
            Category category = modelMapper.map(categoryDTO, Category.class);
            categoryDao.create(category);
            categoryDTO.setId(category.getId());
            logger.info("Successfully saved category");
        }

        return categoryDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(CategoryDTO categoryDTO) {
        Category category = categoryDao.getById(categoryDTO.getId());
        if (category != null) {
            categoryDao.update(modelMapper.map(categoryDTO, Category.class));
            logger.info("Successfully updated category");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(CategoryDTO categoryDTO) {
        Category category = categoryDao.getById(categoryDTO.getId());
        if (category != null)
            categoryDao.delete(modelMapper.map(categoryDTO, Category.class));
        logger.info("Successfully deleted category");
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryDTO getById(Integer id) {
        Category category = categoryDao.getById(id);
        if (category != null) {
            return modelMapper.map(category, CategoryDTO.class);
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryDTO getByName(String name) {
        Category category = categoryDao.getByName(name);
        if (category != null) {
            return modelMapper.map(category, CategoryDTO.class);
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryDTO> getAll() {
        List<Category> categoryList = categoryDao.getAll();
        if (categoryList != null) {
            return categoryList.stream().map(category -> modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
        } else {
            return null;
        }
    }
    @Override
    public void checkCategoryByProduct(Integer id){
        List<Product> productList = productDao.getAll();
        for(int i = 0; i<productList.size();i++){
            if(productList.get(i).getCategory().getId().equals(id)){
                throw new CategoryUsedException("Category contains in some Products");
            }
        }
    }

    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
