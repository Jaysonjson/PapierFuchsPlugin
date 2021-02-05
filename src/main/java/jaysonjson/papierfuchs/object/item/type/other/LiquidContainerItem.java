package jaysonjson.papierfuchs.object.item.type.other;

import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.item.FuchsItemData;
import jaysonjson.papierfuchs.object.item.FuchsMCItem;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.item.interfaces.IItemUseType;
import jaysonjson.papierfuchs.object.liquid.FuchsLiquid;
import jaysonjson.papierfuchs.object.liquid.LiquidList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import net.minecraft.server.v1_16_R3.NBTTagCompound;

public class LiquidContainerItem extends FuchsItem {

    String liquid;
    Double amount;
    FuchsLiquid abstractLiquid;
    public LiquidContainerItem(String id, Material material, IItemUseType itemUseType) {
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
            if(tag.hasKey(ItemNBT.CONTAINED_LIQUID)) {
            	liquid = tag.getString(ItemNBT.CONTAINED_LIQUID);
            }
            if(tag.hasKey(ItemNBT.LIQUID_AMOUNT)) {
            	amount = tag.getDouble(ItemNBT.LIQUID_AMOUNT);
            }
        } else {
        	liquid = LiquidList.NONE.getID();
        	amount = 0.0;
        }
        abstractLiquid = Utility.liquidExists(liquid) ? Utility.getLiquidByID(liquid) : LiquidList.NONE;
        oItem.lore.add(abstractLiquid.getDisplayName());
        
        oItem.lore.add(amount + "ml");
        oItem.lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "»" + liquid + "«");
        oItem.setItem(ChatColor.RESET + "Flüssigkeitsbehälter");
        oItem.createNMSCopy();
        oItem.nmsCopy.setTag(getTag(oItem.getTagCompound()));
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        return oItem.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        tag.setBoolean(ItemNBT.CAN_CRAFT, true);
        if(!tag.hasKey(ItemNBT.CONTAINED_LIQUID)) {
            tag.setString(ItemNBT.CONTAINED_LIQUID, liquid);
        }
        if(!tag.hasKey(ItemNBT.LIQUID_AMOUNT)) {
        	tag.setDouble(ItemNBT.LIQUID_AMOUNT, amount);
        }
        return tag;
    }

    
    @Override
    public void ability(World world, Player player, ItemStack itemStack) {
    	FuchsMCItem fuchsItem = new FuchsMCItem(Utility.getFuchsItemFromNMS(itemStack), itemStack);
    	String liId = fuchsItem.getStringFromTag(ItemNBT.CONTAINED_LIQUID);
    	FuchsLiquid aLiquid = Utility.liquidExists(liId) ? Utility.getLiquidByID(liId) : LiquidList.NONE;
    	aLiquid.drinkAction(world, player, itemStack);
    	//FuchsItem fuchsItem = new FuchsItem(Utility.getFuchsItemFromNMS(itemStack), itemStack);
        //fuchsItem.changeStringTag(ItemNBT.CONTAINED_LIQUID, zLiquid.BEER.getLiquid().getId());
        //fuchsItem.changeDoubleTag(ItemNBT.LIQUID_AMOUNT, 500d);
    	//world.dropItemNaturally(player.getLocation(), fuchsItem.getItemStack());
        //player.setItemInHand(fuchsItem.getItemStack());
    }
    
    @Override
    public boolean isAbilityItem() {
    	return true;
    }

    @Override
    public int getCustomModelData() {
        return abstractLiquid.getCustomModelData();
    }

}
