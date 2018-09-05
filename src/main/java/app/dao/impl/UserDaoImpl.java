package app.dao.impl;

import app.dao.GenericDaoImpl;
import app.dao.api.UserDao;
import app.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public User getByLogin(String login) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        if (login != null) {
            //criteriaQuery.select(userRoot.get("login"));
            criteriaQuery.where(entityManager.getCriteriaBuilder().equal(userRoot.get("login"),login));
        }
        List<User> list = entityManager.createQuery(criteriaQuery).getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public User getByEmail(String email) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);

        if (email != null) {
            //criteriaQuery.select(userRoot.get("email"));
            criteriaQuery.where(entityManager.getCriteriaBuilder().equal(userRoot.get("email"),email));
        }
        List<User> list = entityManager.createQuery(criteriaQuery).getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }

    }
}
