package app.dao.impl;

import app.dao.GenericDaoImpl;
import app.dao.api.OrderProductDao;
import app.entity.OrderProduct;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OrderProductDaoImpl extends GenericDaoImpl<OrderProduct> implements OrderProductDao {

    @Override
    public List<OrderProduct> getOrderProductsByOrderId(Integer orderId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrderProduct> criteriaQuery = criteriaBuilder.createQuery(OrderProduct.class);
        Root<OrderProduct> orderRoot = criteriaQuery.from(OrderProduct.class);
        if (orderId != null) {
            criteriaQuery.where(entityManager.getCriteriaBuilder().equal(orderRoot.get("order"), orderId));
        }
        List<OrderProduct> list = entityManager.createQuery(criteriaQuery).getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }
}
