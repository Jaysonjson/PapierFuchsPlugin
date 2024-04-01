package jaysonjson.papierfuchs.paper.commands;

import jaysonjson.papierfuchs.fuchs.object.intern.inventory.InventoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.lists.HatInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.lists.PetInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CosmeticCommand implements TabExecutor {

    MultiCommand multiCommand;
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String string, @NotNull String[] strings) {
        if(commandSender instanceof Player player) {
            multiCommand = new MultiCommand(player, string, strings);
            switch (multiCommand.secondary) {
                case "pet" -> petInventory();
                case "hat" -> hatInventory();
            }
            if(multiCommand.secondary.equalsIgnoreCase("")) {
                InventoryList.cosmetic.copy().createAndOpen(player);
            }
        }
        return true;
    }

    public void petInventory() {
        PetInventory petInventory = InventoryList.pet.copy();
        petInventory.create(multiCommand.player);
        petInventory.openInventory();
    }

    public void hatInventory() {
        HatInventory hatInventory = InventoryList.hat.copy();
        hatInventory.create(multiCommand.player);
        hatInventory.openInventory();
    }


    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String string, @NotNull String[] strings) {
        ArrayList<String> list = new ArrayList<>();
        if(commandSender instanceof Player player) {
            if(strings.length == 1) {
                list.add("pet");
                list.add("hat");
            }
        }
        return list;
    }
}
