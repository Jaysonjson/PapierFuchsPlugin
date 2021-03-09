package jaysonjson.papierfuchs.object.npc;

import jaysonjson.papierfuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.registry.IFuchsPlugin;
import jaysonjson.papierfuchs.registry.IFuchsRegistryObject;
import jaysonjson.papierfuchs.registry.RegistryType;

public  abstract class FuchsNPC extends FuchsObject {

    public FuchsNPC(String id) {
        super(id, RegistryType.NPC);
    }

}
