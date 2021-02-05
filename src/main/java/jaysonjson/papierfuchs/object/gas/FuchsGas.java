package jaysonjson.papierfuchs.object.gas;

import jaysonjson.papierfuchs.object.item.interfaces.IFuchsItemName;
import jaysonjson.papierfuchs.registry.IFuchsRegistryObject;
import jaysonjson.papierfuchs.registry.RegistryType;

public abstract class FuchsGas implements IFuchsRegistryObject, IFuchsGas, IFuchsItemName {

    @Override
    public String getID() {
        return "";
    }

    @Override
    public RegistryType getType() {
        return RegistryType.GAS;
    }

    @Override
    public String getDisplayName() {
        return null;
    }

}
