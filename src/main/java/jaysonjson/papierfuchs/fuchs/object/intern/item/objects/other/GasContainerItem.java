package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.other;

import jaysonjson.papierfuchs.fuchs.object.intern.category.item.FuchsItemCategory;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.ItemCategoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.item.ItemCategoryInventoryOld;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.object.intern.gas.FuchsGas;
import jaysonjson.papierfuchs.fuchs.object.intern.gas.GasList;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsItemData;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.IItemUseType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GasContainerItem extends FuchsItem {

    FuchsGas abstractGas;
    FuchsItemData fuchsItemData;
    public GasContainerItem(String id, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        fuchsItemData = new FuchsItemData(this, player, stack);
        abstractGas = FuchsUtility.gasExists(fuchsItemData.fuchsMCItem.getData().getGasID()) ? FuchsUtility.getGasByID(fuchsItemData.fuchsMCItem.getData().getGasID()) : GasList.NONE.copy();

        fuchsItemData.addToLore(abstractGas.getDisplayName().getMain());

        fuchsItemData.addToLore(fuchsItemData.fuchsMCItem.getData().getGasAmount() + "ml");
        fuchsItemData.addToLore(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "»" + fuchsItemData.fuchsMCItem.getData().getGasID() + "«");
        fuchsItemData.setItem(ChatColor.RESET + "Gasbehälter");
        return fuchsItemData.item;
    }

    @Override
    public boolean showInItemList() {
        return false;
    }

    @Override
    public int getCustomModelData() {
        return 15;
    }

    @Override
    public FuchsItemCategory[] getCategories() {
        return new FuchsItemCategory[] { ItemCategoryList.gas.copy() };
    }

}
