package jaysonjson.papierfuchs.npc;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import jaysonjson.papierfuchs.data.server.data.FuchsServer;
import jaysonjson.papierfuchs.data.server.obj.FuchsNPC;
import jaysonjson.papierfuchs.data.DataHandler;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

public class NPC {

    public static ArrayList<EntityPlayer> NPC = new ArrayList<>();


    public static void createNPC(Player player, String name, String skinName) {
        FuchsServer fuchsServer = DataHandler.loadServer();
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer world = ((CraftWorld) Bukkit.getWorld(player.getWorld().getName())).getHandle();
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), name);
        FuchsNPC fuchsNPC = new FuchsNPC();
        if(skinName != null) {
            String[] textures = getTextureData(skinName);
            gameProfile.getProperties().put("textures", new Property("textures", textures[0], textures[1]));
            fuchsNPC.texture = textures[0];
            fuchsNPC.signature = textures[1];
        }
        EntityPlayer npc = new EntityPlayer(server, world, gameProfile, new PlayerInteractManager(world));
        npc.setLocation(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch());
        fuchsNPC.skinName = skinName;
        fuchsNPC.name = name;
        fuchsNPC.pitch = npc.pitch;
        fuchsNPC.world = npc.getWorld().getWorld().getName();
        fuchsNPC.yaw = npc.yaw;
        fuchsNPC.x = (int) npc.locX();
        fuchsNPC.y = (int) npc.locY();
        fuchsNPC.z = (int) npc.locZ();
        fuchsNPC.uuid = npc.getUniqueID();
        NPC.add(npc);
        fuchsServer.fuchsNPC.add(fuchsNPC);
        DataHandler.saveServer(fuchsServer);
        sendPackets();
    }

    public static void sendPackets() {
        for (EntityPlayer npc : NPC) {
            for (Player players : Bukkit.getOnlinePlayers()) {
                PlayerConnection connection = ((CraftPlayer) players).getHandle().playerConnection;
                connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
                connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
                connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
            }
        }
    }

    public static void sendSinglePacket(Player player) {
        for (EntityPlayer npc : NPC) {
            PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
            connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
            connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
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
        FuchsServer fuchsServer = DataHandler.loadServer();
        for (FuchsNPC FuchsNPC : fuchsServer.fuchsNPC) {
            MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
            WorldServer world = ((CraftWorld) Bukkit.getWorld(FuchsNPC.world)).getHandle();
            GameProfile gameProfile = new GameProfile(FuchsNPC.uuid, FuchsNPC.name);;
            EntityPlayer npc = new EntityPlayer(server, world, gameProfile, new PlayerInteractManager(world));
            npc.setLocation(FuchsNPC.x, FuchsNPC.y, FuchsNPC.z, FuchsNPC.yaw, FuchsNPC.pitch);
            if(FuchsNPC.skinName != null) {
                gameProfile.getProperties().put("textures", new Property("textures", FuchsNPC.texture, FuchsNPC.signature));
            }
            NPC.add(npc);
            DataHandler.saveServer(fuchsServer);
        }
    }

}
