package jaysonjson.papierfuchs.paper.commands;

import jaysonjson.papierfuchs.fuchs.object.intern.inventory.InventoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.lists.RecipeListInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RecipeInventoryCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player player) {
            RecipeListInventory recipeListInventory = InventoryList.recipeList.copy();
            recipeListInventory.create(player);
            recipeListInventory.openInventory();
            return true;
        }
        return false;
    }
}
