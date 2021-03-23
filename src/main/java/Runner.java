//class witch creates logic of program
import org.apache.commons.collections.map.MultiValueMap;

public class Runner {

    Expression expression;
    MultiValueMap poolForScanning;
    AbstractCommandsFactory factory;
    CommandParameters commandParameters;

    public Runner(Expression expression) {
        this.expression = expression;
    }

    public void generateCommand(){

       // AbstractCommandsFactory factory = new FactoryCreator(new PortScannerVisitor()).getFactory(expression.interpret());
        FactoryCreator factoryCreator = new FactoryCreator(new PortScannerVisitor());
        factory = factoryCreator.getFactory(expression.interpret()); //factory creating
        commandParameters = factory.getCommandParameters(); //class witch keep and return parameters
        PoolCreator poolCreator = new PoolCreator(); //creating pool class
        poolForScanning = poolCreator.setPool(commandParameters.getIP(),commandParameters.getPort()); //pool of IP/port creating



    }

        public void scan (){

            Command scanCommand = factory.getCommand(); //get command from factory
            scanCommand.execute(poolForScanning,commandParameters.getNumberOfThreats()); //execute command

        }
}
