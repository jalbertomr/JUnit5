import org.junit.jupiter.api.*;

import java.util.EmptyStackException;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

@Tag("nestedStack")
@DisplayName("A Stack")
public class NestedStackTest {
    Stack<Object> stack;

    @Test
    @DisplayName("Is instantiated with new Stack()")
    void isInstantiatedWithNew() {
        new Stack();
    }

    @Nested
    @DisplayName("when new")
    class WhenNew {
        @BeforeEach
        void createNewStack() {
            stack = new Stack();
        }

        @Test
        @DisplayName("is Empty")
        void isEmpty() {
            assertTrue(stack.empty());
        }

        @Test
        @DisplayName(("throws EmptyStackException when popped"))
        void throwsExeptionWhenPopped() {
            assertThrows(EmptyStackException.class, stack::pop);
        }

        @Test
        @DisplayName(("throws EmptyStackException when peeked"))
        void throwsExeptionWhenPeeked() {
            assertThrows(EmptyStackException.class, stack::peek);
        }

        @Nested
        @DisplayName("after pushing an element")
        class AfterPushing {
            String anElement = "an Element";

            @BeforeEach
            void pushAnElement() {
                stack.push( anElement);
            }

            @Test
            @DisplayName("not longer empty")
            void isNotEmpty() {
                assertFalse( stack.empty());
            }

            @Test
            @DisplayName("returns the element when popped and is Empty")
            void returnElementWhenPopped() {
                assertEquals( anElement, stack.pop());
                assertTrue(stack::empty);
            }

            @Test
            @DisplayName("returns the element when peeked but remains not empty")
            void returnElementWhenPeeked() {
                assertEquals( anElement, stack.peek());
                assertTrue(stack::empty);
            }
        }
    }
}
