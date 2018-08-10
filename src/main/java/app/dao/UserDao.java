package app.dao;

import app.entity.User;
import java.util.List;

public interface UserDao {
    User findByUsername(String username);

    List<User> getUsersList();

    void saveUser(User user);
    void deleteUser(Integer id);
}
