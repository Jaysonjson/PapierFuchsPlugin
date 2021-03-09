package jaysonjson.papierfuchs.object.inventory;

import jaysonjson.papierfuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.registry.IFuchsPlugin;
import jaysonjson.papierfuchs.registry.IFuchsRegistryObject;
import jaysonjson.papierfuchs.registry.RegistryType;

public abstract class FuchsInventory extends FuchsObject implements IFuchsInventory {


    public FuchsInventory(String id) {
        super(id, RegistryType.INVENTORY);
    }

}
