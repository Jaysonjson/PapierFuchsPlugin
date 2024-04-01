package jaysonjson.papierfuchs.fuchs.registry;


import org.intellij.lang.annotations.Pattern;
import org.jetbrains.annotations.Nullable;

public class FuchsObject implements IFuchsObject, Cloneable {
    private final RegistryType type;
    private FuchsKey key;
    private String permission = "";
    private IFuchsPlugin fuchsPlugin;
    private FuchsRegistryObject<? extends FuchsObject> registryObject = null;
    public FuchsObject(@Pattern("[a-z0-9/._-]+") String id, RegistryType type) {
        this.type = type;
        this.key = new FuchsKey("undefined", id);
    }


    public FuchsKey getKey() {
        return key;
    }

    @Deprecated
    public String getID() {
        return getKey().toString();
    }

    public String getPermission() {
        return permission;
    }

    @Override
    @Deprecated
    public void updateID(IFuchsPlugin fuchsPlugin) {
        key = new FuchsKey(fuchsPlugin, getKey().getKey());
        permission = "papierfuchs." + getType().toString().toLowerCase() + "." + key.getNamespace() + "." + key.getKey();
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

    public boolean hasRightID() {
        return getID().contains(":");
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    @Deprecated
    public String getRealID() {
        return getKey().getKey();
    }

    public FuchsRegistryObject<? extends FuchsObject> getRegistryObject() {
        return registryObject;
    }

    public void setRegistryObject(FuchsRegistryObject<? extends FuchsObject> registryObject) {
        this.registryObject = registryObject;
    }

    public boolean hasRegistryObject() {
        return getRegistryObject() != null;
    }

    @Nullable
    public static <T extends FRO<J>, J extends FuchsObject> T getFromId(String id) {
        return getFromId(id, RegistryType.UNDEFINED);
    }

    @Nullable
    public static <T extends FRO<J>, J extends FuchsObject> T getFromId(String id, RegistryType type) {
        return (T) FuchsRegistries.OBJECTS.get(createObjectId(id, type));
    }

    public static boolean isInRegistry(String id, RegistryType type) {
        if(!id.contains(":")) {
            return FuchsRegistries.OBJECTS.containsKey(createObjectId(id, type));
        }
        return FuchsRegistries.OBJECTS.containsKey(id);
    }

    public String createObjectId() {
        return type.getRegistryName() + ":" + getKey();
    }

    public static String createObjectId(String id, RegistryType type) {
        if(!id.contains(type.registryName)) {
            return type.getRegistryName() + ":" + id;
        }
        return id;
    }

}
