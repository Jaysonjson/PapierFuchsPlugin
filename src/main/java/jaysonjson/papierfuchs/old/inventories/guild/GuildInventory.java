package jaysonjson.papierfuchs.old.inventories.guild;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.io.data.guild.data.FuchsGuild;
import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

import javax.annotation.Nullable;

public class GuildInventory implements Listener {

    @Nullable
    public Inventory inventory;
    @Nullable
    public FuchsGuild guild;
    @Nullable
    public FuchsPlayer fuchsPlayer;
    public GuildInventory(FuchsGuild guild, FuchsPlayer player) {
        this.guild = guild;
        this.fuchsPlayer = player;
        Bukkit.getPluginManager().registerEvents(this, PapierFuchs.INSTANCE);
    }

    public void openInventory(Player player) {
        inventory = Bukkit.createInventory(player, 54, guild.getName());
        setContents();
        player.openInventory(inventory);
    }

    public void setContents() {
        for (int i = 0; i < 54; i++) {
            inventory.setItem(i, new ItemStack(Material.GLASS_PANE));
        }
        ItemStack bannerItem = new ItemStack(Material.WHITE_BANNER);
        BannerMeta im = (BannerMeta) bannerItem.getItemMeta();
        im.setBaseColor(guild.getBanner().color);
        im.setPatterns(guild.getBanner().pattern);
        bannerItem.setItemMeta(im);
        inventory.setItem(31, bannerItem);
        inventory.setItem(10, FuchsUtility.createInventoryStack(Material.PLAYER_HEAD, guild.getMembers().size(), "Spieler", true));
        inventory.setItem(11, FuchsUtility.createInventoryStack(Material.PAPER, guild.getPendingInvites().size(), "Ausstehende Einladungen", true));
        inventory.setItem(12, FuchsUtility.createInventoryStack(Material.PAPER, 1, "Einladen", true));
    }

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        if(event.getInventory().equals(inventory) && FuchsUtility.isTopInventory(event)) {
            if(event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()) {
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Spieler")) {
                    GuildPlayersInventory guildPlayersInventory = new GuildPlayersInventory(this);
                    guildPlayersInventory.createPageData((Player) event.getWhoClicked());
                    guildPlayersInventory.openInventory((Player) event.getWhoClicked(), 0);
                }
                if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Einladen")) {
                    GuildInviteInventory guildInviteInventory = new GuildInviteInventory(this);
                    guildInviteInventory.createPageData((Player) event.getWhoClicked());
                    guildInviteInventory.openInventory((Player) event.getWhoClicked(), 0);
                }
            }
            event.setCancelled(true);
        }
    }
}
