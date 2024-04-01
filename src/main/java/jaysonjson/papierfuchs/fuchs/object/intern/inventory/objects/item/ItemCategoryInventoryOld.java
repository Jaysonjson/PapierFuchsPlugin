package jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.item;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.entity.EntityList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.FuchsInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.InventoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.InventorySize;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.lists.*;
import jaysonjson.papierfuchs.fuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistries;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistry;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistryObject;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemCategoryInventoryOld  {
/*
    private FuchsRegistry fuchsRegistry;
    private ItemModListInventory modListInventory;
    public ItemCategoryInventoryOld(String id) {
        super(id);
    }

    public void setFuchsRegistry(FuchsRegistry fuchsRegistry) {
        this.fuchsRegistry = fuchsRegistry;
    }

    public FuchsRegistry getFuchsRegistry() {
        return fuchsRegistry;
    }

    public ItemModListInventory getModListInventory() {
        return modListInventory;
    }

    public void setModListInventory(ItemModListInventory modListInventory) {
        this.modListInventory = modListInventory;
    }

    @Override
    public void setContents() {
        fillWithGlass();
        getInventory().setItem(10, createItemStack(FuchsLanguageString.c("Alles", "all").get(getFuchsPlayer()),Category.ALL));
        getInventory().setItem(11, createItemStack(FuchsLanguageString.c("Währung", "currency").get(getFuchsPlayer()),Category.CURRENCY));
        getInventory().setItem(12, createItemStack(FuchsLanguageString.c("Essen", "food").get(getFuchsPlayer()), Category.FOOD));
        getInventory().setItem(13, createItemStack(FuchsLanguageString.c("Flüssigkeiten", "liquids").get(getFuchsPlayer()),Category.LIQUID));
        getInventory().setItem(14, createItemStack(FuchsLanguageString.c("Gase", "gasses").get(getFuchsPlayer()),Category.GAS));
        getInventory().setItem(15, createItemStack(FuchsLanguageString.c("Effekte", "effects").get(getFuchsPlayer()),Category.EFFECT));
        getInventory().setItem(16, createItemStack(FuchsLanguageString.c("Entities", "entities").get(getFuchsPlayer()),Category.ENTITY));
        getInventory().setItem(19, createItemStack(FuchsLanguageString.c("Projektile", "projectiles").get(getFuchsPlayer()),Category.PROJECTILE));
        getInventory().setItem(20, createItemStack(FuchsLanguageString.c("Knete", "knead").get(getFuchsPlayer()),Category.KNEAD));
        getInventory().setItem(21, createItemStack(FuchsLanguageString.c("Blöcke", "blocks").get(getFuchsPlayer()),Category.BLOCK));
        getInventory().setItem(22, createItemStack(FuchsLanguageString.c("Waffen", "weapons").get(getFuchsPlayer()),Category.WEAPON));
        getInventory().setItem(23, createItemStack(FuchsLanguageString.c("Schwerter", "swords").get(getFuchsPlayer()),Category.SWORD));
        getInventory().setItem(24, createItemStack(FuchsLanguageString.c("Bögen", "bows").get(getFuchsPlayer()),Category.BOW));
        getInventory().setItem(25, createItemStack(FuchsLanguageString.c("Sensen", "scythes").get(getFuchsPlayer()),Category.SCYTHE));
        getInventory().setItem(28, createItemStack(FuchsLanguageString.c("Rüstung", "armor").get(getFuchsPlayer()),Category.ARMOR));
        getInventory().setItem(29, createItemStack(FuchsLanguageString.c("Admin Werkzeuge", "admin_tools").get(getFuchsPlayer()), Category.ADMIN));
        getInventory().setItem(30, createItemStack(FuchsLanguageString.c("Werkzeuge", "tools").get(getFuchsPlayer()), Category.TOOL));
        getInventory().setItem(31, createItemStack(FuchsLanguageString.c("Job Werkzeuge", "job_tools").get(getFuchsPlayer()),Category.JOB_TOOL));
        getInventory().setItem(32, createItemStack(FuchsLanguageString.c("Pflanzen", "plants").get(getFuchsPlayer()),Category.PLANT));
        getInventory().setItem(33, createItemStack(FuchsLanguageString.c("Dünger", "fertilizer").get(getFuchsPlayer()),Category.FERTILIZER));
        if(modListInventory != null) {
            getInventory().setItem(getSize() - 5, FuchsUtility.createInventoryStackWithTag(Material.PAPER, 1, FuchsLanguageString.c("Zurück", "back").get(getFuchsPlayer()), "back_inv", ""));
        }
    }

    @Override
    public void onItemClick(InventoryClickEvent event, ItemStack clickedItem) {
        if(FuchsUtility.isTopInventory(event)) {
            event.setCancelled(true);
            NBTTagCompound tag = FuchsUtility.getItemTag(clickedItem);
            if(tag.hasKey("category")) {
                ItemListInventory itemListInventory = InventoryList.itemList.copy();
                itemListInventory.setCategory(Category.valueOf(tag.getString("category")));
                itemListInventory.setCategoryInventory(this);
                itemListInventory.setFuchsRegistry(fuchsRegistry);
                itemListInventory.create(getPlayer());
                itemListInventory.openInventory();
            }
            if(tag.hasKey("back_inv")) {
                modListInventory.createAndOpen(getPlayer());
            }
        }
        super.onItemClick(event, clickedItem);
    }

    @Override
    public InventorySize getSizeEnum() {
        return InventorySize.FORTY_FIVE;
    }

    public ItemStack createItemStack(String name, Category category) {
        int modeldata = 0;
        Material material = Material.FEATHER;
        boolean isAll = category.equals(Category.ALL);
        if(fuchsRegistry == null) {
            for (FuchsRegistryObject<FuchsItem> value : FuchsRegistries.items.values()) {
                FuchsItem fuchsItem = value.copy();
                if((Arrays.asList(fuchsItem.getCategories()).contains(category) || isAll) && fuchsItem.isCategoryIcon()) {
                    if(fuchsItem.hasCustomModelData()) {
                        modeldata = fuchsItem.getCustomModelData();
                        material = fuchsItem.getMaterial();
                        break;
                    }
                }
            }
        } else {
            for (FuchsRegistryObject<? extends FuchsObject> object : fuchsRegistry.objects) {
                if(object.copy() instanceof FuchsItem fuchsItem) {
                    if((Arrays.asList(fuchsItem.getCategories()).contains(category) || isAll) && fuchsItem.isCategoryIcon()) {
                        if(fuchsItem.hasCustomModelData()) {
                            modeldata = fuchsItem.getCustomModelData();
                            material = fuchsItem.getMaterial();
                            break;
                        }
                    }
                }
            }
        }

        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setCustomModelData(modeldata);
        itemMeta.setDisplayName(name);
        for (ItemFlag value : ItemFlag.values()) {
            itemMeta.addItemFlags(value);
        }
        itemStack.setItemMeta(itemMeta);
        net.minecraft.world.item.ItemStack nms = FuchsUtility.createNMSCopy(itemStack);
        NBTTagCompound tag = FuchsUtility.getItemTag(nms);
        tag.setString("category", category.name());
        nms.setTag(tag);
        return CraftItemStack.asBukkitCopy(nms);
    }

    public enum Category {
        ALL, CURRENCY, FOOD, GAS, LIQUID, EFFECT, ENTITY, PROJECTILE, KNEAD, BLOCK, SWORD, ARMOR, ADMIN, BOW, TOOL, SCYTHE, WEAPON, JOB_TOOL, PLANT, FERTILIZER
    }


    @Override
    public FuchsLanguageString getDisplayName() {
        return FuchsLanguageString.c("Item Kategorien");
    }*/
}

