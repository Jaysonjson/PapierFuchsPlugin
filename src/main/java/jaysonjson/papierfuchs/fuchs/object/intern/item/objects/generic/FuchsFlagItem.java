package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.generic;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsItemData;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.RarityList;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.UseTypeList;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class FuchsFlagItem extends FuchsItem {

    FuchsLanguageString displayName;
    int modeldata;
    public FuchsFlagItem(String id, Material material, int modeldata, FuchsLanguageString displayName) {
        super(id, material, UseTypeList.NONE.copy());
        this.modeldata = modeldata;
        this.displayName = displayName;
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        FuchsItemData fuchsItemData = new FuchsItemData(this, player, stack);
        fuchsItemData.itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
        fuchsItemData.setItem();
        return fuchsItemData.item;
    }

    @Override
    public FuchsRarity getDefaultRarity() {
        return RarityList.unfindable.copy();
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return displayName;
    }

    @Override
    public int getCustomModelData() {
        return modeldata;
    }
}
