//my context to parsing command line (could be better)
public class ScanPortsContext implements Context {
    @Override
    public Expression evaluate(String s) {
        Expression one=null;
        Expression two=null;
        int pos = 0;

        if (s.length()<17){
            throw new UnknownCommandException("Bad command");
        }

        while (pos<s.length()-1){
            if (s.charAt(pos)!='-'){

                if(s.charAt(pos)=='t'){
                    return new KeyExpression(s.substring(pos+1,s.length()));
                }
                pos++;
            }else if ((s.charAt(pos+1)=='h'||s.charAt(pos+1)=='p'||s.charAt(pos+1)=='t')) {
                    one = evaluate(s.substring(pos+1, s.length()));
                    two = new KeyExpression(s.substring(1, pos));
                    return new ConcatExpression(two,one);
                }else{
                    pos++;
                }

                }
        return new ConcatExpression(two,one);
    }

}
