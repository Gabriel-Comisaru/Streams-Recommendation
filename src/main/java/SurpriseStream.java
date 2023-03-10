public class SurpriseStream implements Executor {
    private final Command command;
    private final Integer userId;
    private final String streamType;

    public SurpriseStream(Command command, Integer userId, String streamType) {
        this.command = command;
        this.userId = userId;
        this.streamType = streamType;
    }

    public void execute() {
        command.surpriseStream(userId, streamType);
    }
}
