package app.service.impl;

import app.dao.api.SprinkleDao;
import app.dto.SprinkleDTO;
import app.entity.Sprinkle;
import app.service.api.SprinkleService;
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
public class SprinkleServiceImpl implements SprinkleService {

    private static final Logger logger = LoggerFactory.getLogger(SprinkleServiceImpl.class);

    @Autowired
    private SprinkleDao sprinkleDao;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void create(SprinkleDTO sprinkleDTO) {
        if (sprinkleDTO != null)
            sprinkleDao.create(modelMapper.map(sprinkleDTO, Sprinkle.class));
        logger.debug(String.format("Successfully saved sprinkle"));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(SprinkleDTO sprinkleDTO) {
        Sprinkle sprinkle = sprinkleDao.getById(sprinkleDTO.getId());
        if (sprinkle != null)
            sprinkleDao.create(modelMapper.map(sprinkleDTO, Sprinkle.class));
        logger.debug(String.format("Successfully updated sprinkle"));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(SprinkleDTO sprinkleDTO) {
        if (sprinkleDTO != null)
            sprinkleDao.delete(modelMapper.map(sprinkleDTO, Sprinkle.class));
        logger.debug(String.format("Successfully deleted sprinkle"));
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
            return null;
        }
    }
}
