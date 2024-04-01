package jaysonjson.papierfuchs.paper.events.item;

import jaysonjson.papierfuchs.fuchs.io.data.crafting.obj.zCraftingItem;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemCurrencyData;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemGeneralData;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.ids.FIGProperty;
import jaysonjson.papierfuchs.fuchs.object.crafting.vanilla.smithing.FuchsSmithingRecipe;
import jaysonjson.papierfuchs.fuchs.object.crafting.vanilla.workbench.FuchsWorkbenchRecipe;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRecipes;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.SmithingInventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class CraftItem implements Listener {

    private final ArrayList<UUID> smithing_close = new ArrayList<>();
    private final HashMap<UUID, FuchsWorkbenchRecipe> recipeHashMap = new HashMap<>();
    @EventHandler
    public void smithItemEvent(PrepareSmithingEvent event) {
        SmithingInventory smithingInventory = event.getInventory();
        ItemStack equipment = smithingInventory.getInputEquipment();
        ItemStack mineral = smithingInventory.getInputMineral();
        /*if(equipment != null && mineral != null) {
            if (equipment.getType().equals(Material.NETHERITE_SWORD) && FuchsUtility.isFuchsItem(mineral)) {
                if(FuchsUtility.getFuchsIDFromItem(smithingInventory.getInputMineral()).equalsIgnoreCase(ItemList.ZORYHA_TEAR.copy().getID())) {
                    event.setResult(ItemList.ZORYHA_FIGHTER_SWORD.copy().createItem((Player) event.getViewers().get(0)));
                }
            }
        }*/

        for (ItemStack content : event.getInventory().getContents()) {
            if(content != null) {
                if (FuchsUtility.isFuchsItem(content)) {
                    FuchsItem fuchsItem = FuchsUtility.getFuchsItemFromNMS(content);
                    fuchsItem.onItemPrepareSmithing(event);
                }
            }
        }

        for (FuchsSmithingRecipe recipe : FuchsRecipes.smithing.values()) {
            if(equipment != null && FuchsUtility.isItemSimilar(equipment, recipe.equipment.getItem(), true) && mineral != null && FuchsUtility.isItemSimilar(mineral, recipe.mineral.getItem(), true)) {
                smithingInventory.setResult(recipe.output.getItem());
                for (HumanEntity viewer : event.getViewers()) {
                    if(!smithing_close.contains(viewer.getUniqueId())) {
                        ((Player) viewer).updateInventory();
                    }
                    smithing_close.add(viewer.getUniqueId());
                }
            }
        }
    }

    @EventHandler
    public void closeEvent(InventoryCloseEvent event) {
        smithing_close.remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void craftItemEvent(PrepareItemCraftEvent event) {
        CraftingInventory craftingInventory = event.getInventory();
        for (ItemStack content : event.getInventory().getContents()) {
            if(!content.isSimilar(event.getInventory().getResult())) {
                if(FuchsUtility.isFuchsItem(content)) {
                    FuchsItemGeneralData fuchsItemGeneralData = FuchsUtility.getGeneralDataFromItem(content);
                    if (fuchsItemGeneralData != null) {
                        if (!fuchsItemGeneralData.getProperty(FIGProperty.CAN_CRAFT_MINECRAFT)) {
                            event.getInventory().setResult(new ItemStack(Material.AIR));
                        }
                    }
                }
                /*for (BannedItems value : BannedItems.values()) {
                    if(value.getMaterial().equals(content.getType()) || value.getMaterial().equals(event.getRecipe().getResult().getType())) {
                        event.setCancelled(true);
                    }
                }*/
            }

            if(FuchsUtility.isFuchsItem(content)) {
                FuchsItem fuchsItem = FuchsUtility.getFuchsItemFromNMS(content);
                fuchsItem.onItemPrepareCraft(event);
            }
        }

        for (FuchsWorkbenchRecipe value : FuchsRecipes.workbench.values()) {
            int passed = 0;
            int items = 0;
            for (zCraftingItem item : value.matrix) {
                for (int i = 1; i < 10; i++) {
                    if(craftingInventory.getItem(i) != null) {
                        if (FuchsUtility.isItemSimilar(item.getItem(), craftingInventory.getItem(i), true) && item.getItem().getAmount() == craftingInventory.getItem(i).getAmount()) {
                            if (value.shapeless) {
                                passed++;
                            } else {
                                if (i == item.slot) {
                                    passed++;
                                }
                            }
                        }
                    }
                }
            }
            for (int i = 1; i < 10; i++) {
                if(craftingInventory.getItem(i) != null) {
                    if(!craftingInventory.getItem(i).getType().equals(Material.AIR)) {
                        items++;
                    }
                }
            }
            if(passed >= value.matrix.size() && value.matrix.size() == items) {
                craftingInventory.setResult(value.result.getItem());
                recipeHashMap.put(event.getViewers().get(0).getUniqueId(), value);
            }
        }

        if(event.getInventory().getResult() != null) {
            if (FuchsUtility.isFuchsItem(event.getInventory().getResult())) {
                event.getInventory().setResult(FuchsUtility.getFuchsItemFromNMS(event.getInventory().getResult()).createItem((Player) event.getViewers().get(0), event.getInventory().getResult()));
            } else if(FuchsUtility.isAbstractVanillaItem(event.getInventory().getResult())) {
                event.getInventory().setResult(FuchsUtility.getAbstractVanillaOverride(event.getInventory().getResult()).createItem((Player) event.getViewers().get(0), event.getInventory().getResult()));
            }
            FuchsMCItem mcItem = new FuchsMCItem(event.getInventory().getResult());
            FuchsItemCurrencyData currencyData = mcItem.currencyData().get();
            if(currencyData.getSellValue() == 0 && currencyData.getBuyValue() == 0) {
                int sellprice = 0;
                int buyprice = 0;
                for (ItemStack matrix : event.getInventory().getMatrix()) {
                    FuchsItemCurrencyData matrixCurrency = new FuchsMCItem(matrix).currencyData().get();
                    sellprice += matrixCurrency.getSellValue();
                    buyprice += matrixCurrency.getBuyValue();
                }
                currencyData.setBuyValue(buyprice);
                currencyData.setSellValue(sellprice);
                mcItem.currencyData().set(currencyData);
                event.getInventory().setResult(mcItem.getItemStack());
            }
        }
    }

    @EventHandler
    public void craftFinishEvent(CraftItemEvent event) {
        ItemStack result = event.getInventory().getResult();
        UUID playerUuid = event.getViewers().get(0).getUniqueId();
        FuchsWorkbenchRecipe recipe = recipeHashMap.get(playerUuid);
        if(recipe != null) {
            event.setCancelled(true);
            for (zCraftingItem craftingItem : recipe.matrix) {
                for (ItemStack matrix : event.getInventory().getMatrix()) {
                    if (FuchsUtility.isItemSimilar(matrix, craftingItem.getItem(), true)) {
                        //matrix.subtract(craftingItem.amount);
                    }
                }
            }
           //result.setAmount(recipe.result.amount);
            recipeHashMap.remove(playerUuid);
        }
    }
}
