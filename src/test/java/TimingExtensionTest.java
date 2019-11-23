import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TimingExtension.class)
public class TimingExtensionTest {
    @Test
    void sleep25ms() throws InterruptedException {
        Thread.sleep(25);
    }

    @Test
    void sleep50ms() throws InterruptedException {
        Thread.sleep(50);
    }
}
