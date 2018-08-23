package app.service;


import app.dao.RoleDao;
import app.dao.UserDao;
import app.dto.RoleDTO;
import app.dto.UserDTO;
import app.entity.Role;
import app.entity.User;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(propagation = Propagation.REQUIRED)
    public void save(UserDTO userDto) {
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        Set<RoleDTO> roles = new HashSet<>();
        RoleDTO roleDto = new RoleDTO();
        roleDto.setId(2);
        roleDto.setName("ROLE_USER");
        roles.add(roleDto);
        roleDao.saveRole(modelMapper.map(roleDto, Role.class));
        userDto.setRoles(roles);
        userDao.saveUser(modelMapper.map(userDto, User.class));
        logger.debug(String.format("Successfully saved user %s", userDto.getLogin()));
    }

    @Override
    public void deleteUser(Integer id) {
        userDao.deleteUser(id);
    }


    @Transactional(readOnly = true)
    @Override
    public UserDTO findUserByLogin(String login) {
        User user = userDao.findUserByLogin(login);
        if (user != null) {
            return modelMapper.map(user, UserDTO.class);
        } else {
            return null;
        }
    }
    @Transactional(readOnly = true)
    @Override
    public UserDTO findUserByEmail(String email) {
        User user = userDao.findUserByEmail(email);
        if (user != null) {
            return modelMapper.map(user, UserDTO.class);
        } else {
            return null;
        }
    }
    @Transactional(readOnly = true)
    @Override
    public List<UserDTO> getUsersList() {
        List<User> list = userDao.getUsersList();
        if (list != null) {
            return list.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        }else {
            return null;
        }
    }
    @Transactional(readOnly = true)
    @Override
    public List<UserDTO> getUsersListByChars(String chars) {
        List<User> list = userDao.getUsersListByChars(chars);
        if (list != null) {
            return list.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        }else {
            return null;
        }

    }
}
