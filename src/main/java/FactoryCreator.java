//class witch uses to create factory. Factory returns concrete command;
public class FactoryCreator {
    private Visitor visitor;
    private Command command;
    private AbstractCommandsFactory factory;
    public FactoryCreator(Visitor visitor) {
        this.visitor = visitor;

    }

    public AbstractCommandsFactory getFactory(String s){
         factory = visitor.visit(s);
         return factory;
    }






}




