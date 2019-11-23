import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImplemManyTimeInterfaceTest implements TimeExecutionLogger, InterfaceLoggedTest, InterfaceDynamicTest {
    @Test
    void isEqualValue() {
        assertEquals( 1, "a".length(), "siempre es Equals");
    }

    @Override
    public void thisMethodIsLoggedByInterface() {

    }

    @Override
    public void alsoLoggedByInterface() {

    }
}
