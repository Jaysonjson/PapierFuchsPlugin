package jaysonjson.papierfuchs.commands.area;

import org.jetbrains.annotations.NotNull;
import jaysonjson.papierfuchs.inventories.area.AreaListInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AreaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if(player.isOp()) {
                AreaListInventory inventory = new AreaListInventory();
                inventory.createPageData(player);
                inventory.openInventory(player, 0);
            }
            return true;
        }
        return false;
    }

}
