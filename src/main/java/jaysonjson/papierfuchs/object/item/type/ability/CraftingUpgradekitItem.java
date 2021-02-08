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
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
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
        oItem.setItem(ChatColor.GOLD + "Herstellungs Upgrade Kit");
        oItem.lore.add("Ändert einen Block zu einer Fuchs-Werkbank");
        oItem.createNMSCopy();
        oItem.nmsCopy.setTag(getTag(oItem.getTagCompound()));
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
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
        FuchsServer fuchsServer = DataHandler.loadServer();
        fuchsServer.BLOCK_METADATA.add(new BlockMetadataSetter(new FuchsLocation(block.getLocation()), block.getType(), BlockMetaData.GENERAL_CRAFTING_BLOCK, "s"));
        DataHandler.saveServer(fuchsServer);
        event.getItem().setAmount(event.getItem().getAmount() - 1);
    }

    @Override
    public int getCustomModelData() {
        return 1;
    }

}
