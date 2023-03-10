public class DeleteStream implements Executor {
    private final Command command;
    private final Stream streamToDel;
    private final Integer streamerId;

    public DeleteStream(Command command, Stream streamToDel, Integer streamerId) {
        this.command = command;
        this.streamToDel = streamToDel;
        this.streamerId = streamerId;
    }

    @Override
    public void execute() {
        command.deleteStream(streamToDel, streamerId);
    }
}
