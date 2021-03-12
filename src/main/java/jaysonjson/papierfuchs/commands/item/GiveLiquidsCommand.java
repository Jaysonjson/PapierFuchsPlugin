package jaysonjson.papierfuchs.commands.item;

import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.object.item.FuchsMCItem;
import jaysonjson.papierfuchs.object.item.ItemList;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.liquid.FuchsLiquid;
import jaysonjson.papierfuchs.registry.FuchsRegistries;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveLiquidsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg0 instanceof Player) {
			Player player = (Player) arg0;
			if (arg0.isOp()) {
				for (FuchsLiquid fuchsLiquid : FuchsRegistries.liquids.values()) {
					ItemStack liquidContainer = ItemList.LIQUID_CONTAINER.get().createItem(player);
					FuchsMCItem fuchsMCItem = new FuchsMCItem(Utility.getFuchsItemFromNMS(liquidContainer), liquidContainer);
					fuchsMCItem.changeStringTag(ItemNBT.CONTAINED_LIQUID, fuchsLiquid.getID());
					fuchsMCItem.changeDoubleTag(ItemNBT.LIQUID_AMOUNT, 500d);
					player.getWorld().dropItemNaturally(player.getLocation(), fuchsMCItem.getItemStack());
				}
			}
		}
		return false;
	}

}
