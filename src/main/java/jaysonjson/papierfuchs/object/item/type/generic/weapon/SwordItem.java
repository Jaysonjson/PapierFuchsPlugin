package jaysonjson.papierfuchs.object.item.type.generic.weapon;

import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.item.FuchsItemData;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.item.interfaces.IItemUseType;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SwordItem extends FuchsItem {

    String resource;
    int damage;
    int durability;
    int cmd;
    float attackSpeed;

    public SwordItem(String id, String resource, int damage, int durability, float attackSpeed, int cmd, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
        this.resource = resource;
        this.damage = damage;
        this.durability = durability;
        this.cmd = cmd;
        this.attackSpeed = attackSpeed;
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        FuchsItemData fuchsItemData = new FuchsItemData(this, player, stack);
        fuchsItemData.addToLore(ChatColor.GRAY + "Ein Schwert aus " + resource);
        fuchsItemData.setItem(ChatColor.DARK_GRAY.toString() + resource + " Schwert");
        return fuchsItemData.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT, true);
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        return tag;
    }

    @Override
    public int getToolDamage() {
        return damage;
    }

    @Override
    public int getMaxDurability() {
        return durability;
    }

    @Override
    public int getCustomModelData() {
        return cmd;
    }

    @Override
    public float getToolAttackSpeed() {
        return attackSpeed;
    }
}
