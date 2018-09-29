package app.dao.impl;

import app.dao.GenericDaoImpl;
import app.dao.api.OrderDao;
import app.entity.Order;
import app.entity.Product;
import app.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
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
        Root<Order> orderRoot = criteriaQuery.from(Order.class);
        if (userId != null) {
            criteriaQuery.where(entityManager.getCriteriaBuilder().equal(orderRoot.get("user"), userId));
        }
        List<Order> list = entityManager.createQuery(criteriaQuery).getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Order> getOrdersForMonth() {
        TypedQuery<Order> q = entityManager.createQuery("from Order WHERE month(purchaseDate) = month(now())-1",Order.class);
//        Select * from table where date between '01-01-2014' and '14-01-2014' - Between(example)
        List<Order> list = q.getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Order> getOrdersForWeek() {
        TypedQuery<Order> q = entityManager.createQuery("from Order WHERE WEEK (purchaseDate) = WEEK( current_date ) - 1 AND YEAR( purchaseDate) = YEAR( current_date )", Order.class);
        List<Order> list = q.getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }
}
