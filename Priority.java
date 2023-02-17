import java.util.Stack;

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
        switch(operator) {
            case "+", "-" :
                return 1;
            case "*","/" :
                return 2;
        }
        return 0;
    }

    public boolean isNeedCount()
    {
        int lastOperPr = getPriority(lastOper);
        int curOperPr = getPriority(CurOper);
        //если приоритет текущей выше, тогда операцию в стек
         return ((curOperPr <= lastOperPr) && CurOper != "(");
    }


}
