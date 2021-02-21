package jaysonjson.papierfuchs.object.item.type.generic;

import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.item.FuchsItemData;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.item.ItemUseType;
import jaysonjson.papierfuchs.object.item.interfaces.IItemUseType;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
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

    public ItemWithTexture(String id, Material material, ItemUseType itemUseType, String displayName, int modelData) {
        super(id, material, itemUseType);
        this.displayName = displayName;
        this.modelData = modelData;
        this.lore = "";
    }

    public ItemWithTexture(String id, Material material, ItemUseType itemUseType, String displayName, int modelData, boolean hide_flag) {
        super(id, material, itemUseType);
        this.displayName = displayName;
        this.modelData = modelData;
        this.lore = "";
        this.hide_flag = hide_flag;
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        FuchsItemData fuchsItemData = new FuchsItemData(this, player);
        fuchsItemData.lore.add(lore);
        if(hide_flag) {
            fuchsItemData.itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
        }
        fuchsItemData.setItem(displayName);
        return fuchsItemData.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT, false);
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        return tag;
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
