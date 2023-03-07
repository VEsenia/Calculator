package org.chepkasovavv;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.chepkasovavv.Numbers;
import org.chepkasovavv.Sum;

class SumTest {

    @Test
    void execute() {
        Numbers numb = new Numbers(2,5);
        Sum sumCommand = new Sum(numb);
        Assertions.assertEquals("7", sumCommand.execute().getValue());
    }
}