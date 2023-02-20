public class Priority {

    private String lastOper;
    private String CurOper;

    public Priority(String lastOper, String curOper) {
        this.lastOper = lastOper;
        CurOper = curOper;
    }

    //Вывод приоритета оператора
    private int getPriority(String operator)
    {
        return switch (operator) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            default -> 0;
        };
    }

    public boolean isNeedCount()
    {
        int lastOperPr = getPriority(lastOper);
        int curOperPr = getPriority(CurOper);
        //если приоритет текущей выше, тогда операцию в стек
         return ((curOperPr <= lastOperPr) && !CurOper.equals("("));
    }


}
