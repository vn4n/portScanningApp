//factory witch creates command to execute (in this case it is "scan" command)
import java.util.List;

public class ScannerCommandFactory implements AbstractCommandsFactory {
List<String> commandString;
private Command command;
    public ScannerCommandFactory(List<String> commandString) {
        this.commandString = commandString;
    }

    @Override
    public Command getCommand() {
        switch (commandString.get(0)) {
            case ("scan"):
                switch (commandString.size()) {
                    case(3):command = new ScanCommand();
                    break;
                    case(4):command = new ScanCommandWithThreads();
                    break;
                    default:break;
                } break;
            //more commands
                default: throw new UnknownCommandException("Unsupported type of command");
        }
        return command;
        }

        public CommandParameters getCommandParameters() throws UnknownCommandException{
            return new CommandParameters(commandString);
        }

}
