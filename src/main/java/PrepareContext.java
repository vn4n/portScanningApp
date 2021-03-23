//class witch prepared command line's arguments for parsing in ScanPortContext
public class PrepareContext implements Context {
    @Override
    public Expression evaluate(String s) {
        String resultString=".";
        String [] tmp = s.split(" ");
        for (String str:tmp) {
            resultString += str;
        }
        return new KeyExpression(resultString);
    }
}
