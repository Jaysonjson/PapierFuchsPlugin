package jaysonjson.papierfuchs.registry;

public interface IFuchsRegistryObject {

    String getID();
    void updateID(IFuchsPlugin fuchsPlugin);
    RegistryType getType();

    @Deprecated
    default String registryString() {
        return getType().getRegistryName() + ":" + getID();
    }

    default String registryString(IFuchsPlugin fuchsPlugin) {
        return fuchsPlugin.getPluginID() + ":" + getType().getRegistryName() + ":" + getID();
    }
}
