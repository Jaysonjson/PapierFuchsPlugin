package jaysonjson.papierfuchs.fuchs.registry;

public interface IFuchsObject {

    FuchsKey getKey();
    @Deprecated
    void updateID(IFuchsPlugin fuchsPlugin);

    void updateID();
    RegistryType getType();

    @Deprecated
    default String registryStringType() {
        return getType().getRegistryName() + ":" + getKey();
    }

    String getRealID();

}
