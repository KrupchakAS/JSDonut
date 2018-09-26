package app.dao.api;

import app.dao.GenericDao;
import app.entity.Address;

import java.util.List;

public interface AddressDao extends GenericDao<Address>{

    List<Address> getAddressesByUserId (Integer id);

}
