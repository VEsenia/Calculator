public class Sub implements Command{
    private Numbers numbers;

    public Sub(Numbers numbers) {
        this.numbers = numbers;
    }

    @Override
    public Lexeme execute() {
        String value = String.valueOf(numbers.numb2 - numbers.numb1);
        Lexeme lexeme = new Lexeme(TypeLexeme.NUMBER, 0, value);
        return lexeme;
    }
}
