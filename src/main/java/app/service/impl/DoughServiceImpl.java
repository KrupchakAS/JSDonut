package app.service.impl;


import app.dao.api.DoughDao;
import app.dao.api.ProductDao;
import app.dto.DoughDTO;
import app.entity.Dough;
import app.entity.Product;
import app.exception.ObjectUsedException;
import app.exception.ObjectExistsException;
import app.service.api.DoughService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoughServiceImpl implements DoughService {

    private static final Logger logger = Logger.getLogger(DoughServiceImpl.class);

    @Autowired
    private DoughDao doughDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductDao productDao;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public DoughDTO create(DoughDTO doughDTO) {
        if (doughDTO != null) {
            Dough existDough = doughDao.getByName(doughDTO.getName());
            if (existDough != null) {
                throw new ObjectExistsException(String.format("Dough with name %s already exists", doughDTO.getName()));
            }
            Dough dough = modelMapper.map(doughDTO, Dough.class);
            doughDao.create(dough);
            doughDTO.setId(dough.getId());
            logger.info("Successfully saved dough");
        }
        return doughDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(DoughDTO doughDTO) {
        Dough corn = doughDao.getById(doughDTO.getId());
        if (corn != null)
            doughDao.update(modelMapper.map(doughDTO, Dough.class));
        logger.info("Successfully updated dough");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(DoughDTO doughDTO) {
        if (doughDTO != null)
            doughDao.delete(modelMapper.map(doughDTO, Dough.class));
        logger.info("Successfully deleted dough");
    }

    @Transactional(readOnly = true)
    @Override
    public DoughDTO getById(Integer id) {
        Dough dough = doughDao.getById(id);
        if (dough != null) {
            return modelMapper.map(dough, DoughDTO.class);
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public DoughDTO getByName(String name) {
        Dough dough = doughDao.getByName(name);
        if (dough != null) {
            return modelMapper.map(dough, DoughDTO.class);
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<DoughDTO> getAll() {
        List<Dough> cornList = doughDao.getAll();
        if (cornList != null) {
            return cornList.stream().map(corn -> modelMapper.map(corn, DoughDTO.class)).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    @Override
    public void checkDoughByProducts(Integer id) {
        List<Product> productList = productDao.getAll();
        for(int i = 0; i < productList.size();i++){
            if(productList.get(i).getDough().getId().equals(id)){
                throw new ObjectUsedException("Some Product use this Dough");
            }
        }
    }

    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
