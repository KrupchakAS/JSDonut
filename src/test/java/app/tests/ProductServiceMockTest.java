package app.tests;

import app.dao.api.ProductDao;
import app.dto.FilterDTO;
import app.dto.ProductDTO;
import app.entity.*;
import app.entity.enums.DeliveryOption;
import app.entity.enums.OrderStatus;
import app.entity.enums.PaymentOption;
import app.entity.enums.PaymentStatus;
import app.exception.MinLengthFieldException;
import app.exception.ObjectExistsException;
import app.service.api.*;
import app.service.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.*;
import static org.junit.Assert.*;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceMockTest {

    @Mock
    private ProductDao productDao;
    @InjectMocks
    private ProductServiceImpl productService;
    @InjectMocks
    private ModelMapper modelMapper;


    private Dough dough;
    private Filling filling;
    private List<Sprinkle> sprinkleList;
    private Category category1;
    private Category category2;
    private List<Order> orderList;
    private User user1;
    private User user2;
    private Address address;
    private Product product;

    @Before
    public void setup() {
        productService.setModelMapper(modelMapper);

        user1 = new User();
        user1.setId(1);
        user1.setLogin("Test1");
        user1.setFirstName("TestFirstName1");
        user1.setSurName("TestSurName1");
        user1.setBirthDate(new Date());

        user2 = new User();
        user2.setId(2);
        user2.setLogin("Test2");
        user2.setFirstName("TestFirstName2");
        user2.setSurName("TestSurName2");
        user2.setBirthDate(new Date());

        address = new Address();
        address.setId(1);
        address.setCity("Spb");
        address.setStreet("Buharestskay");
        address.setHouseNumber((short) 100);
        address.setApartmentNumber((short) 24);
        address.setPostCode("100000");

        dough = new Dough();
        dough.setId(1);
        dough.setName("SweetDough");
        dough.setCalories((short) 300);
        dough.setPrice((float) 90);

        filling = new Filling();
        filling.setId(1);
        filling.setName("SweetFilling");
        filling.setCalories((short) 400);
        filling.setPrice((float) 120);

        sprinkleList = new ArrayList<>();

        Sprinkle sprinkle1 = new Sprinkle();
        sprinkle1.setId(1);
        sprinkle1.setName("SweetSprinkle1");
        sprinkle1.setCalories((short) 450);
        sprinkle1.setPrice((float) 130);
        sprinkleList.add(sprinkle1);

        Sprinkle sprinkle2 = new Sprinkle();
        sprinkle2.setId(2);
        sprinkle2.setName("SweetSprinkle2");
        sprinkle2.setCalories((short) 420);
        sprinkle2.setPrice((float) 110);
        sprinkleList.add(sprinkle2);

        category1 = new Category();
        category1.setId(1);
        category1.setName("IceCream");

        category2 = new Category();
        category2.setId(2);
        category2.setName("Donuts");

        orderList = new ArrayList<>();
        Order order = new Order();
        order.setId(1);
        order.setPurchaseDate(new Date());
        order.setTotalPrice((float) 1000);
        order.setPaymentStatus(PaymentStatus.NOT_PAID);
        order.setOrderStatus(OrderStatus.AWAITING_SHIPMENT);
        order.setPaymentOption(PaymentOption.CASH);
        order.setDeliveryOption(DeliveryOption.DELIVERY);
        order.setAddress(address);
        order.setUser(user1);
        orderList.add(order);

        product = new Product();
        product.setId(3);
        product.setCalories((short) 350);
        product.setPrice((float) 100);
        product.setCategory(category2);
        product.setDough(dough);
        product.setFilling(filling);
        product.setSprinkleList(sprinkleList);
        product.setName("Chocolate");

    }

    @Test
    public void testGetProductById() {
        when(productDao.getById(product.getId())).thenReturn(product);
        assertEquals(productService.getById(product.getId()).getId(), product.getId());
    }

    @Test
    public void testGetAllProducts() {
        when(productDao.getAll()).thenReturn(Collections.EMPTY_LIST);
        assertNotNull(productService.getAll());
    }

    @Test
    public void testFindProductByNameFalse() {
        when(productDao.getByName("Chocolate1")).thenReturn(null);
        assertNull(productService.getByName("Chocolate1"));
    }

    @Test
    public void testProductAlreadyExists() {
        when(productDao.getByName(product.getName())).thenReturn(product);
        ProductDTO productDTO = productService.getByName(product.getName());
        Integer productDtoId = productDTO.getId();
        Integer productId = product.getId();
        assertTrue(productDtoId.equals(productId));
    }

    @Test
    public void testFindProductByNameTrue() {
        when(productDao.getByName("Chocolate")).thenReturn(product);
        assertNotNull(productService.getByName(product.getName()));
    }

    @Test
    public void testGetAllProductsByCategory() {
        when(productDao.getAllByCategory(2)).thenReturn(Collections.EMPTY_LIST);
        assertNotNull(productService.getAllByCategory(2));
    }

    @Test
    public void testPriceNotLessZero() {
        when(productDao.getById(product.getId())).thenReturn(product);
        assertTrue(productService.getById(product.getId()).getPrice() > 0);
    }

    @Test
    public void testPriceCantLessZero() {
        when(productDao.getById(product.getId())).thenReturn(product);
        assertFalse(productService.getById(product.getId()).getPrice() < 0);
    }

    @Test
    public void testGetProductsByParams() {
        FilterDTO filterDTO = new FilterDTO();
        List<Product> list = productDao.getProductsByParameters(filterDTO);
        when(list).thenReturn(Collections.EMPTY_LIST);
        assertNotNull(productService.getProductsByParameters(filterDTO));
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testCreateProductWithoutName() {
        testFindProductByNameTrue();
        expectedException.expect(ObjectExistsException.class);
        given(productService.create(modelMapper.map(product,ProductDTO.class))).willThrow(new ObjectExistsException(""));
    }
}
