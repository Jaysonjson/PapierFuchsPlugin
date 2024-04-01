package jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.item;

import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemGeneralData;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.ids.FIGProperty;
import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.FuchsItemCategory;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.ItemCategoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.FuchsInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.InventoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.generic.ListInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.lists.FuchsMaterial;
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
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class ItemCategoryInventory extends ListInventory {

    private FuchsRegistry fuchsRegistry;
    private ItemModListInventory modListInventory;
    public ItemCategoryInventory(String id) {
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

    @Nullable
    @Override
    public FuchsInventory getLastInventory() {
        if(modListInventory != null) {
            modListInventory.createAndOpen(getPlayer());
        }
        return modListInventory;
    }

    @Override
    public ArrayList<ItemStack> getStacks() {
        ArrayList<ItemStack> itemStacks = new ArrayList<>();
        itemStacks.add(createItemStack(ItemCategoryList.all.copy()));
        for (FuchsRegistryObject<FuchsItemCategory> value : FuchsRegistries.itemCategories.values()) {
            FuchsItemCategory category = value.copy();
            if(!category.getID().equals(ItemCategoryList.all.copy().getID())) {
                if (category.getIcon() != null) {
                    itemStacks.add(createItemStack(category, category.getIcon().copy().getMaterial(), category.getIcon().copy().getCustomModelData()));
                } else {
                    itemStacks.add(createItemStack(category));
                }
            }
        }
        return itemStacks;
    }

    public ItemStack createItemStack(FuchsItemCategory category, Material material, int modeldata) {
        FuchsMCItem mcItem = new FuchsMCItem(new ItemStack(material));
        FuchsItemGeneralData generalData = new FuchsItemGeneralData();
        generalData.setProperty(FIGProperty.SHOULD_NOT_UPDATE, true);
        mcItem.generalData().set(generalData);
        ItemStack itemStack = mcItem.getItemStack();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setCustomModelData(modeldata);
        itemMeta.setDisplayName(category.getDisplayName().get(getFuchsPlayer()));
        for (ItemFlag value : ItemFlag.values()) {
            itemMeta.addItemFlags(value);
        }
        itemStack.setItemMeta(itemMeta);
        net.minecraft.world.item.ItemStack nms = FuchsUtility.createNMSCopy(itemStack);
        NBTTagCompound tag = FuchsUtility.getItemTag(nms);
        tag.setString("category", category.getID());
        nms.setTag(tag);
        return CraftItemStack.asBukkitCopy(nms);
    }

    public ItemStack createItemStack(FuchsItemCategory category) {
        int modeldata = FuchsMaterial.FEATHER.getFallbackModelData();
        Material material = Material.FEATHER;
        boolean isAll = category.getID().equals(ItemCategoryList.all.getID());
        if(fuchsRegistry == null) {
            for (FuchsRegistryObject<FuchsItem> value : FuchsRegistries.items.values()) {
                FuchsItem fuchsItem = value.copy();
                if (fuchsItem.isCategoryIcon()) {
                    for (FuchsItemCategory fuchsItemCategory : value.copy().getCategories()) {
                        if (fuchsItemCategory.getID().equalsIgnoreCase(category.getID()) || isAll) {
                            if (fuchsItem.hasCustomModelData()) {
                                modeldata = fuchsItem.getCustomModelData();
                                material = fuchsItem.getMaterial();
                                break;
                            }
                        }
                    }
                }
            }
        } else {
            for (FuchsRegistryObject<? extends FuchsObject> object : fuchsRegistry.objects) {
                if(object.copy() instanceof FuchsItem fuchsItem) {
                    if (fuchsItem.isCategoryIcon()) {
                        for (FuchsItemCategory fuchsItemCategory : fuchsItem.getCategories()) {
                            if (fuchsItemCategory.getID().equalsIgnoreCase(category.getID()) || isAll) {
                                if (fuchsItem.hasCustomModelData()) {
                                    modeldata = fuchsItem.getCustomModelData();
                                    material = fuchsItem.getMaterial();
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return createItemStack(category, material, modeldata);
    }


    @Override
    public void onItemClick(InventoryClickEvent event, ItemStack clickedItem) {
        if(FuchsUtility.isTopInventory(event)) {
            event.setCancelled(true);
            NBTTagCompound tag = FuchsUtility.getItemTag(clickedItem);
            if(tag.hasKey("category")) {
                ItemListInventory itemListInventory = InventoryList.itemList.copy();
                itemListInventory.setCategory(FuchsRegistries.itemCategories.get(tag.getString("category")).copy());
                itemListInventory.setCategoryInventory(this);
                itemListInventory.setFuchsRegistry(fuchsRegistry);
                itemListInventory.create(getPlayer());
                itemListInventory.openInventory();
            }
        }
        super.onItemClick(event, clickedItem);
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        if(fuchsRegistry == null) {
            return FuchsLanguageString.c("Items: alles");
        }
        return FuchsLanguageString.c("Items: " + fuchsRegistry.getFuchsPlugin().getRegistryID());
    }
}
