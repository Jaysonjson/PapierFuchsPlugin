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
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import net.minecraft.server.v1_16_R3.NBTTagCompound;

public class LiquidContainerItem extends FuchsItem {


    FuchsLiquid abstractLiquid;
    FuchsItemData fuchsItemData;
    public LiquidContainerItem(String id, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        fuchsItemData = new FuchsItemData(this, player, stack);
        abstractLiquid = Utility.liquidExists(fuchsItemData.contained_liquid) ? Utility.getLiquidByID(fuchsItemData.contained_liquid) : LiquidList.NONE;
        fuchsItemData.lore.add(abstractLiquid.getDisplayName());
        fuchsItemData.lore.add(fuchsItemData.liquid_amount + "ml");
        fuchsItemData.lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "»" + fuchsItemData.contained_liquid + "«");
        fuchsItemData.setItem(ChatColor.RESET + "Flüssigkeitsbehälter");
        fuchsItemData.createNMSCopy();
        fuchsItemData.nmsCopy.setTag(getTag(fuchsItemData.getTagCompound()));
        fuchsItemData.item = CraftItemStack.asBukkitCopy(fuchsItemData.nmsCopy);
        return fuchsItemData.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        tag.setBoolean(ItemNBT.CAN_CRAFT, true);
        if(!tag.hasKey(ItemNBT.CONTAINED_LIQUID)) {
            tag.setString(ItemNBT.CONTAINED_LIQUID, fuchsItemData.contained_liquid);
        }
        if(!tag.hasKey(ItemNBT.LIQUID_AMOUNT)) {
        	tag.setDouble(ItemNBT.LIQUID_AMOUNT, fuchsItemData.liquid_amount);
        }
        return tag;
    }


    @Override
    public void onItemRightClickAir(PlayerInteractEvent event) {
        ItemStack itemStack = event.getItem();
        FuchsMCItem fuchsItem = new FuchsMCItem(Utility.getFuchsItemFromNMS(itemStack), itemStack);
        String liId = fuchsItem.getStringFromTag(ItemNBT.CONTAINED_LIQUID);
        FuchsLiquid aLiquid = Utility.liquidExists(liId) ? Utility.getLiquidByID(liId) : LiquidList.NONE;
        aLiquid.drinkAction(event.getPlayer(), itemStack);
    }

    @Override
    public int getCustomModelData() {
        return abstractLiquid.getCustomModelData();
    }

}
