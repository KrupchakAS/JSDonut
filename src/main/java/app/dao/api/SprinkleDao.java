package app.dao.api;

import app.dao.GenericDao;
import app.entity.Sprinkle;

public interface SprinkleDao extends GenericDao<Sprinkle> {
    Sprinkle getByName(String name);
}
