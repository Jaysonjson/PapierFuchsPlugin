package jaysonjson.papierfuchs.old.inventories.guild;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.io.data.guild.data.FuchsGuildPlayer;
import jaysonjson.papierfuchs.fuchs.io.data.guild.obj.FuchsGuildRank;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class GuildPlayerInfoInventory implements Listener {

    @Nullable
    public Inventory inventory;
    public GuildPlayersInventory guildInventory;
    public int playerIndex;
    public FuchsGuildPlayer fuchsGuildPlayer;
    private FuchsGuildRank higherRank = null;
    private FuchsGuildRank lowerRank = null;
    public GuildPlayerInfoInventory(int playerIndex, GuildPlayersInventory guildInventory) {
        this.guildInventory = guildInventory;
        this.playerIndex = playerIndex;
        Bukkit.getPluginManager().registerEvents(this, PapierFuchs.INSTANCE);
        fuchsGuildPlayer = guildInventory.guildInventory.guild.getMembers().get(playerIndex);
    }


    public void openInventory(Player player) {
        inventory = Bukkit.createInventory(player, 27, fuchsGuildPlayer.getPlayer().getName());
        setContents();
        player.openInventory(inventory);
    }

    public void setContents() {
        for (int i = 0; i < 27; i++) {
            inventory.setItem(i, new ItemStack(Material.GLASS_PANE));
        }
        inventory.setItem(13, FuchsUtility.createInventoryStack(Material.BLACK_WOOL, 1, "Degradieren"));
        higherRank = FuchsGuildRank.getHigher(fuchsGuildPlayer.getRank());
        lowerRank = FuchsGuildRank.getLower(fuchsGuildPlayer.getRank());
        inventory.setItem(14, FuchsUtility.createInventoryStack(Material.PAPER, 1, lowerRank == null ? ChatColor.RED + "Niedrigster Rank" : lowerRank.getDisplayName()));
        inventory.setItem(16, FuchsUtility.createInventoryStack(Material.RED_WOOL, 1, "Kicken"));
        inventory.setItem(10, FuchsUtility.createInventoryStack(Material.GREEN_WOOL, 1, "Befördern"));
        inventory.setItem(11, FuchsUtility.createInventoryStack(Material.PAPER, 1, higherRank == null ? ChatColor.RED + "Höchster Rank" : higherRank.getDisplayName()));
        inventory.setItem(22, FuchsUtility.createInventoryStack(Material.PAPER, 1, "Zurück"));
        inventory.setItem(4, FuchsUtility.getPlayerHead(fuchsGuildPlayer.getPlayer()));
    }

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        if(event.getInventory().equals(inventory) && FuchsUtility.isTopInventory(event)) {
            event.setCancelled(true);
            ItemStack itemStack = event.getCurrentItem();
            if (itemStack != null) {
                if (itemStack.getType().equals(Material.PLAYER_HEAD)) {
                    //GuildPlayerInfoInventory guildPlayerInfoInventory = new GuildPlayerInfoInventory("Zapan", guildInventory);
                }
                if (itemStack.hasItemMeta()) {
                    String displayName = itemStack.getItemMeta().getDisplayName();
                    switch (displayName) {
                        case "Zurück":
                            guildInventory.openInventory((Player) event.getWhoClicked(), 0);
                            break;
                        case "Degradieren":
                            if (lowerRank != null) {
                                fuchsGuildPlayer.setRank(lowerRank);
                                setContents();
                            }
                            break;
                        case "Befördern":
                            if (higherRank != null) {
                                fuchsGuildPlayer.setRank(higherRank);
                                setContents();
                            }
                            break;
                        case "Kicken":
                            fuchsGuildPlayer.getGuild().removeMember(fuchsGuildPlayer);
                            guildInventory.createPageData(fuchsGuildPlayer.getPlayer());
                            guildInventory.openInventory((Player) event.getWhoClicked(), 0);
                            break;
                    }
                }
            }
        }
    }
}
