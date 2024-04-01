package jaysonjson.papierfuchs.paper.events.entity.player;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.io.data.server.data.FuchsWorld;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemBlockData;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemGeneralData;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemPlantData;
import jaysonjson.papierfuchs.fuchs.object.EntityMetaData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.utility.References;
import jaysonjson.papierfuchs.old.inventories.crafting.general.GeneralCraftingInventory;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractEntity implements Listener {

    @EventHandler
    public void playerInteractEvent(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        ItemStack item = player.getInventory().getItemInMainHand();
        World world = event.getPlayer().getWorld();
        if(FuchsUtility.isFuchsItem(item)) {
            FuchsItem fuchsItem = FuchsUtility.getFuchsItemFromNMS(item);
            if (fuchsItem != null) {
                fuchsItem.onEntityInteract(event);
                if(fuchsItem.isFood()) {
                    event.setCancelled(true);
                }
            }
        }

        if(entity instanceof ArmorStand armorStand) {
            if(armorStand.hasMetadata(EntityMetaData.ARMORSTAND_GENERAL_CRAFTING)) {
                GeneralCraftingInventory generalCraftingInventory = new GeneralCraftingInventory();
                generalCraftingInventory.createPageData(player);
                generalCraftingInventory.openInventory(player, 0);
            }

            if(player.isSneaking()) {
                if(armorStand.hasMetadata(EntityMetaData.CONTAINED_ITEM) && !armorStand.hasMetadata(EntityMetaData.IS_BLOCK)) {
                    removeContainedItemArmorStand(armorStand, player, true);
                }
            }
            FuchsWorld fuchsWorld = References.data.getWorld(world);
            if(FuchsUtility.isShovel(item)) {
                event.setCancelled(true);
                fuchsWorld.removePlant(armorStand.getUniqueId());
                armorStand.remove();
            }
            if(fuchsWorld.getPlant(armorStand.getUniqueId()) != null) {
                if(FuchsUtility.isFuchsItem(item)) {
                    FuchsItem fuchsItem = FuchsUtility.getFuchsItemFromNMS(item);
                    if(fuchsItem.isFertilizer()) {
                        fuchsWorld.increasePlantGrow(armorStand.getUniqueId(), fuchsItem.getFertilizerAmount());
                        world.spawnParticle(Particle.COMPOSTER, armorStand.getLocation().add(0.25, 0.25, 0.25), 12);
                        world.spawnParticle(Particle.COMPOSTER, armorStand.getLocation().add(-0.25, 0.25, -0.25), 12);
                        world.spawnParticle(Particle.REDSTONE, armorStand.getLocation().add(0, 0.5, 0), 12, new Particle.DustOptions(Color.GREEN, 3));
                        item.subtract();
                    }
                }
            }
            if (References.data.getWorld(world).getPlants().contains(armorStand.getUniqueId())) {
                event.setCancelled(true);
                takeFruits(event, armorStand);
            }

            if(armorStand.hasMetadata(EntityMetaData.ARMORSTAND_DONT_REMOVE_ITEM_ON_RIGHTCLICK)) {
                event.setCancelled(true);
            }
        }

        if (entity instanceof ItemFrame itemFrame) {
            if(player.isSneaking()) {
                if (itemFrame.hasMetadata(EntityMetaData.ITEMFRAME_DELETE_ON_REMOVE)) {
                    boolean destroy = itemFrame.getMetadata(EntityMetaData.ITEMFRAME_DELETE_ON_REMOVE).get(0).asBoolean();
                    if (destroy) {
                        player.getWorld().dropItem(player.getLocation(), itemFrame.getItem());
                        itemFrame.remove();
                    }
                }
            }
        }
    }

    public static void removeContainedItemArmorStand(Entity entity, Player player, boolean dropself) {
        if(entity != null) {
            String itemID = entity.getMetadata(EntityMetaData.CONTAINED_ITEM).get(0).asString();
            if (FuchsUtility.itemIDExists(itemID)) {
                player.getWorld().dropItemNaturally(entity.getLocation(), FuchsUtility.getFuchsItemByID(itemID).createItem(player));
            } else {
                ItemStack itemStack = FuchsUtility.generateItemStack(itemID);
                if (itemStack.getType() != Material.AIR) {
                    ItemStack containedItemStack = FuchsUtility.generateItemStack(itemID);
                    FuchsMCItem mcItem = new FuchsMCItem(containedItemStack);
                    FuchsItemBlockData blockData = mcItem.blockData().get();
                    blockData.removeBarrier();
                    blockData.removeLights();
                    mcItem.blockData().set(blockData);
                    if(dropself) {
                        player.getWorld().dropItemNaturally(entity.getLocation(), mcItem.getItemStack());
                    }
                }
            }
            References.data.getByteWorld(player.getWorld()).getEntityMetadatas().removeIf(entity_metadatum -> entity_metadatum.uuid.equals(entity.getUniqueId()) && entity_metadatum.key.equals(EntityMetaData.CONTAINED_ITEM));
            entity.removeMetadata(EntityMetaData.CONTAINED_ITEM, PapierFuchs.INSTANCE);
            entity.remove();
        }
    }

    public static void takeFruits(PlayerInteractAtEntityEvent event, ArmorStand plantStand) {
        FuchsMCItem mcItem = new FuchsMCItem(plantStand.getItem(EquipmentSlot.HEAD));
        FuchsItemPlantData plantData = mcItem.plantData().get();
        FuchsItemGeneralData generalData = mcItem.generalData().get();
        if(plantData.isFinished()) {
            if(FuchsUtility.itemIDExists(plantData.getFruit())) {
                int i = PapierFuchs.random.nextInt(plantData.getMaxFruitDrop() - plantData.getMinFruitDrop()) + plantData.getMinFruitDrop();
                for (int j = 0; j < i; j++) {
                    plantStand.getWorld().dropItemNaturally(plantStand.getLocation(), FuchsUtility.getFuchsItemByID(plantData.getFruit()).createItem(event.getPlayer()));
                }
            }
            plantData.setFinished(false);
            plantData.setCurrentGrow(0);
            generalData.setModeldata(plantData.getModelData());
            mcItem.plantData().set(plantData);
            mcItem.generalData().set(generalData);
            plantStand.setItem(EquipmentSlot.HEAD, mcItem.getItemStack());
            event.getPlayer().playSound(plantStand.getLocation(), Sound.BLOCK_CROP_BREAK, 1, 1);
        }
    }
}
