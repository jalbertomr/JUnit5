import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DisplayNamesDemo {
    @Test
    @DisplayName("Evaluation @DisplayName")
    void testWithDisplayNameContainingSpaces(){
    }
    @Test
    @DisplayName("© ¶§§µ¼¾½ΦλΔ ")
    void testWithDisplayNameContainingSpecialCharacters(){
    }
    @Test
    @DisplayName("\uD83D\uDE04")
    void testWithDisplayNameContainingEmoji(){
    }
}
