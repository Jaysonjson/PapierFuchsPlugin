package jaysonjson.papierfuchs.events.entity.player;

import jaysonjson.papierfuchs.References;
import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.data.area.data.zArea;
import jaysonjson.papierfuchs.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.other.Scoreboard;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.Random;

public class PlayerMove implements Listener {

    //private ArrayList<Player> players = new ArrayList<>();


    @EventHandler
    public void playerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        FuchsPlayer fuchsPlayer = References.data.getPlayer(player.getUniqueId());
        Random random = new Random();
        if(fuchsPlayer.getPlayerSpecial().getAlcohol() > 0) {
            Utility.makeDrunk(player, fuchsPlayer);
            if(random.nextInt(350) == 1) {
                fuchsPlayer.getPlayerSpecial().decreaseAlcohol(random.nextDouble() / 10);
            }
        }
       /* for (zArea area : PapierFuchs.INSTANCE.areas) {
            Location locationP0 = area.createLocation(player.getWorld()).add(area.getSize(), area.getSize(), area.getSize());
            Location locationP1 = area.createLocation(player.getWorld()).subtract(area.getSize(), area.getSize(), area.getSize());
            if (Utility.isInArea(player.getLocation(), locationP0, locationP1)) {
                player.sendActionBar(new ComponentBuilder(area.getDisplayName().toUpperCase()).color(ChatColor.DARK_PURPLE).create());
            }
        }*/
        //TODO NÄCHSTE GEBIETE ZUSAMMENFASSEN
      /*  zArea area = Utility.getNearestArea(event.getPlayer().getWorld().getEnvironment(), player.getLocation());
        Location locationP0 = area.createLocation(player.getWorld()).add(area.getSize(), area.getSize(), area.getSize());
        Location locationP1 = area.createLocation(player.getWorld()).subtract(area.getSize(), area.getSize(), area.getSize());
        if (Utility.isInArea(player.getLocation(), locationP0, locationP1)) {
            player.sendActionBar(new ComponentBuilder(area.getDisplayName().toUpperCase()).color(ChatColor.DARK_PURPLE).create());
        }*/

        ArrayList<zArea> areaList = Utility.getNearestAreas(750, player.getWorld().getEnvironment(), player.getLocation());
        for (zArea area : areaList) {
            Location locationP0 = area.createLocation(player.getWorld()).add(area.getSize(), area.getSize(), area.getSize());
            Location locationP1 = area.createLocation(player.getWorld()).subtract(area.getSize(), area.getSize(), area.getSize());
            if (Utility.isInArea(player.getLocation(), locationP0, locationP1)) {
                player.sendActionBar(new ComponentBuilder(area.getDisplayName().toUpperCase()).color(ChatColor.DARK_PURPLE).create());
            }
        }
        Scoreboard.updateScoreboard(player, fuchsPlayer, areaList);


        //DataHandler.savePlayer(fuchsPlayer);
    }
}
