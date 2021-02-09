package jaysonjson.papierfuchs.inventories.crafting.general;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.References;
import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.data.crafting.obj.general.zCraftingGeneral;
import jaysonjson.papierfuchs.data.crafting.obj.zCraftingItem;
import jaysonjson.papierfuchs.other.InventoryPage;
import jaysonjson.papierfuchs.other.InventoryPageContainer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class GeneralCraftingInventory implements Listener {

    @Nullable
    private Inventory inventory = null;
    public InventoryPageContainer<ArrayList<ItemStack>> pageContainer = new InventoryPageContainer<>();
    public int currentPage = 0;
    public GeneralCraftingInventory() {
        Bukkit.getPluginManager().registerEvents(this, PapierFuchs.INSTANCE);
    }

    public void createPageData(Player player) {
        Integer page_index = 0;
        Integer page = 0;
        ArrayList<ItemStack> page_content = new ArrayList<>();
        ArrayList<ItemStack> stacks = getStacks(player);
        int page_check = stacks.size();
        for (ItemStack item : stacks) {
            page_index++;
            if (page_index < 46) {
                page_content.add(item);
            }
            if (page_index >= 46 || page_index.equals(stacks.size()) || page_index.equals(page_check)) {
                page++;
                page_check -= 46;
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

        for (zCraftingGeneral general : References.craftings.generals) {
            ItemStack itemStack = new ItemStack(general.output.itemStack);
            ItemMeta itemMeta = itemStack.getItemMeta();
            List<String> defaultLore = itemMeta.getLore();
            if(defaultLore == null) {
                defaultLore = new ArrayList<>();
            }
            defaultLore.add("~Crafting~");
            for (zCraftingItem input : general.inputs) {
                String color = ChatColor.RED.toString();
                String itemName = input.getItem().getI18NDisplayName();
                if(input.getItem().getItemMeta().hasDisplayName()) {
                    itemName = ChatColor.stripColor(input.getItem().getItemMeta().getDisplayName());
                }
                if(Utility.inventoryHasItem(player.getInventory(), input.getItem())) {
                    color = ChatColor.GREEN.toString();
                }
                defaultLore.add(color + itemName + " - " + input.amount);
            }
            itemMeta.setLore(defaultLore);
            itemStack.setItemMeta(itemMeta);
            itemStack.setAmount(general.output.amount);
            itemStacks.add(itemStack);

        }
        return itemStacks;
    }

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        if(event.getInventory().equals(this.inventory) && Utility.isTopInventory(event)) {
            event.setCancelled(true);
            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem != null) {
                if (clickedItem.hasItemMeta()) {
                    //net.minecraft.server.v1_16_R3.ItemStack nmsCopy = Utility.createNMSCopy(clickedItem);
                    //NBTTagCompound tag = Utility.getItemTag(nmsCopy);
                    event.getView().setCursor(clickedItem);
                    if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("Nächste Seite")) {
                        if (currentPage + 1 < pageContainer.size()) {
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

    public void openInventory(Player player, int page) {
        inventory = Bukkit.createInventory(player, 54, "Crafting");
        currentPage = page;
        createPage(player, inventory, page);
    }

    private void createPage(Player player, Inventory inventory, int page) {
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
            inventory.setItem(45, Utility.createInventoryStack(Material.GREEN_WOOL, 1, "Letzte Seite"));
        } else {
            inventory.setItem(45, Utility.createInventoryStack(Material.RED_WOOL, 1, "Letzte Seite"));
        }
        inventory.setItem(49, Utility.createInventoryStack(Material.PAPER, 1, "Derzeitige Seite"));
        if (currentPage + 2 < pageContainer.size()) {
            inventory.setItem(53, Utility.createInventoryStack(Material.GREEN_WOOL, 1, "Nächste Seite"));
        } else {
            inventory.setItem(53, Utility.createInventoryStack(Material.RED_WOOL, 1, "Nächste Seite"));
        }
        player.openInventory(inventory);
    }

}
