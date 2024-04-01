package jaysonjson.papierfuchs.fuchs.object.intern.effect;

import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemBlockData;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemGeneralData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsItemData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.ItemDespawnEvent;

import javax.annotation.Nullable;

public interface IFuchsEffect<T extends EffectItemData> {

    /*String getDisplayName();
    String getDisplayName(FuchsPlayer fuchsPlayer);*/
    default void onEnemyHit(EntityDamageByEntityEvent event, boolean cooldownOver) { }
    FuchsRarity getRarity();
    default void onItemDespawn(ItemDespawnEvent event) {}
    default boolean onItemDamage(Player player, FuchsMCItem fuchsMCItem) {
        return true;
    }
    default void onItemPickup(EntityPickupItemEvent event, FuchsMCItem mcItem) {

    }

    default byte getMaxLevel() {
        return 3;
    }

    default void onGeneralAdd(FuchsItemGeneralData generalData, @Nullable Player player) {

    }

    default T getDefaultData() {
        return (T) new EffectItemData(getMaxLevel());
    }

    default void editItemData(FuchsItemData fuchsItemData) {

    }


    default FuchsItemBlockData.EffectiveTool getTool() {
        return FuchsItemBlockData.EffectiveTool.NONE;
    }

    default boolean isToolSpecific() {
        return getTool() != FuchsItemBlockData.EffectiveTool.NONE;
    }

}
