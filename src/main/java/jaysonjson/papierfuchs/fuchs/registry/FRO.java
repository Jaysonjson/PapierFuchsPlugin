package jaysonjson.papierfuchs.fuchs.registry;

import java.util.function.Supplier;

public class FRO<T extends FuchsObject> extends FuchsRegistryObject<T> {

    public FRO(Supplier<T> obj) {
        super(obj);
    }

    public FRO(T obj) {
        super(obj);
    }

}
