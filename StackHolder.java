import java.util.Stack;

public class StackHolder {
    Stack<Integer> stackNumbers;
    Stack<Character> stackOperations;

    public StackHolder(Stack<Integer> stackNumbers, Stack<Character> stackOperations) {
        this.stackNumbers = stackNumbers;
        this.stackOperations = stackOperations;
    }
}