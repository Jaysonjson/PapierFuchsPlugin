package jaysonjson.papierfuchs.fuchs.object;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.object.intern.item.lists.ItemList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.*;

public class FuchsVanillaRecipes {
    public static void addRecipes() {

       /* ShapelessRecipe craftingKit = new ShapelessRecipe(createRecipeKey("craftingkit"), ItemList.craftingUpgradeKit.copy().createItem());
        craftingKit.addIngredient(new ItemStack(Material.DIAMOND));
        craftingKit.addIngredient(new ItemStack(Material.CRAFTING_TABLE));
        Bukkit.addRecipe(craftingKit);
*/
        /*
        ShapedRecipe grapplinghook = new ShapedRecipe(createRecipeKey("grapplinghook"), ProjectileList.grapplingHook.copy().asItem(null, null));
        grapplinghook.shape(" V ", "VSV", " S ");
        grapplinghook.setIngredient('V', Material.IRON_INGOT);
        grapplinghook.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(grapplinghook);

        ShapedRecipe vilumSword = new ShapedRecipe(createRecipeKey("vilumsword"), ItemList.VILUM_SWORD.createItem());
        vilumSword.shape("V", "V", "S");
        vilumSword.setIngredient('V', ItemList.VILUM_SWORD.createItem());
        vilumSword.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(vilumSword);

        /*net.minecraft.world.item.ItemStack itemStack = FuchsUtility.createNMSCopy(ItemList.VILUM_INGOT.copy().createItem());
        itemStack.setTag(new NBTTagCompound());
        ItemStack itemStack1 = CraftItemStack.asBukkitCopy(itemStack);
        itemStack1.setLore(new ArrayList<>());
        ShapelessRecipe v = new ShapelessRecipe(createRecipeKey("v"), ItemList.CRAFTING_UPGRADE_KIT.copy().createItem());
        v.addIngredient(itemStack1);
        Bukkit.addRecipe(v);

        ItemStack tin_ingot_output = ItemList.TIN_INGOT.copy().createItem();
        tin_ingot_output.setAmount(2);
        ShapelessRecipe tin_ingot = new ShapelessRecipe(createRecipeKey("tin_ingot"), tin_ingot_output);
        tin_ingot.addIngredient(new ItemStack(Material.IRON_INGOT));
        tin_ingot.addIngredient(new ItemStack(Material.COAL));
        Bukkit.addRecipe(tin_ingot);

        ShapelessRecipe vilum_ingot = new ShapelessRecipe(createRecipeKey("vilum_ingot"), ItemList.VILUM_INGOT.copy().createItem());
        vilum_ingot.addIngredient(new ItemStack(Material.IRON_INGOT));
        vilum_ingot.addIngredient(new ItemStack(Material.ENDER_PEARL));
        vilum_ingot.addIngredient(new ItemStack(Material.BLAZE_POWDER));
        Bukkit.addRecipe(vilum_ingot);

        FurnaceRecipe test_furnace = new FurnaceRecipe(createRecipeKey("test_furnace"), ItemList.TIN_INGOT.copy().createItem(), new RecipeChoice.ExactChoice(ItemList.TIN_ORE.copy().createItem()),5, 5);
        Bukkit.addRecipe(test_furnace);
*/
        SmithingRecipe recipe = new SmithingRecipe(createRecipeKey("test_smithing"),
                new ItemStack(Material.AIR),
                new RecipeChoice.MaterialChoice(Material.NETHERITE_SWORD),
                new RecipeChoice.MaterialChoice(Material.FEATHER)
        );
        SmithingRecipe fuchs_smithing = new SmithingRecipe(createRecipeKey("fuchs_smithing"),
                new ItemStack(Material.AIR),
                new RecipeChoice.MaterialChoice(Material.AIR),
                new RecipeChoice.MaterialChoice(Material.AIR)
        );
        Bukkit.addRecipe(recipe);
        Bukkit.addRecipe(fuchs_smithing);
    }

    private static NamespacedKey createRecipeKey(String key) {
        return new NamespacedKey(PapierFuchs.INSTANCE, key);
    }
}
