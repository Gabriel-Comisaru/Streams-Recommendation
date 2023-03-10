public class AddStream implements Executor {
    private final Command command;

    private final Stream stream;
    private final String streamerName;

    public AddStream(Command command, Stream stream, String streamerName) {
        this.command = command;
        this.stream = stream;
        this.streamerName = streamerName;
    }

    @Override
    public void execute() {
        command.addStream(stream, streamerName);
    }
}
