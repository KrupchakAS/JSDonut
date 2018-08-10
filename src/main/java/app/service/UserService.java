package app.service;

import app.entity.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);
    List<User> getUsersList();

    List<User> getUsersList(String username);

    void save(User user);
    void deleteUser(Integer id);
}
