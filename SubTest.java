import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubTest {

    @Test
    void execute() {
        Sub subCommand = new Sub(new Numbers(2,8));
        assertEquals(6, subCommand.execute());
    }
}