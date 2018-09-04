package app.service.impl;

import app.dao.api.CategoryDao;
import app.dto.CategoryDTO;
import app.entity.Category;
import app.service.api.CategoryService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void create(CategoryDTO categoryDTO) {
        if (categoryDTO != null)
            categoryDao.create(modelMapper.map(categoryDTO, Category.class));
        logger.debug(String.format("Successfully saved category"));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(CategoryDTO categoryDTO) {
        Category category = categoryDao.getById(categoryDTO.getId());
        if (category != null) {
            categoryDao.update(modelMapper.map(categoryDTO, Category.class));
            logger.debug(String.format("Successfully updated category"));
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(CategoryDTO categoryDTO) {
        Category category = categoryDao.getById(categoryDTO.getId());
        if (category != null)
            categoryDao.delete(modelMapper.map(categoryDTO, Category.class));
        logger.debug(String.format("Successfully deleted category"));
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
}
