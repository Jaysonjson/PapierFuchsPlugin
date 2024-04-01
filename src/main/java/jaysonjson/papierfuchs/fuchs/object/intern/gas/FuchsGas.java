package jaysonjson.papierfuchs.fuchs.object.intern.gas;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.IFuchsDisplayName;
import jaysonjson.papierfuchs.fuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.fuchs.registry.RegistryType;

public abstract class FuchsGas extends FuchsObject implements IFuchsGas, IFuchsDisplayName {

    public FuchsGas(String id) {
        super(id, RegistryType.GAS);
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return new FuchsLanguageString();
    }


    @Override
    public boolean isDisplayNameChangeable() {
        return false;
    }
}
