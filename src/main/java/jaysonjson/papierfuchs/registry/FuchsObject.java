package jaysonjson.papierfuchs.registry;

public class FuchsObject implements IFuchsRegistryObject {

    String id;
    RegistryType type;
    public FuchsObject(String id, RegistryType type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public void updateID(IFuchsPlugin fuchsPlugin) {
        this.id = fuchsPlugin.getPluginID() + ":" + getID();
    }

    @Override
    public RegistryType getType() {
        return type;
    }
}
