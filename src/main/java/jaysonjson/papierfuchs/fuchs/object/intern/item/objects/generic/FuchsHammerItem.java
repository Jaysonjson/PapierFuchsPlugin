package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.generic;

import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsItemData;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.IItemUseType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class FuchsHammerItem extends FuchsItem {

    int breakSizeX = 1;
    int breakSizeY = 1;
    int breakSizeZ = 1;
    public FuchsHammerItem(String id, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        FuchsItemData fuchsItemData = new FuchsItemData(this, player, stack);
        fuchsItemData.addToLore(breakSize());
        return fuchsItemData.setItem();
    }

    public int getBreakSizeX() {
        return breakSizeX;
    }

    public int getBreakSizeY() {
        return breakSizeY;
    }

    public int getBreakSizeZ() {
        return breakSizeZ;
    }

    public String breakSize() {
        return getBreakSizeX() * 3 + "x" + getBreakSizeY() * 3 + "x" + getBreakSizeZ() * 3;
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Location location = block.getLocation();
        Player player = event.getPlayer();
        for (int i = getBreakSizeX() / -1; i != getBreakSizeX(); i++) {
            for (int j = getBreakSizeY() / -1; j != getBreakSizeY(); j++) {
                for (int k = getBreakSizeZ() / -1; k != getBreakSizeZ(); k++) {
                    block.getRelative(i, j, k).breakNaturally(player.getEquipment().getItemInMainHand());
                }
            }
        }
        super.onBlockBreak(event);
    }
}
