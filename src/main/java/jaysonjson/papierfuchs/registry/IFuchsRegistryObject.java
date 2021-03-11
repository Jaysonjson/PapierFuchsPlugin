package jaysonjson.papierfuchs.registry;

public interface IFuchsRegistryObject {

    String getID();
    @Deprecated
    void updateID(IFuchsPlugin fuchsPlugin);

    void updateID();
    RegistryType getType();

    @Deprecated
    default String registryString() {
        return getType().getRegistryName() + ":" + getID();
    }

    default String registryString(IFuchsPlugin fuchsPlugin) {
        return fuchsPlugin.getPluginID() + ":" + getType().getRegistryName() + ":" + getID();
    }
}
