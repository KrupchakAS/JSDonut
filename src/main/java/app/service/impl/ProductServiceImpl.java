package app.service.impl;

import app.dao.api.ProductDao;
import app.dto.ProductDTO;
import app.entity.Product;
import app.exception.MinLengthFieldException;
import app.exception.MinValueException;
import app.exception.ObjectExistsException;
import app.message.MessageSender;
import app.service.api.ProductService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSender messageSender;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ProductDTO create(ProductDTO productDTO) {
        if (productDTO != null) {
            Product productex = productDao.getByName(productDTO.getName());
            if (productex != null) {
                throw new ObjectExistsException(String.format("Product with name %s already exists", productDTO.getName()));
            }
            Product product = modelMapper.map(productDTO, Product.class);
            productDao.create(product);
            productDTO.setId(product.getId());
            logger.info("Successfully saved product");
            messageSender.sendMessage("Update");
        }
        return productDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(ProductDTO productDTO) {
        Product product = productDao.getById(productDTO.getId());
        if (product != null)
            productDao.update(modelMapper.map(productDTO, Product.class));
        logger.info("Successfully updated product");
        messageSender.sendMessage("Update");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(ProductDTO productDTO) {
        if (productDTO != null)
            productDao.delete(modelMapper.map(productDTO, Product.class));

        logger.info("Successfully deleted product");
        messageSender.sendMessage("Update");
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
        if (productList != null) {
            return productList.stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
        }
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductDTO> getAllByCategory(Integer categoryId) {
        List<Product> productList = productDao.getAllByCategory(categoryId);
        if (productList != null) {
            return productList.stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
        }
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductDTO> getProductsByParameters(Integer categoryId, Integer fillingId, Integer doughId, List<Integer> sprinkleIdList, String productsName, Integer minPrice, Integer maxPrice) {
        List<Product> productList = productDao.getProductsByParameters(categoryId, fillingId, doughId, sprinkleIdList, productsName, minPrice, maxPrice);
        if (productList != null) {
            return productList.stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
        }
        return null;
    }

    private Product selectForUpdateProduct(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Product ID cannot be null!");
        }
        Product product = productDao.selectForUpdate(id);
        if (product == null) {
            throw new ObjectExistsException(String.format("Cannot find object by ID='%d'", id));
        }
        return product;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void byProduct(ProductDTO productDTO) {
        Product product = selectForUpdateProduct(productDTO.getId());
        if (product.getQuantity() < productDTO.getQuantity()) {
            String text = String.format("Cannot buy product '%s'. There are not enough quantity in the shop.", product.getName());
            throw new IllegalArgumentException(text);
        }
        product.setQuantity((short) (product.getQuantity() - productDTO.getQuantity()));
        productDao.update(product);
    }

    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public void checkProductFields(ProductDTO productDTO) {
        if (productDTO.getName().length() < 1 || productDTO.getDescription().length() < 1
                || productDTO.getCalories() == null || productDTO.getQuantity() == null || productDTO.getPrice() == null
                ||  productDTO.getWeight() == null) {
            throw new MinLengthFieldException(" Field can not be empty");
        } else if (productDTO.getCategory().getId() == null) {
            throw new MinLengthFieldException("Field Category can not be empty");
        } else if (productDTO.getDough().getId() == null) {
            throw new MinLengthFieldException("Field Dough can not be empty");
        } else if (productDTO.getPrice() < 10) {
            throw new MinValueException(" Price can not be less than 10P");
        } else if (productDTO.getCalories() < 50) {
            throw new MinValueException("Calories can not be less than 50 ");
        } else if (productDTO.getWeight() < 15) {
            throw new MinValueException("Weight can not be less than 15 ");
        } else if (productDTO.getQuantity() < 1) {
            throw new MinValueException("Quantity can not be less than 1 ");
        }
    }
}
