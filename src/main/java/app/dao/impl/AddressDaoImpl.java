package app.dao.impl;

import app.dao.GenericDaoImpl;
import app.dao.api.AddressDao;
import app.entity.Address;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDaoImpl extends GenericDaoImpl<Address> implements AddressDao {
}
