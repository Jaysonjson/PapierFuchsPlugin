package jaysonjson.papierfuchs.events.item;

import jaysonjson.papierfuchs.References;
import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.inventories.crafting.general.GeneralCraftingInventory;
import jaysonjson.papierfuchs.object.BlockMetaData;
import jaysonjson.papierfuchs.object.EntityMetaData;
import jaysonjson.papierfuchs.object.item.FuchsItem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

public class ItemUse implements Listener {

    @EventHandler
    public void itemUseEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        if (block != null) {
            if (block.getType() != Material.AIR) {
                if (block.hasMetadata(BlockMetaData.GENERAL_CRAFTING_BLOCK) && !event.getPlayer().isSneaking() && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    GeneralCraftingInventory generalCraftingInventory = new GeneralCraftingInventory();
                    generalCraftingInventory.createPageData(player);
                    generalCraftingInventory.openInventory(player, 0);
                }
            }
        }

        if (event.getItem() != null) {
            ItemStack itemStack = event.getItem();
           /* if(Utility.isBannedItem(event.getItem())) {
                event.setCancelled(true);
            }*/
            if (event.getItem().getType() != Material.AIR) {
                FuchsItem fuchsItem = Utility.getFuchsItemFromNMS(itemStack);
                if(fuchsItem != null) {
                    /*if (Utility.isAbilityItemAll(event.getPlayer(), itemStack)) {
                        if (fuchsItem.isAbilityItem()) {
                            //FuchsItem.ability(event.getPlayer().getWorld(), event.getPlayer(), event.getItem());
                            fuchsItem.ability(event);
                        }
                    }
                    */
                    if(player.getOpenInventory().getType() == InventoryType.CRAFTING || player.getOpenInventory().getType() == InventoryType.CREATIVE) {
                        fuchsItem.onItemUse(event);
                        switch (event.getAction()) {
                            case RIGHT_CLICK_BLOCK:
                                fuchsItem.onItemRightClickBlock(event);
                                fuchsItem.onItemRightClick(event);
                                break;
                            case LEFT_CLICK_BLOCK:
                                fuchsItem.onItemLeftClickBlock(event);
                                fuchsItem.onItemLeftClick(event);
                                break;
                            case LEFT_CLICK_AIR:
                                fuchsItem.onItemLeftClickAir(event);
                                fuchsItem.onItemLeftClick(event);
                                break;
                            case RIGHT_CLICK_AIR:
                                fuchsItem.onItemRightClickAir(event);
                                fuchsItem.onItemRightClick(event);
                                break;
                            default:
                                break;
                        }
                    }

                    if (block != null) {
                        if (block.getType() != Material.AIR) {
                            fuchsItem.onBlockInteract(event);
                            if(fuchsItem.isPlaceAble()) {
                                Location loc = event.getClickedBlock().getLocation();
                                ArmorStand armorStand = player.getWorld().spawn(loc.add(0.5, 1, 0.5), ArmorStand.class);
                                armorStand.setInvisible(true);
                                armorStand.setInvulnerable(true);
                                armorStand.setArms(true);
                                armorStand.setRightArmPose(new EulerAngle(0f, 0f, 0f));
                                armorStand.setLeftArmPose(new EulerAngle(0f, 0f, 0f));
                                armorStand.setCustomNameVisible(false);
                                armorStand.setItem(EquipmentSlot.HAND, fuchsItem.createItem(event.getPlayer(), itemStack));
                                ItemStack clonedStack = event.getItem().clone();
                                clonedStack.setAmount(1);
                                event.getItem().setAmount(event.getItem().getAmount() - 1);
                                Utility.addEntityMetadata(References.data.server, armorStand, EntityMetaData.ARMORSTAND_DONT_REMOVE_ITEM_ON_RIGHTCLICK, true);
                                Utility.addEntityMetadata(References.data.server, armorStand, EntityMetaData.CONTAINED_ITEM, Utility.createItemStackString(clonedStack));
                            }
                        }
                    }
                }
            }
        }
    }
/*
    private void shootGun(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        World world = event.getPlayer().getWorld();
        Snowball snowball = world.spawn(player.getEyeLocation(), Snowball.class);

    }
    */
}
