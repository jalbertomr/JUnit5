import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public interface InterfaceDynamicTest {
    @TestFactory
    default Stream<DynamicTest> dynamicTestForPalindromes() {
        return Stream.of("asdfa","asdfas","adsf","asdf")
                .map( text -> dynamicTest( text, () -> assertTrue(isPalindrome(text))));
    }

    default boolean isPalindrome(String text) {
        return true;
    };
}
