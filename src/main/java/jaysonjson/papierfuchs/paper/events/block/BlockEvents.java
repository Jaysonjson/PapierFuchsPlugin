package jaysonjson.papierfuchs.paper.events.block;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.io.data.FuchsLocation;
import jaysonjson.papierfuchs.fuchs.io.data.drop.obj.FuchsBlockDrop;
import jaysonjson.papierfuchs.fuchs.io.data.drop.obj.ItemDropChance;
import jaysonjson.papierfuchs.fuchs.io.data.server.data.ByteFuchsWorld;
import jaysonjson.papierfuchs.fuchs.io.data.server.data.FWBlockChange;
import jaysonjson.papierfuchs.fuchs.io.data.server.data.FuchsWorld;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemBlockData;
import jaysonjson.papierfuchs.fuchs.object.BlockMetaData;
import jaysonjson.papierfuchs.fuchs.object.EntityMetaData;
import jaysonjson.papierfuchs.fuchs.object.intern.block.FuchsBlock;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.MultiStructureData;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistryObject;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.utility.References;
import jaysonjson.papierfuchs.paper.events.entity.player.PlayerInteractEntity;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class BlockEvents implements Listener {

    @EventHandler
    public void blockBreakEvent(BlockBreakEvent event) {
        Random random = new Random();
        Player player = event.getPlayer();
        Block block = event.getBlock();
        World world = block.getWorld();
        FuchsWorld fuchsWorld = References.data.getWorld(world);
        ByteFuchsWorld byteFuchsWorld = References.data.getByteWorld(world);

        byteFuchsWorld.getBlockChanges().add(new FWBlockChange(player, block, FWBlockChange.ChangeType.REMOVE, FWBlockChange.ChangeCause.BREAK));

        if(block.hasMetadata(BlockMetaData.CONTAINED_ITEM)) {
            String itemID = block.getMetadata(BlockMetaData.CONTAINED_ITEM).get(0).asString();
            if(FuchsUtility.itemIDExists(itemID)) {
                world.dropItemNaturally(block.getLocation(), FuchsUtility.getFuchsItemByID(itemID).createItem(event.getPlayer()));
            } else {
                world.dropItemNaturally(block.getLocation(), FuchsUtility.generateItemStack(itemID));
            }
            block.removeMetadata(BlockMetaData.CONTAINED_ITEM, PapierFuchs.INSTANCE);
        }

        if(block.hasMetadata(BlockMetaData.GENERAL_CRAFTING_BLOCK)) {
            block.removeMetadata(BlockMetaData.GENERAL_CRAFTING_BLOCK, PapierFuchs.INSTANCE);
            if(block.hasMetadata(BlockMetaData.ARMOR_STAND_UUID)) {
                String uuid = block.getMetadata(BlockMetaData.ARMOR_STAND_UUID).get(0).asString();
                Entity entity = world.getEntity(UUID.fromString(uuid));
                if(entity != null) {
                    entity.remove();
                }
                block.removeMetadata(BlockMetaData.ARMOR_STAND_UUID, PapierFuchs.INSTANCE);
            }
        }

        if(player.getGameMode() != GameMode.CREATIVE) {
            for (FuchsBlockDrop fuchsBlockDrop : References.drops.getBlockDrops()) {
                if (fuchsBlockDrop.material.equals(block.getType())) {
                    for (ItemDropChance itemDropChance : fuchsBlockDrop.items) {
                        if (random.nextInt(itemDropChance.getChance()) == 1) {
                            ItemStack itemStack = itemDropChance.getFuchsItem().createItem(player);
                            itemStack.setAmount(ThreadLocalRandom.current().nextInt(itemDropChance.min_amount, itemDropChance.max_amount + 1));
                            world.dropItemNaturally(block.getLocation(), itemStack);
                        }
                    }
                }
            }
        }

        if(FuchsUtility.isFuchsItem(player.getEquipment().getItemInMainHand())) {
            FuchsItem fuchsItem = FuchsUtility.getFuchsItemFromNMS(player.getEquipment().getItemInMainHand());
            FuchsMCItem mcItem = new FuchsMCItem(player.getEquipment().getItemInMainHand());
            player.getEquipment().setItemInMainHand(FuchsUtility.damageFuchsItem(player, mcItem));
            fuchsItem.onBlockBreak(event);
        }

        if(block.hasMetadata(BlockMetaData.ARMOR_STAND_UUID)) {
            UUID uuid = UUID.fromString(block.getMetadata(BlockMetaData.ARMOR_STAND_UUID).get(0).asString());
            if(world.getEntity(uuid) != null && world.getEntity(uuid) instanceof ArmorStand armorStand) {
                armorStand.remove();
            }
        }

        damageFuchsBlock(block, world, byteFuchsWorld, player);

    }

    @EventHandler
    public void blockPlaceEvent(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        ByteFuchsWorld byteFuchsWorld = References.data.getByteWorld(player.getWorld());
        byteFuchsWorld.getBlockChanges().add(new FWBlockChange(player, block, FWBlockChange.ChangeType.ADD, FWBlockChange.ChangeCause.PLACE));
    }


    @EventHandler
    public void blockExplosion(BlockExplodeEvent event) {
        Block block = event.getBlock();
        ByteFuchsWorld byteFuchsWorld = References.data.getByteWorld(block.getWorld());
        byteFuchsWorld.getBlockChanges().add(new FWBlockChange(null, block, FWBlockChange.ChangeType.REMOVE, FWBlockChange.ChangeCause.EXPLOSION));
    }

    @EventHandler
    public void blockDamage(BlockDamageEvent event) {
        Block block = event.getBlock();
        World world = event.getBlock().getWorld();
        Player player = event.getPlayer();
        FuchsWorld fuchsWorld = References.data.getWorld(world);
        ByteFuchsWorld byteFuchsWorld = References.data.getByteWorld(world);
        damageFuchsBlock(block, world, byteFuchsWorld, player);
    }

    public void damageFuchsBlock(Block block, World world, ByteFuchsWorld byteFuchsWorld, Player player) {
        if(block.hasMetadata(BlockMetaData.MULTIBLOCK)) {
            UUID uuid = UUID.fromString(block.getMetadata(BlockMetaData.MULTIBLOCK).get(0).asString());
            MultiStructureData multiStructure = byteFuchsWorld.getMultiStructure(uuid);
            if(multiStructure != null) {
                if(world.getEntity(multiStructure.getHeart()) instanceof ArmorStand armorStand) {
                    FuchsMCItem mcItem = new FuchsMCItem(armorStand.getItem(EquipmentSlot.HEAD));
                    FuchsItemBlockData blockData = mcItem.blockData().get();
                    ItemStack itemStack = player.getInventory().getItemInMainHand();
                    double damage = 0.01;
                    if(FuchsUtility.isFuchsItem(itemStack)) {
                        FuchsItem fuchsItem = FuchsUtility.getFuchsItemFromNMS(itemStack);
                        if (fuchsItem.getToolType().equals(blockData.getEffectiveTool())) {
                            damage = fuchsItem.getToolBlockDamage();
                        }
                    } else {
                        if (FuchsItemBlockData.EffectiveTool.fromMaterial(itemStack.getType()).equals(blockData.getEffectiveTool())) {
                            damage = 2;
                        }
                    }
                    if(damage >= armorStand.getHealth() || player.getGameMode().equals(GameMode.CREATIVE)) {
                        if(!player.getGameMode().equals(GameMode.CREATIVE)) {
                            FuchsBlock fuchsBlock = FuchsUtility.getFuchsItemByID(multiStructure.getBlockId()).asBlock().copy();
                            for (FuchsRegistryObject<? extends FuchsItem> drop : fuchsBlock.getDrops()) {
                                world.dropItemNaturally(armorStand.getLocation(), drop.copy().createItem(player));
                            }
                            if (fuchsBlock.dropSelf()) {
                                if (!multiStructure.getHeartItem().equalsIgnoreCase("")) {
                                    world.dropItemNaturally(armorStand.getLocation(), FuchsUtility.generateItemStack(multiStructure.getHeartItem()));
                                }
                            }
                        }
                        multiStructure.remove();
                    } else {
                        //armorStand.damage(damage, player);
                        armorStand.setHealth(armorStand.getHealth() - damage);
                    }
                    player.sendActionBar(Component.text(ChatColor.AQUA + " " + armorStand.getCustomName() + ChatColor.LIGHT_PURPLE + ": " + FuchsUtility.formatDouble(armorStand.getHealth()) + ChatColor.GREEN + " / " + ChatColor.LIGHT_PURPLE + FuchsUtility.formatDouble(armorStand.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue())));
                } else {
                    multiStructure.remove();
                }
            }
        }
    }
}
