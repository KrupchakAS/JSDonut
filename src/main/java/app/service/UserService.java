package app.service;

import app.dto.UserDTO;
import app.entity.User;

import java.util.List;

public interface UserService {
    UserDTO findUserByLogin(String login);
    UserDTO findUserByEmail(String email);
    List<UserDTO> getUsersList();
    List<UserDTO> getUsersListByChars(String username);
    void save(UserDTO userDTO);
    void deleteUser(Integer id);
}
