import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.io.StringReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {

    private static final String USERS_FILE = "users.csv";
    private static final String STREAMERS_FILE = "streamers.csv";
    private static final String STREAMS_FILE = "streams.csv";
    private static final String COMMANDS_FILE = "commands.txt";
    private static final String REFERENCE_FILE = "reference.txt";

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testTheTest() {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));

        ProiectPOO.main(null);

        assert (outPrintStream.toString().trim()).equals("Nothing to read here");
        System.setOut(System.out);
    }


    @Test
    public void test1ListSongsFromArtists() throws IOException {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));
        String inputFolder = "inputs1/";
        String commandFolder = "test1/";

        ProiectPOO.main(getInputArgs(inputFolder, commandFolder));
        String output = outPrintStream.toString();

        assertJsonLineAreEqual(output, commandFolder);

        System.setOut(System.out);
    }

    @Test
    public void test2AddStream() throws IOException {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));
        String inputFolder = "inputs1/";
        String commandFolder = "test2/";

        ProiectPOO.main(getInputArgs(inputFolder, commandFolder));
        String output = outPrintStream.toString();

        assertJsonLineAreEqual(output, commandFolder, true);

        System.setOut(System.out);
    }

    @Test
    public void test3DeleteStream() throws IOException {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));
        String inputFolder = "inputs1/";
        String commandFolder = "test3/";

        ProiectPOO.main(getInputArgs(inputFolder, commandFolder));
        String output = outPrintStream.toString();

        assertJsonLineAreEqual(output, commandFolder, true);

        System.setOut(System.out);
    }

    @Test
    public void test4ListenToStream() throws IOException {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));
        String inputFolder = "inputs1/";
        String commandFolder = "test4/";

        ProiectPOO.main(getInputArgs(inputFolder, commandFolder));
        String output = outPrintStream.toString();

        assertJsonLineAreEqual(output, commandFolder, false, true);

        System.setOut(System.out);
    }

    @Test
    public void test5RecommendSongs() throws IOException {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));
        String inputFolder = "inputs1/";
        String commandFolder = "test5/";

        ProiectPOO.main(getInputArgs(inputFolder, commandFolder));
        String output = outPrintStream.toString();

        assertJsonLineAreEqual(output, commandFolder, true);

        System.setOut(System.out);
    }

    @Test
    public void test6SurpriseSongs() throws IOException {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));
        String inputFolder = "inputs1/";
        String commandFolder = "test6/";

        ProiectPOO.main(getInputArgs(inputFolder, commandFolder));
        String output = outPrintStream.toString();

        assertJsonLineAreEqual(output, commandFolder);

        System.setOut(System.out);
    }

    @Test
    public void test7ListPodcastsByPodcastAuthor() throws IOException {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));
        String inputFolder = "inputs2/";
        String commandFolder = "test7/";

        ProiectPOO.main(getInputArgs(inputFolder, commandFolder));
        String output = outPrintStream.toString();

        assertJsonLineAreEqual(output, commandFolder, false, true);

        System.setOut(System.out);
    }

    @Test
    public void test8ListAudiobooksByAuthor() throws IOException {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));
        String inputFolder = "inputs2/";
        String commandFolder = "test8/";

        ProiectPOO.main(getInputArgs(inputFolder, commandFolder));
        String output = outPrintStream.toString();

        assertJsonLineAreEqual(output, commandFolder, false, true);

        System.setOut(System.out);
    }

    @Test
    public void test9ListUsersMultipleStreams() throws IOException {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));
        String inputFolder = "inputs2/";
        String commandFolder = "test9/";

        ProiectPOO.main(getInputArgs(inputFolder, commandFolder));
        String output = outPrintStream.toString();

        assertJsonLineAreEqual(output, commandFolder, false, true);

        System.setOut(System.out);
    }

    @Test
    public void test10AddMultipleStreams() throws IOException {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));
        String inputFolder = "inputs2/";
        String commandFolder = "test10/";

        ProiectPOO.main(getInputArgs(inputFolder, commandFolder));
        String output = outPrintStream.toString();

        assertJsonLineAreEqual(output, commandFolder, true);

        System.setOut(System.out);
    }

    @Test
    public void test11RecommendStreamPodcast() throws IOException {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));
        String inputFolder = "inputs2/";
        String commandFolder = "test11/";

        ProiectPOO.main(getInputArgs(inputFolder, commandFolder));
        String output = outPrintStream.toString();

        assertJsonLineAreEqual(output, commandFolder);

        System.setOut(System.out);
    }

    @Test
    public void test12RecommendStreamAudiobook() throws IOException {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));
        String inputFolder = "inputs2/";
        String commandFolder = "test12/";

        ProiectPOO.main(getInputArgs(inputFolder, commandFolder));
        String output = outPrintStream.toString();

        assertJsonLineAreEqual(output, commandFolder, true);

        System.setOut(System.out);
    }

    @Test
    public void test13MultipleSurpriseStreamRecommendation() throws IOException {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));
        String inputFolder = "inputs2/";
        String commandFolder = "test13/";

        ProiectPOO.main(getInputArgs(inputFolder, commandFolder));
        String output = outPrintStream.toString();

        assertJsonLineAreEqual(output, commandFolder, true);

        System.setOut(System.out);
    }

    @Test
    public void test14AddAndRecommendPodcasts() throws IOException {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));
        String inputFolder = "inputs2/";
        String commandFolder = "test14/";

        ProiectPOO.main(getInputArgs(inputFolder, commandFolder));
        String output = outPrintStream.toString();

        assertJsonLineAreEqual(output, commandFolder, true);

        System.setOut(System.out);
    }

    @Test
    public void test15MixedActions() throws IOException {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));
        String inputFolder = "inputs2/";
        String commandFolder = "test15/";

        ProiectPOO.main(getInputArgs(inputFolder, commandFolder));
        String output = outPrintStream.toString();

        assertJsonLineAreEqual(output, commandFolder, true);

        System.setOut(System.out);
    }

    private String[] getInputArgs(String inputFolder, String testFolder) {
        return new String[]{inputFolder + STREAMERS_FILE,
                inputFolder + STREAMS_FILE,
                inputFolder + USERS_FILE,
                testFolder + COMMANDS_FILE};
    }

    private void assertJsonLineAreEqual(String actualOutput, String inputFolder)
            throws IOException {
        assertJsonLineAreEqual(actualOutput, inputFolder, false, false);
    }

    private void assertJsonLineAreEqual(String actualOutput, String inputFolder, boolean ignoreDate)
            throws IOException {
        assertJsonLineAreEqual(actualOutput, inputFolder, ignoreDate, false);
    }

    private void assertJsonLineAreEqual(String actualOutput, String inputFolder, boolean ignoreDate,
                                        boolean ignoreOrder)
            throws IOException {

        BufferedReader
                expectedOutput =
                new BufferedReader(new FileReader(
                        System.getProperty("user.dir") + "/src/test/resources/" + inputFolder
                                + REFERENCE_FILE));
        BufferedReader actualOutputFile = new BufferedReader(new StringReader(actualOutput));
        String expectedJsonLine;
        String actualJsonLine;
        while ((expectedJsonLine = expectedOutput.readLine()) != null) {
            actualJsonLine = actualOutputFile.readLine();
            if (actualJsonLine == null) {
                Assertions.fail();
            }

            JsonNode jsonNodeActual = objectMapper.readTree(actualJsonLine.toLowerCase());
            JsonNode jsonNodeExpected = objectMapper.readTree(expectedJsonLine.toLowerCase());

            if (ignoreDate) {
                jsonNodeActual = ((ArrayNode) jsonNodeActual).remove(6);
                jsonNodeExpected = ((ArrayNode) jsonNodeExpected).remove(6);
            }

            if (ignoreOrder) {
                List<StreamOutput>
                        actualStreams =
                        objectMapper.readValue(actualJsonLine,
                                new TypeReference<LinkedList<StreamOutput>>() {
                                });
                List<StreamOutput>
                        expectedStreams =
                        objectMapper.readValue(actualJsonLine,
                                new TypeReference<LinkedList<StreamOutput>>() {
                                });
                Collections.sort(actualStreams);
                Collections.sort(expectedStreams);
                assertEquals(actualStreams, expectedStreams);
            } else {
                assertEquals(jsonNodeExpected, jsonNodeActual);
            }
        }
    }


    public static class StreamOutput implements Comparable<StreamOutput>, Serializable {
        @JsonProperty
        private String id;
        @JsonProperty
        private String name;
        @JsonProperty
        private String streamerName;
        @JsonProperty
        private String noOfListenings;
        @JsonProperty
        private String length;
        @JsonProperty
        private String dateAdded;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getStreamerName() {
            return streamerName;
        }

        public String getNoOfListenings() {
            return noOfListenings;
        }

        public String getLength() {
            return length;
        }

        public String getDateAdded() {
            return dateAdded;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setStreamerName(String streamerName) {
            this.streamerName = streamerName;
        }

        public void setNoOfListenings(String noOfListenings) {
            this.noOfListenings = noOfListenings;
        }

        public void setLength(String length) {
            this.length = length;
        }

        public void setDateAdded(String dateAdded) {
            this.dateAdded = dateAdded;
        }

        @Override
        public int compareTo(StreamOutput o) {
            return this.id.compareTo(o.getId());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof StreamOutput)) {
                return false;
            }

            StreamOutput that = (StreamOutput) o;

            if (!Objects.equals(id, that.id)) {
                return false;
            }
            if (!Objects.equals(name, that.name)) {
                return false;
            }
            if (!Objects.equals(streamerName, that.streamerName)) {
                return false;
            }
            if (!Objects.equals(noOfListenings, that.noOfListenings)) {
                return false;
            }
            if (!Objects.equals(length, that.length)) {
                return false;
            }
            return Objects.equals(dateAdded, that.dateAdded);
        }

        @Override
        public int hashCode() {
            int result = id != null ? id.hashCode() : 0;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            result = 31 * result + (streamerName != null ? streamerName.hashCode() : 0);
            result = 31 * result + (noOfListenings != null ? noOfListenings.hashCode() : 0);
            result = 31 * result + (length != null ? length.hashCode() : 0);
            result = 31 * result + (dateAdded != null ? dateAdded.hashCode() : 0);
            return result;
        }
    }
}