package app.dao.impl;

import app.dao.GenericDaoImpl;
import app.dao.api.ProductDao;
import app.dto.FilterDTO;
import app.entity.Product;
import app.entity.Sprinkle;
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
    public List<Product> getProductsByParameters(FilterDTO filterDTO) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);
        List<Predicate> params = new ArrayList<>();
        if (filterDTO.getSprinkleIdList() != null && filterDTO.getSprinkleIdList().size() > 0) {
            Join<Product, Sprinkle> sprinkleJoin = productRoot.join("sprinkleList", JoinType.INNER);
            sprinkleJoin.on(sprinkleJoin.get("id").in(filterDTO.getSprinkleIdList().toArray()));
        }
        if (filterDTO.getCategoryId() != null && filterDTO.getCategoryId() != 0) {
            params.add(criteriaBuilder.equal(productRoot.get("category"), filterDTO.getCategoryId()));
        }
        if (filterDTO.getFillingId() != null && filterDTO.getFillingId() !=0) {
            params.add(criteriaBuilder.equal(productRoot.get("filling"), filterDTO.getFillingId()));
        }
        if (filterDTO.getDoughId() != null && filterDTO.getDoughId() != 0) {
            params.add(criteriaBuilder.equal(productRoot.get("dough"), filterDTO.getDoughId()));
        }
        if (filterDTO.getProductName() != null) {
            params.add(criteriaBuilder.like(productRoot.get("name"), "%"+filterDTO.getProductName()+"%"));
        }
        if (filterDTO.getMinPrice() != null) {
            params.add(criteriaBuilder.gt(productRoot.get("price"), filterDTO.getMinPrice()));
        }
        if (filterDTO.getMaxPrice() != null) {
            params.add(criteriaBuilder.le(productRoot.get("price"), filterDTO.getMaxPrice()));
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
