package app.dao.impl;

import app.dao.GenericDaoImpl;
import app.dao.api.FillingDao;
import app.entity.Filling;
import org.springframework.stereotype.Repository;

@Repository
public class FillingDaoImpl extends GenericDaoImpl<Filling> implements FillingDao {
}
