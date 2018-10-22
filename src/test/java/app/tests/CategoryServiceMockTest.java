package app.tests;

import app.dao.api.CategoryDao;
import app.dto.CategoryDTO;
import app.entity.Category;
import app.exception.ObjectExistsException;
import app.service.impl.CategoryServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceMockTest {

    @Mock
    private CategoryDao categoryDao;

    @InjectMocks
    private CategoryServiceImpl categoryService;
    @InjectMocks
    private ModelMapper modelMapper;
    private Category category1;
    private Category category2;

    @Before
    public void setup() {
        categoryService.setModelMapper(modelMapper);

        category1 = new Category();
        category1.setId(1);
        category1.setName("Donuts");

        category2 = new Category();
        category2.setId(2);
        category2.setName("Donuts");
    }

    @Test
    public void testGetCategoryById() {
        when(categoryDao.getById(category1.getId())).thenReturn(category1);
        assertEquals(categoryService.getById(category1.getId()).getId(), category1.getId());
    }

    @Test
    public void testProductEquals() {
        when(categoryDao.getByName(category1.getName())).thenReturn(category1);
        CategoryDTO categoryDTO = categoryService.getByName(category1.getName());
        Integer categoryDTOid = categoryDTO.getId();
        Integer categoryId = category1.getId();
        assertTrue(categoryDTOid.equals(categoryId));
    }

    @Test
    public void testFindCategoryByNameTrue() {
        when(categoryDao.getByName("Donuts")).thenReturn(category1);
        assertNotNull(categoryService.getByName(category1.getName()));
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testCategoryAlreadyExists(){
        testFindCategoryByNameTrue();
        expectedException.expect(ObjectExistsException.class);
        given(categoryService.create(modelMapper.map(category1,CategoryDTO.class))).willThrow(new ObjectExistsException("Already Exists"));
    }
}
