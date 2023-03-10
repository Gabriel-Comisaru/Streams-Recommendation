import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Stream extends DataType {
    private Integer streamType;
    private Integer streamGenre;
    private Long noOfStreams;
    private Integer streamerId;
    private Long length;
    private Long dateAdded;

    private String streamerName;

    public Stream() {
    }


    public void plusOneStream() {
        noOfStreams++;
    }

    @Override
    public String toString() {
        long longLength = length;
        int hours = (int) (longLength / 3600);
        int remainder = (int) (longLength - hours * 3600);
        int mins = remainder / 60;
        remainder = remainder - mins * 60;
        int secs = remainder;
        Date date = new Date(dateAdded * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT"));
        String dateText = sdf.format(date);
        String time;
        if (hours == 0) {
            time = String.format("%02d:%02d", mins, secs);
        } else {
            time = String.format("%02d:%02d:%02d", hours, mins, secs);
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", "" + getId() + "");
        jsonObject.addProperty("name", getName());
        jsonObject.addProperty("streamerName", streamerName);
        jsonObject.addProperty("noOfListenings", "" + noOfStreams + "");
        jsonObject.addProperty("length", time);
        jsonObject.addProperty("dateAdded", dateText);
        return jsonObject.toString();
    }

    public void setStreamType(Integer streamType) {
        this.streamType = streamType;
    }

    public void setStreamGenre(Integer streamGenre) {
        this.streamGenre = streamGenre;
    }

    public void setNoOfStreams(Long noOfStreams) {
        this.noOfStreams = noOfStreams;
    }

    public void setStreamerId(Integer streamerId) {
        this.streamerId = streamerId;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public void setDateAdded(Long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Long getNoOfStreams() {
        return noOfStreams;
    }

    public Integer getStreamerId() {
        return streamerId;
    }

    public Long getLength() {
        return length;
    }

    public Long getDateAdded() {
        return dateAdded;
    }

    public String getType() {
        if (streamType == 1) {
            return "song";
        } else if (streamType == 2) {
            return "podcast";
        } else if (streamType == 3) {
            return "audiobook";
        }
        return null;
    }

    public void setStreamerName(String streamerName) {
        this.streamerName = streamerName;
    }

    public String getStreamerName() {
        return streamerName;
    }
}

class StreamComparator implements Comparator<Stream> {
    @Override
    public int compare(Stream o1, Stream o2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateO1 = new Date(o1.getDateAdded() * 1000);
        String dateText = sdf.format(dateO1);
        Date dateO2 = new Date(o2.getDateAdded() * 1000);
        String dateText2 = sdf.format(dateO2);
        if (dateText.compareTo(dateText2) < 0) {
            return 1;
        } else if (dateText.compareTo(dateText2) > 0) {
            return -1;
        } else {
            if (o1.getLength() < o2.getLength()) {
                return 1;
            } else if (o1.getLength() > o2.getLength()) {
                return -1;
            } else {
                return o1.getNoOfStreams().compareTo(o2.getNoOfStreams());
            }
        }
    }
}