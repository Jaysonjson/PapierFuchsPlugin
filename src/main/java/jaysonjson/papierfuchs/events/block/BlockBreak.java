package jaysonjson.papierfuchs.events.block;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.data.DataHandler;
import jaysonjson.papierfuchs.data.FuchsLocation;
import jaysonjson.papierfuchs.data.server.data.FuchsServer;
import jaysonjson.papierfuchs.object.BlockMetaData;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.metadata.MetadataValue;

import java.util.UUID;

public class BlockBreak implements Listener {

    @EventHandler
    public void blockBreakEvent(BlockBreakEvent event) {
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

        if(block.hasMetadata(BlockMetaData.GENERAL_CRAFTING_BLOCK)) {
            FuchsServer fuchsServer = DataHandler.loadServer();
            fuchsServer.BLOCK_METADATA.removeIf(metadataSetter -> metadataSetter.location.compare(new FuchsLocation(block.getLocation())));
            block.removeMetadata(BlockMetaData.GENERAL_CRAFTING_BLOCK, PapierFuchs.INSTANCE);
            if(block.hasMetadata(BlockMetaData.CONTAINED_ITEM)) {
                String itemID = block.getMetadata(BlockMetaData.CONTAINED_ITEM).get(0).asString();
                if(Utility.itemIDExists(itemID)) {
                    block.getWorld().dropItemNaturally(block.getLocation(), Utility.getFuchsItemByID(itemID).createItem(event.getPlayer()));
                }
                block.removeMetadata(BlockMetaData.CONTAINED_ITEM, PapierFuchs.INSTANCE);
            }
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
    }

}
