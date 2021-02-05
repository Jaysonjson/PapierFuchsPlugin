package jaysonjson.papierfuchs.commands.item;

import org.jetbrains.annotations.NotNull;
import jaysonjson.papierfuchs.registry.FuchsRegistries;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ItemIDCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        StringBuilder content = new StringBuilder();
        int i = 0;
        content.append(ChatColor.BOLD + "Items: \n" + ChatColor.RESET);
        for (String itemKey : FuchsRegistries.items.keySet()) {
            i++;
            String suffix = ", ";
            if(i >= FuchsRegistries.items.size()) {
                suffix = "\n";
            }
            content.append(itemKey).append(suffix);
        }

        i = 0;
        content.append(ChatColor.BOLD + "Flüssigkeiten: \n" + ChatColor.RESET);
        for (String liquidKey : FuchsRegistries.liquids.keySet()) {
            i++;
            String suffix = ", ";
            if(i >= FuchsRegistries.liquids.size()) {
                suffix = "\n";
            }
            content.append(liquidKey).append(suffix);
        }

        i = 0;
        content.append(ChatColor.BOLD + "Gase: \n" + ChatColor.RESET);
        for (String gasKey : FuchsRegistries.gasses.keySet()) {
            i++;
            String suffix = ", ";
            if(i >= FuchsRegistries.gasses.size()) {
                suffix = "\n";
            }
            content.append(gasKey).append(suffix);
        }
        commandSender.sendMessage(content.toString());
        return true;
    }
}
