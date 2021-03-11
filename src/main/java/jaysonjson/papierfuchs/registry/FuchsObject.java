package jaysonjson.papierfuchs.registry;

public class FuchsObject implements IFuchsRegistryObject {

    String id;
    RegistryType type;
    IFuchsPlugin fuchsPlugin;
    public FuchsObject(String id, RegistryType type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    @Deprecated
    public void updateID(IFuchsPlugin fuchsPlugin) {
        this.id = fuchsPlugin.getPluginID() + ":" + getID();
    }

    @Override
    public void updateID() {
        updateID(fuchsPlugin);
    }

    @Override
    public RegistryType getType() {
        return type;
    }

    public IFuchsPlugin getFuchsPlugin() {
        return fuchsPlugin;
    }

    public void setFuchsPlugin(IFuchsPlugin fuchsPlugin) {
        this.fuchsPlugin = fuchsPlugin;
    }
}
