package jaysonjson.papierfuchs.fuchs.object.intern.item.interfaces;

import jaysonjson.papierfuchs.fuchs.object.intern.npc.FuchsNPCData;
import net.minecraft.network.protocol.game.PacketPlayInUseEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public interface IFuchsItemUse {

    @Deprecated
    default void ability(PlayerInteractEvent event) {
        onItemUse(event);
    }

    @Deprecated
    default boolean isAbilityItem() {
        return false;
    }

    default void onItemRightClickAir(PlayerInteractEvent event) { }
    default void onItemLeftClickAir(PlayerInteractEvent event) { }
    default void onItemRightClickBlock(PlayerInteractEvent event) { }
    default void onItemLeftClickBlock(PlayerInteractEvent event) { }
    default void onItemUse(PlayerInteractEvent event) { }
    default void onItemRightClick(PlayerInteractEvent event) { }
    default void onItemLeftClick(PlayerInteractEvent event) { }
    default boolean isChargeAble() {
        return false;
    }
    default void onEntityKill(EntityDeathEvent event) { }
    default void onItemPickup(EntityPickupItemEvent event) { }
    default void onItemDrop(EntityDropItemEvent event) { }
    default void onPlayerItemDrop(PlayerDropItemEvent event) { }
    default void onEntityHit(EntityDamageByEntityEvent event) { }
    default void onBlockInteract(PlayerInteractEvent event) { }
    default void onEntityInteract(PlayerInteractEntityEvent event) { }
    default void onNPCInteract(PacketPlayInUseEntity packet, Player player, Entity entity, FuchsNPCData npc) { }
    default void onItemDespawn(ItemDespawnEvent event) {}
    default void onItemEchant(EnchantItemEvent event) {}
    default void onItemPrepareCraft(PrepareItemCraftEvent event) {}
    default void onItemPrepareSmithing(PrepareSmithingEvent event) {}
    default void onItemPrepareSmithingMaterial(PrepareSmithingEvent event) {}
    default void onItemPrepareSmithingEquipment(PrepareSmithingEvent event) {}
    default void onBowShoot(EntityShootBowEvent event) {

    }
    @Deprecated
    default void onAction(Player player, ItemStack itemStack) {}
    @Deprecated
    default void onActionEnd(Player player, ItemStack itemStack) {}
    default void onBlockBreak(BlockBreakEvent event) {

    }
}
