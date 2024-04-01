package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.admin;

import jaysonjson.papierfuchs.fuchs.object.BlockMetaData;
import jaysonjson.papierfuchs.fuchs.object.EntityMetaData;
import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.FuchsItemCategory;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.ItemCategoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.InventoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.ChairVehicleInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.item.ItemCategoryInventoryOld;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.ChairVehicleData;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.RarityList;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.IItemUseType;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.UseTypeList;
import jaysonjson.papierfuchs.fuchs.utility.Colors;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class MakeVehicleStick extends FuchsItem {

    public MakeVehicleStick() {
        super("make_vehicle_stick", Material.STICK, UseTypeList.ADMIN.copy());
    }

    public MakeVehicleStick(String id, IItemUseType use) {
        super(id, Material.STICK, use);
    }

    @Override
    public FuchsRarity getDefaultRarity() {
        return RarityList.none.copy();
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return FuchsLanguageString.c("Erstell Fahrzeug");
    }

    @Override
    public void onItemRightClickBlock(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        Player player = event.getPlayer();
        if(player.isSneaking()) {
            if (!block.hasMetadata(BlockMetaData.CHAIR_DATA)) {
                float height = -0.75f;
                if(FuchsUtility.isStairName(block.getType())) {
                    height = -1.2f;
                }
                if(FuchsUtility.isSlabName(block.getType())) {
                    height = -1.3f;
                }
                ArmorStand armorStand = player.getWorld().spawn(block.getLocation().add(0.5, height, 0.5), ArmorStand.class);
                Location location = armorStand.getLocation();
                Vector direction = armorStand.getLocation().toVector().subtract(player.getEyeLocation().toVector()).normalize();
                location.setYaw((float) (180 - Math.toDegrees(Math.atan2(direction.getX(), direction.getZ()))));
                armorStand.teleport(location);
                armorStand.setInvisible(true);
                armorStand.setInvulnerable(true);
                armorStand.setGravity(false);
                FuchsUtility.addEntityMetadata(armorStand, EntityMetaData.IS_SIT, true);
                new ChairVehicleData(height, armorStand.getUniqueId()).encode(block);
            } else {
                //player.sendMessage(Colors.RED_WHITE + " Block ist bereits ein Sitz!");
                ChairVehicleInventory vehicleInventory = InventoryList.chairVehicle.copy();
                vehicleInventory.setBlock(block);
                vehicleInventory.setVehicleData(ChairVehicleData.decode(block));
                vehicleInventory.createAndOpen(player);
            }
        } else {
            if(block.hasMetadata(BlockMetaData.CHAIR_DATA)) {
                /*
                block.removeMetadata(BlockMetaData.CHAIR_DATA, PapierFuchs.INSTANCE);
                UUID uuid = UUID.fromString(block.getMetadata(BlockMetaData.ARMOR_STAND_UUID).get(0).toString());
                if(player.getWorld().getEntity(uuid) != null) {
                    player.getWorld().getEntity(uuid).remove();
                }
                block.removeMetadata(BlockMetaData.ARMOR_STAND_UUID, PapierFuchs.INSTANCE);*/
            } else {
                player.sendMessage(Colors.RED_WHITE + " Block ist kein Sitz!");
            }
        }
        super.onItemRightClickBlock(event);
    }

    @Override
    public boolean isAdminTool() {
        return true;
    }

    @Override
    public FuchsItemCategory[] getCategories() {
        return new FuchsItemCategory[] { ItemCategoryList.admin_tool.copy(), ItemCategoryList.tool.copy() };
    }
}
