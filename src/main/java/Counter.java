package src.main.java;

public class Counter {
    private String operator;
    private Command sumCommand;
    private Command subCommand;
    private Command multiplyCommand;
    private Command divCommand;

    public Counter(String operator, Command sumCommand, Command subCommand, Command multiplyCommand, Command divCommand) {
        this.sumCommand = sumCommand;
        this.subCommand = subCommand;
        this.multiplyCommand = multiplyCommand;
        this.divCommand = divCommand;
        this.operator = operator;
    }

    public Lexeme getResult()
    {
        return switch (operator) {
            case "+" -> sumCommand.execute();
            case "-" -> subCommand.execute();
            case "*" -> multiplyCommand.execute();
            case "/" -> divCommand.execute();
            default -> null;
        };
    }
}
