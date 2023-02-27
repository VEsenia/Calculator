package src.main.test;

import org.junit.jupiter.api.Test;
import src.main.java.Multiply;
import src.main.java.Numbers;

import static org.junit.jupiter.api.Assertions.*;

class MultiplyTest {

    @Test
    void execute() {
        Numbers numb = new Numbers(2,5);
        Multiply multiplyCommand = new Multiply(numb);
        assertEquals("10", multiplyCommand.execute().getValue());
    }
}