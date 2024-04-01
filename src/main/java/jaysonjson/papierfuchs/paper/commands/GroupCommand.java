package jaysonjson.papierfuchs.paper.commands;

import jaysonjson.papierfuchs.fuchs.io.data.guild.obj.FuchsInvite;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GroupCommand implements CommandExecutor {

    public MultiCommand multiCommand;
    public ArrayList<FuchsInvite> invites = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String string, @NotNull String[] strings) {
        if(commandSender instanceof Player player) {
            multiCommand = new MultiCommand(player, string, strings);
            switch (multiCommand.secondary) {
                case "invite" -> invitePlayer();
                case "accept" -> acceptInvite();
                case "leave" -> leaveGroup();
            }
        }
        return true;
    }

    public void invitePlayer() {

    }

    public void acceptInvite() {

    }

    public void leaveGroup() {

    }

}
