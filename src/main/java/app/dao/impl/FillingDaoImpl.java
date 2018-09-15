package app.dao.impl;

import app.dao.GenericDaoImpl;
import app.dao.api.FillingDao;
import app.entity.Filling;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class FillingDaoImpl extends GenericDaoImpl<Filling> implements FillingDao {

    @Override
    public Filling getByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Filling> criteriaQuery = criteriaBuilder.createQuery(Filling.class);
        Root<Filling> fillingRoot = criteriaQuery.from(Filling.class);
        if (name != null) {
            criteriaQuery.where(entityManager.getCriteriaBuilder().equal(fillingRoot.get("name"), name));
        }
        List<Filling> list = entityManager.createQuery(criteriaQuery).getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }
}
