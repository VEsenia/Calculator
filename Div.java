public class Div implements Command{
    private Numbers numbers;

    public Div(Numbers numbers) {
        this.numbers = numbers;
    }

    @Override
    public Integer execute() {
        return numbers.numb2 / numbers.numb1;
    }
}
