import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class User extends DataType {
    List<Integer> streamsId;
    ArrayList<Stream> streams = new ArrayList<>();
    ArrayList<Streamer> streamers = new ArrayList<>();
    ArrayList<Stream> recomSongs = new ArrayList<>();
    ArrayList<Stream> recomPodcasts = new ArrayList<>();
    ArrayList<Stream> recomAudiobooks = new ArrayList<>();
    PriorityQueue<Stream> surpriseSongs = new PriorityQueue<>(new StreamComparator());
    PriorityQueue<Stream> surprisePodcasts = new PriorityQueue<>(new StreamComparator());
    PriorityQueue<Stream> surpriseAudiobooks = new PriorityQueue<>(new StreamComparator());

    ArrayList<Stream> notSurprStreams = new ArrayList<>();

    public User() {
    }

    @Override
    public String toString() {
        return "User{" + "id=" + getId() + ", name=" + getName() + ", streams=" + streamsId + '}';
    }

    public ArrayList<Stream> getUserStreams() {
        return streams;
    }

    public void setStreams(List<Integer> streams) {
        this.streamsId = streams;
    }

    public List<Integer> getStreams() {
        return streamsId;
    }

    public void updateStreams() {
        recomSongs.clear();
        recomPodcasts.clear();
        recomAudiobooks.clear();
        surpriseSongs.clear();
        surprisePodcasts.clear();
        surpriseAudiobooks.clear();
        notSurprStreams.clear();
        for (Stream userStream : streams) {
            for (Stream stream : Command.getInstance().getStreams()) {
                if (stream.getStreamerId().equals(userStream.getStreamerId()) && !streams.contains(stream)) {
                    if (stream.getType().equals("song")) {
                        recomSongs.add(stream);
                    } else if (stream.getType().equals("podcast")) {
                        recomPodcasts.add(stream);
                    } else if (stream.getType().equals("audiobook")) {
                        recomAudiobooks.add(stream);
                    }
                }
            }
        }
        for (Stream stream : streams) {
            for (Streamer streamer : Command.getInstance().getStreamers()) {
                if (streamer.getId().equals(stream.getStreamerId())) {
                    streamers.add(streamer);
                }
            }
        }
        for (Streamer streamer : streamers) {
            notSurprStreams.addAll(streamer.getStreams());
        }
        for (Stream stream : Command.getInstance().getStreams()) {
            if (!notSurprStreams.contains(stream)) {
                if (stream.getType().equals("song")) {
                    surpriseSongs.add(stream);
                } else if (stream.getType().equals("podcast")) {
                    surprisePodcasts.add(stream);
                } else if (stream.getType().equals("audiobook")) {
                    surpriseAudiobooks.add(stream);
                }
            }
        }
    }
}


