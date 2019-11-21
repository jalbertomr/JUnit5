import com.bext.Calculator;
import com.bext.Persona;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.*;

@Tag("assertions")
public class AssertionsTest {
    private final Calculator calculator = new Calculator();
    private final Persona persona = new Persona("Jose", "Martinez");

    @Test
    void standardAssertions() {
        assertEquals(2, calculator.add(1, 1));
        assertEquals(4, calculator.multiply(2, 2), "mensage opcional de falla es ahora el último parámetro.");
        assertTrue('a' < 'b', () -> "Mensages assert pueden ser evaluados lazy para evitar construcciones complejas innecesarias");
    }

    @Test
    void groupedAssertions() {
        //En assersiones agrupadas todas son ejecutadas, y las fallas son reportadas juntas
        assertAll("persona",
                () -> assertEquals("Jose", persona.getNombre()),
                () -> assertEquals("Martinez", persona.getApellido())
        );
    }

    @Test
    void dependentAssertions() {
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
    void exceptionTestingThrowException() {
        Exception exception = assertThrows( IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("argumento ilegal");
        });
        assertEquals("argumento ilegal", exception.getMessage());
    }

    @Test
    void exceptionTestingLambdaThrowException() {
        Exception exception = assertThrows( ArithmeticException.class,
                () -> { throw new ArithmeticException("/ by zero"); });
        assertEquals("/ by zero", exception.getMessage());
    }

    @Test
    void exceptionTestingMethodThatThrowException() {
        Exception exception = assertThrows( ArithmeticException.class,
                () -> { calculator.divide(1,0); });
        assertEquals("/ by zero", exception.getMessage());
    }
    
    @Test
    @Tag("timeout")
    void timeoutNotExceeded(){  // será exitosa
        assertTimeout(ofMinutes(2),
                () -> { /* código ejecutado en menos de 2 minutos. */ } );
    }

    @Test
    @Tag("timeout")
    void timeoutNotExceededWithResult() {
        // será exitosa, resultado devuelto.
        String actualResult = assertTimeout( ofMinutes(2),
                () -> { return "X resultado";} );
        assertEquals("X resultado", actualResult);
    }

    @Test
    @Tag("timeout")
    void timeoutNotExceededWithMethod(){
        // assert invoca un referencia de método y regresa un objeto.
        String saludoActual = assertTimeout( ofMinutes(2), AssertionsTest::saludo);
        assertEquals("Hola, Mundito!", saludoActual);
    }

    @Test
    @Tag("timeout")
    void timeoutExceeded() {
        // assert fallará org.opentest4j.AssertionFailedError: execution exceeded timeout of 10 ms by 91 ms
        assertTimeout( ofMillis(10), () -> Thread.sleep(100));
    }

    @Test
    @Tag("timeout")
    void timeoutExceededWithPreemtiveTermination() {
        // assert fallará org.opentest4j.AssertionFailedError: execution timed out after 10 ms
        assertTimeoutPreemptively(ofMillis(10), ()->Thread.sleep(100));
    }

    private static String saludo() {
        return "Hola, Mundito!";
    }
}
