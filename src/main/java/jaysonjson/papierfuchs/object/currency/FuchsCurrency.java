package jaysonjson.papierfuchs.object.currency;

import jaysonjson.papierfuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.registry.RegistryType;

public abstract class FuchsCurrency extends FuchsObject implements IFuchsCurrency {

    public FuchsCurrency(String id) {
        super(id, RegistryType.CURRENCY);
    }

}
