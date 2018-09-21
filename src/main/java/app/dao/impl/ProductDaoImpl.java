package app.dao.impl;

import app.dao.GenericDaoImpl;
import app.dao.api.ProductDao;
import app.entity.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

@Repository
public class ProductDaoImpl extends GenericDaoImpl<Product> implements ProductDao {

    @Override
    public Product getByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);
        if (name != null) {
            criteriaQuery.where(entityManager.getCriteriaBuilder().equal(productRoot.get("name"), name));
        }
        List<Product> list = entityManager.createQuery(criteriaQuery).getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public List<Product> getAllByCategory(Integer categoryId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);
        if (categoryId != null) {
            criteriaQuery.where(entityManager.getCriteriaBuilder().equal(productRoot.get("category"), categoryId));
        }
        List<Product> list = entityManager.createQuery(criteriaQuery).getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }

    @Override
    public List<Product> getProductsByParameters(Integer categoryId, String productName, Integer minPrice, Integer maxPrice) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);
        if (categoryId != null || productName != null|| minPrice != null|| maxPrice != null) {
            criteriaQuery.where(criteriaBuilder.and(
                    criteriaBuilder.equal(productRoot.get("category"), categoryId),
                    criteriaBuilder.like(productRoot.get("name"), "%"+productName+"%"),
                    criteriaBuilder.gt(productRoot.get("price"), minPrice),
                    criteriaBuilder.le(productRoot.get("price"), maxPrice)));
        }
        List<Product> list = entityManager.createQuery(criteriaQuery).getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }
}
