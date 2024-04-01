package jaysonjson.papierfuchs.fuchs.event;

public class FuchsSaveEvent extends FuchsEvent<FuchsSaveEvent> {

    public enum CAUSE {
        RUNTASK, UNKNOWN
    }

    private final CAUSE cause = CAUSE.UNKNOWN;

    @Override
    protected String getId() {
        return "save_event";
    }

    @Override
    public void call(FuchsSaveEvent event) {

    }

    public CAUSE getCause() {
        return cause;
    }
}
