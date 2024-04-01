package jaysonjson.papierfuchs.paper.events;

import jaysonjson.papierfuchs.fuchs.utility.References;
import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void Chat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String guildName = "";
        FuchsPlayer fuchsPlayer = References.data.getPlayer(player.getUniqueId());
        String msg = event.getMessage();
        if(fuchsPlayer.isInGuild()) {
            guildName = "[" + ChatColor.AQUA + References.data.getGuild(fuchsPlayer.getGuildUUID()).getName() + ChatColor.RESET + "] ";
        }
        String name = player.getDisplayName();
        if(player.getCustomName() != null) {
            name = player.getCustomName();
        }
        event.setFormat(guildName + name + "§7: " + msg);
        if(msg.toLowerCase().contains("clarest")) {
            event.setCancelled(true);
            player.sendMessage("Man darf das Wort \"Clarest\" nicht schreiben!");
        }
    }

}
