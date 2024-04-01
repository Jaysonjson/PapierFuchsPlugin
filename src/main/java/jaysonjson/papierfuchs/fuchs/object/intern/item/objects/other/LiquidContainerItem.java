package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.other;

import jaysonjson.papierfuchs.fuchs.object.intern.category.item.FuchsItemCategory;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.ItemCategoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.item.ItemCategoryInventoryOld;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsItemData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.IItemUseType;
import jaysonjson.papierfuchs.fuchs.object.intern.liquid.FuchsLiquid;
import jaysonjson.papierfuchs.fuchs.object.intern.liquid.LiquidList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class LiquidContainerItem extends FuchsItem {

    FuchsItemData fuchsItemData;
    public LiquidContainerItem(String id, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        fuchsItemData = new FuchsItemData(this, player, stack);
        fuchsItemData.addLiquidLores();
        fuchsItemData.setItem(ChatColor.RESET + "Flüssigkeitsbehälter");
        return fuchsItemData.item;
    }


    @Override
    public void onItemRightClickAir(PlayerInteractEvent event) {
        ItemStack itemStack = event.getItem();
        FuchsMCItem fuchsItem = new FuchsMCItem(FuchsUtility.getFuchsItemFromNMS(itemStack), event.getPlayer(), itemStack);
        String liId = fuchsItem.getData().getLiquidID();
        FuchsLiquid aLiquid = FuchsUtility.liquidExists(liId) ? FuchsUtility.getLiquidByID(liId) : LiquidList.NONE.copy();
        aLiquid.drinkAction(event.getPlayer(), itemStack);
    }

    @Override
    public int getCustomModelData() {
        if(fuchsItemData != null) {
            if (isEmpty()) {
                return fuchsItemData.fuchsLiquid.getEmptyModelData();
            }
            return fuchsItemData.fuchsLiquid.getCustomModelData();
        }
        return -1;
    }

    public boolean isEmpty() {
        if(fuchsItemData != null) {
            if (fuchsItemData.fuchsMCItem != null) {
                return fuchsItemData.fuchsMCItem.getData().getLiquidAmount() <= 0;
            }
        }
        return true;
    }

    @Override
    public boolean showInItemList() {
        return false;
    }

    @Override
    public FuchsItemCategory[] getCategories() {
        return new FuchsItemCategory[] { ItemCategoryList.liquid.copy() };
    }

}
