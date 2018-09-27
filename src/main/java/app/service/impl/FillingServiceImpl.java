package app.service.impl;

import app.dao.api.FillingDao;
import app.dto.FillingDTO;
import app.entity.Filling;
import app.exception.ObjectExistsException;
import app.service.api.FillingService;
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
public class FillingServiceImpl implements FillingService {

    private static final Logger logger = LogManager.getLogger(FillingServiceImpl.class);

    @Autowired
    private FillingDao fillingDao;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public FillingDTO create(FillingDTO fillingDTO) {
        if (fillingDTO != null) {
            Filling fillingEx = fillingDao.getByName(fillingDTO.getName());
            if (fillingEx != null) {
                throw new ObjectExistsException(String.format("Filling with name %s already exists", fillingDTO.getName()));
            }
            Filling filling = modelMapper.map(fillingDTO, Filling.class);
            fillingDao.create(filling);
            fillingDTO.setId(filling.getId());
            logger.info("Successfully saved filling");
        }
        return fillingDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(FillingDTO fillingDTO) {
        Filling filling = fillingDao.getById(fillingDTO.getId());
        if (filling != null)
            fillingDao.update(modelMapper.map(fillingDTO, Filling.class));
        logger.info("Successfully updated filling");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(FillingDTO fillingDTO) {
        if (fillingDTO != null)
            fillingDao.delete(modelMapper.map(fillingDTO, Filling.class));
        logger.info("Successfully deleted filling");
    }

    @Transactional(readOnly = true)
    @Override
    public FillingDTO getById(Integer id) {
        Filling filling = fillingDao.getById(id);
        if (filling != null) {
            return modelMapper.map(filling, FillingDTO.class);
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public FillingDTO getByName(String name) {
        Filling filling = fillingDao.getByName(name);
        if (filling != null) {
            return modelMapper.map(filling, FillingDTO.class);
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<FillingDTO> getAll() {
        List<Filling> fillingList = fillingDao.getAll();
        if (fillingList != null) {
            return fillingList.stream().map(filling -> modelMapper.map(filling, FillingDTO.class)).collect(Collectors.toList());
        } else {
            return null;
        }
    }

}
