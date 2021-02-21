package jaysonjson.papierfuchs.object.item.type.resource.vilum.tool;

import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.item.interfaces.IItemUseType;
import jaysonjson.papierfuchs.object.item.type.generic.weapon.SwordItem;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.Material;

public class VilumSwordItem extends SwordItem {

    public VilumSwordItem(String id, String resource, int damage, int durability, float attackSpeed, int cmd, Material material, IItemUseType itemUseType) {
        super(id, resource, damage, durability, attackSpeed, cmd, material, itemUseType);
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setDouble(ItemNBT.LIGHT_MAGIC_AMOUNT, 2.1d);
        tag.setDouble(ItemNBT.FIRE_MAGIC_AMOUNT, 1.2d);
        return super.getTag(tag);
    }
}
