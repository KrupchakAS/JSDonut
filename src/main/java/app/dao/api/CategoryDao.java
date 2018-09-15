package app.dao.api;

import app.dao.GenericDao;
import app.entity.Category;

public interface CategoryDao extends GenericDao<Category> {

    Category getByName(String name);
}
