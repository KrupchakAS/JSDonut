package app;

import app.tests.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ProductServiceMockTest.class,
        CategoryServiceMockTest.class,
        DoughServiceMockTest.class,
        FillingServiceMockTest.class,
        OrderServiceMockTest.class,
        SprinkleServiceMockTest.class
})
public class AppTest {
}
