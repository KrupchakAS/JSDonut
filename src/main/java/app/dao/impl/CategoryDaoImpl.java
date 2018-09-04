package app.dao.impl;

import app.dao.GenericDaoImpl;
import app.dao.api.CategoryDao;
import app.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl extends GenericDaoImpl<Category> implements CategoryDao {
}
