package app.service.api;

import app.dto.UserDTO;
import app.entity.User;

import javax.persistence.Id;
import java.util.List;

public interface UserService {
    void create(UserDTO entity);

    void updatePassword(UserDTO entity);

    void delete(UserDTO entity);

    UserDTO getById(Integer id);

    List<UserDTO> getAll();

    UserDTO getByLogin(String login);

    UserDTO getByEmail(String email);

    void updateInfo(UserDTO entity);

    void checkUserFields(UserDTO userDTO);
}
