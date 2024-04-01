package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.other;

import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.io.data.FuchsLocation;
import jaysonjson.papierfuchs.fuchs.object.BlockMetaData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsItemData;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.IItemUseType;
import jaysonjson.papierfuchs.fuchs.utility.References;
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
        //pages.add("Nach dem Eröffnen der Welt, werden alle davor zur unendlichkeit geöffneten Kisten wieder zu sein. Um das zu ändern, benutzt dieses Buch auf einen Goldenen Block");
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
                FuchsLocation fuchsLocation =  new FuchsLocation(chest.getLocation());
                if(!FuchsUtility.arrayContainsFuchsLocation(References.data.server.OPEN_CHESTS, fuchsLocation)) {
                    References.data.server.OPEN_CHESTS.add(fuchsLocation);
                }
                if(!block.hasMetadata(BlockMetaData.CONTAINED_ITEM)) {
                    ItemStack itemStack = event.getItem().clone();
                    itemStack.setAmount(1);
                    FuchsUtility.addBlockMetadata(fuchsLocation, block, BlockMetaData.CONTAINED_ITEM, FuchsUtility.createItemStackString(itemStack));
                    event.getItem().setAmount(event.getItem().getAmount() - 1);
                }
            } else if (event.getClickedBlock().getType() == Material.GOLD_BLOCK) {
                FuchsUtility.openOpenedChests(event.getClickedBlock().getWorld());
            }
        }
    }

    @Override
    public int getCustomModelData() {
        return 1;
    }
}
