package jaysonjson.papierfuchs.fuchs.object.intern.usetype;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.fuchs.registry.RegistryType;

public class FuchsUseType extends FuchsObject implements IItemUseType {

    FuchsLanguageString displayName;
    public FuchsUseType(String id, FuchsLanguageString displayName) {
        super(id, RegistryType.USETYPE);
        this.displayName = displayName;
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return displayName;
    }
}
