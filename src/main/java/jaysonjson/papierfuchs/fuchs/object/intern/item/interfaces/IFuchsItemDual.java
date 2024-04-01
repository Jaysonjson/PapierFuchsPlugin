package jaysonjson.papierfuchs.fuchs.object.intern.item.interfaces;

import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistryObject;
import org.jetbrains.annotations.Nullable;

public interface IFuchsItemDual {

    default boolean isDualItem() {
        return getDualItem() != null;
    }

    default boolean requiresDualItem() {
        return true;
    }

    @Nullable
    default FuchsRegistryObject<? extends FuchsItem> getDualItem() {
        return null;
    }

}
