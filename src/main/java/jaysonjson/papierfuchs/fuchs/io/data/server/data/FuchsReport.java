package jaysonjson.papierfuchs.fuchs.io.data.server.data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class FuchsReport implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 0L;

    private UUID reporter;
    private UUID reportee;
    private String reason;
    private LocalDateTime time;
    private boolean done = false;

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isDone() {
        return done;
    }

    public UUID getReportee() {
        return reportee;
    }

    public String getReason() {
        return reason;
    }

    public UUID getReporter() {
        return reporter;
    }

    public void setReporter(UUID reporter) {
        this.reporter = reporter;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setReportee(UUID reportee) {
        this.reportee = reportee;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getFormattedTime() {
        return getTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SS"));
    }
}
