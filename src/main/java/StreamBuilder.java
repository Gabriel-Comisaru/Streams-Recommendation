public class StreamBuilder {
    private final Stream stream = new Stream();

    public StreamBuilder streamType(Integer streamType) {
        stream.setStreamType(streamType);
        return this;
    }

    public StreamBuilder id(Integer id) {
        stream.setId(id);
        return this;
    }

    public StreamBuilder streamGenre(Integer streamGenre) {
        stream.setStreamGenre(streamGenre);
        return this;
    }

    public StreamBuilder noOfStreams(Long noOfStreams) {
        stream.setNoOfStreams(noOfStreams);
        return this;
    }

    public StreamBuilder streamerId(Integer streamerId) {
        stream.setStreamerId(streamerId);
        return this;
    }

    public StreamBuilder length(Long length) {
        stream.setLength(length);
        return this;
    }

    public StreamBuilder dateAdded(Long dateAdded) {
        stream.setDateAdded(dateAdded);
        return this;
    }

    public StreamBuilder name(String name) {
        stream.setName(name);
        return this;
    }

    public StreamBuilder streamerName(String streamerName) {
        stream.setStreamerName(streamerName);
        return this;
    }

    public Stream build() {
        return stream;
    }
}
