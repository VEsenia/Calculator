import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        String example = " ";
        while (!example.equals("")) {
            System.out.println("Введите пример");
            Scanner in = new Scanner(System.in, StandardCharsets.UTF_8);
            example = in.nextLine();
            if (!example.equals("")) {
                String[] operators = {"+", "-", "/", "*"};
                Stack<Integer> stackNumbers = new Stack<Integer>();
                Stack<String> stackOperations = new Stack<String>();

                example = example.replace(" ", "");

                Parser parser = new Parser(example.toCharArray());
                for (Object ob : parser) {
                    String lexeme = (String) ob;
                    boolean isOperator = Arrays.asList(operators).contains(lexeme);
                    boolean isOpenBracket = lexeme.equals("(");
                    boolean isCloseBracket = lexeme.equals(")");
                    if (isOperator || isOpenBracket || isCloseBracket) {
                        //если приоритет операций одинаковый или приоритет текущей операции ниже
                        //то сначало выполняем действие, которое лежит в стеке
                        //до тех пор, пока в операциях не появится операция приоритетом ниже текущего
                        //или не встретится скобка или конец стека
                        if (!stackOperations.isEmpty()) {
                            String lastOper = stackOperations.peek();
                            Priority priority = new Priority(lastOper, lexeme);
                            boolean isLstOperOpenBracket = lastOper.equals("(");
                            while ((priority.isNeedCount()
                                    && !stackNumbers.isEmpty()
                                    && !stackOperations.isEmpty()
                                    && !isOpenBracket && !isLstOperOpenBracket
                                   )
                                    || (!isLstOperOpenBracket && isCloseBracket)) {
                                //если приоритет текущей выше, тогда операцию в стек
                                StackHolder stackHolder = new StackHolder(stackNumbers, stackOperations);
                                stackHolder = stackHolder.makeOperation(lastOper);
                                stackNumbers = stackHolder.stackNumbers;
                                stackOperations = stackHolder.stackOperations;
                                if (!stackOperations.isEmpty()) {
                                    lastOper = stackOperations.peek();
                                    isLstOperOpenBracket = lastOper.equals("(");
                                    priority = new Priority(lastOper, lexeme);
                                }

                                //вытащить открывающуюся скобку из стека, после расчета
                                if(isLstOperOpenBracket && isCloseBracket)
                                    stackOperations.pop();
                            }
                        }
                        //Внести новую операцию в стек
                        if (!isCloseBracket) {
                            stackOperations.push(lexeme);
                        }
                    } else {
                        stackNumbers.push(Integer.parseInt(lexeme));
                    }
                }

                //обработать оставшиеся в стеке операции
                while (!stackNumbers.isEmpty() && !stackOperations.isEmpty()) {
                    String lastOper = stackOperations.peek();
                    StackHolder stackHolder = new StackHolder(stackNumbers, stackOperations);
                    stackHolder = stackHolder.makeOperation(lastOper);
                    stackNumbers = stackHolder.stackNumbers;
                    stackOperations = stackHolder.stackOperations;
                }
                int res = stackNumbers.peek();
                System.out.println("Ответ: " + res);
            }
        }
    }
}
