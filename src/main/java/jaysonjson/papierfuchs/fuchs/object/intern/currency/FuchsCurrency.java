package jaysonjson.papierfuchs.fuchs.object.intern.currency;

import jaysonjson.papierfuchs.fuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.fuchs.registry.RegistryType;

public abstract class FuchsCurrency extends FuchsObject implements IFuchsCurrency {

    public FuchsCurrency(String id) {
        super(id, RegistryType.CURRENCY);
    }


    @Override
    public boolean isDefault() {
        return false;
    }
}
