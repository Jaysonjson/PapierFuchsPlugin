package jaysonjson.papierfuchs.paper.commands;

import jaysonjson.papierfuchs.fuchs.object.intern.inventory.InventoryList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DropInventoryCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String string, @NotNull String[] strings) {
        if(commandSender instanceof Player player) {
            InventoryList.dropList.copy().createAndOpen(player);
        }
        return false;
    }
}
