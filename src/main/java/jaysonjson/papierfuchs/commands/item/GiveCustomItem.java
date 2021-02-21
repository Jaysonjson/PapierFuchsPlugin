package jaysonjson.papierfuchs.commands.item;

import jaysonjson.papierfuchs.Utility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveCustomItem implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender.isOp() && commandSender instanceof Player) {
            Player player = (Player) commandSender;
            player.getWorld().dropItemNaturally(player.getLocation(), Utility.getFuchsItemByID(args[0]).createItem(player));
        }
        return false;
    }

}
