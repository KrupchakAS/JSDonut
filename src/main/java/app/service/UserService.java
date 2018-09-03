package app.service;

import app.dto.UserDTO;
import app.entity.User;

import javax.persistence.Id;
import java.util.List;

public interface UserService {
    void create(UserDTO entity);

    void update(UserDTO entity);

    void delete(UserDTO entity);

    UserDTO getById(Integer key);

    UserDTO getByName(String Name);

    void detach(UserDTO entity);

    List<UserDTO> getAll();

    UserDTO findUserByLogin(String login);

    UserDTO findUserByEmail(String email);
}
