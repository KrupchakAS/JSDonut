package app.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class GenericDaoImpl<Entity> implements GenericDao<Entity> {

    private static final Logger logger = LoggerFactory.getLogger(GenericDaoImpl.class);

    @PersistenceContext
    protected EntityManager entityManager;

    protected final Class<Entity> daoClass;
    @SuppressWarnings("unchecked")
    public GenericDaoImpl() {
        daoClass = (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @Override
    @Transactional
    public void create(Entity entity) {
        entityManager.persist(entity);
    }

    @Override
    @Transactional
    public void update(Entity entity) {
        entityManager.merge(entity);
    }

    @Transactional
    @Override
    public void detach(Entity entity) {
        entityManager.detach(entity);
    }

    @Override
    @Transactional
    public void delete(Entity entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }
    @Transactional
    @Override
    public Entity getById(Integer id) {
        return entityManager.find(daoClass, id);
    }

    @Override
    @Transactional
    public Entity getByName(String name){
        return entityManager.find(daoClass,name);
    }

    @Transactional
    @Override
    public List<Entity> getAll() {
        CriteriaQuery<Entity> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(daoClass);
        criteriaQuery.from(daoClass);
        return findList(criteriaQuery);
    }
    @Transactional
    protected List<Entity> findList(CriteriaQuery<Entity> criteriaQuery) {
        TypedQuery<Entity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
