package jaysonjson.papierfuchs.object.liquid.obj;

import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.data.DataHandler;
import jaysonjson.papierfuchs.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.object.item.FuchsMCItem;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.liquid.FuchsLiquid;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class BeerLiquid extends FuchsLiquid {

	@Override
	public String getID() {
		return "beer";
	}

	@Override
	public String getDisplayName() {
		return ChatColor.RESET + "" + ChatColor.GOLD + "Bier";
	}
	
	@Override
	public int getCustomModelData() {
		return 5;
	}
	
	@Override
	public boolean drinkAble() {
		return true;
	}
	
	@Override
	public boolean drinkAction(World world, Player player, ItemStack itemStack) {
        FuchsPlayer fuchsPlayer = DataHandler.loadPlayer(player.getUniqueId());
		fuchsPlayer.getPlayerSpecial().alcohol += new Random().nextDouble() / 15;
        player.sendMessage("Alkohol: " + fuchsPlayer.getPlayerSpecial().alcohol);
        
        FuchsMCItem fuchsItem = new FuchsMCItem(Utility.getFuchsItemFromNMS(itemStack), itemStack);
        fuchsItem.changeDoubleTag(ItemNBT.LIQUID_AMOUNT, fuchsItem.getDoubleFromTag(ItemNBT.LIQUID_AMOUNT) - 10);
        
        itemStack = fuchsItem.getItemStack();

        
        player.setItemInHand(itemStack);
        Utility.makeDrunk(player, fuchsPlayer);
        player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_DRINK, 1, 1);
        DataHandler.savePlayer(fuchsPlayer);
        player.updateInventory();
        return true;
	}

}
