import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProiectPOO {

    public static void main(String[] args) {
        if (args == null) {
            System.out.println("Nothing to read here");
            return;
        }
        Command commandExec = Command.getInstance();
        LinkedList<Streamer> streamers = new LinkedList<>();
        LinkedList<Stream> streams = new LinkedList<>();
        LinkedList<User> users = new LinkedList<>();
        Editor editor = new Editor();
        editor.events.add("AddStream", new AddStreamListener("./src/main/java/log.txt"));
        editor.events.add("LIST", new ListListener("./src/main/java/log.txt"));
        editor.events.add("DeleteStream", new DeleteStreamListener("./src/main/java/log.txt"));
        editor.events.add("Listen", new ListenStreamListener("./src/main/java/log.txt"));
        editor.events.add("Recommend", new RecommendListener("./src/main/java/log.txt"));
        editor.events.add("Surprise", new SurpriseListener("./src/main/java/log.txt"));
        ReadFile readStreamerFile = new ReadFile("./src/main/resources/" + args[0]);
        for (Iterator iter = readStreamerFile.getIterator(); iter.hasNext(); ) {
            String streamersLine = (String) iter.next();
            String[] streamerData = streamersLine.split(",");
            Streamer streamer = new StreamerBuilder()
                    .streamerType(Integer.parseInt(streamerData[0].split("\"")[1]))
                    .id(Integer.parseInt(streamerData[1].split("\"")[1]))
                    .name(streamerData[2].split("\"")[1])
                    .build();
            streamers.add(streamer);
            commandExec.getStreamers().add(streamer);
        }
        ReadFile readStreamFile = new ReadFile("./src/main/resources/" + args[1]);
        for (Iterator iter = readStreamFile.getIterator(); iter.hasNext(); ) {
            String streamsLine = (String) iter.next();
            String[] streamData = streamsLine.split("\"");
            String streamerName = null;
            Streamer save_streamer = null;
            for (Streamer s : streamers) {
                if (s.getId().equals(Integer.parseInt(streamData[9]))) {
                    streamerName = s.getName();
                    save_streamer = s;
                    break;
                }
            }
            Stream stream = new StreamBuilder()
                    .streamType(Integer.parseInt(streamData[1]))
                    .id(Integer.parseInt(streamData[3]))
                    .streamGenre(Integer.parseInt(streamData[5]))
                    .noOfStreams(Long.parseLong(streamData[7]))
                    .streamerId(Integer.parseInt(streamData[9]))
                    .length(Long.parseLong(streamData[11]))
                    .dateAdded(Long.parseLong(streamData[13]))
                    .name(streamData[15])
                    .streamerName(streamerName)
                    .build();
            commandExec.getStreams().add(stream);
            streams.add(stream);
            if (save_streamer != null)
                save_streamer.getStreams().add(stream);
        }
        ReadFile readUserFile = new ReadFile("./src/main/resources/" + args[2]);
        for (Iterator iter = readUserFile.getIterator(); iter.hasNext(); ) {
            List<Integer> userStreams = new ArrayList<>();
            List<Stream> userStreamsList = new ArrayList<>();
            String usersLine = (String) iter.next();
            String[] userData = usersLine.split(",");
            String[] userStreamsData = userData[2].split("\"")[1].split(" ");
            for (String s : userStreamsData) {
                userStreams.add(Integer.parseInt(s));
                for (Stream stream : streams) {
                    if (stream.getId().equals(Integer.parseInt(s))) {
                        userStreamsList.add(stream);
                        break;
                    }
                }
            }
            User user = new UserBuilder()
                    .id(Integer.parseInt(userData[0].split("\"")[1]))
                    .name(userData[1].split("\"")[1])
                    .streams(userStreams)
                    .build();
            commandExec.getUsers().add(user);
            users.add(user);
            users.getLast().getUserStreams().addAll(userStreamsList);
        }
        try (BufferedReader Fcommands = new BufferedReader(new FileReader("./src/main/resources/" + args[3]))) {
            String commandsLine = Fcommands.readLine();
            List<String> commands = new ArrayList<>();
            while (commandsLine != null) {
                commands.add(commandsLine);
                commandsLine = Fcommands.readLine();
            }
            CommandManager commandManager = CommandManager.getInstance();
            for (String command : commands) {
                String[] commandData = command.split(" ");
                /*
                voiam sa folosesc lambda expression la switch dar checker-ul de pe github

                nu le recunostea si dadea eroare
                switch(commandData[0]) {
                    case "ADD" -> {}
                    case "REMOVE" -> {}
                }
                 */
                switch (commandData[1]) {
                    case "ADD": {
                        Integer id = Integer.parseInt(commandData[0]);
                        Long date = System.currentTimeMillis() / 1000L;
                        String streamerName = null;
                        for (Streamer s : commandExec.getStreamers()) {
                            if (s.getId().equals(Integer.parseInt(commandData[0]))) {
                                streamerName = s.getName();
                                break;
                            }
                        }
                        Stream stream = new StreamBuilder()
                                .streamType(Integer.parseInt(commandData[2]))
                                .id(Integer.parseInt(commandData[3]))
                                .streamGenre(Integer.parseInt(commandData[4]))
                                .noOfStreams(0L)
                                .streamerId(id)
                                .length(Long.parseLong(commandData[5]))
                                .dateAdded(date)
                                .name(commandData[6])
                                .streamerName(streamerName)
                                .build();
                        int i = 7;
                        while (i < commandData.length) {
                            stream.setName(stream.getName() + " " + commandData[i]);
                            i++;
                        }
                        editor.streamNotify(stream.getId());
                        AddStream addStreamCommand = new AddStream(commandExec, stream, streamerName);
                        commandManager.addCommand(addStreamCommand);
                        break;
                    }
                    case "LIST": {
                        Integer streamerId = Integer.parseInt(commandData[0]);
                        editor.listNotify(streamerId);
                        commandExec.getStreamers().forEach((s) -> {
                            if (s.getId().equals(streamerId)) {
                                ListStreams listStreamsCommand = new ListStreams(commandExec, s);
                                commandManager.addCommand(listStreamsCommand);
                            }
                        });
                        commandExec.getUsers().forEach((u) -> {
                            if (u.getId().equals(streamerId)) {
                                ListStreams listStreamsCommand = new ListStreams(commandExec, u);
                                commandManager.addCommand(listStreamsCommand);
                            }
                        });
                        break;
                    }
                    case "DELETE": {
                        Integer streamerId = Integer.parseInt(commandData[0]);
                        Integer streamId = Integer.parseInt(commandData[2]);
                        Stream streamToDel = null;
                        for (Stream s : commandExec.getStreams()) {
                            if (s.getId().equals(streamId)) {
                                streamToDel = s;
                                break;
                            }
                        }
                        editor.deleteNotify(streamId);
                        DeleteStream deleteStreamCommand = new DeleteStream(commandExec, streamToDel, streamerId);
                        commandManager.addCommand(deleteStreamCommand);
                        break;
                    }
                    case "LISTEN": {
                        Integer userId = Integer.parseInt(commandData[0]);
                        Integer streamId = Integer.parseInt(commandData[2]);
                        Stream streamToListen = null;
                        for (Stream s : commandExec.getStreams()) {
                            if (s.getId().equals(streamId)) {
                                streamToListen = s;
                                break;
                            }
                        }
                        editor.listenNotify(streamId);
                        ListenStream listenStreamCommand = new ListenStream(commandExec, streamToListen, userId);
                        commandManager.addCommand(listenStreamCommand);
                        break;
                    }
                    case "RECOMMEND": {
                        Integer userId = Integer.parseInt(commandData[0]);
                        String streamType = commandData[2];
                        editor.recommendNotify(userId);
                        RecommendStream recommendStreamCommand = new RecommendStream(commandExec, userId, streamType);
                        commandManager.addCommand(recommendStreamCommand);
                        break;
                    }
                    case "SURPRISE": {
                        Integer userId = Integer.parseInt(commandData[0]);
                        String streamType = commandData[2];
                        editor.surpriseNotify(userId);
                        SurpriseStream surpriseStreamCommand = new SurpriseStream(commandExec, userId, streamType);
                        commandManager.addCommand(surpriseStreamCommand);
                        break;
                    }
                }
                commandManager.executeCommands();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
