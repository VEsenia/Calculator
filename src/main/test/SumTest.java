package src.main.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.main.java.Numbers;
import src.main.java.Sum;

class SumTest {

    @Test
    void execute() {
        Numbers numb = new Numbers(2,5);
        Sum sumCommand = new Sum(numb);
        Assertions.assertEquals("7", sumCommand.execute().getValue());
    }
}