package jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.drop;

import jaysonjson.papierfuchs.fuchs.io.data.crafting.obj.zCraftingItem;
import jaysonjson.papierfuchs.fuchs.io.data.drop.obj.FuchsMobDrop;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemGeneralData;
import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.FuchsInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.generic.ListInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class DropContentInventory extends ListInventory {

    DropListInventory dropListInventory;
    FuchsMobDrop drop;
    public DropContentInventory(String id) {
        super(id);
    }

    public void setDropListInventory(DropListInventory dropListInventory) {
        this.dropListInventory = dropListInventory;
    }

    public void setDrop(FuchsMobDrop drop) {
        this.drop = drop;
    }

    @Override
    public ArrayList<ItemStack> getStacks() {
        ArrayList<ItemStack> itemStacks = new ArrayList<>();
        for (zCraftingItem item : drop.getItems()) {
            FuchsMCItem fuchsMCItem = new FuchsMCItem(item.getItem().clone());
            FuchsItemGeneralData generalData = fuchsMCItem.generalData().get();
            generalData.setId("");
            fuchsMCItem.generalData().set(generalData);
            ItemStack itemStack = fuchsMCItem.getItemStack();
            ItemMeta itemMeta = itemStack.getItemMeta();
            ArrayList<Component> lore = new ArrayList<>();
            lore.add(Component.text(ChatColor.GRAY + "Chance: " + ChatColor.AQUA + item.getChance() + ChatColor.GRAY + "%"));
            itemMeta.lore(lore);
            itemStack.setItemMeta(itemMeta);
            itemStacks.add(itemStack);
        }
        return itemStacks;
    }

    @Override
    public void onItemClick(InventoryClickEvent event, ItemStack clickedItem) {
        if(event.getInventory().equals(getInventory()) && FuchsUtility.isTopInventory(event)) {
            event.setCancelled(true);
        }
        super.onItemClick(event, clickedItem);
    }

    @Nullable
    @Override
    public FuchsInventory getLastInventory() {
        if(dropListInventory != null) {
            dropListInventory.createAndOpen(getPlayer());
        }
        return dropListInventory;
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return FuchsLanguageString.c("Drop: " + drop.getDisplayName().get(getFuchsPlayer()));
    }
}
