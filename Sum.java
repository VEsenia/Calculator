public class Sum implements Command{
    private Numbers numbers;

    public Sum(Numbers numbers) {
        this.numbers = numbers;
    }

    @Override
    public Integer execute() {
        return numbers.numb1 + numbers.numb2;
    }
}
