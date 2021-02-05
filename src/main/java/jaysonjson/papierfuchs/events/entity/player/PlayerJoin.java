package jaysonjson.papierfuchs.events.entity.player;

import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.inventories.classes.StarterClassInventory;
import jaysonjson.papierfuchs.data.DataHandler;
import jaysonjson.papierfuchs.npc.NPC;
import jaysonjson.papierfuchs.other.Scoreboard;
import jaysonjson.papierfuchs.skillclass.zClass;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        FuchsPlayer fuchsPlayer = DataHandler.loadPlayer(player.getUniqueId());
        Utility.refreshHearts(event.getPlayer(), fuchsPlayer);
        Scoreboard.updateScoreboard(player, fuchsPlayer);
        NPC.sendSinglePacket(player);
        if(fuchsPlayer.getPlayerClass().current.equals(zClass.NONE)) {
            StarterClassInventory classInventory = new StarterClassInventory();
            classInventory.openInventory(event.getPlayer());
        }
        Utility.updateInventory(player);
        //AreaInventory areaInventory = new AreaInventory();
        //areaInventory.openInventory(player, Utility.getNearestArea(World.Environment.NORMAL, player.getLocation()).name);
        //GuildChunkInventory inventory = new GuildChunkInventory();
        //inventory.openInventory(event.getPlayer());
      /*  zPlayer player = DataHandler.loadPlayer(event.getPlayer().getUniqueId());
        if(player.isInGuild()) {
            zGuild guild = DataHandler.loadGuild(player.getGuildUuid());
            System.out.println("open guild_" + guild.name);
            GuildInventory guildInventory = new GuildInventory(guild, player);
            guildInventory.openInventory(event.getPlayer());
        }*/

      /*  Player player = event.getPlayer();
        Inventory inventory = Bukkit.createInventory(player, 54, player.getDisplayName() + "'s Inventar");
        for (int i = 0; i < player.getInventory().getContents().length; i++) {
            inventory.setItem(i, player.getInventory().getContents()[i]);
        }
        player.openInventory(inventory);
       */
    }

}
