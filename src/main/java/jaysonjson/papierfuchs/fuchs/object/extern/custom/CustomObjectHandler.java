package jaysonjson.papierfuchs.fuchs.object.extern.custom;

import jaysonjson.papierfuchs.fuchs.io.data.DataHandler;
import jaysonjson.papierfuchs.fuchs.io.data.FileHandler;
import jaysonjson.papierfuchs.fuchs.object.extern.custom.currency.CustomCurrency;
import jaysonjson.papierfuchs.fuchs.object.extern.custom.currency.CustomCurrencyData;
import jaysonjson.papierfuchs.fuchs.object.extern.custom.item.CustomItem;
import jaysonjson.papierfuchs.fuchs.object.extern.custom.item.CustomItemData;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistry;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistryObject;
import jaysonjson.papierfuchs.fuchs.utility.FuchsLog;
import org.bukkit.ChatColor;

import java.io.File;

public class CustomObjectHandler {

    public static void registerItems(FuchsRegistry fuchsRegistry) {
        for (File file : (new File(FileHandler.CUSTOM_OBJECTS_ITEMS_DIR)).listFiles()) {
            if(file.getName().contains("json")) {
                CustomItemData customItemData = DataHandler.gson.fromJson(DataHandler.readDataISO(file), CustomItemData.class);
                FuchsLog.log(ChatColor.GREEN + "Eigenes Item mit der ID " + ChatColor.AQUA + customItemData.id + ChatColor.GREEN + " gefunden!");
                fuchsRegistry.registerItems(new FuchsRegistryObject<>(new CustomItem(customItemData)));
            }
        }
    }

    public static void registerCurrencies(FuchsRegistry fuchsRegistry) {
        for (File file : (new File(FileHandler.CUSTOM_OBJECTS_CURRENCY_DIR)).listFiles()) {
            if(file.getName().contains("json")) {
                CustomCurrencyData customCurrencyData = DataHandler.gson.fromJson(DataHandler.readDataISO(file), CustomCurrencyData.class);
                FuchsLog.log(ChatColor.GREEN + "Eigene Währung mit der ID " + ChatColor.AQUA + customCurrencyData.id + ChatColor.GREEN + " gefunden!");
                fuchsRegistry.registerCurrencies(new FuchsRegistryObject<>(new CustomCurrency(customCurrencyData)));
            }
        }
    }

}
