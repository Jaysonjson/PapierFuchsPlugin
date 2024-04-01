package jaysonjson.papierfuchs.fuchs.object.intern.category.item.objects;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.effect.FuchsEffect;
import jaysonjson.papierfuchs.fuchs.object.intern.entity.FuchsEntity;
import jaysonjson.papierfuchs.fuchs.object.intern.gas.FuchsGas;
import jaysonjson.papierfuchs.fuchs.object.intern.gas.GasList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.FuchsInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.lists.ItemList;
import jaysonjson.papierfuchs.fuchs.object.intern.liquid.FuchsLiquid;
import jaysonjson.papierfuchs.fuchs.object.intern.liquid.LiquidList;
import jaysonjson.papierfuchs.fuchs.object.intern.projectile.FuchsProjectile;
import jaysonjson.papierfuchs.fuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistries;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistry;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistryObject;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class AllItemCategory extends DefaultItemCategory {

    public AllItemCategory(String id, FuchsLanguageString languageString) {
        super(id, languageString);
    }

    @Override
    public void onListInventory(FuchsRegistry fuchsRegistry, FuchsInventory fuchsInventory, ArrayList<ItemStack> itemStacks) {
        if(fuchsRegistry == null) {
            FuchsRegistries.items.values().forEach(fuchsItem -> {
                if (fuchsItem.copy().showInItemList()) {
                    itemStacks.add(fuchsItem.copy().createItem(fuchsInventory.getPlayer(), null));
                }
            });
            FuchsRegistries.liquids.values().forEach(fuchsLiquid -> {
                if (fuchsLiquid.copy() != LiquidList.NONE.copy()) {
                    ItemStack liquidContainer = ItemList.liquidContainer.copy().createItem(fuchsInventory.getPlayer(), null);
                    FuchsMCItem fuchsMCItem = new FuchsMCItem(ItemList.liquidContainer.copy(), fuchsInventory.getPlayer(), liquidContainer);
                    fuchsMCItem.getData().setLiquidID(fuchsLiquid.getID());
                    fuchsMCItem.getData().setLiquidAmount(500f);
                    itemStacks.add(fuchsMCItem.getItemStack());
                }
            });
            FuchsRegistries.gasses.values().forEach(fuchsGas -> {
                if (fuchsGas.copy() != GasList.NONE.copy()) {
                    ItemStack gasContainer = ItemList.gasContainer.copy().createItem(fuchsInventory.getPlayer(), null);
                    FuchsMCItem fuchsMCItem = new FuchsMCItem(ItemList.gasContainer.copy(), fuchsInventory.getPlayer(), gasContainer);
                    fuchsMCItem.getData().setGasID(fuchsGas.getID());
                    fuchsMCItem.getData().setGasAmount(500f);
                    itemStacks.add(fuchsMCItem.getItemStack());
                }
            });
            FuchsRegistries.effects.values().forEach(effect -> itemStacks.add(FuchsUtility.createEffectBook(effect.copy(), fuchsInventory.getPlayer())));
            FuchsRegistries.entities.values().forEach(fuchsEntity -> itemStacks.add(FuchsUtility.createSpawnEgg(fuchsEntity.copy(), fuchsInventory.getPlayer())));
            FuchsRegistries.projectiles.values().forEach(projectile -> itemStacks.add(FuchsUtility.createBowProjectiles(projectile.copy(), fuchsInventory.getPlayer())));
        } else {
            for (FuchsRegistryObject<? extends FuchsObject> object : fuchsRegistry.objects) {
                FuchsObject fuchsObject = object.copy();
                if(fuchsObject instanceof FuchsItem fuchsItem) {
                    if(fuchsItem.showInItemList()) {
                        itemStacks.add(fuchsItem.createItem(fuchsInventory.getPlayer()));
                    }
                }
                if(fuchsObject instanceof FuchsLiquid fuchsLiquid) {
                    if (fuchsLiquid != LiquidList.NONE.copy()) {
                        ItemStack liquidContainer = ItemList.liquidContainer.copy().createItem(fuchsInventory.getPlayer(), null);
                        FuchsMCItem fuchsMCItem = new FuchsMCItem(ItemList.liquidContainer.copy(), fuchsInventory.getPlayer(), liquidContainer);
                        fuchsMCItem.getData().setLiquidID(fuchsLiquid.getID());
                        fuchsMCItem.getData().setLiquidAmount(500f);
                        itemStacks.add(fuchsMCItem.getItemStack());
                    }
                }
                if(fuchsObject instanceof FuchsGas fuchsGas) {
                    if (fuchsGas != GasList.NONE.copy()) {
                        ItemStack gasContainer = ItemList.gasContainer.copy().createItem(fuchsInventory.getPlayer(), null);
                        FuchsMCItem fuchsMCItem = new FuchsMCItem(ItemList.gasContainer.copy(), fuchsInventory.getPlayer(), gasContainer);
                        fuchsMCItem.getData().setGasID(fuchsGas.getID());
                        fuchsMCItem.getData().setGasAmount(500f);
                        itemStacks.add(fuchsMCItem.getItemStack());
                    }
                }
                if(fuchsObject instanceof FuchsEffect<?> fuchsEffect) {
                    itemStacks.add(FuchsUtility.createEffectBook(fuchsEffect, fuchsInventory.getPlayer()));
                }
                if(fuchsObject instanceof FuchsEntity<?> fuchsEntity) {
                    itemStacks.add(FuchsUtility.createSpawnEgg(fuchsEntity, fuchsInventory.getPlayer()));
                }
                if(fuchsObject instanceof FuchsProjectile fuchsProjectile) {
                    itemStacks.add(FuchsUtility.createBowProjectiles(fuchsProjectile, fuchsInventory.getPlayer()));
                }
            }
        }
    }
}
