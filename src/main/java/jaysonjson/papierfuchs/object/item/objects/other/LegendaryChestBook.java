package jaysonjson.papierfuchs.object.item.objects.other;

import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.data.DataHandler;
import jaysonjson.papierfuchs.data.FuchsLocation;
import jaysonjson.papierfuchs.data.server.data.FuchsServer;
import jaysonjson.papierfuchs.object.BlockMetaData;
import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.item.FuchsItemData;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.item.interfaces.IItemUseType;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.List;

public class LegendaryChestBook extends FuchsItem {

    public LegendaryChestBook(String id, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        FuchsItemData oItem = new FuchsItemData(this, player, stack);
        oItem.setItem(ChatColor.GOLD + "Das Buch der legendären Kiste");
        oItem.item.setItemMeta(createBookTag(oItem.item));
        return oItem.item;
    }

    public BookMeta createBookTag(ItemStack itemStack) {
        BookMeta bookMeta = (BookMeta) itemStack.getItemMeta();
        bookMeta.setTitle("Das Buch der legendären Kiste");
        bookMeta.setAuthor("Jayson & Nick");
        List<String> pages = new ArrayList<>();
        pages.add("Man besagt, dieses Buch kann Kisten zur Unendlichkeit öffnen.");
        pages.add("Nach dem Eröffnen der Welt, werden alle davor zur unendlichkeit geöffneten Kisten wieder zu sein. Um das zu ändern, benutzt dieses Buch auf einen Goldenen Block");
        bookMeta.setPages(pages);
        return bookMeta;
    }

    @Override
    public void onBlockInteract(PlayerInteractEvent event) {
        if(event.getClickedBlock() != null) {
            Block block = event.getClickedBlock();
            if (block.getState() instanceof Chest) {
                Chest chest = (Chest) block.getState();
                chest.open();
                FuchsServer fuchsServer = DataHandler.loadServer();
                FuchsLocation fuchsLocation =  new FuchsLocation(chest.getLocation());
                if(!Utility.arrayContainsFuchsLocation(fuchsServer.OPEN_CHESTS, fuchsLocation)) {
                    fuchsServer.OPEN_CHESTS.add(fuchsLocation);
                    DataHandler.saveServer(fuchsServer);
                }
                if(!block.hasMetadata(BlockMetaData.CONTAINED_ITEM)) {
                    ItemStack itemStack = event.getItem().clone();
                    itemStack.setAmount(1);
                    Utility.addBlockMetadata(fuchsServer, fuchsLocation, block, BlockMetaData.CONTAINED_ITEM, Utility.createItemStackString(itemStack));
                    event.getItem().setAmount(event.getItem().getAmount() - 1);
                    DataHandler.saveServer(fuchsServer);
                }
            } else if (event.getClickedBlock().getType() == Material.GOLD_BLOCK) {
                Utility.openOpenedChests(event.getClickedBlock().getWorld());
            }
        }
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        return tag;
    }

    @Override
    public int getCustomModelData() {
        return 1;
    }
}
