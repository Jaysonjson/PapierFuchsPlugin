package jaysonjson.papierfuchs.inventories.item;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.object.gas.GasList;
import jaysonjson.papierfuchs.object.item.FuchsMCItem;
import jaysonjson.papierfuchs.object.item.ItemList;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.item.ItemUseType;
import jaysonjson.papierfuchs.object.item.objects.other.EffectBookItem;
import jaysonjson.papierfuchs.object.liquid.LiquidList;
import jaysonjson.papierfuchs.other.InventoryPage;
import jaysonjson.papierfuchs.other.InventoryPageContainer;
import jaysonjson.papierfuchs.registry.FuchsRegistries;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class ItemInventory implements Listener {
    @Nullable
    private Inventory inventory = null;
    public InventoryPageContainer<ArrayList<ItemStack>> pageContainer = new InventoryPageContainer<>();
    public int currentPage = 0;
    public ItemInventory() {
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
            page_content.add(item);
            if (page_index >= 45 || page_index.equals(stacks.size() + 1) || page_index.equals(page_check + 1)) {
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

        FuchsRegistries.items.values().forEach(fuchsItem -> itemStacks.add(fuchsItem.createItem(player)));
        FuchsRegistries.liquids.values().forEach(fuchsLiquid -> {
            if(fuchsLiquid != LiquidList.NONE) {
                ItemStack liquidContainer = ItemList.LIQUID_CONTAINER.createItem(player);
                FuchsMCItem fuchsMCItem = new FuchsMCItem(Utility.getFuchsItemFromNMS(liquidContainer), player, liquidContainer);
                fuchsMCItem.setLiquidID(fuchsLiquid.getID());
                fuchsMCItem.setLiquidAmount(500d);
                itemStacks.add(fuchsMCItem.getItemStack());
            }
        });
        FuchsRegistries.gasses.values().forEach(fuchsGas -> {
            if(fuchsGas != GasList.NONE) {
                ItemStack gasContainer = ItemList.GAS_CONTAINER.createItem(player);
                FuchsMCItem fuchsMCItem = new FuchsMCItem(Utility.getFuchsItemFromNMS(gasContainer), player, gasContainer);
                fuchsMCItem.setGasID(fuchsGas.getID());
                fuchsMCItem.setGasAmount(500d);
                itemStacks.add(fuchsMCItem.getItemStack());
            }
        });
        FuchsRegistries.effects.values().forEach(effect -> {
            //EffectBookItem effectBookItem = (EffectBookItem) ItemList.EFFECT_BOOK.createCopy();
            try {
                EffectBookItem effectBookItem = (EffectBookItem) ItemList.EFFECT_BOOK.clone();
                effectBookItem.setDisplayName(effect.getDisplayName());
                itemStacks.add(effectBookItem.createItem(player));
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        });
        return itemStacks;
    }

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        if(event.getInventory().equals(this.inventory) && Utility.isTopInventory(event)) {
            event.setCancelled(true);
            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem != null) {
                if (clickedItem.hasItemMeta()) {
                    net.minecraft.server.v1_16_R3.ItemStack nmsCopy = Utility.createNMSCopy(clickedItem);
                    NBTTagCompound tag = Utility.getItemTag(nmsCopy);
                    if(tag.hasKey(ItemNBT.ITEM_ID) && event.getWhoClicked().isOp()) {
                        event.getView().setCursor(clickedItem);
                        event.setCancelled(true);
                    }
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
        inventory = Bukkit.createInventory(player, 54, "Items");
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
