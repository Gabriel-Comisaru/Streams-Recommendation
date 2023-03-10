import java.util.ArrayList;

public class Streamer extends DataType {
    private Integer streamerType;
    private final ArrayList<Stream> streams = new ArrayList<>();

    public Streamer() {
    }

    @Override
    public String toString() {
        return "Streamer{" + "streamerType=" + streamerType + ", id=" + getId() + ", name=" + getName() + '}';
    }

    public ArrayList<Stream> getStreams() {
        return streams;
    }

    public void setStreamerType(Integer streamerType) {
        this.streamerType = streamerType;
    }

}
