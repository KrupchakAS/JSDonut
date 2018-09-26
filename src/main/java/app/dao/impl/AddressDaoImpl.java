package app.dao.impl;

import app.dao.GenericDaoImpl;
import app.dao.api.AddressDao;
import app.entity.Address;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class AddressDaoImpl extends GenericDaoImpl<Address> implements AddressDao {
    @Override
    public List<Address> getAddressesByUserId(Integer userId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Address> criteriaQuery = criteriaBuilder.createQuery(Address.class);
        Root<Address> userRoot = criteriaQuery.from(Address.class);
        if (userId != null) {
            criteriaQuery.where(entityManager.getCriteriaBuilder().equal(userRoot.get("user"), userId));
        }
        List<Address> list = entityManager.createQuery(criteriaQuery).getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }
}
