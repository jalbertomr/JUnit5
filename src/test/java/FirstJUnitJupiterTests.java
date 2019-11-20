import com.bext.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstJUnitJupiterTests {
    private final Calculator calculator = new Calculator();

    @Test
    void addition(){
        assertEquals( 2, calculator.add(1,1));
    }
}
