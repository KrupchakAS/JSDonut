package app.dao;

import app.dto.UserDTO;
import app.entity.User;
import java.util.List;

public interface UserDao {
    User findUserByEmail(String email);
    User findUserByLogin(String login);
    List<User> getUsersListByChars(String chars);
    List<User> getUsersList();
    void saveUser(User user);
    void deleteUser(Integer id);
}
