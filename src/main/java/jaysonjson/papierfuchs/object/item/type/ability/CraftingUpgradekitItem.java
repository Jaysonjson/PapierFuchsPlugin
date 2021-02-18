package jaysonjson.papierfuchs.object.item.type.ability;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.data.DataHandler;
import jaysonjson.papierfuchs.data.FuchsLocation;
import jaysonjson.papierfuchs.data.server.data.BlockMetadataSetter;
import jaysonjson.papierfuchs.data.server.data.EntityMetadataSetter;
import jaysonjson.papierfuchs.data.server.data.FuchsServer;
import jaysonjson.papierfuchs.object.BlockMetaData;
import jaysonjson.papierfuchs.object.EntityMetaData;
import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.item.FuchsItemData;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.item.interfaces.IItemUseType;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
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
        FuchsItemData oItem = new FuchsItemData(this, player);
        oItem.lore.add("Ändert einen Block zu einer Fuchs-Werkbank");
        oItem.lore.add("Item wird konsumiert,");
        oItem.lore.add("droppt aber wenn der Block wieder zerstört wird");
        oItem.setItem(ChatColor.GOLD + "Herstellungs Upgrade Kit");
        return oItem.item;
    }


    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        return tag;
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
        FuchsServer fuchsServer = DataHandler.loadServer();
        FuchsLocation fuchsLocation = new FuchsLocation(block.getLocation());
        fuchsServer.BLOCK_METADATA.add(new BlockMetadataSetter(fuchsLocation, block.getType(), BlockMetaData.GENERAL_CRAFTING_BLOCK, "s"));
        fuchsServer.BLOCK_METADATA.add(new BlockMetadataSetter(fuchsLocation, block.getType(), BlockMetaData.CONTAINED_ITEM, getID()));
        fuchsServer.BLOCK_METADATA.add(new BlockMetadataSetter(fuchsLocation, block.getType(), BlockMetaData.ARMOR_STAND_UUID, armorStand.getUniqueId().toString()));
        fuchsServer.ENTITY_METADATA.add(new EntityMetadataSetter(armorStand.getUniqueId(), EntityMetaData.HIT_ABLE, true));
        DataHandler.saveServer(fuchsServer);
        event.getItem().setAmount(event.getItem().getAmount() - 1);
    }

    @Override
    public int getCustomModelData() {
        return 24;
    }

}
