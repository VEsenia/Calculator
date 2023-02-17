public class Parser implements Collection{
    private char[] example;

    public Parser(char[] example) {
        this.example = example;
    }

    @Override
    public Iterator getIterator() {
        return new LexemeIterator();
    }

    private class LexemeIterator implements Iterator{
        private int index;

        public boolean hasNext() {
            if(index < example.length)
            {
                return true;
            }
            return false;
        }

        public Object next() {
            if(Character.isDigit((Character)example[index]))
            {
                StringBuilder number = new StringBuilder();
                while(Character.isDigit((Character)example[index])) {
                    number.append(example[index]);
                    index++;
                }
                return number.toString();
            }
            else
            {
                return String.valueOf(example[index++]);
            }
        }
    };
}

