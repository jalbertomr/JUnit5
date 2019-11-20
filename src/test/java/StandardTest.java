import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class StandardTest {
    @BeforeAll
    static void initAll(){
    }

    @BeforeEach
    void init(){
    }

    @Test
    void succedingTest(){
        assertEquals(2,2);
    }

    @Test
    void failingTest(){
        fail("una prueba fallida");
    }

    @Test
    @Disabled("this test is disabled")
    void skippedTest(){
        // not executed
    }

    @Test
    void abortedTest(){
        assumeTrue("abc".contains("Z"));
        fail("this should have been aborted");
    }

    @AfterEach
    void tearDown(){}

    @AfterAll
    static void tearDownAll(){}
}
