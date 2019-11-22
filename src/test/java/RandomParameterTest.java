import com.example.random.RandomParametersExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(com.example.random.RandomParametersExtension.class)
public class RandomParameterTest {

    @Test
    void injectInteger(@RandomParametersExtension.Random int  i, @RandomParametersExtension.Random int  j) {
        assertNotEquals(i, j);
    }

    @Test
    void injectDouble(@RandomParametersExtension.Random double d) {
        assertEquals(0.0, d, 1.0);
    }
}
