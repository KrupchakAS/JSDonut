package app.service;

import app.entity.User;

import java.util.List;

public interface UserService {
    User findUserByLogin(String login);
    User findUserByEmail(String email);
    List<User> getUsersList();
    List<User> getUsersList(String username);
    void save(User user);
    void deleteUser(Integer id);
}
