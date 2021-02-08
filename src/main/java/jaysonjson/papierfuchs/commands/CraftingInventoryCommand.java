package jaysonjson.papierfuchs.commands;

import jaysonjson.papierfuchs.inventories.crafting.general.GeneralCraftingInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CraftingInventoryCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if(player.isOp()) {
                GeneralCraftingInventory generalCraftingInventory = new GeneralCraftingInventory();
                generalCraftingInventory.createPageData(player);
                generalCraftingInventory.openInventory(player, 0);
            }
        }
        return false;
    }
}
