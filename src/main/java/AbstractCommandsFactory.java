//Abstract factory witch uses to create command
public interface AbstractCommandsFactory {
    public Command getCommand();

    public CommandParameters getCommandParameters();
}
