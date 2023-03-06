package org.chepkasovavv;

public class Sum implements Command {
    private Numbers numbers;

    public Sum(Numbers numbers) {
        this.numbers = numbers;
    }

    @Override
    public Lexeme execute()
    {
        String value = String.valueOf(numbers.numb1 + numbers.numb2);
        Lexeme lexeme = new Lexeme(TypeLexeme.NUMBER, 0, value);
        return lexeme;
    }
}
