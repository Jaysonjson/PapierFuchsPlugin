package jaysonjson.papierfuchs.events.item;

import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.inventories.crafting.general.GeneralCraftingInventory;
import jaysonjson.papierfuchs.object.BlockMetaData;
import jaysonjson.papierfuchs.object.item.FuchsItem;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

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
                if (Utility.isAbilityItemAll(event.getPlayer(), itemStack)) {
                    if (fuchsItem != null && fuchsItem.isAbilityItem()) {
                        //FuchsItem.ability(event.getPlayer().getWorld(), event.getPlayer(), event.getItem());
                        fuchsItem.ability(event);
                    }
                }
                if (block != null) {
                    if (block.getType() != Material.AIR) {
                        if (fuchsItem != null) {
                            fuchsItem.onBlockInteract(event);
                        }
                    }
                }

            }
        }
    }
}
