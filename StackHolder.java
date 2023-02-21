import java.util.Stack;

public class StackHolder {
    public Stack<Lexeme> stackNumbers;
    public Stack<Lexeme> stackOperations;
    //был расчет или нет
    public boolean wasCount;

    public StackHolder(Stack<Lexeme> stackNumbers, Stack<Lexeme> stackOperations) {
        this.stackNumbers = stackNumbers;
        this.stackOperations = stackOperations;
        wasCount = true;
    }

    public boolean isNeedCount(int priorityLasOper, int priorityCurOper) {
        //если приоритет текущей выше, тогда операцию в стек
        return (priorityCurOper <= priorityLasOper);
    }
    private void FillStacks(Lexeme lastLexeme)
    {
        int numb1 = Integer.parseInt(stackNumbers.pop().getValue());
        int numb2 = Integer.parseInt(stackNumbers.pop().getValue());
        Command sumCommand = new Sum(new Numbers(numb1, numb2));
        Command subCommand = new Sub(new Numbers(numb1, numb2));
        Command divCommand = new Div(new Numbers(numb1, numb2));
        Command multiplyCommand = new Multiply(new Numbers(numb1, numb2));

        Counter counter = new Counter(lastLexeme.getValue(), sumCommand, subCommand, multiplyCommand, divCommand);
        Lexeme res = counter.getResult();
        stackNumbers.push(res);

        //вынуть операцию из стека
        stackOperations.pop();
    }

    public StackHolder makeOperation(Lexeme lastLexeme, Lexeme curLexeme) {
        boolean wasCount = false;
        if (isNeedCount(lastLexeme.getPriority(), curLexeme.getPriority())
                && curLexeme.typeBracket != TypeBracket.OPEN) {
            FillStacks(lastLexeme);
            wasCount = true;
        }
        StackHolder stackHolder = new StackHolder(stackNumbers, stackOperations);
        stackHolder.wasCount = wasCount;
        return stackHolder;
    }

    public StackHolder makeOperation(Lexeme lastLexeme) {
        FillStacks(lastLexeme);
        StackHolder stackHolder = new StackHolder(stackNumbers, stackOperations);
        return stackHolder;
    }


}