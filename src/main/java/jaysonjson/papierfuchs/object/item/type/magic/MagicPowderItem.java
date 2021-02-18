package jaysonjson.papierfuchs.object.item.type.magic;

import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.item.FuchsItemData;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.item.interfaces.IItemUseType;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MagicPowderItem extends FuchsItem {

    public MagicType magicType;
    public int modelData;
    public MagicPowderItem(String id, Material material, IItemUseType itemUseType, MagicType magicType, int modelData) {
        super(id, material, itemUseType);
        this.magicType = magicType;
        this.modelData = modelData;
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        FuchsItemData oItem = new FuchsItemData(this, player);

        oItem.lore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Verstärkt das Zauber-Attribut von " + magicType.getColor() + magicType.getName() + ChatColor.GRAY + " Gegenständen");
        oItem.setItem(magicType.getColor() + magicType.getName() + ChatColor.RESET + "-Zauber Staub");
        return oItem.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT, false);
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        tag.setString(ItemNBT.MAGIC_TYPE, magicType.getNbt());
        return tag;
    }

    @Override
    public int getCustomModelData() {
        return modelData;
    }
}
