package jaysonjson.papierfuchs.paper.commands.area;

import jaysonjson.papierfuchs.FuchsPermission;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.InventoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.lists.AreaListInventory;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.jetbrains.annotations.NotNull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AreaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player player) {
            if(FuchsUtility.hasPermission(player, FuchsPermission.COMMAND_AREA_LIST)) {
                AreaListInventory areaListInventory = InventoryList.areaList.copy();
                areaListInventory.create(player);
                areaListInventory.openInventory();
                return true;
            }
        }
        return false;
    }

}
