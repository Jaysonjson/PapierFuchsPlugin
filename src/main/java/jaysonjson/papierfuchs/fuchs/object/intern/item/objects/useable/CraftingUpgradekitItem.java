package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.useable;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.io.data.FuchsLocation;
import jaysonjson.papierfuchs.fuchs.object.BlockMetaData;
import jaysonjson.papierfuchs.fuchs.object.EntityMetaData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsItemData;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.IItemUseType;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class CraftingUpgradekitItem extends FuchsItem {

    public CraftingUpgradekitItem(String id, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        FuchsItemData oItem = new FuchsItemData(this, player, stack);
        oItem.addToLore("Ändert einen Block zu einer Fuchs-Werkbank");
        oItem.addToLore("Item wird konsumiert,");
        oItem.addToLore("droppt aber wenn der Block wieder zerstört wird");
        oItem.setItem(ChatColor.GOLD + "Herstellungs Upgrade Kit");
        return oItem.item;
    }

    @Override
    public void onBlockInteract(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        block.setMetadata(BlockMetaData.GENERAL_CRAFTING_BLOCK, new FixedMetadataValue(PapierFuchs.INSTANCE, "s"));
        block.setMetadata(BlockMetaData.CONTAINED_ITEM, new FixedMetadataValue(PapierFuchs.INSTANCE, getID()));
        Location location = block.getLocation();
        location.add(0.5, 0, 0.5);
        location.subtract(0, 0.5, 0);
        ArmorStand armorStand = block.getWorld().spawn(location, ArmorStand.class);
        armorStand.setCustomName("Crafting");
        armorStand.setInvisible(true);
        armorStand.setCustomNameVisible(true);
        armorStand.setMetadata(EntityMetaData.HIT_ABLE, new FixedMetadataValue(PapierFuchs.INSTANCE, true));
        block.setMetadata(BlockMetaData.ARMOR_STAND_UUID, new FixedMetadataValue(PapierFuchs.INSTANCE, armorStand.getUniqueId().toString()));
        FuchsLocation fuchsLocation = new FuchsLocation(block.getLocation());
        //fuchsServer.ENTITY_METADATA.add(new EntityMetadataSetter(armorStand.getUniqueId(), EntityMetaData.HIT_ABLE, true));
        FuchsUtility.addBlockMetadata(fuchsLocation, block, BlockMetaData.GENERAL_CRAFTING_BLOCK, "");
        FuchsUtility.addBlockMetadata(fuchsLocation, block, BlockMetaData.CONTAINED_ITEM, getID());
        FuchsUtility.addBlockMetadata(fuchsLocation, block, BlockMetaData.ARMOR_STAND_UUID, armorStand.getUniqueId().toString());
        event.getItem().setAmount(event.getItem().getAmount() - 1);
    }

    @Override
    public int getCustomModelData() {
        return 24;
    }

}
