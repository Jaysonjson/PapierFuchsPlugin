package jaysonjson.papierfuchs.fuchs.io.persistentdata;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class FuchsPrimitivePersistentData<T>  implements PersistentDataType<T, T> {
    private final Class<T> primitiveType;

    FuchsPrimitivePersistentData(@NotNull Class<T> primitiveType) {
        this.primitiveType = primitiveType;
    }

    @NotNull
    public Class<T> getPrimitiveType() {
        return this.primitiveType;
    }

    @NotNull
    public Class<T> getComplexType() {
        return this.primitiveType;
    }

    @NotNull
    public T toPrimitive(@NotNull T complex, @NotNull PersistentDataAdapterContext context) {
        return complex;
    }

    @NotNull
    public T fromPrimitive(@NotNull T primitive, @NotNull PersistentDataAdapterContext context) {
        return primitive;
    }
}
