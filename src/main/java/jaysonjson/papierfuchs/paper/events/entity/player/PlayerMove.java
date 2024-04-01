package jaysonjson.papierfuchs.paper.events.entity.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

    //private ArrayList<Player> players = new ArrayList<>();


    @EventHandler
    public void playerMoveEvent(PlayerMoveEvent event) {
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

        /*ArrayList<zArea> areaList = FuchsUtility.getNearestAreas(15750, player.getWorld().getEnvironment(), player.getLocation());
        for (zArea area : areaList) {
            Location locationP0 = area.createLocation(player.getWorld()).add(area.getSize().getX(), area.getSize().getY(), area.getSize().getZ());
            Location locationP1 = area.createLocation(player.getWorld()).subtract(area.getSize().getX(), area.getSize().getY(), area.getSize().getZ());
            if (FuchsUtility.isInArea(player.getLocation(), locationP0, locationP1)) {
                player.sendActionBar(new ComponentBuilder(area.getDisplayName().toUpperCase()).color(ChatColor.DARK_PURPLE).create());
            }
        }*/
        //Scoreboard.updateScoreboard(player, fuchsPlayer, areaList);


        //DataHandler.savePlayer(fuchsPlayer);
    }
}
