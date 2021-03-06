package jaysonjson.papierfuchs.events;

import jaysonjson.papierfuchs.References;
import jaysonjson.papierfuchs.data.DataHandler;
import jaysonjson.papierfuchs.data.player.FuchsPlayer;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

    @EventHandler(priority= EventPriority.LOWEST)
    public void Chat(AsyncPlayerChatEvent event) {
        String guildName = "";
        FuchsPlayer fuchsPlayer = References.data.getPlayer(event.getPlayer().getUniqueId());
        if(fuchsPlayer.isInGuild()) {
            guildName = "[" + ChatColor.AQUA + DataHandler.loadGuild(fuchsPlayer.getGuildUuid()).getName() + ChatColor.RESET + "] ";
        }
        event.setFormat(guildName + event.getPlayer().getDisplayName() + "§7: " + event.getMessage());
        // PAPER -> event.formatter().chat(P, M);
    }

}
