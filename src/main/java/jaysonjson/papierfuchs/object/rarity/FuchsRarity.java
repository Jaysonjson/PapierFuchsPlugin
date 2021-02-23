package jaysonjson.papierfuchs.object.rarity;

import jaysonjson.papierfuchs.registry.IFuchsRegistryObject;
import jaysonjson.papierfuchs.registry.RegistryType;

public abstract class FuchsRarity implements IFuchsRegistryObject, IFuchsRarity {

    String id;
    public FuchsRarity(String id) {
        this.id = id;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public RegistryType getType() {
        return RegistryType.RARITY;
    }
}
