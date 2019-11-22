import com.bext.Calculator;
import com.bext.Persona;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.*;

@Tag("assertions")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AssertionsTest {
    final static Logger logger = LogManager.getLogger( AssertionsTest.class);
    private final Calculator calculator = new Calculator();
    private final Persona persona = new Persona("Jose", "Martinez");

    @Test
    @Order(11)
    void standardAssertions(TestInfo testinfo) {
        logger.info( "11 " + testinfo.getDisplayName());
        assertEquals(2, calculator.add(1, 1));
        assertEquals(4, calculator.multiply(2, 2), "mensage opcional de falla es ahora el último parámetro.");
        assertTrue('a' < 'b', () -> "Mensages assert pueden ser evaluados lazy para evitar construcciones complejas innecesarias");
    }

    @Test
    @Order(10)
    void groupedAssertions(TestInfo testinfo) {
        logger.info( "10 " + testinfo.getDisplayName());
        //En assersiones agrupadas todas son ejecutadas, y las fallas son reportadas juntas
        assertAll("persona",
                () -> assertEquals("Jose", persona.getNombre()),
                () -> assertEquals("Martinez", persona.getApellido())
        );
    }

    @Test
    @Order(9)
    void dependentAssertions(TestInfo testinfo) {
        logger.info( "9 " + testinfo.getDisplayName());
        //En un mismo bloque de codigo, si una assertion falla el código del mismo bloque será omitido.
        assertAll("properties",
                () -> {
                    String nombre = persona.getNombre();
                    assertNotNull( nombre);
                    // se ejecuta solo si la assert anterior es valida.
                    assertAll("nombre",
                            () -> assertTrue( nombre.startsWith("J")),
                            () -> assertTrue(nombre.endsWith("e"))
                    );
                },
                () -> { // assert agrupados, se procesa independientemente del otro bloque, por lo tanto se ejecuta
                    String apellido = persona.getApellido();
                    assertNotNull( apellido);
                    // se ejecuta solo si la assert anterior es valida.
                    assertAll("apellido",
                            () -> assertTrue(apellido.startsWith("M")),
                            () -> assertTrue( apellido.endsWith("z"))
                    );
                }
        );
    }

    @Test
    @Order(8)
    void exceptionTestingThrowException(TestInfo testinfo) {
        logger.info( "8 " + testinfo.getDisplayName());
        Exception exception = assertThrows( IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("argumento ilegal");
        });
        assertEquals("argumento ilegal", exception.getMessage());
    }

    @Test
    @Order(7)
    void exceptionTestingLambdaThrowException(TestInfo testinfo) {
        logger.info( "7 " + testinfo.getDisplayName());
        Exception exception = assertThrows( ArithmeticException.class,
                () -> { throw new ArithmeticException("/ by zero"); });
        assertEquals("/ by zero", exception.getMessage());
    }

    @Test
    @Order(6)
    void exceptionTestingMethodThatThrowException(TestInfo testinfo) {
        logger.info( "6 " + testinfo.getDisplayName());
        Exception exception = assertThrows( ArithmeticException.class,
                () -> { calculator.divide(1,0); });
        assertEquals("/ by zero", exception.getMessage());
    }
    
    @Test
    @Tag("timeout")
    @Order(5)
    void timeoutNotExceeded(TestInfo testinfo){  // será exitosa
        logger.info( "5 " + testinfo.getDisplayName());
        assertTimeout(ofMinutes(2),
                () -> { /* código ejecutado en menos de 2 minutos. */ } );
    }

    @Test
    @Tag("timeout")
    @Order(4)
    void timeoutNotExceededWithResult(TestInfo testinfo) {
        logger.info( "4 " + testinfo.getDisplayName());
        // será exitosa, resultado devuelto.
        String actualResult = assertTimeout( ofMinutes(2),
                () -> { return "X resultado";} );
        assertEquals("X resultado", actualResult);
    }

    @Test
    @Tag("timeout")
    @Order(3)
    void timeoutNotExceededWithMethod(TestInfo testinfo){
        logger.info( "3 " + testinfo.getDisplayName());
        // assert invoca un referencia de método y regresa un objeto.
        String saludoActual = assertTimeout( ofMinutes(2), AssertionsTest::saludo);
        assertEquals("Hola, Mundito!", saludoActual);
    }

    @Test
    @Tag("timeout")
    @Order(2)
    void timeoutExceeded(TestInfo testinfo) {
        logger.info( "2 " + testinfo.getDisplayName());
        // assert fallará org.opentest4j.AssertionFailedError: execution exceeded timeout of 10 ms by 91 ms
        assertTimeout( ofMillis(10), () -> Thread.sleep(100));
    }

    @Test
    @Tag("timeout")
    @Order(1)
    void timeoutExceededWithPreemtiveTermination(TestInfo testinfo) {
        logger.info( "1 " + testinfo.getDisplayName());
        // assert fallará org.opentest4j.AssertionFailedError: execution timed out after 10 ms
        assertTimeoutPreemptively(ofMillis(10), ()->Thread.sleep(100));
    }

    private static String saludo() {
        return "Hola, Mundito!";
    }
}
