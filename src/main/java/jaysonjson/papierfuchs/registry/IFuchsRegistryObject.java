package jaysonjson.papierfuchs.registry;

public interface IFuchsRegistryObject {
    String getID();
    RegistryType getType();

    default String registryString() {
        return getType().getRegistryName() + ":" + getID();
    }
}
