import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnJre;

import static org.junit.jupiter.api.condition.JRE.*;

public class JavaRunTimeEnvironmentConditionsTest {
    @Test
    @EnabledOnJre(JAVA_8)
    void onlyOnJava8() {
        /* validation code here*/
    }

    @Test
    @EnabledOnJre({JAVA_9, JAVA_10})
    void onJava9Or10() {
        /* validation code here*/
    }

    @Test
    @DisabledOnJre(JAVA_9)
    void notOnJava9() {
        /* validation code here*/
    }
}
