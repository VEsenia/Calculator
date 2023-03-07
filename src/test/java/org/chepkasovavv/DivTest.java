package org.chepkasovavv;

import org.junit.jupiter.api.Test;
import org.chepkasovavv.Div;
import org.chepkasovavv.Numbers;

import static org.junit.jupiter.api.Assertions.*;

class DivTest {

    @Test
    void execute() {
        Numbers numb = new Numbers(2,6);
        Div divCommand = new Div(numb);
        assertEquals("3", divCommand.execute().getValue());
    }
}