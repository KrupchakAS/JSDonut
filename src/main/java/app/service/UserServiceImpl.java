package app.service;


import app.dao.RoleDao;
import app.dao.UserDao;
import app.entity.Role;
import app.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<Role>();
        Role role = new Role();
        role.setId(2);
        role.setName("ROLE_USER");
        roleDao.saveRole(role);
        roles.add(role);
        user.setRoles(roles);
        userDao.saveUser(user);
        logger.debug(String.format("Successfully saved user %s", user.getUsername()));
    }

    @Override
    public void deleteUser(Integer id) {
        userDao.deleteUser(id);
    }

    @Override
    public User findByUsername(String username) {

        return userDao.findByUsername(username);
    }

    @Override
    public List<User> getUsersList() {
        return userDao.getUsersList();
    }

    @Override
    public List<User> getUsersList(String username) {
        List<User> list1 = userDao.getUsersList();
        List<User> list =new ArrayList<>();
        for(User us:list1){
            if (us.getUsername().toLowerCase().contains(username.toLowerCase())){
                list.add(us);
            }
        }
        return list;
    }
}
