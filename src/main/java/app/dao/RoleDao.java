package app.dao;


import app.entity.Role;
import app.entity.User;

public interface RoleDao {
    public void saveRole(Role role);
    public Role getRole(User user);
}
