package jaysonjson.papierfuchs.registry;

public interface IFuchsPlugin {
    @Deprecated
    String getPluginID();
    default String getRegistryID() {
        return getPluginID();
    }
}
