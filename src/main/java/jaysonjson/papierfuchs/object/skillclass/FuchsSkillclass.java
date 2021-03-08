package jaysonjson.papierfuchs.object.skillclass;

import jaysonjson.papierfuchs.registry.IFuchsPlugin;
import jaysonjson.papierfuchs.registry.IFuchsRegistryObject;
import jaysonjson.papierfuchs.registry.RegistryType;

public abstract class FuchsSkillclass implements IFuchsRegistryObject {

    String id;
    public FuchsSkillclass(String id) {
        this.id = id;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public RegistryType getType() {
        return RegistryType.SKILLCLASS;
    }

    @Override
    public void updateID(IFuchsPlugin fuchsPlugin) {
        this.id = fuchsPlugin.getPluginID() + ":" + getID();
    }
}
