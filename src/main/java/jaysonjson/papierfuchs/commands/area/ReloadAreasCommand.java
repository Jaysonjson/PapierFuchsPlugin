package jaysonjson.papierfuchs.commands.area;

import jaysonjson.papierfuchs.Utility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadAreasCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender.isOp()) {
            Utility.reloadAreas();
            commandSender.sendMessage("Gebiete wurden Neugeladen!");
            return true;
        }
        return true;
    }

}
