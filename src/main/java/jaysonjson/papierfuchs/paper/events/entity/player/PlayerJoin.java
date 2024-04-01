package jaysonjson.papierfuchs.paper.events.entity.player;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.io.data.area.data.FuchsArea;
import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.utility.FuchsPacketHandler;
import jaysonjson.papierfuchs.fuchs.utility.References;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.old.npc.NPC;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class PlayerJoin implements Listener {

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(!References.data.players.containsKey(player.getUniqueId())) {
            References.data.savePlayer(new FuchsPlayer(player.getUniqueId()));
        }
        FuchsPlayer fuchsPlayer = References.data.getPlayer(player.getUniqueId());
        fuchsPlayer.setDisplayName(player.getDisplayName());
        fuchsPlayer.setPlayerName(player.getName());
        fuchsPlayer.setPlayer(player);

        if(fuchsPlayer.getTempBan().isBanned()) {
            player.kick(Component.text(fuchsPlayer.getTempBan().getBanText()));
        }

        FuchsUtility.refreshHearts(event.getPlayer(), fuchsPlayer);
        /*if(!fuchsPlayer.getSettings().isHideScoreboard()) {
            FuchsScoreboard.createScoreboard(player);
        }*/
        for (FuchsLanguageString pendingMessage : fuchsPlayer.getPendingMessages()) {
            player.sendMessage(Component.text(pendingMessage.get(fuchsPlayer)));
        }
        fuchsPlayer.getPendingMessages().clear();
        NPC.sendSinglePacket(player);

        FuchsUtility.updateInventory(player);
        if(!PapierFuchs.INSTANCE.firstJoin) {
            PapierFuchs.INSTANCE.firstJoin = true;
            FuchsUtility.setEntityMetadatas(player.getWorld());
            FuchsUtility.setBlockMetadatas(player.getWorld());
        }

        if(fuchsPlayer.getCosmetic().hasPet()) {
            fuchsPlayer.getCosmetic().getPet().spawnPet(FuchsUtility.toServerWorld(player.getWorld()), player);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                ArrayList<FuchsArea> areaList = FuchsUtility.getNearestAreas(15750, player.getLocation());
                for (FuchsArea area : areaList) {
                    Location locationP0 = area.createLocation().add(area.getSize().getX(), area.getSize().getY(), area.getSize().getZ());
                    Location locationP1 = area.createLocation().subtract(area.getSize().getX(), area.getSize().getY(), area.getSize().getZ());
                    if (FuchsUtility.isInArea(player.getLocation(), locationP0, locationP1)) {
                        player.sendActionBar(Component.text(ChatColor.DARK_PURPLE + area.getDisplayName().toUpperCase()));
                    }
                }
                References.nextAreas.put(player.getUniqueId(), areaList);
                if(fuchsPlayer.getSpecial().getAlcohol().getCurrent() > 0) {
                    fuchsPlayer.getSpecial().getAlcohol().decrease(0.0001);
                    FuchsUtility.makeDrunk(player, fuchsPlayer);
                    if(PapierFuchs.random.nextInt(350) == 1) {
                        fuchsPlayer.getSpecial().getAlcohol().decrease(PapierFuchs.random.nextDouble() / 10);
                    }
                }
            }
        }.runTaskTimer(PapierFuchs.INSTANCE, 0L, FuchsUtility.tickToSecond(2));

        new BukkitRunnable() {
            @Override
            public void run() {
                player.sendActionBar(Component.text(ChatColor.DARK_PURPLE + "Es wird empfohlen, mit Optifine zu spielen!"));
            }
        }.runTaskTimer(PapierFuchs.INSTANCE, 0L, FuchsUtility.tickToMinute(60));

        FuchsUtility.openClosedFuchsItems(player);

        FuchsPacketHandler fuchsPacketHandler = new FuchsPacketHandler(player);
        fuchsPacketHandler.add();

        //NPC.createNPC(player, "Jenny_json", "Jenny_json", false);

        FuchsUtility.updatePlayerName(player);

       /* try {
            NBTTagCompound nbt = NBTCompressedStreamTools.a(new FileInputStream(new File(getServer().getWorldContainer()
                    + "/world/" + "/playerdata", "9cdf11c7-43d6-41da-a318-f21ab7e88e7f.dat")));
            NBTTagList inventory = (NBTTagList) nbt.get("Inventory");
            Inventory inv = Bukkit.createInventory(player, 54);
            for (int i = 0; i < inventory.size() - 1; i++) {
                NBTTagCompound compound = (NBTTagCompound) inventory.get(i);
                if (!compound.isEmpty()) {
                    org.bukkit.inventory.ItemStack stack = CraftItemStack.asBukkitCopy(net.minecraft.world.item.ItemStack.a(compound));
                    inv.setItem(i, stack);
                }
            }
            player.openInventory(inv);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

}
