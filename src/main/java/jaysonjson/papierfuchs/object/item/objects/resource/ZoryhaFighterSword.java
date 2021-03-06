package jaysonjson.papierfuchs.object.item.objects.resource;

import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.object.effect.EffectList;
import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.item.FuchsItemData;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.item.interfaces.IItemUseType;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ZoryhaFighterSword extends FuchsItem {

    FuchsItemData fuchsItemData;
    public ZoryhaFighterSword(String id, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        fuchsItemData = new FuchsItemData(this, player, stack);
        fuchsItemData.setItem(ChatColor.AQUA + "Schwert der Krieger von Zoryha");
        return fuchsItemData.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        Utility.addEffectToTag(tag, EffectList.ANTI_DESPAWN);
        Utility.addEffectToTag(tag, EffectList.BLOOD_BOUND, fuchsItemData.player);
        //tag.setBoolean(ItemNBT.HAS_EFFECT_ID + EffectList.SMELT.getID(), true);
        return tag;
    }

    @Override
    public int getMaxDurability() {
        return 3000;
    }

    @Override
    public float getToolAttackSpeed() {
        return 1.8f;
    }

    @Override
    public int getToolDamage() {
        return 10;
    }

    @Override
    public int getCustomModelData() {
        return 29;
    }

    @Override
    public boolean stackAble() {
        return false;
    }
}
