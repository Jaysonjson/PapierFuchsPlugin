package jaysonjson.papierfuchs.object.item.type.other;

import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.object.gas.FuchsGas;
import jaysonjson.papierfuchs.object.gas.GasList;
import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.item.FuchsItemData;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.item.interfaces.IItemUseType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import net.minecraft.server.v1_16_R3.NBTTagCompound;

public class GasContainerItem extends FuchsItem {

    FuchsGas abstractGas;
    FuchsItemData fuchsItemData;
    public GasContainerItem(String id, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        fuchsItemData = new FuchsItemData(this, player, stack);
        abstractGas = Utility.gasExists(fuchsItemData.contained_gas) ? Utility.getGasByID(fuchsItemData.contained_gas) : GasList.NONE;

        fuchsItemData.lore.add(abstractGas.getDisplayName());

        fuchsItemData.lore.add(fuchsItemData.gas_amount + "ml");
        fuchsItemData.lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "»" + fuchsItemData.contained_gas + "«");
        fuchsItemData.setItem(ChatColor.RESET + "Gasbehälter");
        return fuchsItemData.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        tag.setBoolean(ItemNBT.CAN_CRAFT, true);
        if(!tag.hasKey(ItemNBT.CONTAINED_GAS)) {
            tag.setString(ItemNBT.CONTAINED_GAS, fuchsItemData.contained_gas);
        }
        if(!tag.hasKey(ItemNBT.GAS_AMOUNT)) {
        	tag.setDouble(ItemNBT.GAS_AMOUNT, fuchsItemData.gas_amount);
        }
        return tag;
    }

    @Override
    public int getCustomModelData() {
        return 15;
    }
}
