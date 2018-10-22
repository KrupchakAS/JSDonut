package app.tests;

import app.dao.api.DoughDao;
import app.dto.DoughDTO;
import app.entity.Dough;
import app.exception.ObjectExistsException;
import app.service.impl.DoughServiceImpl;
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
public class DoughServiceMockTest {

    @Mock
    private DoughDao doughDao;

    @InjectMocks
    private DoughServiceImpl doughService;
    @InjectMocks
    private ModelMapper modelMapper;
    private Dough dough;

    @Before
    public void setup() {
        doughService.setModelMapper(modelMapper);

        dough = new Dough();
        dough.setId(1);
        dough.setName("Classic");
        dough.setPrice((float)50);
        dough.setCalories((short)100);
    }

    @Test
    public void testGetDoughById() {
        when(doughDao.getById(dough.getId())).thenReturn(dough);
        assertEquals(doughService.getById(dough.getId()).getId(), dough.getId());
    }

    @Test
    public void testDoughEquals() {
        when(doughDao.getByName(dough.getName())).thenReturn(dough);
        DoughDTO dDTO = doughService.getByName(dough.getName());
        Integer dDTOid = dDTO.getId();
        Integer dId = dough.getId();
        assertTrue(dDTOid.equals(dId));
    }

    @Test
    public void testFindDoughByNameFalse() {
        when(doughDao.getByName("Classic1")).thenReturn(null);
        assertNull(doughService.getByName("Classic1"));
    }

    @Test
    public void testFindDoughByNameTrue() {
        when(doughDao.getByName("Classic")).thenReturn(dough);
        assertNotNull(doughService.getByName(dough.getName()));
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testDoughWithExistName(){
        testFindDoughByNameTrue();
        expectedException.expect(ObjectExistsException.class);
        given(doughService.create(modelMapper.map(dough,DoughDTO.class))).willThrow(new ObjectExistsException("Already Exists"));
    }
}
