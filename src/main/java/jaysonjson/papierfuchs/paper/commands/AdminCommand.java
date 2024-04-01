package jaysonjson.papierfuchs.paper.commands;

import jaysonjson.papierfuchs.FuchsPermission;
import jaysonjson.papierfuchs.fuchs.io.data.FuchsLocation;
import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.fuchs.io.data.server.data.ByteFuchsWorld;
import jaysonjson.papierfuchs.fuchs.object.intern.currency.CurrencyList;
import jaysonjson.papierfuchs.fuchs.object.intern.currency.FuchsCurrency;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.InventoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.AddEffectInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.SellInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.item.ItemModListInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.lists.SavedLocationsInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.lists.SkillclassInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.lists.WorldListInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistries;
import jaysonjson.papierfuchs.fuchs.utility.Colors;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.utility.References;
import jaysonjson.papierfuchs.old.inventories.crafting.general.GeneralCraftingInventory;
import jaysonjson.papierfuchs.old.npc.NPC;
import net.kyori.adventure.text.Component;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AdminCommand implements TabExecutor {

    public MultiCommand multiCommand;

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String string, @NotNull String[] strings) {

        if(commandSender instanceof Player player) {
            multiCommand = new MultiCommand(player, string, strings);
            switch (multiCommand.secondary + " " + multiCommand.tertiary) {
                case "compress currency", "currency compress" -> compressCurrency();
                case "currency give" -> giveCurrency();
                case "item inventory", "inventory item" -> itemInventory();
                case "item data" -> itemData();
                case "item edit" -> editItem();
                case "inventory sell" -> sellInventory();
                case "inventory reports" -> reportsInventory();
                case "inventory crafting" -> craftingInventory();
                //case "inventory applyeffect" -> applyEffectInventory();
                case "inventory locationlist", "location list" -> locationList();
                case "inventory skillclass" -> skillClassInventory();
                case "inventory addeffect" -> addEffectInventory();
                case "set health" -> setHealth();
                case "item give" -> giveItem();
                case "location add" -> locationAdd();
                case "world inventory", "inventory worlds" -> worldList();
                case "npc corpse" -> createCorpse();
                case "clear blockchange" -> clearBlockChange();
            }
            switch (multiCommand.secondary) {
                case "save" -> save();
            }
        }
        return true;

    }


    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String string, @NotNull String[] strings) {
        ArrayList<String> list = new ArrayList<>();
        if(commandSender instanceof Player player) {
            MultiCommand tab = new MultiCommand(player, string, strings);
            switch (strings.length) {
                case 1 -> {
                    list.add("item");
                    list.add("inventory");
                    list.add("currency");
                    list.add("compress");
                    list.add("clear");
                    list.add("save");
                    list.add("world");
                    list.add("location");
                    list.add("set");
                }
                case 2 -> {
                    switch (tab.secondary) {
                        case "item" -> {
                            list.add("give");
                            list.add("inventory");
                            list.add("edit");
                            list.add("data");
                        }
                        case "location" -> {
                            list.add("add");
                            list.add("list");
                        }
                        case "world" -> {
                            list.add("inventory");
                        }
                        case "inventory" -> {
                            list.add("sell");
                            list.add("locationlist");
                            list.add("skillclass");
                            list.add("addeffect");
                            list.add("item");
                        }
                        case "currency" -> {
                            list.add("give");
                        }
                        case "compress" -> {
                            list.add("currency");
                        }
                        case "clear" -> {
                            list.add("blockchange");
                        }
                        case "set" -> {
                            list.add("health");
                        }
                    }
                }
                case 3 -> {
                    switch (tab.secondary + " " + tab.tertiary) {
                        case "item give" -> {
                            list.addAll(FuchsRegistries.items.keySet());
                        }
                        case "currency give" -> {
                            list.addAll(FuchsRegistries.currencies.keySet());
                        }
                        case "set health" -> {
                            for (int i = 0; i < 1025; i++) {
                             list.add(i + "");
                            }
                        }
                    }
                }
                case 4 -> {
                    switch (tab.secondary + " " + tab.tertiary) {
                        case "item give" -> {
                            for (int i = 0; i < 65; i++) {
                                list.add(i + "");
                            }
                        }
                        case "currency give" -> {
                            list.add(0 + "");
                        }
                        case "set health" -> {
                            for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
                                list.add(onlinePlayer.getName());
                            }
                        }
                    }
                }
                case 5 -> {
                    switch (tab.secondary + " " + tab.tertiary) {
                        case "currency give" -> {
                            for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
                                list.add(onlinePlayer.getName());
                            }
                        }
                    }
                }
            }
        }
        return list;
    }

    public void save() {
        if(multiCommand.validate(FuchsPermission.COMMAND_ADMIN_SAVE)) {
            References.data.save();
            multiCommand.player.sendMessage(Component.text(Colors.GREEN + "Gespeichert!"));
        }
    }

    public void clearBlockChange() {
        if(multiCommand.validate(FuchsPermission.COMMAND_ADMIN_CLEAR_BLOCKCHANGE)) {
            for (ByteFuchsWorld value : References.data.byteWorlds.values()) {
                long difference = 0;
                try {
                    Path path = new File(value.path()).toPath();
                    long pre = Files.size(path);
                    value.getBlockChanges().clear();
                    References.data.saveByteWorld(value);
                    long post = Files.size(path);
                    difference = pre - post;
                } catch (Exception ignored) {
                    value.getBlockChanges().clear();
                    References.data.saveByteWorld(value);
                }
                multiCommand.player.sendMessage(Component.text(Colors.GREEN + "BlockChanges wurden in der Welt: " + ChatColor.AQUA + Bukkit.getServer().getWorld(value.getWorldUuid()).getName() + Colors.GREEN + " geleert! (" + ChatColor.AQUA + FuchsUtility.formatDouble(difference / 1048576d) + "mb" + Colors.GREEN + " entfernt)"));
            }
        }
    }

    public void reportsInventory() {
        if(multiCommand.validate(FuchsPermission.COMMAND_ADMIN_INVENTORY_REPORTS)) {
            if(References.data.getByteServer().getReports().size() > 0) {
                InventoryList.reportList.copy().createAndOpen(multiCommand.player);
            } else {
                multiCommand.player.sendMessage(Component.text(Colors.RED_WHITE + "Es sind keine Reports vorhanden!"));
            }
        }
    }

    public void skillClassInventory() {
        if(multiCommand.validate(FuchsPermission.COMMAND_ADMIN_INVENTORY_SKILLCLASS)) {
            SkillclassInventory skillclassInventory = InventoryList.skillclass.copy();
            skillclassInventory.create(multiCommand.player);
            skillclassInventory.openInventory();
        }
    }

    public void addEffectInventory() {
        if(multiCommand.validate(FuchsPermission.COMMAND_ADMIN_INVENTORY_SKILLCLASS)) {
            AddEffectInventory addEffectInventory = InventoryList.addEffect.copy();
            addEffectInventory.create(multiCommand.player);
            addEffectInventory.openInventory();
        }
    }


    public void createCorpse() {
        if(multiCommand.validate(FuchsPermission.COMMAND_ADMIN_NPC_CORPSE)) {
            Player player = multiCommand.player;
            if(!multiCommand.tertiaryData.equalsIgnoreCase("")) {
                String name = player.getName();
                if(!multiCommand.tertiarySubData.equalsIgnoreCase("")) {
                    name = multiCommand.tertiarySubData;
                }
                NPC.createNPC(player, name, name, true);
                player.sendMessage(Component.text(Colors.GREEN + "Leiche erstellt!"));
            } else {
                player.sendMessage(multiCommand.missingArguments());
            }
        }
    }

    public void editItem() {
        if(multiCommand.validate(FuchsPermission.COMMAND_ADMIN_ITEM_EDIT)) {
            Player player = multiCommand.player;
            ItemStack itemStack = player.getEquipment().getItemInMainHand();
            if(FuchsUtility.isFuchsItem(itemStack)) {
            } else {
                player.sendMessage(Component.text(Colors.RED_WHITE + "Item ist kein FuchsItem"));
            }
        }
    }

    public void worldList() {
        if(multiCommand.validate(FuchsPermission.COMMAND_ADMIN_WORLD_LIST)) {
            WorldListInventory worldListInventory = InventoryList.worldList.copy();
            worldListInventory.create(multiCommand.player);
            worldListInventory.openInventory();
        }
    }

    public void giveCurrency() {
        if(multiCommand.validate(FuchsPermission.COMMAND_ADMIN_CURRENCY_GIVE)) {
            Player player = multiCommand.player;
            if(!multiCommand.tertiarySubData.equalsIgnoreCase("") && !multiCommand.tertiaryData.equalsIgnoreCase("")) {
                if(FuchsUtility.currencyExists(multiCommand.tertiaryData)) {
                    if (StringUtils.isNumeric(multiCommand.tertiarySubData) || Pattern.matches("[+-]?([0-9]*[.])?[0-9]+", multiCommand.tertiarySubData)) {
                        FuchsCurrency currency = FuchsUtility.getCurrencyByID(multiCommand.tertiaryData);
                        float amount = Float.parseFloat(multiCommand.tertiarySubData);
                        Player target = player;
                        boolean isSender = true;
                        if (!multiCommand.quaternary.equalsIgnoreCase("")) {
                            if (Bukkit.getPlayer(multiCommand.quaternary) != null) {
                                target = Bukkit.getPlayer(multiCommand.quaternary);
                                isSender = false;
                            } else {
                                player.sendMessage(Component.text(Colors.RED_WHITE + "Spieler " + ChatColor.AQUA + multiCommand.quaternary + Colors.RED_WHITE + " existiert nicht!"));
                            }
                        }
                        if (isSender) {
                            player.sendMessage(Component.text(Colors.GREEN + "Du hast dir " + ChatColor.AQUA + amount + currency.getSymbol() + Colors.GREEN + " gegeben!"));
                            FuchsUtility.addCurrency(currency, amount, player);
                        } else {
                            FuchsUtility.addCurrency(currency, amount, target);
                            player.sendMessage(Component.text(Colors.GREEN + "Du hast " + ChatColor.AQUA + target.getName() + " " + amount + currency.getSymbol() + Colors.GREEN + " gegeben!"));
                            target.sendMessage(Component.text(ChatColor.AQUA + player.getName() + Colors.GREEN + " hat dir " + ChatColor.AQUA + amount + currency.getSymbol() + Colors.GREEN + " gegeben!"));
                        }
                    } else {
                        player.sendMessage(multiCommand.notANumber(multiCommand.tertiaryData));
                    }
                } else {
                    player.sendMessage(Component.text(Colors.RED_WHITE + "FuchsCurrency mit dem Schlüssel " + ChatColor.AQUA + multiCommand.tertiaryData + Colors.RED_WHITE + " existiert nicht!"));
                }
            } else {
                player.sendMessage(multiCommand.missingArguments());
            }
        }
    }

    public void craftingInventory() {
        if(multiCommand.validate(FuchsPermission.COMMAND_ADMIN_INVENTORY_CRAFTING)) {
            GeneralCraftingInventory generalCraftingInventory = new GeneralCraftingInventory();
            generalCraftingInventory.createPageData(multiCommand.player);
            generalCraftingInventory.openInventory(multiCommand.player, 0);
        }
    }

    public void locationList() {
        if(multiCommand.validate(FuchsPermission.COMMAND_ADMIN_LOCATION_LIST)) {
            SavedLocationsInventory savedLocationsInventory = InventoryList.savedLocations.copy();
            savedLocationsInventory.create(multiCommand.player);
            savedLocationsInventory.openInventory();
        }
    }

    public void locationAdd() {
        if(multiCommand.validate(FuchsPermission.COMMAND_ADMIN_LOCATION_ADD)) {
            Player player = multiCommand.player;
            FuchsPlayer fuchsPlayer = multiCommand.fuchsPlayer;
            if(!multiCommand.tertiaryData.equalsIgnoreCase("")) {
                String joined = multiCommand.tertiaryData;
                joined = joined + String.join(" ", multiCommand.args);
                joined = joined.substring(multiCommand.args[0].length() + multiCommand.args[1].length() + multiCommand.args[2].length() + 2);

                if (!fuchsPlayer.getSavedLocations().containsKey(joined)) {
                    fuchsPlayer.getSavedLocations().put(joined, new FuchsLocation(player.getWorld(), player.getLocation()));
                    player.sendMessage(Component.text(Colors.GREEN + "Location mit den Namen \"" + ChatColor.AQUA + joined + Colors.GREEN + "\" hinzugefügt! [" + FuchsUtility.getLocationString(player.getLocation()) + "]"));
                } else {
                    player.sendMessage(Component.text(Colors.RED_WHITE + "Location mit den Namen \"" + ChatColor.AQUA + joined + Colors.RED_WHITE + "\" existiert bereits!"));
                }

            } else {
                player.sendMessage(multiCommand.missingArguments());
            }
        }
    }

    public void giveItem() {
        if(multiCommand.validate(FuchsPermission.COMMAND_ADMIN_ITEM_GIVE)) {
            Player player = multiCommand.player;
            if(!multiCommand.tertiaryData.equalsIgnoreCase("")) {
                if(FuchsUtility.itemIDExists(multiCommand.tertiaryData)) {
                    ItemStack itemStack = FuchsUtility.getFuchsItemByID(multiCommand.tertiaryData).createItem(player);
                    if(!multiCommand.tertiarySubData.equalsIgnoreCase("")) {
                        if(StringUtils.isNumeric(multiCommand.tertiarySubData)) {
                            itemStack.setAmount(Integer.parseInt(multiCommand.tertiarySubData));
                        } else {
                            player.sendMessage(multiCommand.notANumber(multiCommand.tertiarySubData));
                        }
                    }
                    FuchsUtility.addItemOrDrop(player, itemStack);
                } else {
                    player.sendMessage(Component.text(Colors.RED_WHITE + "FuchsItem mit der Id " + ChatColor.AQUA + multiCommand.tertiaryData + Colors.RED_WHITE + " existiert nicht!"));
                }
            } else {
                player.sendMessage(multiCommand.missingArguments());
            }
        }
    }

    public void setHealth() {
        if(multiCommand.validate(FuchsPermission.COMMAND_ADMIN_SET_HEALTH)) {
            Player player = multiCommand.player;
            if(!multiCommand.tertiaryData.equalsIgnoreCase("")) {
                if(StringUtils.isNumeric(multiCommand.tertiaryData)) {
                    int health = Integer.parseInt(multiCommand.tertiaryData);
                    Player target = player;
                    boolean isSender = true;
                    if (!multiCommand.tertiarySubData.equalsIgnoreCase("")) {
                        if (Bukkit.getPlayer(multiCommand.tertiarySubData) != null) {
                            target = Bukkit.getPlayer(multiCommand.tertiarySubData);
                            isSender = false;
                        } else {
                            player.sendMessage(Component.text(Colors.RED_WHITE + "Spieler " + ChatColor.AQUA + multiCommand.tertiarySubData + Colors.RED_WHITE + " existiert nicht!"));
                        }
                    }
                    FuchsPlayer fuchsPlayer = References.data.getPlayer(target);
                    fuchsPlayer.getSpecial().getHealth().setHealth(health);
                    FuchsUtility.refreshHearts(target, fuchsPlayer);
                    if(isSender) {
                        player.sendMessage(Component.text(Colors.GREEN + "Du hast dein Leben auf " + ChatColor.AQUA + health + Colors.GREEN + " geändert!"));
                    } else {
                        player.sendMessage(Component.text(Colors.GREEN + "Du hast das Leben von " + ChatColor.AQUA + target.getName() + Colors.GREEN + " auf " + ChatColor.AQUA + health + Colors.GREEN + " geändert!"));
                        target.sendMessage(Component.text(ChatColor.AQUA + player.getName() + Colors.GREEN + " hat dein Leben auf " + ChatColor.AQUA + health + Colors.GREEN + " geändert!"));
                    }
                } else {
                    player.sendMessage(multiCommand.notANumber(multiCommand.tertiaryData));
                }
            } else {
                player.sendMessage(multiCommand.missingArguments());
            }
        }
    }

    public void compressCurrency() {
        if(multiCommand.validate(FuchsPermission.COMMAND_ADMIN_COMPRESS_CURRENCY)) {
            FuchsUtility.compressCurrency(CurrencyList.FUCHSO.copy(), multiCommand.player);
        }
    }

    public void itemInventory() {
        if(multiCommand.validate(FuchsPermission.COMMAND_ADMIN_ITEM_INVENTORY)) {
            Player player = multiCommand.player;
            ItemModListInventory modListInventory = InventoryList.itemModList.copy();
            modListInventory.createAndOpen(player);
        }
    }


    public void itemData() {
        if(multiCommand.validate(FuchsPermission.COMMAND_ADMIN_ITEM_DATA)) {
            Player player = multiCommand.player;
            ItemStack stack = player.getEquipment().getItemInMainHand();
            if(stack.getType() != Material.AIR) {
                FuchsMCItem fuchsMCItem = new FuchsMCItem(player, stack);
                player.sendMessage(Component.text("Fuchsdaten: "));
                if(fuchsMCItem.generalData().has()) {
                    player.sendMessage(Component.text(fuchsMCItem.generalData().get().toString()));
                }
                if(fuchsMCItem.armorData().has()) {
                    player.sendMessage(Component.text(fuchsMCItem.armorData().get().toString()));
                }
                if(fuchsMCItem.toolData().has()) {
                    player.sendMessage(Component.text(fuchsMCItem.toolData().get().toString()));
                }
                if(fuchsMCItem.alchemyData().has()) {
                    player.sendMessage(Component.text(fuchsMCItem.alchemyData().get().toString()));
                }
                if(fuchsMCItem.currencyData().has()) {
                    player.sendMessage(Component.text(fuchsMCItem.currencyData().get().toString()));
                }
                if(fuchsMCItem.magicData().has()) {
                    player.sendMessage(Component.text(fuchsMCItem.magicData().get().toString()));
                }
                if(fuchsMCItem.gasData().has()) {
                    player.sendMessage(Component.text(fuchsMCItem.gasData().get().toString()));
                }
                if(fuchsMCItem.blockData().has()) {
                    player.sendMessage(Component.text(fuchsMCItem.blockData().get().toString()));
                }
                if(fuchsMCItem.liquidData().has()) {
                    player.sendMessage(Component.text(fuchsMCItem.liquidData().get().toString()));
                }
                if(fuchsMCItem.projectileData().has()) {
                    player.sendMessage(Component.text(fuchsMCItem.projectileData().get().toString()));
                }
                player.sendMessage(Component.text("Ende"));
            } else {
                player.sendMessage(Component.text(Colors.RED_WHITE + "Item darf nicht LUFT sein!"));
            }
        }
    }

    public void sellInventory() {
        if(multiCommand.validate(FuchsPermission.COMMAND_ADMIN_INVENTORY_SELL)) {
            SellInventory sellInventory = InventoryList.sellInventory.copy();
            sellInventory.create(multiCommand.player);
            sellInventory.openInventory();
        }
    }

}


