package jaysonjson.papierfuchs.paper.commands;

import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.fuchs.utility.Colors;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.utility.References;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MultiCommand {

    public String main;
    public String secondary;
    public String tertiary;
    public String tertiaryData;
    public String tertiarySubData;
    public String quaternary;
    public Player player;
    public FuchsPlayer fuchsPlayer;
    public String[] args;
    public MultiCommand(Player player, String command, String[] strings) {
        main = command;
        secondary = getArg(strings, 0);
        tertiary = getArg(strings, 1);
        tertiaryData = getArg(strings, 2);
        tertiarySubData = getArg(strings, 3);
        quaternary = getArg(strings, 4);
        this.player = player;
        this.fuchsPlayer = References.data.getPlayer(player);
        this.args = strings;
    }

    public String getArg(String[] args, int index) {
        if(args.length > index) {
            return args[index];
        }
        return "";
    }

    public boolean validate(String permission) {
        if(FuchsUtility.hasPermission(player, permission)) {
            return true;
        }
        player.sendMessage(noPermissions(permission));
        return false;
    }

    public Component noPermissions(String permission) {
        return Component.text(Colors.RED_WHITE + "Keine Rechte! Du benötigst: " + ChatColor.AQUA + permission);
    }

    public Component missingArguments() {
        return Component.text(Colors.RED_WHITE + "Fehlende Argumente!");
    }

    public Component notANumber(String sub) {
        return Component.text(ChatColor.AQUA + sub + Colors.RED_WHITE + " ist keine Zahl!");
    }

    public String commandString() {
        return main + " " + secondary + " " + tertiary + " " + tertiaryData + " " + tertiarySubData + " ";
    }

}
