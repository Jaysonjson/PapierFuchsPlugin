package jaysonjson.papierfuchs.paper.commands.guild;

import jaysonjson.papierfuchs.fuchs.io.data.guild.data.FuchsGuild;
import jaysonjson.papierfuchs.fuchs.io.data.guild.obj.FuchsInvite;
import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.currency.CurrencyList;
import jaysonjson.papierfuchs.fuchs.utility.Colors;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.utility.References;
import jaysonjson.papierfuchs.old.inventories.guild.GuildInventory;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
public class GuildCommands implements CommandExecutor {

    public Component NOT_IN_GUILD = Component.text(Colors.RED_WHITE + "Du bist in keiner Guilde!");

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(commandSender instanceof Player player) {
            FuchsPlayer fuchsPlayer = References.data.getPlayer(player.getUniqueId());
            FuchsGuild guild = fuchsPlayer.getGuild();
            boolean inGuild = fuchsPlayer.isInGuild();
            if(args.length >= 1) {
                String subcommand = args[0];
                String guildNameArg = "";
                if(args.length >= 2) {
                     guildNameArg = args[1];
                }
                switch (subcommand) {
                    //CREATE-PROCESS
                    case "create" -> {
                        if(!inGuild) {
                            if (guildNameArg.length() < FuchsGuild.CHAR_LIMIT && !guildNameArg.equalsIgnoreCase("")) {
                                if (FuchsUtility.countCurrency(player, FuchsGuild.CURRENCY.getID(), false) >= FuchsGuild.GUILD_COST) {
                                    if (!FuchsUtility.guildExists(guildNameArg)) {
                                        FuchsGuild fuchsGuild = FuchsGuild.create(guildNameArg, player.getUniqueId());
                                        if(fuchsGuild != null) {
                                            fuchsPlayer.setGuildUUID(fuchsGuild.getUUID());
                                            player.sendMessage(Colors.GREEN + "Guilde " + fuchsGuild.getName() + " (" + fuchsGuild.getUUID() + ") erstellt!");
                                            FuchsUtility.removeCurrency(FuchsGuild.CURRENCY.getID(), 50000, player);
                                        } else {
                                            player.sendMessage(Component.text(Colors.RED_WHITE + "Unbekannter Fehler beim Erstellen der Guilde!"));
                                        }
                                    } else {
                                        player.sendMessage(Component.text(Colors.RED_WHITE + "Eine Guilde mit den Namen " + Colors.GREEN + guildNameArg + Colors.RED_WHITE + " existiert bereits!"));
                                    }
                                } else {
                                    player.sendMessage(Component.text(Colors.RED_WHITE + "Du hast nicht genügend Geld! Man benötigt " + Colors.GREEN + FuchsUtility.formatDouble(FuchsGuild.GUILD_COST) + FuchsGuild.CURRENCY.getSymbol() + Colors.RED_WHITE + " für eine Guilde!"));
                                }
                            } else {
                                player.sendMessage(Component.text(Colors.RED_WHITE + "Der Name ist zu lang! Man darf Maximal " + (FuchsGuild.CHAR_LIMIT - 1) + " Zeichen haben!"));
                            }
                        } else {
                            player.sendMessage(Component.text(Colors.RED_WHITE + "Du bist bereits in einer Guilde! Falls du deine Guilde verlassen willst, benutz: \"/guild leave\""));
                        }
                    }
                    //LEAVE-PROCESS
                    case "leave" -> {
                        if(inGuild) {
                             if(guild != null) {
                                 if(!guild.getMember(player).isOwner()) {
                                     guild.removeMember(player);
                                     fuchsPlayer.setGuildUUID(null);
                                     player.sendMessage(Component.text(Colors.GREEN + "Guilde " + guild.getName() + " verlassen!"));
                                 } else {
                                     player.sendMessage(Component.text(Colors.RED_WHITE + "Du kannst als Leiter die Guilde nicht verlassen! Übertrage deinen Rank auf einen anderen Spieler oder lösch die Guilde mit \"/guild disband\""));
                                 }
                             }
                        } else {
                            player.sendMessage(NOT_IN_GUILD);
                        }
                    }

                    case "disband" -> {
                        if(inGuild) {
                            if(guild != null) {
                                if(guild.getMember(player).isOwner()) {
                                    guild.delete();
                                    player.sendMessage(Component.text(Colors.GREEN + "Guilde wurde aufgelöst!"));
                                } else {
                                    player.sendMessage(Component.text(Colors.RED_WHITE + "Nur der Leiter darf die Guilde auflösen!"));
                                }
                            }
                        } else {
                            player.sendMessage(NOT_IN_GUILD);
                        }
                    }

                    //INVITE-PROCESS
                    case "invite" -> {
                        if (inGuild) {
                            OfflinePlayer target = Bukkit.getOfflinePlayer(guildNameArg);
                            FuchsPlayer fuchsTarget = References.data.getPlayer(target.getUniqueId());
                            if (!player.getUniqueId().equals(target.getUniqueId())) {
                                if (fuchsTarget != null) {
                                    if (!fuchsTarget.isInGuild()) {
                                        FuchsInvite guildInvite = new FuchsInvite(player.getUniqueId(), target.getUniqueId());
                                        guild.getPendingInvites().add(guildInvite);
                                        player.sendMessage(Component.text(Colors.GREEN + target.getName() + " wurde eingeladen!"));
                                        FuchsLanguageString text = FuchsLanguageString.c("Du wurdest in die Guilde " + guild.getName() + " von " + player.getName() + " eingeladen! Benutze: \"/guild accept " + guild.getName() + "\" um beizutreten!");
                                        if (target.getPlayer() != null) {
                                            Player targetPlayer = target.getPlayer();
                                            targetPlayer.sendMessage(Component.text(text.get(fuchsPlayer)));
                                        } else {
                                            fuchsTarget.getPendingMessages().add(text);
                                        }
                                    } else {
                                        player.sendMessage(Component.text(Colors.RED_WHITE + "Spieler ist bereits in einer Guilde!"));
                                    }
                                } else {
                                    player.sendMessage(Component.text(Colors.RED_WHITE + "Spieler existiert nicht!"));
                                }
                            } else {
                                player.sendMessage(Component.text("Du kannst dich nicht selber einladen!"));
                            }
                        } else {
                            player.sendMessage(NOT_IN_GUILD);
                        }
                    }
                    case "accept" -> {
                        if (!inGuild) {
                            guild = References.data.getGuild(guildNameArg);
                            if(guild != null) {
                                FuchsInvite invite = guild.getInviteFromInvitee(player.getUniqueId());
                                if(invite != null) {
                                    guild.addMember(player);
                                    fuchsPlayer.setGuildUUID(guild.getUUID());
                                    player.sendMessage(Component.text("Guilde " + guild.getName() + " beigetreten!"));
                                    OfflinePlayer inviter = Bukkit.getOfflinePlayer(invite.getInviter());
                                    FuchsLanguageString text = FuchsLanguageString.c(player.getName() + " ist deiner Guilde beigetreten!");
                                    if(inviter.getPlayer() != null) {
                                        inviter.getPlayer().sendMessage(Component.text(text.get(fuchsPlayer)));
                                    } else {
                                        References.data.getPlayer(inviter.getUniqueId()).getPendingMessages().add(text);
                                    }
                                    guild.getPendingInvites().remove(invite);
                                } else {
                                    player.sendMessage(Component.text(Colors.RED_WHITE + "Du wurdest nicht in die Guilde " + guildNameArg + " eingeladen!"));
                                }
                            } else {
                                player.sendMessage(Component.text(Colors.RED_WHITE + "Guilde " + guildNameArg + " nicht gefunden!"));
                            }
                        } else {
                            player.sendMessage(Component.text(Colors.RED_WHITE + "Du bist bereits in einer Guilde!"));
                        }
                    }
                }
            } else {
                if(inGuild) {
                    GuildInventory guildInventory = new GuildInventory(fuchsPlayer.getGuild(), fuchsPlayer);
                    guildInventory.openInventory(player);
                } else {
                    player.sendMessage(Component.text(Colors.RED_WHITE + "Du bist in keiner Guilde! Um eine Guilde zu erstellen, benutz: \"/guild create (name)\""));
                }
            }
        }
        return true;
    }

}
