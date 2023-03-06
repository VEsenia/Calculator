package org.chepkasovavv;

import org.junit.jupiter.api.Test;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    void Run() {
        String str = "5+2*(2+3)";
        ArrayList<TypeLexeme> lstTypesLexeme = new ArrayList<TypeLexeme>();
        lstTypesLexeme.add(TypeLexeme.NUMBER);
        lstTypesLexeme.add(TypeLexeme.COMMAND);
        lstTypesLexeme.add(TypeLexeme.NUMBER);
        lstTypesLexeme.add(TypeLexeme.COMMAND);
        lstTypesLexeme.add(TypeLexeme.BRACKET);
        lstTypesLexeme.add(TypeLexeme.NUMBER);
        lstTypesLexeme.add(TypeLexeme.COMMAND);
        lstTypesLexeme.add(TypeLexeme.NUMBER);
        lstTypesLexeme.add(TypeLexeme.BRACKET);

        char[] example = str.toCharArray();
        Parser parser = new Parser(example);
        int iterator = 0;
        for (Object lexOb : parser) {
            Lexeme curLexeme = (Lexeme) lexOb;
            TypeLexeme typeLexeme = curLexeme.getType();
            String lexValue = curLexeme.getValue();
            assertEquals(lexValue, String.valueOf(example[iterator]));
            assertEquals(typeLexeme, lstTypesLexeme.get(iterator));
            iterator++;
        }


    }

}