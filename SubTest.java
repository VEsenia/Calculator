import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubTest {

    @Test
    void execute() {
        Numbers numb = new Numbers(2,5);
        Sub subCommand = new Sub(numb);
        Lexeme lexemeNeed = new Lexeme(TypeLexeme.NUMBER, 0, "3");
        assertEquals("3", subCommand.execute().getValue());
    }
}