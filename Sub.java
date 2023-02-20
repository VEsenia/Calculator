public class Sub implements Command{
    private Numbers numbers;

    public Sub(Numbers numbers) {
        this.numbers = numbers;
    }

    @Override
    public Integer execute() {
        return numbers.numb2 - numbers.numb1;
    }
}
