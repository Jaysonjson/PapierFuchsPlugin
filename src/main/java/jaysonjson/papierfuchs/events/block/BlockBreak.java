package jaysonjson.papierfuchs.events.block;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.References;
import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.data.DataHandler;
import jaysonjson.papierfuchs.data.FuchsLocation;
import jaysonjson.papierfuchs.data.drop.obj.FuchsBlockDrop;
import jaysonjson.papierfuchs.data.drop.obj.ItemDropChance;
import jaysonjson.papierfuchs.data.server.data.FuchsServer;
import jaysonjson.papierfuchs.object.BlockMetaData;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class BlockBreak implements Listener {

    @EventHandler
    public void blockBreakEvent(BlockBreakEvent event) {
        Random random = new Random();
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if(!Utility.canBreakBlock(event.getPlayer(), block.getLocation(), event.getBlock().getWorld())) {
            event.setCancelled(true);
        }

       /* if(block.hasMetadata(BlockMetaData.GENERAL_CRAFTING_BLOCK)) {
            FuchsServer fuchsServer = DataHandler.loadServer();
            fuchsServer.BLOCK_METADATA.removeIf(metadataSetter -> metadataSetter.location.compare(new FuchsLocation(block.getLocation())));
            DataHandler.saveServer(fuchsServer);
            block.getWorld().dropItemNaturally(block.getLocation(), ItemList.CRAFTING_UPGRADE_KIT.createItem(event.getPlayer()));
        }*/
        if(block.hasMetadata(BlockMetaData.CONTAINED_ITEM)) {
            FuchsServer fuchsServer = DataHandler.loadServer();
            String itemID = block.getMetadata(BlockMetaData.CONTAINED_ITEM).get(0).asString();
            if(Utility.itemIDExists(itemID)) {
                block.getWorld().dropItemNaturally(block.getLocation(), Utility.getFuchsItemByID(itemID).createItem(event.getPlayer()));
            } else {
                block.getWorld().dropItemNaturally(block.getLocation(), Utility.generateItemStack(itemID));
            }
            fuchsServer.BLOCK_METADATA.removeIf(metadataSetter -> metadataSetter.location.compare(new FuchsLocation(block.getLocation())));
            block.removeMetadata(BlockMetaData.CONTAINED_ITEM, PapierFuchs.INSTANCE);
            DataHandler.saveServer(fuchsServer);
        }

        if(block.hasMetadata(BlockMetaData.GENERAL_CRAFTING_BLOCK)) {
            FuchsServer fuchsServer = DataHandler.loadServer();
            fuchsServer.BLOCK_METADATA.removeIf(metadataSetter -> metadataSetter.location.compare(new FuchsLocation(block.getLocation())));
            block.removeMetadata(BlockMetaData.GENERAL_CRAFTING_BLOCK, PapierFuchs.INSTANCE);
            if(block.hasMetadata(BlockMetaData.ARMOR_STAND_UUID)) {
                String uuid = block.getMetadata(BlockMetaData.ARMOR_STAND_UUID).get(0).asString();
                Entity entity = block.getWorld().getEntity(UUID.fromString(uuid));
                if(entity != null) {
                    entity.remove();
                }
                block.removeMetadata(BlockMetaData.ARMOR_STAND_UUID, PapierFuchs.INSTANCE);

            }
            DataHandler.saveServer(fuchsServer);
        }
        if(block instanceof Chest) {
            FuchsServer fuchsServer = DataHandler.loadServer();
            fuchsServer.OPEN_CHESTS.removeIf(location -> location.compare(new FuchsLocation(block.getLocation())));
            DataHandler.saveServer(fuchsServer);
        }
        if(player.getGameMode() != GameMode.CREATIVE) {
            for (FuchsBlockDrop fuchsBlockDrop : References.drops.getBlockDrops()) {
                if (fuchsBlockDrop.material.equals(block.getType())) {
                    for (ItemDropChance itemDropChance : fuchsBlockDrop.items) {
                        if (random.nextInt(itemDropChance.getChance()) == 1) {
                            ItemStack itemStack = itemDropChance.getFuchsItem().createItem(player);
                            itemStack.setAmount(ThreadLocalRandom.current().nextInt(itemDropChance.min_amount, itemDropChance.max_amount + 1));
                            player.getWorld().dropItemNaturally(block.getLocation(), itemStack);
                        }
                    }
                }
            }
        }
    }

}
