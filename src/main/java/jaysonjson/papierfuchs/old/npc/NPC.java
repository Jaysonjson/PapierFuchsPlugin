package jaysonjson.papierfuchs.old.npc;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.io.data.FuchsLocation;
import jaysonjson.papierfuchs.fuchs.object.intern.npc.FuchsNPCData;
import jaysonjson.papierfuchs.fuchs.utility.References;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.game.PacketPlayOutEntityHeadRotation;
import net.minecraft.network.protocol.game.PacketPlayOutEntityMetadata;
import net.minecraft.network.protocol.game.PacketPlayOutNamedEntitySpawn;
import net.minecraft.network.protocol.game.PacketPlayOutPlayerInfo;
import net.minecraft.network.syncher.DataWatcher;
import net.minecraft.network.syncher.DataWatcherRegistry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.level.WorldServer;
import net.minecraft.server.network.PlayerConnection;
import net.minecraft.world.entity.EntityPose;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class NPC {

    public static ArrayList<FuchsNPCData> NPC = new ArrayList<>();


    public static FuchsNPCData createNPC(Player player, String name, String skinName, float yaw, float pitch, boolean corpse) {
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer world = ((CraftWorld) Bukkit.getWorld(player.getWorld().getName())).getHandle();
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), name);
        FuchsNPCData fuchsNPCData = new FuchsNPCData();
        if(skinName != null) {
            String[] textures = getTextureData(skinName);
            gameProfile.getProperties().put("textures", new Property("textures", textures[0], textures[1]));
            fuchsNPCData.setTexture(textures[0]);
            fuchsNPCData.setSignature(textures[1]);
        }
        EntityPlayer npc = new EntityPlayer(server, world, gameProfile);
        npc.setLocation(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), yaw, pitch);
        fuchsNPCData.setSkinName(skinName);
        fuchsNPCData.setName(name);
        fuchsNPCData.setLocation(new FuchsLocation(npc.getWorld().getWorld(), npc.locX(), npc.locY(), npc.locZ(), npc.getBukkitYaw(), npc.getXRot()));
        fuchsNPCData.setUuid(npc.getUniqueID());
        fuchsNPCData.setEntity(npc);
        fuchsNPCData.setCorpse(corpse);
        fuchsNPCData.setCorpseOwner(player.getUniqueId());
        if(name.equalsIgnoreCase("")) {
            npc.getBukkitEntity().setCustomNameVisible(false);
        }
        NPC.add(fuchsNPCData);
        References.data.server.fuchsNPCData.add(fuchsNPCData);
        sendPackets();
        return fuchsNPCData;
    }

    public static FuchsNPCData createNPC(Player player, String name, String skinName, boolean corpse) {
        return createNPC(player, name, skinName, player.getLocation().getYaw(), player.getLocation().getPitch(), corpse);
    }

    public static void sendPackets() {
        for (Player players : Bukkit.getOnlinePlayers()) {
            sendSinglePacket(players);
        }
    }

    public static void sendSinglePacket(Player player) {
        for (FuchsNPCData npc : NPC) {
            PlayerConnection connection = ((CraftPlayer) player).getHandle().b;
            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, npc.getEntity()));
            connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc.getEntity()));
            connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc.getEntity(), (byte) (npc.getEntity().getXRot() * 256 / 360)));
            new BukkitRunnable() {
                @Override
                public void run() {
                    connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.e, npc.getEntity()));
                }
            }.runTaskLater(PapierFuchs.INSTANCE, 10);
            if(npc.isCorpse()) {
                Location l = npc.getEntity().getBukkitEntity().getLocation();
                BlockPosition b = new BlockPosition(l.getBlockX(), l.getBlockY(), l.getBlockZ());
                DataWatcher watcher = npc.getEntity().getDataWatcher();
                watcher.set(DataWatcherRegistry.s.a(6), EntityPose.c);
                watcher.set(DataWatcherRegistry.m.a(13), Optional.of(b));
                connection.sendPacket(new PacketPlayOutEntityMetadata(npc.getEntity().getId(), watcher, true));
                System.out.println(npc.getEntity().getBukkitEntity().getLocation());
                npc.getEntity().getBukkitEntity().teleport(npc.getEntity().getBukkitEntity().getLocation().subtract(0, 0.5d, 0));
                System.out.println(npc.getEntity().getBukkitEntity().getLocation());
            }
        }
    }

    public static String[] getTextureData(String playerName) {
        try {
            URL apiURL = new URL("https://api.mojang.com/users/profiles/minecraft/" + playerName);
            InputStreamReader apiReader = new InputStreamReader(apiURL.openStream());
            String uuid = new JsonParser().parse(apiReader).getAsJsonObject().get("id").getAsString();
            URL sessionURL = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false");
            InputStreamReader sessionReader = new InputStreamReader(sessionURL.openStream());
            JsonObject properties = new JsonParser().parse(sessionReader).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
            String texture = properties.get("value").getAsString();
            String signature = properties.get("signature").getAsString();
            return new String[] {texture,signature};
        } catch (Exception exception) {
            exception.printStackTrace();
            return new String[] {"noData", "noData"};
        }
    }

    public static void loadNPCS() {
        for (FuchsNPCData FuchsNPCData : References.data.server.fuchsNPCData) {
            MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
            WorldServer world = ((CraftWorld) FuchsNPCData.getLocation().getWorld()).getHandle();
            GameProfile gameProfile = new GameProfile(FuchsNPCData.getUuid(), FuchsNPCData.getName());
            EntityPlayer npc = new EntityPlayer(server, world, gameProfile);
            npc.setLocation(FuchsNPCData.getLocation().getX(), FuchsNPCData.getLocation().getY(), FuchsNPCData.getLocation().getZ(), FuchsNPCData.getLocation().getYaw(), FuchsNPCData.getLocation().getPitch());
            if(FuchsNPCData.getSkinName() != null) {
                gameProfile.getProperties().put("textures", new Property("textures", FuchsNPCData.getTexture(), FuchsNPCData.getSignature()));
            }
            FuchsNPCData.setEntity(npc);
            NPC.add(FuchsNPCData);
        }
    }
}
