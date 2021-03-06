package jaysonjson.papierfuchs.object.liquid.objects;

import jaysonjson.papierfuchs.References;
import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.object.item.FuchsMCItem;
import jaysonjson.papierfuchs.object.item.ItemList;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.liquid.FuchsLiquid;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class CokeLiquid extends FuchsLiquid {

    @Override
    public String getID() {
        return "coke";
    }


    @Override
    public String getDisplayName() {
        return ChatColor.RESET + "" + ChatColor.GRAY + "Cola";
    }

    @Override
    public int getCustomModelData() {
        return 33;
    }

    @Override
    public int getEmptyModelData() {
        return 34;
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
