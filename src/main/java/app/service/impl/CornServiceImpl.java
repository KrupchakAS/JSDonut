package app.service.impl;


import app.dao.api.CornDao;
import app.dto.CornDTO;
import app.dto.ProductDTO;
import app.entity.Corn;
import app.entity.Product;
import app.service.api.CornSevice;
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
public class CornServiceImpl implements CornSevice {

    private static final Logger logger = LoggerFactory.getLogger(CornServiceImpl.class);

    @Autowired
    private CornDao cornDao;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void create(CornDTO cornDTO) {
        if (cornDTO != null)
            cornDao.create(modelMapper.map(cornDTO, Corn.class));
        logger.debug(String.format("Successfully saved corn"));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(CornDTO cornDTO) {
        Corn corn = cornDao.getById(cornDTO.getId());
        if (corn != null)
            cornDao.create(modelMapper.map(cornDTO, Corn.class));
        logger.debug(String.format("Successfully updated corn"));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(CornDTO cornDTO) {
        if (cornDTO != null)
            cornDao.delete(modelMapper.map(cornDTO, Corn.class));
        logger.debug(String.format("Successfully deleted corn"));
    }

    @Transactional(readOnly = true)
    @Override
    public CornDTO getById(Integer id) {
        Corn corn = cornDao.getById(id);
        if (corn != null) {
            return modelMapper.map(corn, CornDTO.class);
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public CornDTO getByName(String name) {
        Corn corn = cornDao.getByName(name);
        if (corn != null) {
            return modelMapper.map(corn, CornDTO.class);
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<CornDTO> getAll() {
        List<Corn> cornList = cornDao.getAll();
        if (cornList != null) {
            return cornList.stream().map(corn -> modelMapper.map(corn, CornDTO.class)).collect(Collectors.toList());
        } else {
            return null;
        }
    }
}
