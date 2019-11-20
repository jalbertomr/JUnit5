import com.bext.Calculator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

//@Disabled("Tests para Clase Deshabilitada hasta pasar bug #12")
public class AssumptionsDemo {

    private final Calculator calculator = new Calculator();

    @Disabled("Test para método deshabilitada hasta pasar bug #15")
    @Test
    void testOnlyOnContinuousIntegrationServer() {
        assumeTrue("CI".equals( System.getenv("ENV")) );
    }

    @Test
    void testOnlyOnDeveloperWorkstation() {
        assumeTrue("DEV".equals( System.getenv("ENV")), () -> "test abortada: no en estación de desarrollo.");
    }

    @Test
    void testInSpecificEnvironments() {
        // perform test if environment is Continuous Intergration
        assumingThat("CI".equals( System.getenv( "ENV")),
                () -> assertEquals( 2, calculator.divide(4,2)));
        // perform test in all environment
        assertEquals( 42, calculator.multiply( 6, 7));
    }
}
