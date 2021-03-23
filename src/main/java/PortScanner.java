//main class
public class PortScanner {
    public static void main(String[] args) {

        //---first way of parsing---
       Expression expression = new CLIContext().evaluate(String.join(" ",args));

        //---second way of parsing---
       //Expression expression = new ScanPortsContext().evaluate(new PrepareContext().evaluate(String.join(" ",args)).interpret());


        Runner runner = new Runner(expression); //command's creator and configurator
        runner.generateCommand(); //generate command and configure it

        runner.scan();  //execute scanning command with parameters


    }

}


