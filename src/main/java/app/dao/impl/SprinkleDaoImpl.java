package app.dao.impl;

import app.dao.GenericDaoImpl;
import app.dao.api.SprinkleDao;
import app.entity.Sprinkle;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class SprinkleDaoImpl extends GenericDaoImpl<Sprinkle> implements SprinkleDao {

    @Override
    public Sprinkle getByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Sprinkle> criteriaQuery = criteriaBuilder.createQuery(Sprinkle.class);
        Root<Sprinkle> sprinkleRoot = criteriaQuery.from(Sprinkle.class);
        if (name != null) {
            criteriaQuery.where(entityManager.getCriteriaBuilder().equal(sprinkleRoot.get("name"), name));
        }
        List<Sprinkle> list = entityManager.createQuery(criteriaQuery).getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }
}
