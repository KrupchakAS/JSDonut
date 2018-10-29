package app.service.impl;

import app.dao.api.OrderDao;
import app.dao.api.ProductDao;
import app.dto.FilterDTO;
import app.dto.ProductDTO;
import app.entity.Order;
import app.entity.Product;
import app.exception.MinLengthFieldException;
import app.exception.MinValueException;
import app.exception.ObjectExistsException;
import app.exception.ObjectUsedException;
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

    @Autowired
    private OrderDao orderDao;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ProductDTO create(ProductDTO productDTO) {
        if (productDTO != null) {
            Product productEx = productDao.getByName(productDTO.getName());
            if (productEx != null) {
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
    public List<ProductDTO> getProductsByParameters(FilterDTO filterDTO) {
        List<Product> productList = productDao.getProductsByParameters(filterDTO);
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
    public Boolean byProduct(ProductDTO productDTO) {
        Product product = selectForUpdateProduct(productDTO.getId());
        if (product.getQuantity() < productDTO.getQuantity()) {
            String text = String.format("Cannot buy product '%s'. There are not enough quantity in the shop.", product.getName());
            throw new IllegalArgumentException(text);
        }
        product.setQuantity((short) (product.getQuantity() - productDTO.getQuantity()));
        productDao.update(product);
        return true;
    }

    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public void checkProductFields(ProductDTO productDTO) {
        if (productDTO.getName().length() < 1){
            throw new MinLengthFieldException("Field Name can not be empty");
        }
        if(productDTO.getDescription().length() < 1){
            throw new MinLengthFieldException("Field Description can not be empty");
        }
        if(productDTO.getCalories() == null){
            throw new MinLengthFieldException("Field Calories can not be empty");
        }
        if(productDTO.getQuantity() == null){
            throw new MinLengthFieldException("Field Quantity can not be empty");
        }
        if(productDTO.getPrice() == null){
            throw new MinLengthFieldException("Field Price can not be empty");
        }
        if(productDTO.getWeight() == null) {
            throw new MinLengthFieldException("Field Weight can not be empty");
        }
        if (productDTO.getCategory().getId() == null || productDTO.getCategory().getId() == 0) {
            throw new MinLengthFieldException("Field Category can not be empty");
        }
        if (productDTO.getDough().getId() == null || productDTO.getDough().getId() == 0) {
            throw new MinLengthFieldException("Field Dough can not be empty");
        }
        if (productDTO.getPrice() < 10) {
            throw new MinValueException(" Price can not be less than 10P");
        }
        if (productDTO.getCalories() < 50) {
            throw new MinValueException("Calories can not be less than 50 ");
        }
        if (productDTO.getWeight() < 15) {
             throw new MinValueException("Weight can not be less than 15 ");
        }
        if (productDTO.getQuantity() < 1) {
            throw new MinValueException("Quantity can not be less than 1 ");
        }
    }

    @Override
    public void checkProductByUsed(Integer id) {
        List<Order> orderList = orderDao.getAll();
        for(int i = 0; i < orderList.size();i++){
            for(int j = 0; j < orderList.get(i).getProductList().size();j++){
                if(orderList.get(i).getProductList().get(j).getId().equals(id)){
                    throw new ObjectUsedException("Product is in use, it is impossible to delete.");
                }
            }
        }
    }
}
