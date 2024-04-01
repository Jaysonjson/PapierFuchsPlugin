package jaysonjson.papierfuchs.fuchs.event;

import java.util.ArrayList;

public class EventHandler {
    private final ArrayList<FuchsEvent> events = new ArrayList<>();

    public void fire(FuchsEvent event) {
        for (FuchsEvent fuchsEvent : events) {
            if(fuchsEvent.getId().equals(event.getId())) {
                fuchsEvent.call(fuchsEvent);
            }
        }
    }

    public ArrayList<FuchsEvent> getEvents() {
        return events;
    }
}
