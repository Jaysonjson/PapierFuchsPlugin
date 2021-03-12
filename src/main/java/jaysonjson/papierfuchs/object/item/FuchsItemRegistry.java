package jaysonjson.papierfuchs.object.item;

import jaysonjson.papierfuchs.registry.IFuchsPlugin;

public class FuchsItemRegistry<T extends FuchsItem> {

    public T item;

    public FuchsItemRegistry(T item) {
        this.item = item;
    }

    public void update(IFuchsPlugin fuchsPlugin) {
        this.item.setFuchsPlugin(fuchsPlugin);
    }

    @Deprecated
    public T get() {
        try {
            T t = (T) item.clone();
            t.updateID();
            return t;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
