package jaysonjson.papierfuchs.paper.commands;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.InventoryList;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.network.protocol.game.PacketPlayOutAnimation;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FuchsSingleCommand implements CommandExecutor {

    Player player;
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String string, @NotNull String[] strings) {
        if(commandSender instanceof Player player) {
           this.player = player;
            switch (string.toLowerCase()) {
                case "settings" -> openPlayerSettings();
                case "fuchs", "papierfuchs", "paperfox" -> fuchs();
                case "test" -> {
                    PacketPlayOutAnimation packet = new PacketPlayOutAnimation(((CraftPlayer)player).getHandle(), 3);
                    ((CraftPlayer)player).getHandle().b.sendPacket(packet);
                }
            }
            gamemode(string);
            return true;
        }
        return false;
    }

    public void openPlayerSettings() {
        InventoryList.playerSettings.copy().createAndOpen(player);
    }

    public void fuchs() {
        player.sendMessage("Fuchs Plugin Version: " + ChatColor.AQUA + "" + PapierFuchs.getBuild() + ChatColor.RESET + " [" + ChatColor.GREEN + PapierFuchs.MC + ChatColor.RESET +"] - " + ChatColor.LIGHT_PURPLE + "Jayson.json");
    }

    public void gamemode(String s) {
        String commandString = switch (s) {
            case "gmc" -> "gamemode creative";
            case "gms" -> "gamemode survival";
            case "gmsp" -> "gamemode spectator";
            case "gma" -> "gamemode adventure";
            default -> "";
        };
        Bukkit.dispatchCommand(player, commandString);
    }

}
