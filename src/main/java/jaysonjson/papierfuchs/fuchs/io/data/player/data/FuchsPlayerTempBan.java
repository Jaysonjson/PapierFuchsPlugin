package jaysonjson.papierfuchs.fuchs.io.data.player.data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class FuchsPlayerTempBan {

    private long start = 0L;
    private long end = 0L;


    public void setEnd(long end) {
        this.end = end;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public long getStart() {
        return start;
    }

    public boolean isBanned() {
        if(start == 0 && end == 0) {
            return false;
        }
        if((end - System.currentTimeMillis()) <= 0) {
            unban();
            return false;
        } else {
            return true;
        }
    }

    public void unban() {
        end = 0;
        start = 0;
    }

    public void tempBanMillis(long millis) {
        start = System.currentTimeMillis();
        end = start + millis;
    }

    public void tempBanSeconds(long seconds) {
        tempBanMillis(seconds * 1000);
    }

    public void tempBanMinutes(long minutes) {
        tempBanSeconds(minutes * 60);
    }

    public String getEndDate() {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(end), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
    }

    public String getBanText() {
        return "Du bist noch bis zum " + getEndDate() + " gebannt!";
    }

}
