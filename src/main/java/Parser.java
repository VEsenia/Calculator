package src.main.java;

import java.util.Iterator;

public class Parser implements Iterable{
    private char[] example;

    public Parser(char[] example) {
        this.example = example;
    }

    @Override
    public Iterator iterator() {
        return new LexemeIterator();
    }

    private class LexemeIterator implements java.util.Iterator {
        private int index;

        public boolean hasNext() {
            return index < example.length;
        }

        private int getPriority(String command)
        {
            return switch (command) {
                case "+", "-" -> 1;
                case "*", "/" -> 2;
                case "(",")" -> -1;
                default -> 0;
            };
        }

        private TypeLexeme getTypeLexeme(String value)
        {
            return switch (value) {
                case "+", "-","*", "/"  -> TypeLexeme.COMMAND;
                case "(",")" -> TypeLexeme.BRACKET;
                default -> null;
            };
        }

        public Lexeme next() {
            if (Character.isDigit(example[index])) {
                StringBuilder number = new StringBuilder();
                while (hasNext() && Character.isDigit(example[index])) {
                    number.append(example[index]);
                    index++;
                }
                Lexeme lexeme = new Lexeme(TypeLexeme.NUMBER, 0, number.toString());
                return lexeme;
            } else {
                String value = String.valueOf(example[index++]);
                Lexeme lexeme = new Lexeme(getTypeLexeme(value),
                        getPriority(value),
                        value);
                return lexeme;
            }
        }
    }
}

