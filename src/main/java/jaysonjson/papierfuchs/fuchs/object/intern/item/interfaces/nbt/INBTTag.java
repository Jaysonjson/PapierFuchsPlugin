package jaysonjson.papierfuchs.fuchs.object.intern.item.interfaces.nbt;

public interface INBTTag {
    NBTType getType();
    boolean canUpdate();
    String getKey();
    Object getDefaultValue();
}
