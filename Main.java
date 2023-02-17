import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    private static Integer getResult(Integer number1, Integer number2, String operator)
    {
        switch (operator) {
            case "+":
                return number2 + number1;
            case "-":
                return number2 - number1;
            case "*":
                return number2 * number1;
            case "/":
                return number2 / number1;
        }
        return 0;
    }

    private static StackHolder makeOperation(Stack<Integer> stackNumbers, Stack<String> stackOperations, String operation)
    {
        int numb1 = stackNumbers.pop();
        int numb2 = stackNumbers.pop();
        int res = getResult(numb1, numb2, operation);
        stackNumbers.push(res);

        //вынуть операцию из стека
        stackOperations.pop();

        StackHolder stackHolder = new StackHolder(stackNumbers, stackOperations);
        return  stackHolder;
    }

    public static void main(String[] args) {

        String example = "";
        do {
            System.out.println("Введите пример");
            Scanner in = new Scanner(System.in, "UTF-8");
            example = in.nextLine();

            String[] operators = {"+", "-", "/", "*"};
            String[] brackets = {"(", ")"};
            Stack<Integer> stackNumbers = new Stack<Integer>();
            Stack<String> stackOperations = new Stack<String>();

            example.replace(" ", "");
            StringBuilder strNumber = new StringBuilder();
            Parser parser = new Parser(example.toCharArray());
            Iterator iterator = parser.getIterator();
            while (iterator.hasNext()) {
                String lexeme = (String) iterator.next();
                if (Arrays.asList(operators).contains(lexeme)
                        || Arrays.asList(brackets).contains(lexeme)) {

                    //если приоритет операций одинаковый или приоритет текущей операции ниже
                    //то сначало выполняем действие, которое лежит в стеке
                    //до тех пор, пока в операциях не появится операция приоритетом ниже текущего
                    //или не встретится скобка или конец стека
                    if (!stackOperations.isEmpty()) {
                        String lastOper = stackOperations.peek();
                        Priority priority = new Priority(lastOper, lexeme);
                        do {
                            //если приоритет текущей выше, тогда операцию в стек
                            if (priority.isNeedCount()) {
                                StackHolder stackHolder = makeOperation(stackNumbers, stackOperations, lastOper);
                                stackNumbers = stackHolder.stackNumbers;
                                stackOperations = stackHolder.stackOperations;
                                if (!stackOperations.isEmpty()) {
                                    lastOper = stackOperations.peek();
                                    priority = new Priority(lastOper, lexeme);
                                }
                            }
                        }
                        while ((priority.isNeedCount()
                                && !stackNumbers.isEmpty()
                                && !stackOperations.isEmpty() && (lexeme != "(")) && (lastOper != "(" && lexeme == ")"));

                        //убрать открывающ скобку
                        if (lexeme == ")") {
                            stackOperations.pop();
                        }
                    }
                    //Внести новую операцию в стек
                    if (lexeme != ")") {
                        stackOperations.push(lexeme);
                    }
                }
                else
                    stackNumbers.push(Integer.parseInt(lexeme));
            }
            while (!stackNumbers.isEmpty() && !stackOperations.isEmpty()) {
                String lastOper = stackOperations.peek();
                StackHolder stackHolder = makeOperation(stackNumbers, stackOperations, lastOper);
                stackNumbers = stackHolder.stackNumbers;
                stackOperations = stackHolder.stackOperations;
            }
            int res = stackNumbers.peek();
            System.out.println("Ответ: " + res);
        }
        while (example!="");
    }
}
