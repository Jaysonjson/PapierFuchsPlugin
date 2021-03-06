package jaysonjson.papierfuchs.object.liquid.objects;

import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.object.item.FuchsMCItem;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.liquid.FuchsLiquid;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DrinkableWaterLiquid extends FuchsLiquid {

    @Override
    public String getID() {
        return "drinkablewater";
    }

    @Override
    public String getDisplayName() {
        return ChatColor.RESET + "" + ChatColor.AQUA + "Trinkbares Wasser";
    }

    @Override
    public int getCustomModelData() {
        return 35;
    }

    @Override
    public int getEmptyModelData() {
        return 36;
    }

    @Override
    public void drinkAction(Player player, ItemStack itemStack) {
        FuchsMCItem fuchsItem = new FuchsMCItem(Utility.getFuchsItemFromNMS(itemStack), player, itemStack);
        if(fuchsItem.getTagFromOriginal().hasKey(ItemNBT.LIQUID_AMOUNT)) {
            if(fuchsItem.getDoubleFromTag(ItemNBT.LIQUID_AMOUNT) > 0) {
                fuchsItem.changeDoubleTag(ItemNBT.LIQUID_AMOUNT, fuchsItem.getDoubleFromTag(ItemNBT.LIQUID_AMOUNT) - 10);
                itemStack = fuchsItem.getItemStack();
                if(fuchsItem.getDoubleFromTag(ItemNBT.LIQUID_AMOUNT) < 0) {
                    fuchsItem.changeDoubleTag(ItemNBT.LIQUID_AMOUNT, 0d);
                }
                player.getInventory().setItemInMainHand(itemStack);
                player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_DRINK, 1, 1);
                player.updateInventory();
            }
        }
    }

}
