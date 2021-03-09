package jaysonjson.papierfuchs.object.gas;

import jaysonjson.papierfuchs.object.item.interfaces.IFuchsItemName;
import jaysonjson.papierfuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.registry.IFuchsPlugin;
import jaysonjson.papierfuchs.registry.IFuchsRegistryObject;
import jaysonjson.papierfuchs.registry.RegistryType;

public abstract class FuchsGas extends FuchsObject implements IFuchsGas, IFuchsItemName {

    public FuchsGas(String id) {
        super(id, RegistryType.GAS);
    }

    @Override
    public String getDisplayName() {
        return null;
    }

}
