import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ImplementsInterfaceLoggedTest implements InterfaceLoggedTest{

    @Override
    @Test
    public void thisMethodIsLoggedByInterface() {
        assertTrue( Boolean.TRUE);
    }

    @Override
    @Test
    public void alsoLoggedByInterface() {
        assertTrue( Boolean.TRUE);
    }
}
