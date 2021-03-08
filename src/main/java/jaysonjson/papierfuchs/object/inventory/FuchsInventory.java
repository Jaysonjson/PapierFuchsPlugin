package jaysonjson.papierfuchs.object.inventory;

import jaysonjson.papierfuchs.registry.IFuchsPlugin;
import jaysonjson.papierfuchs.registry.IFuchsRegistryObject;
import jaysonjson.papierfuchs.registry.RegistryType;

public abstract class FuchsInventory implements IFuchsRegistryObject, IFuchsInventory {

    String id;
    public FuchsInventory(String id) {
        this.id = id;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public RegistryType getType() {
        return RegistryType.INVENTORY;
    }

    @Override
    public void updateID(IFuchsPlugin fuchsPlugin) {
        this.id = fuchsPlugin.getPluginID() + ":" + getID();
    }
}
