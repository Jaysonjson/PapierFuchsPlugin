package jaysonjson.papierfuchs.fuchs.object.intern.rarity;

import jaysonjson.papierfuchs.fuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.fuchs.registry.RegistryType;

public abstract class FuchsRarity extends FuchsObject implements IFuchsRarity {

    public FuchsRarity(String id) {
        super(id, RegistryType.RARITY);
    }

}
