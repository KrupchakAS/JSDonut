package app.service.impl;

import app.dao.api.ProductDao;
import app.dto.ProductDTO;
import app.entity.Product;
import app.service.api.ProductService;
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
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ProductDTO create(ProductDTO productDTO) {
        if (productDTO != null) {
            Product product = modelMapper.map(productDTO, Product.class);
            productDao.create(product);
            productDTO.setId(product.getId());
            logger.debug(String.format("Successfully saved product"));
        }
        return productDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(ProductDTO productDTO) {
        Product product = productDao.getById(productDTO.getId());
        if (product != null)
            productDao.update(modelMapper.map(productDTO, Product.class));
        logger.debug(String.format("Successfully updated product"));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(ProductDTO productDTO) {
        if (productDTO != null)
            productDao.delete(modelMapper.map(productDTO, Product.class));
        logger.debug(String.format("Successfully deleted product"));
    }

    @Transactional(readOnly = true)
    @Override
    public ProductDTO getById(Integer id) {
        Product product = productDao.getById(id);
        if (product != null) {
            return modelMapper.map(product, ProductDTO.class);
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public ProductDTO getByName(String name) {
        Product product = productDao.getByName(name);
        if (product != null) {
            return modelMapper.map(product, ProductDTO.class);
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductDTO> getAll() {
        List<Product> productList = productDao.getAll();
        if (productList != null){
            return productList.stream().map(product -> modelMapper.map(product,ProductDTO.class)).collect(Collectors.toList());
        }
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public ProductDTO getLastProduct() {
        List<Product> productList = productDao.getAll();
        ProductDTO productDTO = modelMapper.map(productList.get(productList.size()-1),ProductDTO.class);
        if(productDTO != null){
            return productDTO;
        }else {
            return null;
        }
    }
}
