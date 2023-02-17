import java.util.Stack;

public class StackHolder {
    Stack<Integer> stackNumbers;
    Stack<String> stackOperations;

    public StackHolder(Stack<Integer> stackNumbers, Stack<String> stackOperations) {
        this.stackNumbers = stackNumbers;
        this.stackOperations = stackOperations;
    }
}