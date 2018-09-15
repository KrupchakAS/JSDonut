package app.service.impl;

import app.dao.api.AddressDao;
import app.dto.AddressDTO;
import app.entity.Address;
import app.service.api.AddressService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void create(AddressDTO addressDTO) {
        if (addressDTO != null)
            addressDao.create(modelMapper.map(addressDTO, Address.class));
        logger.debug(String.format("Successfully saved address"));

    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(AddressDTO addressDTO) {
        Address address = addressDao.getById(addressDTO.getId());
        if (address != null)
            addressDao.update(modelMapper.map(addressDTO,Address.class));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(AddressDTO addressDTO) {
        if (addressDTO != null)
            addressDao.delete(modelMapper.map(addressDTO, Address.class));
    }

    @Transactional(readOnly = true)
    @Override
    public AddressDTO getById(Integer id) {
        Address address = addressDao.getById(id);
        if (address != null) {
            return modelMapper.map(address, AddressDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public void detach(AddressDTO addressDTO) {

    }

    @Transactional(readOnly = true)
    @Override
    public List<AddressDTO> getAll() {
        List<Address> addressList = addressDao.getAll();
        if (addressList != null) {
            return addressList.stream().map(address -> modelMapper.map(address, AddressDTO.class)).collect(Collectors.toList());
        }
        return null;
    }
}
