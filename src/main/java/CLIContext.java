//Concrete context witch uses to parse command arguments (with apache commons CLI)
import org.apache.commons.cli.*;

public class CLIContext implements Context {
    @Override
    public Expression evaluate(String s) {
        String stringForIP="";
        String stringForPorts="";
        String stringForThreads="";
        String stringForCommand;
        String [] args = s.split(" ");

        String [] commandLineArguments = new String[args.length];
        for (int i=0;i<commandLineArguments.length;i++){
            commandLineArguments[i]="";

        }
        if(commandLineArguments.length<3||args.length<5){
            throw new UnknownCommandException("Unsupported type of command");
        }
        commandLineArguments[0]=args[0]; //command "scan"
        stringForCommand = commandLineArguments[0];
        for (int i=1;i<3;i++){
            commandLineArguments[1]+=args[i]; // -h
        }
        for (int i=3;i<5;i++){
            commandLineArguments[2]+=args[i]; // -p
        }
        if(args.length==7){
            for (int i=5;i<7;i++){
                commandLineArguments[3]+=args[i]; // -t
            }
        }

        Option host = new Option("h",true,"Host IP");
        host.setArgs(Option.UNLIMITED_VALUES);
        host.setOptionalArg(false);
        host.setValueSeparator(',');

        Option port = new Option("p",true,"Port");
        port.setArgs(Option.UNLIMITED_VALUES);
        port.setOptionalArg(false);
        host.setValueSeparator(',');

        Option threads = new Option("t",true,"Threads");
        threads.setArgs(1);
        threads.setOptionalArg(false);


        Options options = new Options();
        options.addOption(host).addOption(port).addOption(threads);
        CommandLineParser commandLineParser = new PosixParser();

        try {
            CommandLine commandLine = commandLineParser.parse(options,commandLineArguments);
            if (commandLine.hasOption("h")){
                String [] stringOfHosts = commandLine.getOptionValues("h");
                for (int i=0;i<stringOfHosts.length;i++){
                    if (stringOfHosts[i]!=""&&stringOfHosts[i].length()>6&&stringOfHosts[i].length()<17){
                        stringForIP+=stringOfHosts[i];
                        if (stringOfHosts.length>1&&i<stringOfHosts.length-1&&stringOfHosts[i+1]!=""){
                            stringForIP+=",";
                        }
                    }else{
                        throw new UnknownCommandException("Unsupported type of command");
                    }
                }

            }
            if (commandLine.hasOption("p")){
                String [] stringOfPorts = commandLine.getOptionValues("p");
                for (int i=0;i<stringOfPorts.length;i++){
                    if (stringOfPorts[i]!=""){
                        stringForPorts+=stringOfPorts[i];
                        if (stringOfPorts.length>1&&i<stringOfPorts.length-1&&stringOfPorts[i+1]!=""){
                            stringForPorts+=",";
                        }
                    }
                }
            }else{
                throw new UnknownCommandException("Unsupported type of command");
            }
            if (commandLine.hasOption("t")){
                String [] stringOfThreads = commandLine.getOptionValues("t");
                for (int i=0;i<stringOfThreads.length;i++){
                    if (stringOfThreads[i]!=""){
                        stringForThreads+=stringOfThreads[i];
                        if (stringOfThreads.length>1&&i<stringOfThreads.length-1&&stringOfThreads[i+1]!=""){
                            stringForThreads+=",";
                        }
                    }
                }
            }
        }
        catch (ParseException e){
            e.printStackTrace();
        }

        String resultStringForAllCommandArguments = stringForCommand+" "+stringForIP+" "+stringForPorts;
        if (stringForThreads.length()>0){
            resultStringForAllCommandArguments+=" "+stringForThreads;
        }
    return new KeyExpression(resultStringForAllCommandArguments);
    }
}