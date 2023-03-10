import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Command {
    private static Command uniqueInstance;

    private final List<Stream> streams = new ArrayList<>();
    private final List<Streamer> streamers = new ArrayList<>();
    private final LinkedList<User> users = new LinkedList<>();

    private Command() {
    }

    public static Command getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Command();
        }
        return uniqueInstance;
    }

    public List<Stream> getStreams() {
        return streams;
    }

    public List<Streamer> getStreamers() {
        return streamers;
    }

    public LinkedList<User> getUsers() {
        return users;
    }

    public void addStream(Stream stream, String streamerName) {
        streams.add(stream);
        int flag = 0;
        for (Streamer streamer : streamers) {
            if (streamer.getName().equals(streamerName)) {
                streamer.getStreams().add(stream);
                flag = 1;
                break;
            }
        }
        if (flag == 0) {
            for (User user : users) {
                if (user.getName().equals(streamerName)) {
                    user.getUserStreams().add(stream);
                    break;
                }
            }
        }
    }

    public void listStreams(Object streamer) {
        System.out.print("[");
        if (streamer instanceof Streamer) {
            for (Stream s : ((Streamer) streamer).getStreams()) {
                System.out.print(s);
                if (s.equals(((Streamer) streamer).getStreams().get(((Streamer) streamer).getStreams().size() - 1))) {
                    System.out.print("");
                } else {
                    System.out.print(",");
                }
            }
        } else if (streamer instanceof User) {
            for (Stream s : ((User) streamer).getUserStreams()) {
                System.out.print(s);
                if (s.equals(((User) streamer).getUserStreams().get(((User) streamer).getUserStreams().size() - 1))) {
                    System.out.print("");
                } else {
                    System.out.print(",");
                }
            }
        }
        System.out.println("]");
    }

    public void deleteStream(Stream streamToDel, Integer streamerId) {
        for (Stream s : streams) {
            if (s.getId().equals(streamToDel.getId())) {
                streams.remove(s);
                break;
            }
        }
        int flag = 0;
        for (Streamer streamer : streamers) {
            if (streamer.getId().equals(streamerId)) {
                for (Stream s : streamer.getStreams()) {
                    if (s.getId().equals(streamToDel.getId())) {
                        streamer.getStreams().remove(s);
                        flag = 1;
                        break;
                    }
                }
            }
        }
        if (flag == 0) {
            for (User user : users) {
                if (user.getId().equals(streamerId)) {
                    for (Stream s : user.getUserStreams()) {
                        if (s.getId().equals(streamToDel.getId())) {
                            user.getUserStreams().remove(s);
                            break;
                        }
                    }
                }
            }
        }
    }

    public void listenStream(Stream streamToListen, Integer listenerId) {
        for (User user : users) {
            if (user.getId().equals(listenerId)) {
                user.getStreams().add(streamToListen.getId());
                user.getUserStreams().add(streamToListen);
                break;
            }
        }
        for (Stream s : streams) {
            if (s.getId().equals(streamToListen.getId())) {
                s.plusOneStream();
                break;
            }
        }
    }

    public void recommendStream(Integer userId, String streamType) {
        for (User u : users) {
            if (u.getId().equals(userId)) {
                u.updateStreams();
                System.out.print("[");
                switch (streamType) {
                    case "SONG":
                        for (Stream s : u.recomSongs) {
                            System.out.print(s);
                            if (s.equals(u.recomSongs.get(u.recomSongs.size() - 1))) {
                                System.out.print("");
                            } else {
                                System.out.print(",");
                            }
                        }
                        break;
                    case "PODCAST":
                        for (Stream s : u.recomPodcasts) {
                            System.out.print(s);
                            if (s.equals(u.recomPodcasts.get(u.recomPodcasts.size() - 1))) {
                                System.out.print("");
                            } else {
                                System.out.print(",");
                            }
                        }
                        break;
                    case "AUDIOBOOK":
                        for (Stream s : u.recomAudiobooks) {
                            System.out.print(s);
                            if (s.equals(u.recomAudiobooks.get(u.recomAudiobooks.size() - 1))) {
                                System.out.print("");
                            } else {
                                System.out.print(",");
                            }
                        }
                        break;
                }
                System.out.println("]");
                break;
            }
        }
    }

    public void surpriseStream(Integer userId, String streamType) {
        for (User u : users) {
            if (u.getId().equals(userId)) {
                u.updateStreams();
                System.out.print("[");
                switch (streamType) {
                    case "SONG":
                        PriorityQueue<Stream> aux = new PriorityQueue<>(new StreamComparator());
                        for (int i = 0; i < 3; i++) {
                            System.out.print(u.surpriseSongs.peek());
                            aux.add(u.surpriseSongs.poll());
                            if (i == 3 - 1) {
                                System.out.print("");
                            } else {
                                System.out.print(",");
                            }
                        }
                        u.surpriseSongs = aux;
                        break;
                    case "PODCAST":
                        PriorityQueue<Stream> aux2 = new PriorityQueue<>(new StreamComparator());
                        for (int i = 0; i < 3; i++) {
                            System.out.print(u.surprisePodcasts.peek());
                            aux2.add(u.surprisePodcasts.poll());
                            if (i == 3 - 1) {
                                System.out.print("");
                            } else {
                                System.out.print(",");
                            }
                        }
                        u.surprisePodcasts = aux2;
                        break;
                    case "AUDIOBOOK":
                        PriorityQueue<Stream> aux3 = new PriorityQueue<>(new StreamComparator());
                        for (int i = 0; i < 3; i++) {
                            System.out.print(u.surpriseAudiobooks.peek());
                            aux3.add(u.surpriseAudiobooks.poll());
                            if (i == 3 - 1) {
                                System.out.print("");
                            } else {
                                System.out.print(",");
                            }
                        }
                        u.surpriseAudiobooks = aux3;
                        break;
                }
                System.out.println("]");
                break;
            }
        }
    }
}
