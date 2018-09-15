package app.dao.api;

import app.dao.GenericDao;
import app.entity.Product;

public interface ProductDao extends GenericDao<Product>{
    Product getByName(String name);
}
