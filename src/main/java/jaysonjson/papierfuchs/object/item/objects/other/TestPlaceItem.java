package jaysonjson.papierfuchs.object.item.objects.other;

import jaysonjson.papierfuchs.References;
import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.object.EntityMetaData;
import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.item.FuchsItemData;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.item.interfaces.IItemUseType;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

public class TestPlaceItem extends FuchsItem {

    public TestPlaceItem(String id, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        FuchsItemData oItem = new FuchsItemData(this, player);

        oItem.setItem(ChatColor.GRAY + "Test Block");
        return oItem.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT, true);
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        return tag;
    }

    @Override
    public void onBlockInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Location loc = event.getClickedBlock().getLocation();
        ArmorStand armorStand = player.getWorld().spawn(loc.add(0.5, 1, 0.5), ArmorStand.class);
        armorStand.setInvisible(true);
        armorStand.setInvulnerable(true);
        armorStand.setArms(true);
        armorStand.setRightArmPose(new EulerAngle(0f, 0f, 0f));
        armorStand.setLeftArmPose(new EulerAngle(0f, 0f, 0f));
        armorStand.setCustomNameVisible(false);
        armorStand.setItem(EquipmentSlot.HAND, createItem(event.getPlayer()));
        ItemStack itemStack = event.getItem().clone();
        itemStack.setAmount(1);
        event.getItem().setAmount(event.getItem().getAmount() - 1);
        Utility.addEntityMetadata(References.data.server, armorStand, EntityMetaData.ARMORSTAND_DONT_REMOVE_ITEM_ON_RIGHTCLICK, true);
        Utility.addEntityMetadata(References.data.server, armorStand, EntityMetaData.CONTAINED_ITEM, Utility.createItemStackString(itemStack));
    }
}
