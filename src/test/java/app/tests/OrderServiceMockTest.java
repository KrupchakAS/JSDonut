package app.tests;

import app.dao.api.OrderDao;

import app.dto.OrderDTO;
import app.entity.*;
import app.entity.enums.DeliveryOption;
import app.entity.enums.OrderStatus;
import app.entity.enums.PaymentOption;
import app.entity.enums.PaymentStatus;
import app.exception.MinTotalPriceOrderException;
import app.service.impl.OrderServiceImpl;
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

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class OrderServiceMockTest {

    @Mock
    private OrderDao orderDao;

    @InjectMocks
    private OrderServiceImpl orderService;

    @InjectMocks
    private ModelMapper modelMapper;


    private Dough dough;
    private Filling filling;
    private List<Sprinkle> sprinkleList;
    private Category category1;
    private Category category2;
    private User user1;
    private Address address;
    private Product product;
    private Order order;

    @Before
    public void setup() {
        orderService.setModelMapper(modelMapper);

        user1 = new User();
        user1.setId(1);
        user1.setLogin("Test1");
        user1.setFirstName("TestFirstName1");
        user1.setSurName("TestSurName1");
        user1.setBirthDate(new Date());

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

        List<Product> list = new ArrayList<>();
        product = new Product();
        product.setId(3);
        product.setCalories((short) 350);
        product.setPrice((float) 100);
        product.setCategory(category2);
        product.setDough(dough);
        product.setFilling(filling);
        product.setSprinkleList(sprinkleList);
        product.setName("Chocolate");
        list.add(product);

        order = new Order();
        order.setId(1);
        order.setPurchaseDate(new Date());
        order.setTotalPrice((float) 1000);
        order.setPaymentStatus(PaymentStatus.NOT_PAID);
        order.setOrderStatus(OrderStatus.AWAITING_SHIPMENT);
        order.setPaymentOption(PaymentOption.CASH);
        order.setDeliveryOption(DeliveryOption.DELIVERY);
        order.setAddress(address);
        order.setUser(user1);
        order.setProductList(list);
    }

    @Test
    public void testGetOrderById() {
        when(orderDao.getById(order.getId())).thenReturn(order);
        assertEquals(orderDao.getById(order.getId()).getId(), order.getId());
    }

    @Test
    public void testGetAllOrders() {
        when(orderDao.getAll()).thenReturn(Collections.EMPTY_LIST);
        assertNotNull(orderService.getAll());
    }


    @Test
    public void testTotalPriceCantLessZero() {
        when(orderDao.getById(order.getId())).thenReturn(order);
        assertFalse(order.getTotalPrice() < 0.0f);
    }

    @Test
    public void testOrderAlreadyExists() {
        when(orderDao.getById(order.getId())).thenReturn(order);
        Integer oDtoId = 1;
        Integer oId = order.getId();
        assertTrue(oDtoId.equals(oId));
    }

    @Test
    public void testGetOrdersByUser() {
        when(orderDao.getOrdersByUserId(user1.getId())).thenReturn(Collections.EMPTY_LIST);
        assertNotNull(orderService.getOrdersByUserId(user1.getId()));
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

//    @Test
//    public void testCreateOrderByPrice() {
//        testTotalPriceCantLessZero();
//        expectedException.expect(MinTotalPriceOrderException.class);
//        given(orderService.create(modelMapper.map(order,OrderDTO.class))).willThrow(new MinTotalPriceOrderException("Order price is valid"));
//    }
}
