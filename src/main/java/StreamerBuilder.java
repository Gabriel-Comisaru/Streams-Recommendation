public class StreamerBuilder {
    private final Streamer streamer = new Streamer();

    public StreamerBuilder streamerType(Integer streamerType) {
        streamer.setStreamerType(streamerType);
        return this;
    }

    public StreamerBuilder id(Integer id) {
        streamer.setId(id);
        return this;
    }

    public StreamerBuilder name(String name) {
        streamer.setName(name);
        return this;
    }

    public Streamer build() {
        return streamer;
    }
}
