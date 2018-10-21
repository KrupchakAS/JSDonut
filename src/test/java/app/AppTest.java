package app;

import app.tests.ProductServiceMockTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ProductServiceMockTest.class
})
public class AppTest {
}
