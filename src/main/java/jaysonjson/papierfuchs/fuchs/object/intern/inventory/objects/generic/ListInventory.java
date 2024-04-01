package jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.generic;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.FuchsInventory;
import jaysonjson.papierfuchs.fuchs.utility.FLSL;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.old.other.InventoryPage;
import jaysonjson.papierfuchs.old.other.InventoryPageContainer;
import net.kyori.adventure.text.Component;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public abstract class ListInventory extends FuchsInventory implements Listener {

    public InventoryPageContainer<ArrayList<ItemStack>> pageContainer = new InventoryPageContainer<>();
    public int currentPage = 0;


    public ListInventory(String id) {
        super(id);
        Bukkit.getPluginManager().registerEvents(this, PapierFuchs.INSTANCE);
    }

    @Nullable
    public FuchsInventory getLastInventory() {
        return null;
    }

    public int getBackPaperSlot() {
        return 6;
    }

    private void createPageData() {
        pageContainer.pages.clear();
        int page_index = 0;
        Integer page = 0;
        ArrayList<ItemStack> page_content = new ArrayList<>();
        ArrayList<ItemStack> stacks = getStacks();
        int page_check = stacks.size();
        for (ItemStack item : stacks) {
            page_content.add(item);
            page_index++;
            if (page_index == (getSize() - 9) || page_index >= page_check) {
                page++;
                page_check -= (getSize() - 9);
                InventoryPage<ArrayList<ItemStack>> pageInv = new InventoryPage<>(page_content, page);
                pageInv.stacks = page_content.toArray(new ItemStack[0]);
                pageContainer.addPage(pageInv);
                page_content.clear();
                page_index = 0;
            }
        }
    }

    @Override
    public void create(Player player) {
        super.create(player);
        createPageData();
    }

    public ArrayList<ItemStack> getStacks() {
        return new ArrayList<>();
    }

    @Override
    public void onItemClick(InventoryClickEvent event, ItemStack clickedItem) {
        if (clickedItem.hasItemMeta()) {
            NBTTagCompound tag = FuchsUtility.getItemTag(clickedItem);
            if (tag.hasKey("next_site")) {
                if (currentPage + 1 < pageContainer.pages.size()) {
                    openInventory(currentPage + 1);
                }
            }
            if (tag.hasKey("last_site")) {
                if (currentPage > 0) {
                    openInventory(currentPage - 1);
                }
            }

            if(tag.hasKey("back_inv")) {
                getLastInventory();
            }
        }
        for (int i = getSize() - 9; i < getSize(); i++) {
            if(event.getSlot() == i) {
                event.setCancelled(true);
            }
        }
    }

    @Override
    public void openInventory() {
        openInventory(0);
    }

    @Override
    public void openInventory(int page) {
        setSize(getSizeFromInt(pageContainer.getPage(page).getStacks().length).getAsInt() + 9);
        setInventory(Bukkit.createInventory(getPlayer(), getSize(), Component.text(getDisplayName().get(getFuchsPlayer()))));
        currentPage = page;
        createPage(page);
        getPlayer().openInventory(getInventory());
    }

    public boolean fillEmptySpace() {
        return true;
    }

    @Override
    public void setContents() {

    }

    public void createPage(int page) {
        try {
            getInventory().clear();
            ItemStack[] contents = pageContainer.getPage(page).getStacks();
            if(fillEmptySpace()) {
                for (int i = 0; i < getSize(); i++) {
                    getInventory().setItem(i, new ItemStack(Material.GLASS_PANE));
                }
            }
            int i = 0;
            for (ItemStack content : contents) {
                getInventory().setItem(i, content);
                i++;
            }
            if (currentPage > 0) {
                getInventory().setItem(getSize() - 9, FuchsUtility.createInventoryStackWithTag(Material.GREEN_WOOL, 1, FLSL.lastSite.get(getFuchsPlayer()), "last_site", ""));
            } else {
                getInventory().setItem(getSize() - 9, FuchsUtility.createInventoryStackWithTag(Material.RED_WOOL, 1, FLSL.lastSite.get(getFuchsPlayer()), "last_site", ""));
            }
            getInventory().setItem(getSize() - 5, FuchsUtility.createInventoryStack(Material.PAPER, page + 1, FLSL.currentSite.get(getFuchsPlayer())));
            if (currentPage + 2 < pageContainer.size()) {
                getInventory().setItem(getSize() - 1, FuchsUtility.createInventoryStackWithTag(Material.GREEN_WOOL, 1, FLSL.nextSite.get(getFuchsPlayer()), "next_site", ""));
            } else {
                getInventory().setItem(getSize() - 1, FuchsUtility.createInventoryStackWithTag(Material.RED_WOOL, 1, FLSL.nextSite.get(getFuchsPlayer()), "next_site", ""));
            }
            if(getLastInventory() != null) {
                getInventory().setItem(getSize() - getBackPaperSlot(), FuchsUtility.createInventoryStackWithTag(Material.PAPER, 1, FLSL.back.get(getFuchsPlayer()), "back_inv", ""));
            }
        } catch (Exception e)  {
            getPlayer().closeInventory();
        }
    }

}
