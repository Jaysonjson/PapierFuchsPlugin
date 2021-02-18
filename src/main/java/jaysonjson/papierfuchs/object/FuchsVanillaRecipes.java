package jaysonjson.papierfuchs.object;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.object.item.ItemList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class FuchsVanillaRecipes {
    public static void addRecipes() {

        ShapelessRecipe craftingKit = new ShapelessRecipe(createRecipeKey("craftingkit"), ItemList.CRAFTING_UPGRADE_KIT.createItem());
        craftingKit.addIngredient(new ItemStack(Material.DIAMOND));
        craftingKit.addIngredient(new ItemStack(Material.CRAFTING_TABLE));
        Bukkit.addRecipe(craftingKit);
/*
        ShapedRecipe vilumSword = new ShapedRecipe(createRecipeKey("vilumsword"), ItemList.VILUM_SWORD.createItem());
        vilumSword.shape("V", "V", "S");
        vilumSword.setIngredient('V', ItemList.VILUM_SWORD.createItem());
        vilumSword.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(vilumSword);
        */
        ShapelessRecipe v = new ShapelessRecipe(createRecipeKey("v"), ItemList.CRAFTING_UPGRADE_KIT.createItem());
        v.getIngredientList().add(ItemList.VILUM_INGOT.createItem());
        Bukkit.addRecipe(v);
    }

    private static NamespacedKey createRecipeKey(String key) {
        return new NamespacedKey(PapierFuchs.INSTANCE, key);
    }
}
