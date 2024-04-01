package jaysonjson.papierfuchs.old.inventories.crafting.general;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.utility.References;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.io.data.crafting.obj.general.zCraftingGeneral;
import jaysonjson.papierfuchs.fuchs.io.data.crafting.obj.zCraftingItem;
import jaysonjson.papierfuchs.old.other.InventoryPage;
import jaysonjson.papierfuchs.old.other.InventoryPageContainer;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GeneralCraftingInventory implements Listener {

    @Nullable
    private Inventory inventory = null;
    public InventoryPageContainer<ArrayList<ItemStack>> pageContainer = new InventoryPageContainer<>();
    public int currentPage = 0;
    public GeneralCraftingInventory() {
        Bukkit.getPluginManager().registerEvents(this, PapierFuchs.INSTANCE);
    }
    public HashMap<Integer, Boolean> can_craft = new HashMap<>();
    public HashMap<Integer, zCraftingGeneral> added_recipes = new HashMap<>();
    public void createPageData(Player player) {
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
        int id = 0;
        for (zCraftingGeneral general : References.craftings.generals) {
            ItemStack itemStack = new ItemStack(general.output.itemStack);
            if(FuchsUtility.getFuchsItemFromNMS(general.output.itemStack) != null) {
                itemStack = FuchsUtility.getFuchsItemFromNMS(general.output.itemStack).createItem(player, itemStack);
            }
            ItemMeta itemMeta = itemStack.getItemMeta();
            List<String> defaultLore = itemMeta.getLore();
            if(defaultLore == null) {
                defaultLore = new ArrayList<>();
            }
            defaultLore.add("~Crafting~");
            int input_amount = 0;
            for (zCraftingItem input : general.inputs) {
                String color = ChatColor.RED.toString();
                String itemName = input.getItem().getI18NDisplayName();
                if(input.getItem().getItemMeta().hasDisplayName()) {
                    itemName = ChatColor.stripColor(input.getItem().getItemMeta().getDisplayName());
                }
                if(FuchsUtility.inventoryHasItem(player.getInventory(), input.getItem(), false)) {
                    color = ChatColor.GREEN.toString();
                    input_amount++;
                }
                defaultLore.add(color + itemName + " - " + input.amount);
            }
            itemMeta.setLore(defaultLore);
            itemStack.setItemMeta(itemMeta);
            itemStack.setAmount(general.output.amount);
            net.minecraft.world.item.ItemStack nmsCopy = FuchsUtility.createNMSCopy(itemStack);
            NBTTagCompound tag = FuchsUtility.getItemTag(nmsCopy);
            tag.setInt("id", id);
            nmsCopy.setTag(tag);
            itemStack = CraftItemStack.asBukkitCopy(nmsCopy);
            itemStacks.add(itemStack);
            if(input_amount >= general.inputs.size()) {
                can_craft.put(id, true);
            } else {
                can_craft.put(id, false);
            }
            added_recipes.put(id, general);
            id++;
        }
        return itemStacks;
    }

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        if(event.getInventory().equals(this.inventory) && FuchsUtility.isTopInventory(event)) {
            event.setCancelled(true);
            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem != null) {
                if (clickedItem.hasItemMeta()) {
                    NBTTagCompound tag = FuchsUtility.getItemTag(clickedItem);
                    if(tag.hasKey("id")) {
                        System.out.println("Click0");
                        if(can_craft.get(tag.getInt("id"))) {
                            for (zCraftingItem input : added_recipes.get(tag.getInt("id")).inputs) {
                                FuchsUtility.removeItemsFromInventory(event.getWhoClicked().getInventory(), input.getItem(), input.amount, false);
                                System.out.println("Click1");
                            }
                            event.getView().setCursor(clickedItem);
                        }
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
            inventory.setItem(45, FuchsUtility.createInventoryStack(Material.GREEN_WOOL, 1, "Letzte Seite"));
        } else {
            inventory.setItem(45, FuchsUtility.createInventoryStack(Material.RED_WOOL, 1, "Letzte Seite"));
        }
        inventory.setItem(49, FuchsUtility.createInventoryStack(Material.PAPER, page + 1, "Derzeitige Seite"));
        if (currentPage + 2 < pageContainer.size()) {
            inventory.setItem(53, FuchsUtility.createInventoryStack(Material.GREEN_WOOL, 1, "Nächste Seite"));
        } else {
            inventory.setItem(53, FuchsUtility.createInventoryStack(Material.RED_WOOL, 1, "Nächste Seite"));
        }
        player.openInventory(inventory);
    }

}
