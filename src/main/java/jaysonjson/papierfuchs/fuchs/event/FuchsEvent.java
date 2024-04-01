package jaysonjson.papierfuchs.fuchs.event;

public abstract class FuchsEvent<T> implements IFuchsEvent<T> {

    protected String getId() {
        return "all";
    }

}
