import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@Tag("displayNames")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class DisplayNamesTest {
    final static Logger logger = LogManager.getLogger( DisplayNamesTest.class);
    StringBuffer forTestLifeCycle = new StringBuffer("varinitializedInClass");

    @Test
    @DisplayName("Evaluation @DisplayName")
    void testWithDisplayNameContainingSpaces(){
        logger.info( "in methodA: " + forTestLifeCycle );
        forTestLifeCycle.append(" passed by methodA");
        logger.info( "in methodA: " + forTestLifeCycle );
        /* validation code */
    }
    @Test
    @DisplayName("© ¶§§µ¼¾½ΦλΔ ")
    void testWithDisplayNameContainingSpecialCharacters(){
        logger.info( "in methodB: " + forTestLifeCycle );
        forTestLifeCycle.append(" passed by methodB");
        logger.info( "in methodB: " + forTestLifeCycle );
        /* validation code */
    }
    @Test
    @DisplayName("\uD83D\uDE04")
    void testWithDisplayNameContainingEmoji(){
        logger.info( "in methodC: " + forTestLifeCycle );
        forTestLifeCycle.append(" passed by methodC");
        logger.info( "in methodC: " + forTestLifeCycle );
        /* validation code */
    }
}
