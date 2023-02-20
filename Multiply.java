public class Multiply implements Command{
    private Numbers numbers;

    public Multiply(Numbers numbers) {
        this.numbers = numbers;
    }

    @Override
    public Integer execute() {
        return numbers.numb1 * numbers.numb2;
    }
}
