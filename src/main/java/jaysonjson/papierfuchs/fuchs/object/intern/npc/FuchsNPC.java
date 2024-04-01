package jaysonjson.papierfuchs.fuchs.object.intern.npc;

import jaysonjson.papierfuchs.fuchs.object.IFuchsDisplayName;
import jaysonjson.papierfuchs.fuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.fuchs.registry.RegistryType;

public  abstract class FuchsNPC<T extends FuchsNPCData> extends FuchsObject implements IFuchsNPC<T>, IFuchsDisplayName {

    public FuchsNPC(String id) {
        super(id, RegistryType.NPC);
    }

    @Override
    public void onInteract() {

    }

}
