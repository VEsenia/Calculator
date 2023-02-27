package src.main.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.main.java.Numbers;
import src.main.java.Sub;

class SubTest {

    @Test
    void execute() {
        Numbers numb = new Numbers(2,5);
        Sub subCommand = new Sub(numb);
        Assertions.assertEquals("3", subCommand.execute().getValue());
    }
}