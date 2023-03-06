package org.chepkasovavv;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.chepkasovavv.Sub;

class SubTest {

    @Test
    void execute() {
        Numbers numb = new Numbers(2,5);
        Sub subCommand = new Sub(numb);
        Assertions.assertEquals("3", subCommand.execute().getValue());
    }
}