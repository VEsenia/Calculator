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

        public Object next() {
            if (Character.isDigit(example[index])) {
                    StringBuilder number = new StringBuilder();
                    while (hasNext() && Character.isDigit(example[index])) {
                        number.append(example[index]);
                        index++;
                    }
                    return number.toString();
                } else {
                    return String.valueOf(example[index++]);
                }
        }
    }
}

