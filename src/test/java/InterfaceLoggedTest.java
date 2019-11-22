import com.sun.javafx.binding.StringFormatter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public interface InterfaceLoggedTest {
    static Logger logger = LogManager.getLogger(InterfaceLoggedTest.class.getName());

    @BeforeAll
    default void beforeAllTest() {
        logger.info("Antes que todas las pruebas");
    }

    @AfterAll
    default void afterAllTest() {
        logger.info("Despues de todas las pruebas");
    }

    @BeforeEach
    default void beforeEachTest( TestInfo testInfo){
        logger.info( () -> String.format("antes   de %s", testInfo.getDisplayName()));
    }

    @AfterEach
    default void afterEachTest( TestInfo testInfo) {
        logger.info( ()-> String.format("despues de %s", testInfo.getDisplayName()));
    }

    void thisMethodIsLoggedByInterface();
    void alsoLoggedByInterface();

}
