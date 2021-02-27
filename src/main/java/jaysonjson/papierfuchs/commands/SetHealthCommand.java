package jaysonjson.papierfuchs.commands;

import jaysonjson.papierfuchs.References;
import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.data.player.FuchsPlayer;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SetHealthCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender.hasPermission("zapan.sethealth") || commandSender.isOp()) {
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;
                int health = 0;
                if (args.length == 1) {
                    if (StringUtils.isNumeric(args[0])) {
                        health = Integer.parseInt(args[0]);
                    } else {
                        commandSender.sendMessage(args[0] + " ist keine Zahl!");
                    }
                } else if (args.length == 2) {
                    player = Bukkit.getPlayer(args[0]);
                    if (StringUtils.isNumeric(args[1])) {
                        health = Integer.parseInt(args[1]);
                    } else {
                        commandSender.sendMessage(args[1] + " ist keine Zahl!");
                    }
                }
                UUID uuid = player.getUniqueId();
                FuchsPlayer fuchsPlayer = References.data.getPlayer(player.getUniqueId());
                fuchsPlayer.getHealth().health = health;
                //DataHandler.savePlayer(fuchsPlayer);
                Utility.refreshHearts(player, fuchsPlayer);
                commandSender.sendMessage("Das Leben von " + player.getDisplayName() + " wurde auf " + fuchsPlayer.getHealth().health + " geändert!");
                return true;
            }
            return false;
        } else {
            commandSender.sendMessage("Du hast dafür keine Rechte!");
            return true;
        }
    }

}
