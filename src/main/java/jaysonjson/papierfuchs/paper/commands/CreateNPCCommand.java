package jaysonjson.papierfuchs.paper.commands;

import jaysonjson.papierfuchs.old.npc.NPC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateNPCCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length < 3) {
            NPC.createNPC((Player) commandSender, strings[0], strings[1], false);
        } else {
            NPC.createNPC((Player) commandSender, strings[0], strings[1], Float.parseFloat(strings[2]), Float.parseFloat(strings[3]), false);
        }
        //NPC.createNPC((Player) commandSender, "NPC", "");
        return false;
    }
}
