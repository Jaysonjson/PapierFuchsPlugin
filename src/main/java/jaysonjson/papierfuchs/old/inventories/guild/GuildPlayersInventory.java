package jaysonjson.papierfuchs.old.inventories.guild;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.io.data.guild.data.FuchsGuildPlayer;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.old.other.InventoryPage;
import jaysonjson.papierfuchs.old.other.InventoryPageContainer;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class GuildPlayersInventory implements Listener {

    @Nullable
    public Inventory inventory;
    public GuildInventory guildInventory;
    public InventoryPageContainer<ArrayList<ItemStack>> pageContainer = new InventoryPageContainer<>();
    public int currentPage = 0;
    public GuildPlayersInventory(GuildInventory guildInventory) {
        this.guildInventory = guildInventory;
        Bukkit.getPluginManager().registerEvents(this, PapierFuchs.INSTANCE);
    }


    public void createPageData(Player player) {
        pageContainer.pages.clear();
        int page_index = 0;
        Integer page = 0;
        ArrayList<ItemStack> page_content = new ArrayList<>();
        ArrayList<ItemStack> stacks = getStacks(player);
        int page_check = stacks.size();
        for (ItemStack item : stacks) {
            page_content.add(item);
            page_index++;
            if (page_index == 45 || page_index >= page_check) {
                page++;
                page_check -= 45;
                InventoryPage<ArrayList<ItemStack>> pageInv = new InventoryPage<>(page_content, page);
                pageInv.stacks = page_content.toArray(new ItemStack[0]);
                pageContainer.addPage(pageInv);
                page_content.clear();
                page_index = 0;
            }
        }
    }

    public ArrayList<ItemStack> getStacks(Player player) {
        ArrayList<ItemStack> itemStacks = new ArrayList<>();
        int i = 0;
        for (FuchsGuildPlayer member : guildInventory.guild.getMembers()) {
            ItemStack itemStack = FuchsUtility.getPlayerHead(member.getPlayer());
            NBTTagCompound tag = FuchsUtility.getItemTag(itemStack);
            tag.setInt("guild_player", i);
            net.minecraft.world.item.ItemStack nms = FuchsUtility.createNMSCopy(itemStack);
            nms.setTag(tag);
            itemStack = CraftItemStack.asBukkitCopy(nms);
            itemStacks.add(itemStack);
            i++;
        }
        return itemStacks;
    }

    public void openInventory(Player player, int page) {
        inventory = Bukkit.createInventory(player, 54, "Spieler");
        currentPage = page;
        createPage(player, inventory, page);
    }

    public void setContents() {
        for (int i = 0; i < 54; i++) {
            inventory.setItem(i, new ItemStack(Material.GLASS_PANE));
        }
       // inventory.setItem(10, FuchsUtility.createInventoryStack(Material.PLAYER_HEAD, 1, "Jayson"));
    }


    private void createPage(Player player, Inventory inventory, int page) {
        try {
            inventory.clear();
            ItemStack[] contents = pageContainer.getPage(page).getStacks();
            for (int i = 0; i < 54; i++) {
                inventory.setItem(i, new ItemStack(Material.GLASS_PANE));
            }
            int i = 0;
            for (ItemStack content : contents) {
                inventory.setItem(i, content);
                i++;
            }
            if (currentPage > 0) {
                inventory.setItem(45, FuchsUtility.createInventoryStack(Material.GREEN_WOOL, 1, "Letzte Seite"));
            } else {
                inventory.setItem(45, FuchsUtility.createInventoryStack(Material.RED_WOOL, 1, "Letzte Seite"));
            }
            //inventory.setItem(49, FuchsUtility.createInventoryStack(Material.PAPER, 1, "Derzeitige Seite"));
            if (currentPage + 2 < pageContainer.size()) {
                inventory.setItem(53, FuchsUtility.createInventoryStack(Material.GREEN_WOOL, 1, "Nächste Seite"));
            } else {
                inventory.setItem(53, FuchsUtility.createInventoryStack(Material.RED_WOOL, 1, "Nächste Seite"));
            }
            player.openInventory(inventory);
            inventory.setItem(49, FuchsUtility.createInventoryStack(Material.PAPER, 1, "Zurück"));
            inventory.setItem(50, FuchsUtility.createInventoryStack(Material.PAPER, page + 1, "Derzeitige Seite"));
        } catch (Exception e)  {
            player.closeInventory();
        }
    }

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        if(event.getInventory().equals(inventory) && FuchsUtility.isTopInventory(event)) {
            event.setCancelled(true);
            ItemStack clickedItem = event.getCurrentItem();
            assert clickedItem != null;
            if (clickedItem.hasItemMeta()) {
                if (clickedItem.getType().equals(Material.PLAYER_HEAD)) {
                    if(!clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase(event.getWhoClicked().getName()) && guildInventory.guild.getMember((Player) event.getWhoClicked()).isOwner()) {
                        NBTTagCompound tag = FuchsUtility.getItemTag(event.getCurrentItem());
                        GuildPlayerInfoInventory guildPlayerInfoInventory = new GuildPlayerInfoInventory(tag.getInt("guild_player"), this);
                        guildPlayerInfoInventory.openInventory((Player) event.getWhoClicked());
                    }
                }
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Zurück")) {
                    guildInventory.openInventory((Player) event.getWhoClicked());
                }

                if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("Nächste Seite")) {
                    if (currentPage + 1 < pageContainer.pages.size()) {
                        openInventory((Player) event.getWhoClicked(), currentPage + 1);
                    }
                }
                if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("Letzte Seite")) {
                    if (currentPage > 0) {
                        openInventory((Player) event.getWhoClicked(), currentPage - 1);
                    }
                }
            }
        }
    }

}
