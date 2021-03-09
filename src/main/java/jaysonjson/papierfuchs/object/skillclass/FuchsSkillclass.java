package jaysonjson.papierfuchs.object.skillclass;

import jaysonjson.papierfuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.registry.IFuchsPlugin;
import jaysonjson.papierfuchs.registry.IFuchsRegistryObject;
import jaysonjson.papierfuchs.registry.RegistryType;

public abstract class FuchsSkillclass extends FuchsObject {

    public FuchsSkillclass(String id) {
        super(id, RegistryType.SKILLCLASS);
    }

}
