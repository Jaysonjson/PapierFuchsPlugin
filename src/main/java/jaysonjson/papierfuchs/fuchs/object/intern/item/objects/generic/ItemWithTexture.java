package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.generic;

import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsItemData;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.IItemUseType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class ItemWithTexture extends FuchsItem {

    String displayName;
    int modelData;
    String lore;
    boolean hide_flag = false;
    public ItemWithTexture(String id, Material material, IItemUseType itemUseType, String displayName, int modelData, String lore) {
         super(id, material, itemUseType);
         this.displayName = displayName;
         this.modelData = modelData;
         this.lore = lore;
    }

    public ItemWithTexture(String id, Material material, IItemUseType itemUseType, String displayName, int modelData) {
        super(id, material, itemUseType);
        this.displayName = displayName;
        this.modelData = modelData;
        this.lore = "";
    }

    public ItemWithTexture(String id, Material material, IItemUseType itemUseType, String displayName, int modelData, boolean hide_flag) {
        super(id, material, itemUseType);
        this.displayName = displayName;
        this.modelData = modelData;
        this.lore = "";
        this.hide_flag = hide_flag;
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        FuchsItemData fuchsItemData = new FuchsItemData(this, player, stack);
        if(!lore.equals("")) {
            fuchsItemData.addToLore(lore);
        }
        if(hide_flag) {
            fuchsItemData.itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
        }
        fuchsItemData.setItem(displayName);
        return fuchsItemData.item;
    }

    @Override
    public int getCustomModelData() {
        return modelData;
    }

    @Override
    public boolean hasCustomModelData() {
        return modelData != -1;
    }
}
