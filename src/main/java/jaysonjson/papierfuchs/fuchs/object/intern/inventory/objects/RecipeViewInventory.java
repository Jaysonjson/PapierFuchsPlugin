package jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects;

import jaysonjson.papierfuchs.fuchs.io.data.crafting.obj.zCraftingItem;
import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.crafting.vanilla.workbench.FuchsWorkbenchRecipe;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.FuchsInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.InventoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.InventorySize;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.lists.RecipeListInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.item.lists.ItemList;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRecipes;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class RecipeViewInventory extends FuchsInventory {

    public FuchsWorkbenchRecipe recipe;
    public RecipeListInventory listInventory;
    public int listInventoryPage = 0;
    public RecipeViewInventory(String id) {
        super(id);
    }

    @Override
    public InventorySize getSizeEnum() {
        return InventorySize.FORTY_FIVE;
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return FuchsLanguageString.c("Rezept: " + recipe.getID());
    }

    public void setRecipe(FuchsWorkbenchRecipe recipe) {
        this.recipe = recipe;
    }


    public void setListInventory(RecipeListInventory listInventory) {
        this.listInventory = listInventory;
    }

    public void setListInventoryPage(int listInventoryPage) {
        this.listInventoryPage = listInventoryPage;
    }

    @Override
    public void onItemClick(InventoryClickEvent event, ItemStack clickedItem) {
        if (FuchsUtility.isTopInventory(event) && event.getInventory().equals(getInventory())) {
            event.setCancelled(true);
            if (listInventory != null) {
                if (clickedItem.hasItemMeta()) {
                    if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("Zurück")) {
                        listInventory.openInventory(listInventoryPage);
                    }
                    NBTTagCompound tag = FuchsUtility.getItemTag(event.getCurrentItem());
                    if (tag.hasKey("recipe")) {
                        RecipeViewInventory viewInventory = InventoryList.recipeView.copy();
                        viewInventory.setListInventory(listInventory);
                        viewInventory.setRecipe(FuchsRecipes.workbench.get(tag.getString("recipe")));
                        viewInventory.setListInventoryPage(listInventoryPage);
                        viewInventory.create(getPlayer());
                        viewInventory.openInventory();
                    }
                }
            }
        }
        super.onItemClick(event, clickedItem);
    }

    @Override
    public void setContents() {
        fillWith(Material.GRAY_STAINED_GLASS_PANE);
        if(recipe != null && listInventory != null) {

            for (int i = 15; i < 18; i++) {
                getInventory().setItem(i, new ItemStack(Material.GLASS_PANE));
            }
            for (int i = 24; i < 27; i++) {
                getInventory().setItem(i, new ItemStack(Material.GLASS_PANE));
            }
            for (int i = 33; i < 36; i++) {
                getInventory().setItem(i, new ItemStack(Material.GLASS_PANE));
            }
            for (int i = 9; i < 12; i++) {
                getInventory().setItem(i, new ItemStack(Material.GLASS_PANE));
            }
            for (int i = 18; i < 21; i++) {
                getInventory().setItem(i, new ItemStack(Material.GLASS_PANE));
            }
            for (int i = 27; i < 30; i++) {
                getInventory().setItem(i, new ItemStack(Material.GLASS_PANE));
            }

            for (zCraftingItem matrix : recipe.matrix) {
                ItemStack itemStack = matrix.getItem();
                for (FuchsWorkbenchRecipe value : FuchsRecipes.workbench.values()) {
                    if(value.result.fuchsItem.equalsIgnoreCase(matrix.fuchsItem) && value.result.material.equals(matrix.material) && value.result.itemData.equalsIgnoreCase(matrix.itemData)) {
                        NBTTagCompound tag = FuchsUtility.getItemTag(itemStack);
                        tag.setString("recipe", value.getID());
                        net.minecraft.world.item.ItemStack nms = CraftItemStack.asNMSCopy(itemStack);
                        nms.setTag(tag);
                        itemStack = CraftItemStack.asBukkitCopy(nms);
                    }
                }
                switch (matrix.slot) {
                    case 1 -> getInventory().setItem(9, itemStack);
                    case 2 -> getInventory().setItem(10, itemStack);
                    case 3 -> getInventory().setItem(11, itemStack);
                    case 4 -> getInventory().setItem(18, itemStack);
                    case 5 -> getInventory().setItem(19, itemStack);
                    case 6 -> getInventory().setItem(20, itemStack);
                    case 7 -> getInventory().setItem(27, itemStack);
                    case 8 -> getInventory().setItem(28, itemStack);
                    case 9 -> getInventory().setItem(29, itemStack);
                }
            }
            getInventory().setItem(25, recipe.result.getItem());
            getInventory().setItem(22, ItemList.arrowRight.copy().createItem());
            getInventory().setItem(40, FuchsUtility.createInventoryStack(Material.PAPER, 1, "Zurück"));
        }
    }

}
