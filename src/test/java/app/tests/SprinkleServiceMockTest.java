package app.tests;

import app.dao.api.SprinkleDao;
import app.dto.SprinkleDTO;
import app.entity.Sprinkle;
import app.exception.ObjectExistsException;
import app.service.impl.SprinkleServiceImpl;
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
public class SprinkleServiceMockTest {

    @Mock
    private SprinkleDao sprinkleDao;

    @InjectMocks
    private SprinkleServiceImpl sprinkleService;
    @InjectMocks
    private ModelMapper modelMapper;
    private Sprinkle sprinkle;

    @Before
    public void setup() {
        sprinkleService.setModelMapper(modelMapper);

        sprinkle = new Sprinkle();
        sprinkle.setId(1);
        sprinkle.setName("Classic");
        sprinkle.setPrice((float)50);
        sprinkle.setCalories((short)100);
    }

    @Test
    public void testGetSprinkleById() {
        when(sprinkleDao.getById(sprinkle.getId())).thenReturn(sprinkle);
        assertEquals(sprinkleService.getById(sprinkle.getId()).getId(), sprinkle.getId());
    }

    @Test
    public void testSprinkleEquals() {
        when(sprinkleDao.getByName(sprinkle.getName())).thenReturn(sprinkle);
        SprinkleDTO sDTO = sprinkleService.getByName(sprinkle.getName());
        Integer sDTOid = sDTO.getId();
        Integer sId = sprinkle.getId();
        assertTrue(sDTOid.equals(sId));
    }

    @Test
    public void testFindSprinkleByNameFalse() {
        when(sprinkleDao.getByName("Classic1")).thenReturn(null);
        assertNull(sprinkleService.getByName("Classic1"));
    }
    @Test
    public void testFindSprinkleByNameTrue() {
        when(sprinkleDao.getByName("Classic")).thenReturn(sprinkle);
        assertNotNull(sprinkleService.getByName(sprinkle.getName()));
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testSprinkleAlreadyWithExistName(){
        testFindSprinkleByNameTrue();
        expectedException.expect(ObjectExistsException.class);
        given(sprinkleService.create(modelMapper.map(sprinkle,SprinkleDTO.class))).willThrow(new ObjectExistsException("Already Exists"));
    }
}
