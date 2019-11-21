import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import sun.awt.OSInfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.junit.jupiter.api.condition.OS.*;

public class OperativeSystemConditionsTest {
    @Test
    @EnabledOnOs(WINDOWS)
    void onlyOnWindowsOs() {  /* validation code here*/
    }

    @Test
    @EnabledOnOs(MAC)
    void onlyOnMacOs() {  /* validation code here*/
    }

    @TestOnMac
    void testOnMac() {  /* validation code here*/
    }

    @Test
    @EnabledOnOs({LINUX, MAC})
    void onLinuxOrMac() { /* validation code here*/ }

    @Test
    @DisabledOnOs(WINDOWS)
    void notOnWindows() {  /* validation code here*/ }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Test
    @EnabledOnOs(MAC)
    @interface TestOnMac {
        /* validation code here*/
    }
}
