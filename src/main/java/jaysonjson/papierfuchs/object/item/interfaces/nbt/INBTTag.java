package jaysonjson.papierfuchs.object.item.interfaces.nbt;

public interface INBTTag {
    NBTType getType();
    boolean canUpdate();
    String getKey();
    Object getDefaultValue();
}
