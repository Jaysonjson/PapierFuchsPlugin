package jaysonjson.papierfuchs.events.block;

import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.data.DataHandler;
import jaysonjson.papierfuchs.data.FuchsLocation;
import jaysonjson.papierfuchs.data.server.data.BlockMetadataSetter;
import jaysonjson.papierfuchs.data.server.data.FuchsServer;
import jaysonjson.papierfuchs.object.BlockMetaData;
import jaysonjson.papierfuchs.object.item.ItemList;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;


public class BlockBreak implements Listener {

    @EventHandler
    public void blockBreakEvent(BlockBreakEvent event) {
        Block block = event.getBlock();
        if(!Utility.canBreakBlock(event.getPlayer(), block.getLocation(), event.getBlock().getWorld())) {
            event.setCancelled(true);
        }

        if(block.hasMetadata(BlockMetaData.GENERAL_CRAFTING_BLOCK)) {
            FuchsServer fuchsServer = DataHandler.loadServer();
            fuchsServer.BLOCK_METADATA.removeIf(metadataSetter -> metadataSetter.location.compare(new FuchsLocation(block.getLocation())));
            DataHandler.saveServer(fuchsServer);
            block.getWorld().dropItemNaturally(block.getLocation(), ItemList.CRAFTING_UPGRADE_KIT.createItem(event.getPlayer()));
        }
    }

}
