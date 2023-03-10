public class ListenStream implements Executor {
    private final Command command;
    private final Stream streamToListen;
    private final Integer userId;

    public ListenStream(Command command, Stream streamToListen, Integer userId) {
        this.command = command;
        this.streamToListen = streamToListen;
        this.userId = userId;
    }

    public void execute() {
        command.listenStream(streamToListen, userId);
    }
}
