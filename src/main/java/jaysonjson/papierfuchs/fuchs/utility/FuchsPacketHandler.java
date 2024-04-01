package jaysonjson.papierfuchs.fuchs.utility;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.object.intern.npc.FuchsNPCData;
import jaysonjson.papierfuchs.fuchs.object.EntityMetaData;
import jaysonjson.papierfuchs.fuchs.object.intern.npc.FuchsNPC;
import jaysonjson.papierfuchs.old.npc.NPC;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.PacketPlayInUseEntity;
import net.minecraft.world.EnumHand;
import net.minecraft.world.entity.Entity;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

public class FuchsPacketHandler {

    private final String INJECTOR = "FuchsPacketInject";
    final Player player;
    final CraftPlayer craftPlayer;
    private static FuchsPacketHandler INSTANCE;
    public FuchsPacketHandler(Player player) {
        this.player = player;
        this.craftPlayer = (CraftPlayer) player;
        INSTANCE = this;
    }

    public void add() {
        craftPlayer.getHandle().b.a.k.pipeline().addAfter("decoder", INJECTOR, new MessageToMessageDecoder<Packet<?>>() {
            @Override
            protected void decode(ChannelHandlerContext channelHandlerContext, Packet<?> packet, List<Object> list) {
                try {
                    list.add(packet);
                    read(packet);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void read(Packet<?> packet) {
        /*if(packet.getClass().equals(PacketPlayInUseEntity.class)) {
            PacketPlayInUseEntity.b actionType = ((PacketPlayInUseEntity) packet).getActionType();
            PacketPlayInUseEntity packetPlayInUseEntity = (PacketPlayInUseEntity) packet;
            //System.out.println(actionType);
            if (value(packet, "a") != null) {
                int id = (int) value(packet, "a");
                Bukkit.getScheduler().scheduleSyncDelayedTask(PapierFuchs.INSTANCE, () -> {
                    Entity entity = ((CraftWorld) player.getWorld()).getHandle().getEntity(id);
                    org.bukkit.entity.Entity bukkitEntity = null;
                    if (entity != null) {
                        bukkitEntity = entity.getBukkitEntity();
                    }
                    if (bukkitEntity != null) {
                        if (bukkitEntity.hasMetadata(EntityMetaData.NPC_ID)) {
                            String npcId = bukkitEntity.getMetadata(EntityMetaData.NPC_ID).get(0).asString();
                            FuchsNPC fuchsNPC = FuchsUtility.getNPCByID(npcId);
                            if (fuchsNPC != null) {
                                fuchsNPC.onInteract();
                            }
                        }
                    }
                    for (FuchsNPCData fuchsNPCData : NPC.NPC) {
                        if (fuchsNPCData.getEntity().getBukkitEntity().getEntityId() == id) {
                            int i = 0;
                            //PacketPlayInUseEntity packetPlayInUseEntity = (PacketPlayInUseEntity) Objects.requireNonNull(value(packet, "d"));
                            /*PacketPlayInUseEntity.b b = (PacketPlayInUseEntity.b) value(packet, "b");
                            switch (b) {
                                case a -> {
                                    System.out.println("a");
                                }
                                case b -> {
                                    System.out.println("b");
                                }
                                case c -> {
                                    System.out.println("c");
                                }
                            }*/
                    /*

                    switch ((PacketPlayInUseEntity.b) Objects.requireNonNull(value(packet, "action"))) {
                        case INTERACT: {
                            if (hand.equals(EnumHand.MAIN_HAND)) {
                                i++;
                                //SICHERZUGEHEN
                                if (i == 1) {
                                    if (fuchsNPCData.isCorpse() && fuchsNPCData.getCorpseOwner().equals(player.getUniqueId())) {
                                        new BukkitRunnable() {
                                            @Override
                                            public void run() {
                                                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                                                    ((CraftPlayer) onlinePlayer).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(fuchsNPCData.getEntity().getId()));
                                                }
                                                if (!fuchsNPCData.getCorpseInventory().equalsIgnoreCase("")) {
                                                    ItemStack[] itemStacks = FuchsUtility.generateInventoryContent(fuchsNPCData.getCorpseInventory());
                                                    for (ItemStack itemStack : itemStacks) {
                                                        player.getWorld().dropItemNaturally(fuchsNPCData.getEntity().getBukkitEntity().getLocation(), itemStack);
                                                    }
                                                }
                                                fuchsNPCData.setCorpseInventory("");
                                                NPC.NPC.remove(fuchsNPCData);
                                                References.data.saveServer();
                                            }
                                        }.runTaskLater(PapierFuchs.INSTANCE, 10L);
                                        //fuchsNPC.getEntity().getBukkitEntity().remove();
                                    }
                                    if (player.getEquipment() != null) {
                                        if (FuchsUtility.isFuchsItem(player.getEquipment().getItemInMainHand())) {
                                            FuchsItem fuchsItem = FuchsUtility.getFuchsItemFromNMS(player.getEquipment().getItemInMainHand());
                                            fuchsItem.onNPCInteract((PacketPlayInUseEntity) packet, player, entity, fuchsNPCData);
                                        }
                                    }
                                }
                            }
                        }
                        break;
                        default:
                            break;
                    }
                        }
                    }
                });
            }
        }*/
    }

    public static void remove(Player player) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        if(craftPlayer.getHandle().b.a.k.pipeline().get(INSTANCE.INJECTOR) != null) {
            craftPlayer.getHandle().b.a.k.pipeline().remove(INSTANCE.INJECTOR);
        }
    }

    @Nullable
    private Object value(Object clazz, String fieldname) {
        try {
            Field field = clazz.getClass().getDeclaredField(fieldname);
            field.setAccessible(true);
            return field.get(clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
