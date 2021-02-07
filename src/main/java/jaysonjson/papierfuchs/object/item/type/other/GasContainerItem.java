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
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import net.minecraft.server.v1_16_R3.NBTTagCompound;

public class GasContainerItem extends FuchsItem {

    String gas;
    Double amount;
    FuchsGas abstractGas;
    public GasContainerItem(String id, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        boolean exists = true;
        if(stack == null) {
            stack = new ItemStack(getMaterial());
            exists = false;
        }
        FuchsItemData oItem = new FuchsItemData(this, player, stack);

        if(exists) {
            NBTTagCompound tag = getTag(Utility.getItemTag(Utility.createNMSCopy(stack)));
            if(tag.hasKey(ItemNBT.CONTAINED_GAS)) {
            	gas = tag.getString(ItemNBT.CONTAINED_GAS);
            }
            if(tag.hasKey(ItemNBT.GAS_AMOUNT)) {
            	amount = tag.getDouble(ItemNBT.GAS_AMOUNT);
            }
        } else {
        	gas = GasList.NONE.getID();
        	amount = 0.0;
        }

        abstractGas = Utility.gasExists(gas) ? Utility.getGasByID(gas) : GasList.NONE;
              
        oItem.lore.add(abstractGas.getDisplayName());
        
        oItem.lore.add(amount + "ml");
        oItem.lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "»" + gas + "«");
        oItem.setItem(ChatColor.RESET + "Gasbehälter");
        oItem.createNMSCopy();
        oItem.nmsCopy.setTag(getTag(oItem.getTagCompound()));
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        return oItem.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        tag.setBoolean(ItemNBT.CAN_CRAFT, true);
        if(!tag.hasKey(ItemNBT.CONTAINED_GAS)) {
            tag.setString(ItemNBT.CONTAINED_GAS, gas);
        }
        if(!tag.hasKey(ItemNBT.GAS_AMOUNT)) {
        	tag.setDouble(ItemNBT.GAS_AMOUNT, amount);
        }
        return tag;
    }

    @Override
    public int getCustomModelData() {
        return 15;
    }
}
