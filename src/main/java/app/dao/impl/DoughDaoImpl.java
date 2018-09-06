package app.dao.impl;

import app.dao.GenericDaoImpl;
import app.dao.api.DoughDao;
import app.entity.Dough;
import org.springframework.stereotype.Repository;

@Repository
public class DoughDaoImpl extends GenericDaoImpl<Dough> implements DoughDao {
}
