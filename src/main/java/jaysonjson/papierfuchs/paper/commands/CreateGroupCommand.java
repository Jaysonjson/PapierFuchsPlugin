package jaysonjson.papierfuchs.paper.commands;

import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.fuchs.object.intern.group.FuchsGroup;
import jaysonjson.papierfuchs.fuchs.object.intern.group.FuchsGroupPlayer;
import jaysonjson.papierfuchs.fuchs.utility.References;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

@Deprecated
public class CreateGroupCommand implements CommandExecutor {

    public HashMap<UUID, UUID> invites = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player player) {
            if(strings.length > 0) {
                String subcommand = strings[0];
                if(subcommand.equalsIgnoreCase("invite")) {
                    if(strings.length > 1) {
                        String target_name = strings[1];
                        if(Bukkit.getPlayer(target_name) != null) {
                            Player target = Bukkit.getPlayer(target_name);
                            FuchsPlayer fuchsPlayer = References.data.getPlayer(player);
                            FuchsGroup fuchsGroup;
                            if(!fuchsPlayer.isInGroup()) {
                                fuchsGroup = new FuchsGroup();
                                fuchsGroup.getPlayers().add(new FuchsGroupPlayer(player, true));
                                References.groups.put(player.getUniqueId(), fuchsGroup);
                            }
                            FuchsPlayer fuchsTarget = References.data.getPlayer(target);
                            if(!fuchsTarget.isInGroup()) {
                                invites.put(target.getUniqueId(), player.getUniqueId());
                                target.sendMessage("Du hast eine Gruppenanfrage von " + player.getName() + " erhalten! /group accept");
                                player.sendMessage(target.getName() + " wurde eingeladen!");
                            } else {
                                player.sendMessage(target.getName() + " ist bereits in einer Gruppe!");
                            }
                        } else {
                            player.sendMessage("Spieler existiert nicht!");
                        }
                    } else {
                        player.sendMessage("Zu wenig Argumente! /group invite (spieler)");
                    }
                } else if(subcommand.equalsIgnoreCase("accept")) {
                    if(invites.containsKey(player.getUniqueId())) {
                        FuchsPlayer fuchsPlayer = References.data.getPlayer(player);
                        References.groups.get(invites.get(player.getUniqueId())).getPlayers().add(new FuchsGroupPlayer(player, false));
                        fuchsPlayer.setGroup(invites.get(player.getUniqueId()));
                        player.sendMessage("Gruppe von " + Bukkit.getPlayer(invites.get(player.getUniqueId())).getName() + " beigetreten!");
                        invites.remove(player.getUniqueId());
                    } else {
                        player.sendMessage("Du hast keine Gruppenanfragen!");
                    }
                }

            }
        }
        return true;
    }
}
