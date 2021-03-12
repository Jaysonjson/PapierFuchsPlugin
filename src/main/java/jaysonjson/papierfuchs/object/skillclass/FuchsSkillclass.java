package jaysonjson.papierfuchs.object.skillclass;

import jaysonjson.papierfuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.registry.RegistryType;

public abstract class FuchsSkillclass<T extends FuchsSkillclassData> extends FuchsObject {

    public FuchsSkillclass(String id) {
        super(id, RegistryType.SKILLCLASS);
    }

    public T loadData() {
        return null;
    }

}
