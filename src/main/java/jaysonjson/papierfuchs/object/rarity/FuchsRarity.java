package jaysonjson.papierfuchs.object.rarity;

import jaysonjson.papierfuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.registry.IFuchsPlugin;
import jaysonjson.papierfuchs.registry.IFuchsRegistryObject;
import jaysonjson.papierfuchs.registry.RegistryType;

public abstract class FuchsRarity extends FuchsObject implements IFuchsRarity {


    public FuchsRarity(String id) {
        super(id, RegistryType.RARITY);
    }


    @Override
    public String getLoreTextFromLanguage() {
        return "";
    }

}
