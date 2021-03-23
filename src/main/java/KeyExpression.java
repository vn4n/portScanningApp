//expression witch return String value
public class KeyExpression implements Expression {
    private String key;
    public KeyExpression(String key) {
        this.key = key;
    }

    @Override
    public String interpret() {
        return key;
    }
}
