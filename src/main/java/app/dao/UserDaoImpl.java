package app.dao;

import app.entity.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    @Autowired
    private  SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public User findByUsername(String username) {
        List<User> users = sessionFactory.getCurrentSession()
                .createQuery("from User where username=?")
                .setParameter(0, username)
                .list();
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsersList() {
       return sessionFactory.getCurrentSession().createCriteria(User.class).list();
    }


    public void saveUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void deleteUser(Integer id) {
        Query query = sessionFactory.getCurrentSession().createQuery("delete from User where id = :i");
        query.setParameter("i", id);
        query.executeUpdate();
        logger.debug(String.format("Successfully deleted user "));
    }


}
