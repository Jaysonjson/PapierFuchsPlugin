package jaysonjson.papierfuchs.paper.commands;

import jaysonjson.papierfuchs.fuchs.io.data.server.data.FuchsReport;
import jaysonjson.papierfuchs.fuchs.utility.Colors;
import jaysonjson.papierfuchs.fuchs.utility.References;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public class ReportCommand implements CommandExecutor {

    MultiCommand multiCommand;
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String string, @NotNull String[] strings) {
        if(commandSender instanceof Player player) {
            multiCommand = new MultiCommand(player, string, strings);
            if(!multiCommand.tertiary.equalsIgnoreCase("")) {
                String joined = multiCommand.tertiary;
                joined = joined + String.join(" ", multiCommand.args);
                joined = joined.substring(multiCommand.args[0].length() + multiCommand.args[1].length() + 1);

                Player target = null;
                if (!multiCommand.secondary.equalsIgnoreCase("")) {
                    if (Bukkit.getPlayer(multiCommand.secondary) != null) {
                        target = Bukkit.getPlayer(multiCommand.secondary);
                    } else {
                        player.sendMessage(Component.text(Colors.RED_WHITE + "Spieler " + ChatColor.AQUA + multiCommand.tertiarySubData + Colors.RED_WHITE + " existiert nicht!"));
                        return true;
                    }
                }
                if(target != null) {
                    FuchsReport fuchsReport = new FuchsReport();
                    fuchsReport.setReporter(player.getUniqueId());
                    fuchsReport.setReportee(target.getUniqueId());
                    fuchsReport.setReason(joined);
                    fuchsReport.setTime(LocalDateTime.now());
                    References.data.getByteServer().getReports().add(fuchsReport);
                    player.sendMessage(Colors.GREEN + "Spieler " + ChatColor.AQUA + target.getName() + Colors.GREEN + " wurde wegen dem Grund \"" + ChatColor.AQUA + joined + Colors.GREEN + "\" gemeldet!");
                }
                return true;
            } else {
                player.sendMessage(multiCommand.missingArguments());
            }
        }
        return false;
    }

}
