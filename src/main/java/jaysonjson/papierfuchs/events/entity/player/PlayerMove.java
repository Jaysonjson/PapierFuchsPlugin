package jaysonjson.papierfuchs.events.entity.player;

import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.data.DataHandler;
import jaysonjson.papierfuchs.data.area.data.zArea;
import jaysonjson.papierfuchs.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.other.Scoreboard;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Random;

public class PlayerMove implements Listener {

    //private ArrayList<Player> players = new ArrayList<>();


    @EventHandler
    public void playerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        FuchsPlayer fuchsPlayer = DataHandler.loadPlayer(player.getUniqueId());
        Random random = new Random();
        if(fuchsPlayer.getPlayerSpecial().alcohol > 0) {
            Utility.makeDrunk(player, fuchsPlayer);
            if(random.nextInt(350) == 1) {
                fuchsPlayer.getPlayerSpecial().alcohol -= random.nextDouble() / 10;
            }
        }
        Scoreboard.updateScoreboard(player, fuchsPlayer);
        zArea area = Utility.getNearestArea(event.getPlayer().getWorld().getEnvironment(), player.getLocation());
        Location locationP0 = area.createLocation(player.getWorld()).add(area.getSize(), area.getSize(), area.getSize());
        Location locationP1 = area.createLocation(player.getWorld()).subtract(area.getSize(), area.getSize(), area.getSize());
        if (Utility.isInArea(player.getLocation(), locationP0, locationP1)) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(area.getDisplayName().toUpperCase()).color(ChatColor.DARK_PURPLE).create());
            //if (!players.contains(player)) {
                //player.sendMessage("Du bist jetzt im Gebiet " + area.name + "!");
                //players.add(player);
            //}
        }// else {
            //if (players.contains(player)) {
                //player.sendMessage("Du hast das Gebiet " + area.name + " verlassen!");
                //players.remove(player);
           // }
        //}
        DataHandler.savePlayer(fuchsPlayer);
    }
}
