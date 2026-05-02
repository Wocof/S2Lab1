package commands;

public interface ArgumentCommand extends ExecuteCommand {
    boolean executeCommand(String[] args);
}
