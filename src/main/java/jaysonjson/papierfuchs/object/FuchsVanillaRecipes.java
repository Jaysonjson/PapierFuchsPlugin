package jaysonjson.papierfuchs.object;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.object.item.ItemList;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.ArrayList;

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
        net.minecraft.server.v1_16_R3.ItemStack itemStack = Utility.createNMSCopy(ItemList.VILUM_INGOT.createItem());
        itemStack.setTag(new NBTTagCompound());
        ItemStack itemStack1 = CraftItemStack.asBukkitCopy(itemStack);
        itemStack1.setLore(new ArrayList<>());
        ShapelessRecipe v = new ShapelessRecipe(createRecipeKey("v"), ItemList.CRAFTING_UPGRADE_KIT.createItem());
        v.addIngredient(itemStack1);
        Bukkit.addRecipe(v);

        ItemStack tin_ingot_output = ItemList.TIN_INGOT.createItem();
        tin_ingot_output.setAmount(2);
        ShapelessRecipe tin_ingot = new ShapelessRecipe(createRecipeKey("tin_ingot"), tin_ingot_output);
        tin_ingot.addIngredient(new ItemStack(Material.IRON_INGOT));
        tin_ingot.addIngredient(new ItemStack(Material.COAL));
        Bukkit.addRecipe(tin_ingot);

        ShapelessRecipe vilum_ingot = new ShapelessRecipe(createRecipeKey("vilum_ingot"), ItemList.VILUM_INGOT.createItem());
        vilum_ingot.addIngredient(new ItemStack(Material.IRON_INGOT));
        vilum_ingot.addIngredient(new ItemStack(Material.ENDER_PEARL));
        vilum_ingot.addIngredient(new ItemStack(Material.BLAZE_POWDER));
        Bukkit.addRecipe(vilum_ingot);
    }

    private static NamespacedKey createRecipeKey(String key) {
        return new NamespacedKey(PapierFuchs.INSTANCE, key);
    }
}
