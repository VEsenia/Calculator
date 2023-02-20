import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SumTest {

    @Test
    void execute() {
        Sum sumCommand = new Sum(new Numbers(2,3));
        assertEquals(5, sumCommand.execute());
    }
}