package app.tests;

import app.dao.api.FillingDao;
import app.dto.FillingDTO;
import app.entity.Filling;
import app.exception.ObjectExistsException;
import app.service.api.FillingService;
import app.service.impl.FillingServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FillingServiceMockTest {

    @Mock
    private FillingDao fillingDao;

    @InjectMocks
    private FillingServiceImpl fillingService;
    @InjectMocks
    private ModelMapper modelMapper;
    private Filling filling;

    @Before
    public void setup() {
        fillingService.setModelMapper(modelMapper);

        filling = new Filling();
        filling.setId(1);
        filling.setName("Classic");
        filling.setPrice((float)50);
        filling.setCalories((short)100);
    }

    @Test
    public void testGetFillingById() {
        when(fillingDao.getById(filling.getId())).thenReturn(filling);
        assertEquals(fillingService.getById(filling.getId()).getId(), filling.getId());
    }

    @Test
    public void testFillingEquals() {
        when(fillingDao.getByName(filling.getName())).thenReturn(filling);
        FillingDTO dDTO = fillingService.getByName(filling.getName());
        Integer dDTOid = dDTO.getId();
        Integer dId = filling.getId();
        assertTrue(dDTOid.equals(dId));
    }

    @Test
    public void testFindFillingByNameFalse() {
        when(fillingDao.getByName("Classic1")).thenReturn(null);
        assertNull(fillingService.getByName("Classic1"));
    }
    @Test
    public void testFindFillingByNameTrue() {
        when(fillingDao.getByName("Classic")).thenReturn(filling);
        assertNotNull(fillingService.getByName(filling.getName()));
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testFillingAlreadyWithExistName(){
        testFindFillingByNameTrue();
        expectedException.expect(ObjectExistsException.class);
        given(fillingService.create(modelMapper.map(filling,FillingDTO.class))).willThrow(new ObjectExistsException("Already Exists"));
    }
}
