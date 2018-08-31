package app.dao;

import app.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public User findUserByEmail(String email) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("email",email)).list();
        List<User> list = criteria.list();
        if(list.isEmpty()){
            return null;
        }else {
            return list.get(0);
        }

    }

    @SuppressWarnings("unchecked")
    public User findUserByLogin(String login) {
         Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
                criteria.add(Restrictions.eq("login",login)).list();
                List<User> list = criteria.list();
         if(list.isEmpty()){
             return null;
         }else {
             return list.get(0);
         }
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<User> getUsersListByChars(String chars) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class)
                .add(Restrictions.like("login","%"+chars+"%"));
        return criteria.list();
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
        Query query = sessionFactory.getCurrentSession().createQuery("delete from User where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        logger.debug(String.format("Successfully deleted user "));
    }


}
