package app.dao.impl;

import app.dao.GenericDaoImpl;
import app.dao.api.ProductDao;
import app.entity.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.ArrayList;
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

        List<Predicate> params = new ArrayList<>();
        if (categoryId != null) {
            params.add(criteriaBuilder.equal(productRoot.get("category"), categoryId));
        }
        if (productName != null) {
            params.add(criteriaBuilder.like(productRoot.get("name"), "%"+productName+"%"));
        }
        if (minPrice != null) {
            params.add(criteriaBuilder.gt(productRoot.get("price"), minPrice));
        }
        if (maxPrice != null) {
            params.add(criteriaBuilder.le(productRoot.get("price"), maxPrice));
        }

        criteriaQuery.where(params.toArray(new Predicate[]{}));

        List<Product> list = entityManager.createQuery(criteriaQuery).getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }
}
