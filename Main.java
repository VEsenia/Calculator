import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static boolean isNotEmptyStacks(Stack<Lexeme> stackNumbers, Stack<Lexeme> stackOperations) {
        return !stackNumbers.isEmpty()
                && !stackOperations.isEmpty();
    }

    public static void main(String[] args) {

        String example = " ";
        while (!example.equals("")) {
            System.out.println("Введите пример");
            Scanner in = new Scanner(System.in, StandardCharsets.UTF_8);
            example = in.nextLine();
            if (!example.equals("")) {
                Stack<Lexeme> stackNumbers = new Stack<Lexeme>();
                Stack<Lexeme> stackOperations = new Stack<Lexeme>();

                example = example.replace(" ", "");

                Parser parser = new Parser(example.toCharArray());
                for (Object lexOb : parser) {
                    Lexeme curLexeme = (Lexeme) lexOb;
                    TypeLexeme typeLexeme = curLexeme.getType();
                    if (typeLexeme == typeLexeme.COMMAND || typeLexeme == typeLexeme.BRACKET) {
                        //если приоритет операций одинаковый или приоритет текущей операции ниже
                        //то сначало выполняем действие, которое лежит в стеке
                        //до тех пор, пока в операциях не появится операция приоритетом ниже текущего
                        //или не встретится скобка или конец стека
                        if (!stackOperations.isEmpty()) {
                            Lexeme lastOperLexeme = stackOperations.peek();
                            StackHolder stackHolder = new StackHolder(stackNumbers, stackOperations);
                            while (((!isNotEmptyStacks(stackNumbers, stackOperations)
                                    && (typeLexeme == TypeLexeme.COMMAND)
                                    )
                                    || (typeLexeme == TypeLexeme.BRACKET
                                       && curLexeme.typeBracket==TypeBracket.CLOSE
                                       && lastOperLexeme.typeBracket!=TypeBracket.OPEN))
                                    && stackHolder.wasCount
                            ) {
                                //если приоритет текущей выше, тогда операцию в стек
                                //или если текущая закрыв скобка и не встретили открывающую
                                stackHolder = new StackHolder(stackNumbers, stackOperations);
                                stackHolder = stackHolder.makeOperation(lastOperLexeme, curLexeme);
                                stackNumbers = stackHolder.stackNumbers;
                                stackOperations = stackHolder.stackOperations;
                                if (!stackOperations.isEmpty()) {
                                    lastOperLexeme = stackOperations.peek();
                                }

                                //вытащить открывающуюся скобку из стека, после расчета
                                if (lastOperLexeme.typeBracket == TypeBracket.OPEN
                                        && curLexeme.typeBracket == TypeBracket.CLOSE)
                                    stackOperations.pop();
                            }
                        }
                        //Внести новую операцию в стек
                        if (curLexeme.typeBracket != TypeBracket.CLOSE) {
                            stackOperations.push(curLexeme);
                        }
                    } else {
                        stackNumbers.push(curLexeme);
                    }
                }

                //обработать оставшиеся в стеке операции
                while (!isNotEmptyStacks(stackNumbers, stackOperations)) {
                    Lexeme lastOperLexeme = stackOperations.peek();
                    StackHolder stackHolder = new StackHolder(stackNumbers, stackOperations);
                    stackHolder = stackHolder.makeOperation(lastOperLexeme);
                    stackNumbers = stackHolder.stackNumbers;
                    stackOperations = stackHolder.stackOperations;
                }
                Lexeme resLexeme = stackNumbers.peek();
                System.out.println("Ответ: " + resLexeme.getValue());
            }
        }
    }
}
