package app.dao.impl;

import app.dao.GenericDaoImpl;
import app.dao.api.CornDao;
import app.entity.Corn;
import org.springframework.stereotype.Repository;

@Repository
public class CornDaoImpl extends GenericDaoImpl<Corn> implements CornDao {
}
