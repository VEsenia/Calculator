package org.chepkasovavv;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.chepkasovavv.Main;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testMakeCalc() {
        assertEquals("5", Main.makeCalc("2+3"));
        assertEquals("7", Main.makeCalc("7"));
        assertEquals("7", Main.makeCalc("17-2*(2+3)"));
        assertEquals("0", Main.makeCalc("36-2*(3+5*(2+1))"));
        assertEquals("87", Main.makeCalc("40+7*8-9"));
    }
}