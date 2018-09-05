package app.dao;

import java.util.List;


public interface GenericDao<Entity> {
    void create(Entity entity);

    void update(Entity entity);

    void delete(Entity entity);

    Entity getById(Integer id);

    Entity getByName(String Name);



    List<Entity> getAll();

}
