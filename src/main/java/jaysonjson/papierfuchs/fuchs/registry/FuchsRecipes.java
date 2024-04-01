package jaysonjson.papierfuchs.fuchs.registry;

import jaysonjson.papierfuchs.fuchs.io.data.DataHandler;
import jaysonjson.papierfuchs.fuchs.io.data.FileHandler;
import jaysonjson.papierfuchs.fuchs.io.data.crafting.obj.zCraftingItem;
import jaysonjson.papierfuchs.fuchs.object.crafting.vanilla.smithing.FuchsSmithingRecipe;
import jaysonjson.papierfuchs.fuchs.object.crafting.vanilla.workbench.FuchsWorkbenchRecipe;
import jaysonjson.papierfuchs.fuchs.utility.FuchsAnsi;
import jaysonjson.papierfuchs.fuchs.utility.FuchsLog;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FuchsRecipes {

    public static Map<String, FuchsSmithingRecipe> smithing = new HashMap<>();
    public static Map<String, FuchsWorkbenchRecipe> workbench = new HashMap<>();

    public static void loadSmithing() {
        for (File file : new File(FileHandler.RECIPE_SMITHING).listFiles()) {
            if(file.getName().contains(".json")) {
                FuchsSmithingRecipe fuchsSmithingRecipe = DataHandler.gson.fromJson(DataHandler.readDataISO(file), FuchsSmithingRecipe.class);
                if(!smithing.containsKey(fuchsSmithingRecipe.getID())) {
                    fuchsSmithingRecipe.equipment.generate(file.getAbsolutePath().substring(file.getAbsolutePath().indexOf("plugins")));
                    fuchsSmithingRecipe.output.generate(file.getAbsolutePath().substring(file.getAbsolutePath().indexOf("plugins")));
                    fuchsSmithingRecipe.mineral.generate(file.getAbsolutePath().substring(file.getAbsolutePath().indexOf("plugins")));
                    smithing.put(fuchsSmithingRecipe.getID(), fuchsSmithingRecipe);
                    FuchsLog.log(FuchsAnsi.GREEN + "Smithing Rezept " + FuchsAnsi.CYAN + fuchsSmithingRecipe.getID() + FuchsAnsi.GREEN + " geladen!");
                } else {
                    FuchsLog.warn("Doppeltes Smithing Rezept mit ID " + FuchsAnsi.CYAN + fuchsSmithingRecipe.getID());
                }
            }
        }
    }

    public static void loadWorkbench() {
        for (File file : new File(FileHandler.RECIPE_WORKBENCH).listFiles()) {
            if(file.getName().contains(".json")) {
                registerWorkbenchRecipe(DataHandler.readDataISO(file), file.getAbsolutePath().substring(file.getAbsolutePath().indexOf("plugins")), "unknown");
            }
        }
        for (FuchsRegistry registry : FuchsRegistries.registries) {
            if(registry.fuchsPlugin.getClass().getResource("/recipes/workbench/") != null) {
                for (String fileNamesResource : FuchsUtility.getFileNamesResources(registry.fuchsPlugin.getClass(),"recipes/workbench/")) {
                    if(fileNamesResource.contains(".json")) {
                        registerWorkbenchRecipe(FuchsUtility.getContentFromResource(registry.fuchsPlugin.getClass(), "/" + fileNamesResource), fileNamesResource, registry.fuchsPlugin.getRegistryID());
                    }
                }
            }
        }
    }

    public static void registerWorkbenchRecipe(String content, String name, String mod) {
            FuchsWorkbenchRecipe fuchsWorkbenchRecipe = DataHandler.gson.fromJson(content, FuchsWorkbenchRecipe.class);
            fuchsWorkbenchRecipe.setId(mod + ":" + fuchsWorkbenchRecipe.getID());
            if(!workbench.containsKey(fuchsWorkbenchRecipe.getID())) {
                for (zCraftingItem item : fuchsWorkbenchRecipe.matrix) {
                    item.generate(name);
                }
                fuchsWorkbenchRecipe.result.generate(name);
                workbench.put(fuchsWorkbenchRecipe.getID(), fuchsWorkbenchRecipe);
                FuchsLog.log(FuchsAnsi.GREEN + "Workbench Rezept " + FuchsAnsi.CYAN + fuchsWorkbenchRecipe.getID() + FuchsAnsi.GREEN + " geladen!");
            } else {
                FuchsLog.warn("Doppeltes Workbench Rezept mit ID " + FuchsAnsi.CYAN + fuchsWorkbenchRecipe.getID());
            }
    }

}
