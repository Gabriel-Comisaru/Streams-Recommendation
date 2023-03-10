public class ListStreams implements Executor {
    private final Command command;
    private final Object streamer_user;

    public ListStreams(Command command, Object streamer) {
        this.command = command;
        this.streamer_user = streamer;
    }

    @Override
    public void execute() {
        command.listStreams(streamer_user);
    }
}
