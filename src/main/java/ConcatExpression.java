//expression witch concatenates two strings (using in context)
public class ConcatExpression implements Expression {
    Expression one;
    Expression two;

    public ConcatExpression(Expression one, Expression two) {
        this.one = one;
        this.two = two;
    }

    @Override
    public String interpret() {
        return one.interpret()+" "+two.interpret();
    }
}
