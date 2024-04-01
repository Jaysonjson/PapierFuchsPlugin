package jaysonjson.papierfuchs.paper.events.item;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.io.data.FuchsLocation;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.*;
import jaysonjson.papierfuchs.fuchs.object.BlockMetaData;
import jaysonjson.papierfuchs.fuchs.object.EntityMetaData;
import jaysonjson.papierfuchs.fuchs.object.intern.block.FuchsBlock;
import jaysonjson.papierfuchs.fuchs.object.intern.block.FuchsBlockArmorStand;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.MultiBlockData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.MultiStructureData;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.utility.References;
import jaysonjson.papierfuchs.old.inventories.crafting.general.GeneralCraftingInventory;
import net.minecraft.network.protocol.game.PacketPlayOutAnimation;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Light;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collection;

public class ItemUse implements Listener {


    @EventHandler
    public void itemDamageEvent(PlayerItemDamageEvent event) {
        if(FuchsUtility.isFuchsItem(event.getItem())) {
            if(!FuchsUtility.getFuchsItemFromNMS(event.getItem()).isVanillaOverride()) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void itemUseEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        if (block != null) {
            if (block.getType() != Material.AIR) {
                if (block.hasMetadata(BlockMetaData.GENERAL_CRAFTING_BLOCK) && !event.getPlayer().isSneaking() && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    GeneralCraftingInventory generalCraftingInventory = new GeneralCraftingInventory();
                    generalCraftingInventory.createPageData(player);
                    generalCraftingInventory.openInventory(player, 0);
                }
                /*if (player.isSneaking()) {
                    if (block.hasMetadata(BlockMetaData.PLACED_BLOCK_BARRIER) && block.hasMetadata(BlockMetaData.ARMOR_STAND_UUID)) {
                        PlayerInteractEntity.removeContainedItemArmorStand(player.getWorld().getEntity(UUID.fromString(block.getMetadata(BlockMetaData.ARMOR_STAND_UUID).get(0).asString())), player);
                        References.data.getWorld(player.getWorld()).getBlockMetadatas().removeIf(entity_metadatum -> entity_metadatum.location.equals(new FuchsLocation(block.getLocation())) && entity_metadatum.key.equals(BlockMetaData.PLACED_BLOCK_BARRIER));
                        References.data.getWorld(player.getWorld()).getBlockMetadatas().removeIf(entity_metadatum -> entity_metadatum.location.equals(new FuchsLocation(block.getLocation())) && entity_metadatum.key.equals(BlockMetaData.ARMOR_STAND_UUID));
                    }
                }*/
            }
        }

        if (event.getItem() != null) {
            ItemStack itemStack = event.getItem();
           /* if(Utility.isBannedItem(event.getItem())) {
                event.setCancelled(true);
            }*/
            if (itemStack.getType() != Material.AIR) {
                Action action = event.getAction();
                FuchsItem fuchsItem = FuchsUtility.getFuchsItemFromNMS(itemStack);
                FuchsMCItem mcItem = new FuchsMCItem(itemStack);
                if(itemStack.getType().equals(Material.BOW)) {
                    FuchsItemProjectileData projectileData = mcItem.projectileData().get();
                    if(projectileData.hasProjectile()) {
                        boolean hasAmmo = false;
                        for (ItemStack stack : player.getInventory().getContents()) {
                            FuchsMCItem mcItem1 = new FuchsMCItem(stack);
                            FuchsItemProjectileData projectileData1 = mcItem1.projectileData().get();
                            if(projectileData1.isProjectile()) {
                                if(projectileData.getContainedProjectile().equalsIgnoreCase(projectileData1.getProjectileId())) {
                                    hasAmmo = true;
                                }
                            }
                        }
                        if(!hasAmmo && !player.getGameMode().equals(GameMode.CREATIVE)) {
                            event.setCancelled(true);
                        }
                    }
                }
                if(fuchsItem != null) {
                    if(player.getOpenInventory().getType() == InventoryType.CRAFTING || player.getOpenInventory().getType() == InventoryType.CREATIVE) {
                        fuchsItem.onItemUse(event);
                        switch (action) {
                            case RIGHT_CLICK_BLOCK:
                                fuchsItem.onItemRightClickBlock(event);
                                fuchsItem.onItemRightClick(event);
                                if(fuchsItem.isFood()) {
                                    event.setCancelled(true);
                                }
                                if(fuchsItem.canBeItemFramePlaced()) {
                                    Location location = event.getClickedBlock().getLocation();
                                    location.add(event.getBlockFace().getDirection());
                                    ItemFrame frame = event.getPlayer().getWorld().spawn(location, ItemFrame.class);
                                    ItemStack itemStack1 = event.getItem().clone();
                                    event.getItem().setAmount(event.getItem().getAmount() - 1);
                                    ItemMeta itemMeta = itemStack1.getItemMeta();
                                    if(itemMeta != null) {
                                        itemMeta.setDisplayName("");
                                        itemStack1.setItemMeta(itemMeta);
                                    }
                                    frame.setCustomNameVisible(false);
                                    frame.setItem(itemStack1);
                                    frame.setVisible(false);
                                    //frame.setInvulnerable(true);
                                    FuchsUtility.addEntityMetadata(frame, EntityMetaData.ITEMFRAME_DELETE_ON_REMOVE, true);
                                }
                                break;
                            case LEFT_CLICK_BLOCK:
                                fuchsItem.onItemLeftClickBlock(event);
                                fuchsItem.onItemLeftClick(event);
                                if(fuchsItem.isSword() && player.getGameMode().equals(GameMode.CREATIVE)) {
                                    event.setCancelled(true);
                                }
                                break;
                            case LEFT_CLICK_AIR:
                                fuchsItem.onItemLeftClickAir(event);
                                fuchsItem.onItemLeftClick(event);
                                break;
                            case RIGHT_CLICK_AIR:
                                fuchsItem.onItemRightClickAir(event);
                                fuchsItem.onItemRightClick(event);
                                if(fuchsItem.canPutOnHead()) {
                                    if(player.getEquipment() == null || player.getEquipment().getHelmet() == null) {
                                        ItemStack hat = fuchsItem.createItem(player, itemStack);
                                        hat.setAmount(1);
                                        player.getEquipment().setHelmet(hat);
                                        itemStack.setAmount(itemStack.getAmount() - 1);
                                    }
                                }
                                break;
                            default:
                                break;
                        }
                    }

                    if (block != null && action.equals(Action.RIGHT_CLICK_BLOCK)) {
                        if (block.getType() != Material.AIR) {
                            fuchsItem.onBlockInteract(event);
                            placePlant(event, fuchsItem, player, block);
                            placeBlockNew(event, fuchsItem, player, block);
                        }
                    }
                }
            }
        }

        ItemStack mainHand = player.getEquipment().getItemInMainHand();
        ItemStack offHand = player.getEquipment().getItemInOffHand();
        if(FuchsUtility.isFuchsItem(mainHand)) {
            FuchsItem mainItem = FuchsUtility.getFuchsItemFromNMS(mainHand);
            if(mainItem.isDualItem()) {
                if(FuchsUtility.isFuchsItem(offHand)) {
                    if(mainItem.getDualItem().getID().equalsIgnoreCase(FuchsUtility.getFuchsItemFromNMS(offHand).getID())) {
                        if(event.getHand().equals(EquipmentSlot.OFF_HAND)) {
                            PacketPlayOutAnimation packet = new PacketPlayOutAnimation(((CraftPlayer) player).getHandle(), 3);
                            ((CraftPlayer) player).getHandle().b.sendPacket(packet);
                        }
                        /*PacketPlayOutAnimation packet2 = new PacketPlayOutAnimation(((CraftPlayer)player).getHandle(), 0);
                        ((CraftPlayer)player).getHandle().b.sendPacket(packet2);*/
                    } else {
                        event.setCancelled(true);
                    }
                } else {
                    event.setCancelled(true);
                }
            }
        }

    }

    @EventHandler
    public void onItemFrame(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof ItemFrame itemFrame) {
            if(itemFrame.hasMetadata(EntityMetaData.ITEMFRAME_DELETE_ON_REMOVE)) {
                boolean destroy = itemFrame.getMetadata(EntityMetaData.ITEMFRAME_DELETE_ON_REMOVE).get(0).asBoolean();
                if(destroy) {
                    event.getDamager().getWorld().dropItem(event.getDamager().getLocation(), itemFrame.getItem());
                    itemFrame.remove();
                }
            }
        }
    }

