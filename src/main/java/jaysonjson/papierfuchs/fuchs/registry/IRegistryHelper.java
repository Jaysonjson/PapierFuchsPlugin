package jaysonjson.papierfuchs.fuchs.registry;

import javax.annotation.Nullable;

@Deprecated
public interface IRegistryHelper<T> {

    @Nullable
    T getFromId(String id);
    boolean inRegistry(String id);

}
