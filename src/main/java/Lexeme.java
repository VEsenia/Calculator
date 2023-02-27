package src.main.java;

public class Lexeme {
    private TypeLexeme type;
    private Integer priority;
    private String value;

    public TypeBracket typeBracket;

    public Lexeme(TypeLexeme type, Integer priority, String value) {
        this.type = type;
        this.priority = priority;
        this.value = value;

        if(type == TypeLexeme.BRACKET)
        {
            typeBracket = switch (value) {
                case "(" -> TypeBracket.OPEN;
                case ")" -> TypeBracket.CLOSE;
                default -> null;
            };
        }
        else
        {
            typeBracket = null;
        }
    }

    public TypeLexeme getType() {
        return type;
    }

    public Integer getPriority() {
        return priority;
    }

    public String getValue() {
        return value;
    }
}
