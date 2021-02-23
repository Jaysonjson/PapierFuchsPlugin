package jaysonjson.papierfuchs.object.item.objects.crafting;

import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.item.FuchsItemData;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.item.interfaces.IItemUseType;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TestSwordItem extends FuchsItem {

    public TestSwordItem(String id, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
    }


    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        FuchsItemData oItem = new FuchsItemData(this, player, stack);
        oItem.lore.add(ChatColor.GRAY + "Ein Schwert aus Test");
        oItem.setItem(ChatColor.LIGHT_PURPLE + "Test Schwert");
        return oItem.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT, true);
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        if(!tag.hasKey(ItemNBT.ITEM_DURABILITY)) {
            tag.setInt(ItemNBT.ITEM_DURABILITY, getMaxDurability());
        }
        return tag;
    }

    @Override
    public int getMaxDurability() {
        return 5;
    }

    @Override
    public int getToolDamage() {
        return 555;
    }

    @Override
    public int getCustomModelData() {
        return 25;
    }
}
