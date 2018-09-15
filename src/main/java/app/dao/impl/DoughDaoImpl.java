package app.dao.impl;

import app.dao.GenericDaoImpl;
import app.dao.api.DoughDao;
import app.entity.Dough;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class DoughDaoImpl extends GenericDaoImpl<Dough> implements DoughDao {

    @Override
    public Dough getByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Dough> criteriaQuery = criteriaBuilder.createQuery(Dough.class);
        Root<Dough> doughRoot = criteriaQuery.from(Dough.class);
        if (name != null) {
            criteriaQuery.where(entityManager.getCriteriaBuilder().equal(doughRoot.get("name"), name));
        }
        List<Dough> list = entityManager.createQuery(criteriaQuery).getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }
}
