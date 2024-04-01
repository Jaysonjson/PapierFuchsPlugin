package jaysonjson.papierfuchs.fuchs.object.intern.item.interfaces;

public interface IFuchsItemArmor {
    default int getArmorModel() {
        return -1;
    }
    default double getArmorToughness() {
        return 0;
    }
    default double getArmor() {
        return 0;
    }
    default boolean hasArmorModel() {
        return getArmorModel() != -1;
    }
}
