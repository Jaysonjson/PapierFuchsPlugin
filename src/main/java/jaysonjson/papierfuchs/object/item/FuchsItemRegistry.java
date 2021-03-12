package jaysonjson.papierfuchs.object.item;

public class FuchsItemRegistry<T extends FuchsItem> {

    public T item;

    public FuchsItemRegistry(T item) {
        this.item = item;
    }

    public T get() {
        try {
            return (T) item.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
