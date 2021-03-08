package jaysonjson.papierfuchs.object.gas;

import jaysonjson.papierfuchs.object.item.interfaces.IFuchsItemName;
import jaysonjson.papierfuchs.registry.IFuchsPlugin;
import jaysonjson.papierfuchs.registry.IFuchsRegistryObject;
import jaysonjson.papierfuchs.registry.RegistryType;

public abstract class FuchsGas implements IFuchsRegistryObject, IFuchsGas, IFuchsItemName {

    private String id;
    public FuchsGas(String id) {
        this.id = id;
    }
    @Override
    public String getID() {
        return id;
    }

    @Override
    public RegistryType getType() {
        return RegistryType.GAS;
    }

    @Override
    public String getDisplayName() {
        return null;
    }


    @Override
    public void updateID(IFuchsPlugin fuchsPlugin) {
        this.id = fuchsPlugin.getPluginID() + ":" + getID();
    }
}
