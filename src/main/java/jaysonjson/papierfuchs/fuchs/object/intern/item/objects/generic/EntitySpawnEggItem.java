package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.generic;

import jaysonjson.papierfuchs.fuchs.object.intern.category.item.FuchsItemCategory;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.ItemCategoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.entity.FuchsEntity;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.item.ItemCategoryInventoryOld;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.ItemNBT;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.RarityList;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.IItemUseType;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsItemData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class EntitySpawnEggItem extends FuchsItem {
    String containedEntity = "";
    public EntitySpawnEggItem(String id, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        FuchsItemData fuchsItemData = new FuchsItemData(this, player, stack);
        if(fuchsItemData.fuchsMCItem != null) {
            containedEntity = fuchsItemData.fuchsMCItem.getStringFromTag(ItemNBT.CONTAINED_ENTITY);
            fuchsItemData.addToLore(fuchsItemData.fuchsMCItem.getStringFromTag(ItemNBT.CONTAINED_ENTITY));
        }
        fuchsItemData.setItem("Entity Spawnegg");
        return fuchsItemData.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        if(!tag.hasKey(ItemNBT.CONTAINED_ENTITY)) {
            tag.setString(ItemNBT.CONTAINED_ENTITY, containedEntity);
        }
        return super.getTag(tag);
    }

    @Override
    public void onItemRightClick(PlayerInteractEvent event) {
        FuchsMCItem fuchsMCItem = new FuchsMCItem(this, event.getPlayer(), event.getItem());
        FuchsEntity<?> fuchsEntity = FuchsUtility.getEntityByID(fuchsMCItem.getStringFromTag(ItemNBT.CONTAINED_ENTITY));
        if(fuchsEntity != null) {
            if(event.getInteractionPoint() != null) {
                ((CraftWorld) event.getPlayer().getWorld()).getHandle().addEntity(fuchsEntity.create(((CraftWorld) event.getPlayer().getWorld()).getHandle(), event.getPlayer(), event.getInteractionPoint()));
            }
        }
    }

    @Override
    public boolean showInItemList() {
        return false;
    }

    @Override
    public FuchsRarity getDefaultRarity() {
        return RarityList.unfindable.copy();
    }

    @Override
    public FuchsItemCategory[] getCategories() {
        return new FuchsItemCategory[] { ItemCategoryList.entity.copy() };
    }

}
