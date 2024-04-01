package jaysonjson.papierfuchs.fuchs.object.intern.item.interfaces;

import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemBlockData;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public interface IFuchsItemTool {
    default int getMaxDurability() {
        return 0;
    }
    default double getToolDamage() {
        return 0;
    }

    default double getToolBlockDamage() {
        return 0;
    }

    default int getToolEfficiency() {
        return 0;
    }
    default float getToolAttackSpeed() {
        return 0f;
    }
    default void onBackstab(EntityDamageByEntityEvent event) { }
    default double getBackstabDamage() { return 0f;}

    default FuchsItemBlockData.EffectiveTool getToolType() {
        return FuchsItemBlockData.EffectiveTool.NONE;
    }
}
