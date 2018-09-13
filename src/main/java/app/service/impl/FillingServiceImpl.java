package app.service.impl;

import app.dao.api.FillingDao;
import app.dto.FillingDTO;
import app.entity.Filling;
import app.service.api.FillingService;
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
public class FillingServiceImpl implements FillingService {

    private static final Logger logger = LoggerFactory.getLogger(FillingServiceImpl.class);

    @Autowired
    private FillingDao fillingDao;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public FillingDTO create(FillingDTO fillingDTO) {
        if (fillingDTO != null) {
            Filling filling = modelMapper.map(fillingDTO, Filling.class);
            fillingDao.create(filling);
            fillingDTO.setId(filling.getId());
            logger.debug(String.format("Successfully saved filling"));
        }
        return fillingDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(FillingDTO fillingDTO) {
        Filling filling = fillingDao.getById(fillingDTO.getId());
        if (filling != null)
            fillingDao.update(modelMapper.map(fillingDTO, Filling.class));
        logger.debug(String.format("Successfully updated filling"));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(FillingDTO fillingDTO) {
        if (fillingDTO != null)
            fillingDao.delete(modelMapper.map(fillingDTO, Filling.class));
        logger.debug(String.format("Successfully deleted filling"));
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

    @Transactional(readOnly = true)
    @Override
    public FillingDTO getLastFilling() {
        List<Filling> fillingList = fillingDao.getAll();
        FillingDTO fillingDTO = modelMapper.map(fillingList.get(fillingList.size()-1),FillingDTO.class);
        if(fillingDTO != null){
            return fillingDTO;
        }else {
            return null;
        }
    }
}
