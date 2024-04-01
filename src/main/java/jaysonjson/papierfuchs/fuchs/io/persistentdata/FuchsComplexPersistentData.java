package jaysonjson.papierfuchs.fuchs.io.persistentdata;

import org.apache.commons.lang.SerializationUtils;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class FuchsComplexPersistentData<T extends Serializable> implements PersistentDataType<byte[], T> {

    private final Class<T> clazz;
    private final T catcher;
    FuchsComplexPersistentData(T catcher, Class<T> clazz) {
        this.clazz = clazz;
        this.catcher = catcher;
    }

    @Override
    public @NotNull Class<byte[]> getPrimitiveType() {
        return BYTE_ARRAY.getPrimitiveType();
    }

    @Override
    public @NotNull Class<T> getComplexType() {
        return clazz;
    }

    @NotNull
    @Override
    public byte[] toPrimitive(@NotNull T t, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        return SerializationUtils.serialize(t);
    }

    @Override
    public @NotNull T fromPrimitive(@NotNull byte[] bytes, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        try {
            InputStream fis = new ByteArrayInputStream(bytes);
            ObjectInputStream o = new ObjectInputStream(fis);
            return (T) o.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return catcher;
    }
}
