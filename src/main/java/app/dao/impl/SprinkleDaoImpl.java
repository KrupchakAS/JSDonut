package app.dao.impl;

import app.dao.GenericDaoImpl;
import app.dao.api.SprinkleDao;
import app.entity.Sprinkle;
import org.springframework.stereotype.Repository;

@Repository
public class SprinkleDaoImpl extends GenericDaoImpl<Sprinkle> implements SprinkleDao {
}
