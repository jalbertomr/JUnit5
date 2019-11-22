import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("TestInfo test")
public class TestInfoTest {
    final static Logger logger = LogManager.getLogger( TestInfoTest.class);

    TestInfoTest(TestInfo testInfo) {
        assertEquals("TestInfo test", testInfo.getDisplayName());
    }

    @BeforeEach
    void init(TestInfo testInfo) {
        String displayName = testInfo.getDisplayName();
        logger.info("init: " + displayName);
        assertTrue( displayName.equals("TEST 1") || displayName.equals("test2()"));
    }

    @Test
    @DisplayName("TEST 1")
    @Tag("test1Tagged")
    void test1(TestInfo testInfo) {
        assertEquals( "TEST 1", testInfo.getDisplayName());
        assertTrue( testInfo.getTags().contains("test1Tagged"));
    }

    @Test
    void test2(){
    }
}
