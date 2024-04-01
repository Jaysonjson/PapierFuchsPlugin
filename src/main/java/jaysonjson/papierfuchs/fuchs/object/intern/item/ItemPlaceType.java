package jaysonjson.papierfuchs.fuchs.object.intern.item;

import org.bukkit.inventory.EquipmentSlot;

public enum ItemPlaceType {
    RIGHT_ARM, LEFT_ARM, HEAD;

    public EquipmentSlot toEquipment() {
        return switch (this) {
            case HEAD -> EquipmentSlot.HEAD;
            case RIGHT_ARM -> EquipmentSlot.HAND;
            case LEFT_ARM -> EquipmentSlot.OFF_HAND;
        };
    }
}
