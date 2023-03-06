package org.chepkasovavv;

import org.junit.jupiter.api.Test;
import org.chepkasovavv.Multiply;
import org.chepkasovavv.Numbers;

import static org.junit.jupiter.api.Assertions.*;

class MultiplyTest {

    @Test
    void execute() {
        Numbers numb = new Numbers(2,5);
        Multiply multiplyCommand = new Multiply(numb);
        assertEquals("10", multiplyCommand.execute().getValue());
    }
}