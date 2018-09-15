package app.dao.api;

import app.dao.GenericDao;
import app.entity.Dough;

public interface DoughDao extends GenericDao<Dough> {

    Dough getByName(String name);
}
