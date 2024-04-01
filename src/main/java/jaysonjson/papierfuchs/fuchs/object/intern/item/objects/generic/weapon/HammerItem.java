package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.generic.weapon;

import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsItemData;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.IItemUseType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class HammerItem extends FuchsItem {

    String resource;
    int damage;
    int durability;
    int cmd;
    float attackSpeed;

    public HammerItem(String id, String resource, int damage, int durability, float attackSpeed, int cmd, Material material, IItemUseType itemUseType) {
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
        fuchsItemData.addToLore(ChatColor.GRAY + "Ein Hammer aus " + resource);
        fuchsItemData.setItem(ChatColor.DARK_GRAY + resource + " Hammer");
        return fuchsItemData.item;
    }

    @Override
    public double getToolDamage() {
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
