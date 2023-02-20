import java.util.Stack;

public class StackHolder {
    Stack<Integer> stackNumbers;
    Stack<String> stackOperations;

    public StackHolder(Stack<Integer> stackNumbers, Stack<String> stackOperations) {
        this.stackNumbers = stackNumbers;
        this.stackOperations = stackOperations;
    }

    public StackHolder makeOperation(String operation)
    {
        int numb1 = stackNumbers.pop();
        int numb2 = stackNumbers.pop();
        Command sumCommand = new Sum(new Numbers(numb1, numb2));
        Command subCommand = new Sub(new Numbers(numb1, numb2));
        Command divCommand = new Div(new Numbers(numb1, numb2));
        Command multiplyCommand = new Multiply(new Numbers(numb1, numb2));

        Counter counter = new Counter(operation, sumCommand, subCommand, multiplyCommand, divCommand);
        int res = counter.getResult();
        stackNumbers.push(res);

        //вынуть операцию из стека
        stackOperations.pop();

        StackHolder stackHolder = new StackHolder(stackNumbers, stackOperations);
        return  stackHolder;
    }


}