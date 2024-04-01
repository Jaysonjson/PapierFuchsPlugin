package jaysonjson.papierfuchs.fuchs.event;

public interface IFuchsEvent<T> {
    void call(T event);
}