    public static void placePlant(PlayerInteractEvent event, FuchsItem fuchsItem, Player player, Block block) {
        if (fuchsItem.hasFruit()) {
            if (block.getType().equals(Material.DIRT) || block.getType().equals(Material.GRASS_BLOCK)) {
                for (Entity nearbyEntity : player.getWorld().getNearbyEntities(block.getLocation().clone().add(0, 1, 0), 1, 1, 1)) {
                    if(nearbyEntity instanceof ArmorStand) {
                        return;
                    }
                }
                FuchsMCItem mcItem = new FuchsMCItem(new ItemStack(Material.FEATHER));
                FuchsItemPlantData plantData = mcItem.plantData().get();
                FuchsItemGeneralData generalData = mcItem.generalData().get();
                plantData.fromFuchsItem(fuchsItem);
                generalData.setModeldata(plantData.getModelData());
                mcItem.plantData().set(plantData);
                mcItem.generalData().set(generalData);
                ArmorStand plantStand = player.getWorld().spawn(block.getLocation().add(0.5, 1, 0.5), ArmorStand.class);
                plantStand.setItem(EquipmentSlot.HEAD, mcItem.getItemStack());
                plantStand.setInvulnerable(true);
                plantStand.setInvisible(true);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        plantStand.setGravity(false);
                        plantStand.setCanMove(false);
                        plantStand.setCanTick(false);
                    }
                }.runTaskLater(PapierFuchs.INSTANCE, 5L);
                Location plantStandLocation = plantStand.getLocation();
                plantStandLocation.setYaw(PapierFuchs.random.nextInt(359) + PapierFuchs.random.nextFloat());
                plantStand.teleport(plantStandLocation);
                //FuchsUtility.addEntityMetadata(plantStand, EntityMetaData.ARMORSTAND_DONT_REMOVE_ITEM_ON_RIGHTCLICK, true);
                //FuchsUtility.addEntityMetadata(plantStand, EntityMetaData.IS_PLANT, true);
                References.data.getWorld(player.getWorld()).addPlant(plantStand);
                event.getItem().subtract();
            }
        }
    }

    public static void placeBlockNew(PlayerInteractEvent event, FuchsItem fuchsItem, Player player, Block block) {
        if(fuchsItem.asBlock() != null) {
            ItemStack itemStack = event.getItem();
            FuchsMCItem mcItem = new FuchsMCItem(itemStack);
            FuchsBlock fuchsBlock = fuchsItem.asBlock().copy();
            MultiStructureData structureData = new MultiStructureData();
            Collection<MultiBlockData> multiBlockDatas = new ArrayList<>();
            Location loc = event.getClickedBlock().getLocation();
            loc = loc.toVector().add(event.getBlockFace().getDirection()).toLocation(loc.getWorld());
            ArmorStand heart = player.getWorld().spawn(loc.clone().add(0.5, 0, 0.5), ArmorStand.class);
            heart.setInvulnerable(true);
            heart.setInvisible(true);
            heart.setHealth(fuchsBlock.getDefaultHealth());
            heart.setCustomNameVisible(false);
            heart.setCustomName(fuchsBlock.getDisplayName().get(References.data.getPlayer(player)));
            heart.setCanPickupItems(false);
            heart.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(fuchsBlock.getDefaultHealth());
            heart.setSmall(true);
            heart.setMarker(true);
            heart.setBasePlate(false);
            Vector direction = heart.getLocation().toVector().subtract(player.getEyeLocation().toVector()).normalize();
            Location location = heart.getLocation().clone();
            if(fuchsBlock.shouldPlaceRotate()) {
                rotateArmorStand(heart, player, direction, location);
            } else {
                heart.setGravity(false);
                heart.setCanMove(false);
                heart.setCanTick(false);
            }
            heart.teleport(location);
            FuchsItemBlockData blockData = mcItem.blockData().get();
            blockData.setLightLevel(fuchsBlock.getDefaultLightLevel());
            for (Vector barrierLocation : fuchsBlock.getBarrierLocations(heart.getLocation().clone())) {
                multiBlockDatas.add(new MultiBlockData(Material.BARRIER, heart.getLocation().clone().add(barrierLocation)));
            }
            for (Vector lightPlacement : fuchsBlock.getLightPlacements(heart.getLocation().clone())) {
                multiBlockDatas.add(new MultiBlockData(Material.LIGHT, heart.getLocation().clone().add(lightPlacement)));
            }
            mcItem.blockData().set(blockData);
            for (FuchsBlockArmorStand armorStand : fuchsBlock.getArmorStands()) {
                multiblockArmorStand(loc, mcItem, structureData, armorStand, multiBlockDatas, fuchsBlock, player);
            }
            ItemStack clonedStack = mcItem.getItemStack().clone();
            clonedStack.setAmount(1);
            structureData.setHeartItem(FuchsUtility.createItemStackString(clonedStack));
            structureData.setBlockId(fuchsItem.getID());
            ItemStack stack = itemStack.clone();
            itemStack.setAmount(0);
            BukkitTask task = new BukkitRunnable() {
                @Override
                public void run() {
                    if(!player.getGameMode().equals(GameMode.CREATIVE)) {
                        stack.subtract();
                    }
                    player.getEquipment().setItemInMainHand(stack);
                }
            }.runTaskLater(PapierFuchs.INSTANCE, 5L);
            if(!structureData.create(block.getWorld(), multiBlockDatas, heart, blockData)) {
                structureData.remove();
                task.cancel();
                player.getEquipment().setItemInMainHand(stack);
            }
        }
    }

    public static void multiblockArmorStand(Location loc, FuchsMCItem mcItem, MultiStructureData multiStructureData, FuchsBlockArmorStand fuchsBlockArmorStand, Collection<MultiBlockData> multiBlockDatas, FuchsBlock fuchsBlock, Player player) {
        ArmorStand armorStand = player.getWorld().spawn(loc.add(0.5, 0, 0.5), ArmorStand.class);
        FuchsItemGeneralData generalData = mcItem.generalData().get();
        generalData.setModeldata(fuchsBlockArmorStand.getModeldata());
        mcItem.generalData().set(generalData);
        ItemStack clonedStack = mcItem.getItemStack().clone();
        clonedStack.setAmount(1);
        armorStand.setItem(EquipmentSlot.HEAD, clonedStack);
        Block barrier = player.getWorld().getBlockAt(armorStand.getLocation());
        if(fuchsBlock.hasHitBox()) {
            barrier.setType(Material.BARRIER);
            multiBlockDatas.add(new MultiBlockData(barrier));
        }
        armorStand.setInvulnerable(true);
        armorStand.setArms(false);
        armorStand.setInvisible(true);
        armorStand.setCanPickupItems(false);
        if(fuchsBlock.isSmall()) {
            armorStand.setSmall(true);
        }
        Location location = armorStand.getLocation().clone();
        Vector direction = armorStand.getLocation().toVector().subtract(player.getEyeLocation().toVector()).normalize();
        if(fuchsBlock.shouldPlaceRotate()) {
            rotateArmorStand(armorStand, player, direction, location);
        } else {
            armorStand.setGravity(false);
            armorStand.setCanMove(false);
            armorStand.setCanTick(false);
        }
        location.add(fuchsBlockArmorStand.getVector());
        armorStand.teleport(location);
        armorStand.setRightArmPose(new EulerAngle(0f, 0f, 0f));
        armorStand.setLeftArmPose(new EulerAngle(0f, 0f, 0f));
        multiStructureData.getSlaves().add(armorStand.getUniqueId());
    }

    public static void rotateArmorStand(ArmorStand armorStand, Player player, Vector direction, Location location) {
        if (player.isSneaking()) {
            location.setYaw((float) (180 - Math.toDegrees(Math.atan2(direction.getX(), direction.getZ()))));
        } else {
            float yaw = (float) (180 - Math.toDegrees(Math.atan2(direction.getX(), direction.getZ())));
            if (yaw >= 0 && yaw <= 75) {
                location.setYaw(0);
            } else if (yaw >= 76 && yaw <= 160) {
                location.setYaw(90);
            } else if (yaw >= 161 && yaw <= 250) {
                location.setYaw(180);
            } else {
                location.setYaw(270);
            }
            location.setPitch((float) (90 - Math.toDegrees(Math.acos(direction.getY()))));
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                armorStand.setGravity(false);
                armorStand.setCanMove(false);
                armorStand.setCanTick(false);
                armorStand.teleport(location);
            }
        }.runTaskLater(PapierFuchs.INSTANCE, 5L);
    }

}
