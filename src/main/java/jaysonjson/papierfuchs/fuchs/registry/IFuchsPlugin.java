package jaysonjson.papierfuchs.fuchs.registry;

import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;

public interface IFuchsPlugin {
    String getRegistryID();
    FuchsRegistry getRegistry();
    FuchsItem getIcon();
}
