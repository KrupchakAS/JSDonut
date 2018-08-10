package app.dao;

import app.entity.Role;
import app.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository

public class RoleDaoImpl implements RoleDao{

    @Autowired
    private SessionFactory sessionFactory;


    public void saveRole(Role role) {
        sessionFactory.getCurrentSession().save(role);
    }


    public Role getRole(User user) {
        return null;
    }
}
