import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private static CommandManager uniqueInstance;

    private CommandManager() {
    }

    public static CommandManager getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CommandManager();
        }
        return uniqueInstance;
    }

    private final List<Executor> commands = new ArrayList<>();

    public void addCommand(Executor command) {
        commands.add(command);
    }

    public void executeCommands() {
        for (Executor command : commands) {
            command.execute();
        }
        commands.clear();
    }
}
