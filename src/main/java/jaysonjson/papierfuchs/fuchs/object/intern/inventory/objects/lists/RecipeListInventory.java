package jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.lists;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.crafting.vanilla.workbench.FuchsWorkbenchRecipe;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.InventoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.generic.ListInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.RecipeViewInventory;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRecipes;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class RecipeListInventory extends ListInventory {

    public RecipeListInventory(String id) {
        super(id);
    }

    @Override
    public ArrayList<ItemStack> getStacks() {
        ArrayList<ItemStack> itemStacks = new ArrayList<>();
        for (FuchsWorkbenchRecipe value : FuchsRecipes.workbench.values()) {
            ItemStack itemStack = new ItemStack(value.result.getItem());
            if (FuchsUtility.getFuchsItemFromNMS(value.result.getItem()) != null) {
                itemStack = FuchsUtility.getFuchsItemFromNMS(value.result.getItem()).createItem(getPlayer(), itemStack);
            }
            NBTTagCompound tag = FuchsUtility.getItemTag(itemStack);
            tag.setString("recipe", value.getID());
            net.minecraft.world.item.ItemStack nms = CraftItemStack.asNMSCopy(itemStack);
            nms.setTag(tag);
            itemStacks.add(CraftItemStack.asBukkitCopy(nms));
        }
        return itemStacks;
    }

    @Override
    public void onItemClick(InventoryClickEvent event, ItemStack clickedItem) {
        if(FuchsUtility.isTopInventory(event)) {
            event.setCancelled(true);
            if (clickedItem.hasItemMeta()) {
                NBTTagCompound tag = FuchsUtility.getItemTag(clickedItem);
                if (tag.hasKey("recipe")) {
                    RecipeViewInventory viewInventory = InventoryList.recipeView.copy();
                    viewInventory.setListInventory(this);
                    viewInventory.setRecipe(FuchsRecipes.workbench.get(tag.getString("recipe")));
                    viewInventory.setListInventoryPage(currentPage);
                    viewInventory.create(getPlayer());
                    viewInventory.openInventory();
                }
            }
        }
        super.onItemClick(event, clickedItem);
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return FuchsLanguageString.c("Rezepte");
    }
}
