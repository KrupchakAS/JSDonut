package app.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;


public abstract class GenericDaoImpl<Entity> implements GenericDao<Entity> {

    @PersistenceContext
    protected EntityManager entityManager;

    protected final Class<Entity> daoClass;
    @SuppressWarnings("unchecked")
    public GenericDaoImpl() {
        daoClass = (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];}

    @Override
    public void create(Entity entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Entity entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Entity entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity)); }

    @Override
    public Entity getById(Integer id) {
        return entityManager.find(daoClass, id);
    }

    @Override
    public List<Entity> getAll() {
        CriteriaQuery<Entity> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(daoClass);
        criteriaQuery.from(daoClass);
        return findList(criteriaQuery);
    }

    protected List<Entity> findList(CriteriaQuery<Entity> criteriaQuery) {
        TypedQuery<Entity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
