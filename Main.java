import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    //Вывод приоритета оператора
    private static int getPriority(char operator)
    {
        switch(operator) {
            case '+', '-' :
                return 1;
            case '*','/' :
                return 2;
        }
        return 0;
    }

    private static Integer getResult(Integer number1, Integer number2, char operator)
    {
        switch (operator) {
            case '+':
                return number2 + number1;
            case '-':
                return number2 - number1;
            case '*':
                return number2 * number1;
            case '/':
                return number2 / number1;
        }
        return 0;
    }

    private static StackHolder makeOperation(Stack<Integer> stackNumbers, Stack<Character> stackOperations, char operation)
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

            Character[] operators = {'+', '-', '/', '*'};
            Character[] brackets = {'(', ')'};
            Stack<Integer> stackNumbers = new Stack<Integer>();
            Stack<Character> stackOperations = new Stack<Character>();

            example.replace(" ", "");
            StringBuilder strNumber = new StringBuilder();
            for (char ch : example.toCharArray()) {
                if (Arrays.asList(operators).contains(ch)
                        || Arrays.asList(brackets).contains(ch)) {

                    //если до оператора было число, то вставляем его в стек с числами
                    if (strNumber.toString() != "") {
                        Integer number = Integer.parseInt(strNumber.toString());
                        stackNumbers.push(number);
                        strNumber.setLength(0);
                    }

                    //если приоритет операций одинаковый или приоритет текущей операции ниже
                    //то сначало выполняем действие, которое лежит в стеке
                    //до тех пор, пока в операциях не появится операция приоритетом ниже текущего
                    //или не встретится скобка или конец стека
                    if (!stackOperations.isEmpty()) {
                        char lastOper = stackOperations.peek();
                        int lastOperPr = getPriority(lastOper);
                        int curOperPr = getPriority(ch);
                        do {
                            //если приоритет текущей выше, тогда операцию в стек
                            if ((curOperPr < lastOperPr) && ch != '(') {
                                StackHolder stackHolder = makeOperation(stackNumbers, stackOperations, lastOper);
                                stackNumbers = stackHolder.stackNumbers;
                                stackOperations = stackHolder.stackOperations;
                                if (!stackOperations.isEmpty()) {
                                    lastOper = stackOperations.peek();
                                    lastOperPr = getPriority(lastOper);
                                }
                            }
                        }
                        while (((curOperPr <= lastOperPr)
                                && !stackNumbers.isEmpty()
                                && !stackOperations.isEmpty() && (ch != '(')) && (lastOper != '(' && ch == ')'));

                        //убрать открывающ скобку
                        if (ch == ')') {
                            stackOperations.pop();
                        }
                    }
                    //Внести новую операцию в стек
                    if (ch != ')') {
                        stackOperations.push(ch);
                    }
                }
                if (Character.isDigit(ch)) {
                    strNumber.append(ch);
                }
            }

            if (!strNumber.isEmpty()) {
                Integer number = Integer.parseInt(strNumber.toString());
                stackNumbers.push(number);
                strNumber.setLength(0);
            }
            while (!stackNumbers.isEmpty() && !stackOperations.isEmpty()) {
                char lastOper = stackOperations.peek();
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
