package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.knead;

import jaysonjson.papierfuchs.fuchs.object.intern.category.item.FuchsItemCategory;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.ItemCategoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.item.ItemCategoryInventoryOld;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.ItemNBT;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.RarityList;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.UseTypeList;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;

public class FuchsKneadItem extends FuchsItem {

    public FuchsKneadItem(String id, Material material) {
        super(id, material, UseTypeList.KNEAD.copy());
    }

    @Override
    public void onItemUse(PlayerInteractEvent event) {
        NBTTagCompound tag = FuchsUtility.getItemTag(event.getItem());
        if(tag.hasKey(ItemNBT.KNEAD_NOT_USEABLE) && tag.getBoolean(ItemNBT.KNEAD_NOT_USEABLE)) {
            return;
        }
    }

    public void onStart(PlayerInteractEvent event, int item_slot) {

    }

    public void onEnd(PlayerInteractEvent event) {

    }

    @Override
    public FuchsRarity getDefaultRarity() {
        return RarityList.unfindable.copy();
    }

    @Override
    public boolean isKneadItem() {
        return true;
    }

    public int getChance() {
        return 1;
    }

    @Override
    public FuchsItemCategory[] getCategories() {
        return new FuchsItemCategory[] { ItemCategoryList.knead.copy() };
    }
}
