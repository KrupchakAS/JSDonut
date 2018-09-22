package app.dao.impl;

import app.dao.GenericDaoImpl;
import app.dao.api.OrderDao;
import app.entity.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {
    @Override
    public List<Order> getOrdersByUserId(Integer userId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> userRoot = criteriaQuery.from(Order.class);
        if (userId != null) {
            criteriaQuery.where(entityManager.getCriteriaBuilder().equal(userRoot.get("user"), userId));
        }
        List<Order> list = entityManager.createQuery(criteriaQuery).getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }
}
