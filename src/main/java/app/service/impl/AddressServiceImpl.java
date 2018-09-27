package app.service.impl;

import app.dao.api.AddressDao;
import app.dto.AddressDTO;
import app.entity.Address;
import app.service.api.AddressService;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    private static final Logger logger = LogManager.getLogger(AddressServiceImpl.class);

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void create(AddressDTO addressDTO) {
        if (addressDTO != null)
            addressDao.create(modelMapper.map(addressDTO, Address.class));
        logger.info("Successfully saved address");

    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(AddressDTO addressDTO) {
        Address address = addressDao.getById(addressDTO.getId());
        if (address != null)
            addressDao.update(modelMapper.map(addressDTO,Address.class));
        logger.info("Successfully updated address");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(AddressDTO addressDTO) {
        if (addressDTO != null)
            addressDao.delete(modelMapper.map(addressDTO, Address.class));
        logger.info("Successfully deleted address");
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



    @Transactional(readOnly = true)
    @Override
    public List<AddressDTO> getAll() {
        List<Address> addressList = addressDao.getAll();
        if (addressList != null) {
            return addressList.stream().map(address -> modelMapper.map(address, AddressDTO.class)).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<AddressDTO> getAddressesByUserId(Integer id) {
        List<Address> addressList = addressDao.getAddressesByUserId(id);
        if (addressList != null) {
            return addressList.stream().map(address -> modelMapper.map(address, AddressDTO.class)).collect(Collectors.toList());
        }
        return null;
    }
}
