package app.service.impl;

import app.dao.api.ProductDao;
import app.dao.api.SprinkleDao;
import app.dto.SprinkleDTO;
import app.entity.Product;
import app.entity.Sprinkle;
import app.exception.ObjectExistsException;
import app.exception.ObjectUsedException;
import app.service.api.SprinkleService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SprinkleServiceImpl implements SprinkleService {

    private static final Logger logger = Logger.getLogger(SprinkleServiceImpl.class);

    @Autowired
    private SprinkleDao sprinkleDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductDao productDao;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public SprinkleDTO create(SprinkleDTO sprinkleDTO) {
        if (sprinkleDTO != null) {
            Sprinkle sprinkleEx = sprinkleDao.getByName(sprinkleDTO.getName());
            if(sprinkleEx != null){
                throw new ObjectExistsException(String.format("Sprinkle with name %s already exists", sprinkleDTO.getName()));
            }
            Sprinkle sprinkle= modelMapper.map(sprinkleDTO, Sprinkle.class);
            sprinkleDao.create(sprinkle);
            sprinkleDTO.setId(sprinkle.getId());
            logger.info("Successfully saved sprinkle");
        }
        return sprinkleDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(SprinkleDTO sprinkleDTO) {
        Sprinkle sprinkle = sprinkleDao.getById(sprinkleDTO.getId());
        if (sprinkle != null)
            sprinkleDao.update(modelMapper.map(sprinkleDTO, Sprinkle.class));
        logger.info("Successfully updated sprinkle");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(SprinkleDTO sprinkleDTO) {
        if (sprinkleDTO != null)
            sprinkleDao.delete(modelMapper.map(sprinkleDTO, Sprinkle.class));
        logger.info("Successfully deleted sprinkle");
    }

    @Transactional(readOnly = true)
    @Override
    public SprinkleDTO getById(Integer id) {
        Sprinkle sprinkle = sprinkleDao.getById(id);
        if (sprinkle != null) {
            return modelMapper.map(sprinkle, SprinkleDTO.class);
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public SprinkleDTO getByName(String name) {
        Sprinkle sprinkle = sprinkleDao.getByName(name);
        if (sprinkle != null) {
            return modelMapper.map(sprinkle, SprinkleDTO.class);
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<SprinkleDTO> getAll() {
        List<Sprinkle> sprinkleList = sprinkleDao.getAll();
        if (sprinkleList != null) {
            return sprinkleList.stream().map(sprinkle -> modelMapper.map(sprinkle, SprinkleDTO.class)).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void checkSprinkleByProducts(Integer id) {
        List<Product> productList = productDao.getAll();
        for(int i = 0; i < productList.size();i++){
            for( int j = 0; j < productList.get(i).getSprinkleList().size();j++) {
                if (productList.get(i).getSprinkleList().get(j).getId().equals(id)) {
                    throw new ObjectUsedException("Some Product use this Sprinkle");
                }
            }
        }
    }

    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
