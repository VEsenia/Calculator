package src.main.test;

import org.junit.jupiter.api.Test;
import src.main.java.Div;
import src.main.java.Numbers;

import static org.junit.jupiter.api.Assertions.*;

class DivTest {

    @Test
    void execute() {
        Numbers numb = new Numbers(2,6);
        Div divCommand = new Div(numb);
        assertEquals("3", divCommand.execute().getValue());
    }
}