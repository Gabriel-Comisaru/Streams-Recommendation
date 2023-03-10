
public class Editor {
    public EventManager events;

    public Editor() {
        this.events = new EventManager("AddStream", "LIST", "DeleteStream", "Listen", "Recommend", "Surprise");
    }

    public void streamNotify(Integer streamId) {
        events.notify("AddStream", streamId);
    }

    public void listNotify(Integer streamId) {
        events.notify("LIST", streamId);
    }

    public void deleteNotify(Integer streamId) {
        events.notify("DeleteStream", streamId);
    }

    public void listenNotify(Integer streamId) {
        events.notify("Listen", streamId);
    }

    public void recommendNotify(Integer streamId) {
        events.notify("Recommend", streamId);
    }

    public void surpriseNotify(Integer streamId) {
        events.notify("Surprise", streamId);
    }
}
