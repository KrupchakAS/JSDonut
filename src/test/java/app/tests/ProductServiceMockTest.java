package app.tests;

import app.dao.api.ProductDao;
import app.dto.ProductDTO;
import app.entity.*;
import app.entity.enums.DeliveryOption;
import app.entity.enums.OrderStatus;
import app.entity.enums.PaymentOption;
import app.entity.enums.PaymentStatus;
import app.service.api.*;
import app.service.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
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
    private Category category;
    private List<Order> orderList;
    private User user;
    private Address address;
    private Product product;

    @Before
    public void setup(){
        productService.setModelMapper(modelMapper);

        user =new User();
        user.setId(1);
        user.setLogin("Test");
        user.setFirstName("TestFirstName");
        user.setSurName("TestSurName");
        user.setBirthDate(new Date());

        address = new Address();
        address.setId(1);
        address.setCity("Spb");
        address.setStreet("Buharestskay");
        address.setHouseNumber((short)100);
        address.setApartmentNumber((short)24);
        address.setPostCode("100000");

        dough = new Dough();
        dough.setId(1);
        dough.setName("SweetDough");
        dough.setCalories((short)300);
        dough.setPrice((float)90);

        filling = new Filling();
        filling.setId(1);
        filling.setName("SweetFilling");
        filling.setCalories((short)400);
        filling.setPrice((float)120);

        sprinkleList =new ArrayList<>();

        Sprinkle sprinkle1 = new Sprinkle();
        sprinkle1.setId(1);
        sprinkle1.setName("SweetSprinkle1");
        sprinkle1.setCalories((short)450);
        sprinkle1.setPrice((float)130);
        sprinkleList.add(sprinkle1);

        Sprinkle sprinkle2 = new Sprinkle();
        sprinkle2.setId(2);
        sprinkle2.setName("SweetSprinkle2");
        sprinkle2.setCalories((short)420);
        sprinkle2.setPrice((float)110);
        sprinkleList.add(sprinkle2);

        category = new Category();
        category.setId(1);
        category.setName("IceCream");

        orderList = new ArrayList<>();
        Order order = new Order();
        order.setId(1);
        order.setPurchaseDate(new Date());
        order.setTotalPrice((float)1000);
        order.setPaymentStatus(PaymentStatus.NOT_PAID);
        order.setOrderStatus(OrderStatus.AWAITING_SHIPMENT);
        order.setPaymentOption(PaymentOption.CASH);
        order.setDeliveryOption(DeliveryOption.DELIVERY);
        order.setAddress(address);
        order.setUser(user);
        orderList.add(order);

        product = new Product();
        product.setId(350);
        product.setCalories((short)350);
        product.setCategory(category);
        product.setDough(dough);
        product.setFilling(filling);
        product.setSprinkleList(sprinkleList);
        product.setName("Шоколадный");
    }

    @Test
    public void testProductAlreadyExists(){
        when(productDao.getByName(product.getName())).thenReturn(product);
        ProductDTO productDTO = productService.getByName(product.getName());
        Integer productDtoId = productDTO.getId();
        Integer productId = product.getId();
        assertTrue(productDtoId.equals(productId));
    }


}
