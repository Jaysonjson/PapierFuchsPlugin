package jaysonjson.papierfuchs.commands.guild;

import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.data.DataHandler;
import jaysonjson.papierfuchs.data.guild.data.zGuild;
import jaysonjson.papierfuchs.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.data.guild.obj.zGuildRank;
import jaysonjson.papierfuchs.inventories.guild.GuildInventory;
import jaysonjson.papierfuchs.object.item.CurrencyType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CreateGuildCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            FuchsPlayer fuchsPlayer = DataHandler.loadPlayer(player.getUniqueId());
            if(args.length >= 1) {
                if(args[0].equalsIgnoreCase("create")) {
                    if(fuchsPlayer.isInGuild()) {
                        player.sendMessage("Du bist bereits in einer Guilde!");
                        return true;
                    }
                    if(Utility.countCurrency(player, CurrencyType.HACKSILVER.getId(), true) >= 50000) {
                        if(!Utility.guildExists(args[1])) {
                            zGuild zGuild = new zGuild();
                            zGuild.setName(args[1]);
                            zGuild.setOwner(player.getUniqueId());
                            CreateGuildUUID(zGuild);
                            zGuild.getMembers().put(player.getUniqueId(), zGuildRank.OWNER);
                            fuchsPlayer.setGuildUuid(zGuild.getUUID());
                            DataHandler.saveGuild(zGuild);
                            DataHandler.savePlayer(fuchsPlayer);
                            player.sendMessage("Guilde " + zGuild.getName() + " (" + zGuild.getUUID() + ") erstellt!");
                           // player.getInventory().setContents(Utility.removeMoneyBackpack(player.getInventory(), 50000));
                            player.updateInventory();
                        } else {
                            player.sendMessage("Eine Guilde mit dem Namen \"" + args[1] + "\" existiert bereits!");
                            return true;
                        }
                    } else {
                        player.sendMessage("Du hast nicht genügend Geld!");
                    }
                    return true;
                }
            } else {
                if(fuchsPlayer.getGuildUuid() != null) {
                    zGuild zGuild = DataHandler.loadGuild(fuchsPlayer.getGuildUuid());
                    GuildInventory guildInventory = new GuildInventory(zGuild, fuchsPlayer);
                    guildInventory.openInventory(player);
                } else {
                    player.sendMessage("Du bist in keiner Guilde!");
                }
                return true;
            }
        }
        return false;
    }

    private void CreateGuildUUID(zGuild zGuild) {
        UUID uuid = UUID.randomUUID();
        if(!Utility.guildExists(uuid)) {
            zGuild.setUUID(uuid);
        } else {
            CreateGuildUUID(zGuild);
        }
    }
}
