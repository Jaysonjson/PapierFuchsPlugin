package jaysonjson.papierfuchs.object.item.objects.other;

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
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import net.minecraft.server.v1_16_R3.NBTTagCompound;

public class LiquidContainerItem extends FuchsItem {

    FuchsItemData fuchsItemData;
    public LiquidContainerItem(String id, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        fuchsItemData = new FuchsItemData(this, player, stack);
        fuchsItemData.addLiquidLores();
        fuchsItemData.setItem(ChatColor.RESET + "Flüssigkeitsbehälter");
        return fuchsItemData.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        tag.setBoolean(ItemNBT.CAN_CRAFT, true);
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
        if(isEmpty()) {
            return fuchsItemData.fuchsLiquid.getEmptyModelData();
        }
        return fuchsItemData.fuchsLiquid.getCustomModelData();
    }

    public boolean isEmpty() {
        return fuchsItemData.liquid_amount <= 0;
    }
}
