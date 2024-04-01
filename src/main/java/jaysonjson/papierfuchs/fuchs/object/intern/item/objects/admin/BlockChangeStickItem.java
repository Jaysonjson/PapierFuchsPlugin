package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.admin;

import jaysonjson.papierfuchs.fuchs.io.data.server.data.ByteFuchsWorld;
import jaysonjson.papierfuchs.fuchs.io.data.server.data.FWBlockChange;
import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.FuchsItemCategory;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.ItemCategoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.InventoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.item.ItemCategoryInventoryOld;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.lists.BlockChangeInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.RarityList;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.UseTypeList;
import jaysonjson.papierfuchs.fuchs.utility.Colors;
import jaysonjson.papierfuchs.fuchs.utility.References;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;

public class BlockChangeStickItem extends FuchsItem {

    public BlockChangeStickItem() {
        super("blockchange_stick", Material.STICK, UseTypeList.ADMIN.copy());
    }

    @Override
    public FuchsRarity getDefaultRarity() {
        return RarityList.none.copy();
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return FuchsLanguageString.c("BlockChange Stick");
    }

    @Override
    public void onItemRightClickBlock(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Location location = event.getClickedBlock().getLocation();
        ByteFuchsWorld fuchsWorld = References.data.getByteWorld(player.getWorld());
        ArrayList<FWBlockChange> blockChanges = new ArrayList<>();
        for (FWBlockChange blockChange : fuchsWorld.getBlockChanges()) {
            if(blockChange.getLocation().compare(location)) {
                blockChanges.add(blockChange);
            }
        }
        if(blockChanges.size() > 0) {
            BlockChangeInventory blockChangeInventory = InventoryList.blockChange.copy();
            blockChangeInventory.setBlockChanges(blockChanges);
            blockChangeInventory.create(player);
            blockChangeInventory.openInventory();
        } else {
            player.sendMessage(Component.text(Colors.RED_WHITE + "Keine Änderungen am Block festgestellt!"));
        }
        super.onItemRightClickBlock(event);
    }

    @Override
    public boolean isAdminTool() {
        return true;
    }

    @Override
    public FuchsItemCategory[] getCategories() {
        return new FuchsItemCategory[] { ItemCategoryList.admin_tool.copy(), ItemCategoryList.tool.copy() };
    }

}
