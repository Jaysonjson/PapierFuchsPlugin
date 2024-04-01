package jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects;

import com.google.common.io.BaseEncoding;
import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.object.BlockMetaData;
import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.FuchsInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.InventorySize;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.ChairVehicleData;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import net.kyori.adventure.text.Component;
import net.minecraft.nbt.NBTTagCompound;
import org.apache.commons.lang.SerializationUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;
import java.util.regex.Pattern;

public class ChairVehicleInventory extends FuchsInventory {

    public Block block = null;
    public ChairVehicleData vehicleData = new ChairVehicleData();
    public boolean removed = false;
    enum ChatType {
        HEIGHT, X, Z
    }

    HashMap<UUID, ChairVehicleInventory> chatPlayers = new HashMap<>();
    HashMap<UUID, ChatType> chatPlayersType = new HashMap<>();

    public ChairVehicleInventory(String id) {
        super(id);
        Bukkit.getPluginManager().registerEvents(this, PapierFuchs.INSTANCE);
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public void setVehicleData(ChairVehicleData vehicleData) {
        this.vehicleData = vehicleData;
    }

    @Override
    public void setContents() {
        fillWithGlass();
        getInventory().setItem(25, FuchsUtility.createInventoryStack(Material.BARRIER, 1, "Entfernen"));
        getInventory().setItem(10, FuchsUtility.createInventoryStackWithTag(Material.PAPER, 1, FuchsLanguageString.c("Höhe", "height").get(getFuchsPlayer()) + ": " + vehicleData.getHeight(), "height", ""));
        getInventory().setItem(11, FuchsUtility.createInventoryStack(Material.PAPER, 1, "X: " + vehicleData.getX()));
        getInventory().setItem(12, FuchsUtility.createInventoryStack(Material.PAPER, 1, "Z: " + vehicleData.getZ()));
    }

    @Override
    public void onItemClick(InventoryClickEvent event, ItemStack clickedItem) {
        if(block != null) {
            if (FuchsUtility.isTopInventory(event)) {
                if (clickedItem.hasItemMeta()) {
                    String displayName = clickedItem.getItemMeta().getDisplayName();
                    NBTTagCompound tag = FuchsUtility.getItemTag(clickedItem);
                    switch (displayName) {
                        case "Entfernen" -> {
                            block.removeMetadata(BlockMetaData.CHAIR_DATA, PapierFuchs.INSTANCE);
                            block.getWorld().getEntity(vehicleData.getArmorStand()).remove();
                            removed = true;
                            getPlayer().closeInventory();
                        }
                    }
                    if(tag.hasKey("height")) {
                        chatPlayersType.put(getPlayer().getUniqueId(), ChatType.HEIGHT);
                        chatPlayers.put(getPlayer().getUniqueId(), this);
                        getPlayer().closeInventory();
                        getPlayer().sendMessage(Component.text("Schreibe eine Fließkommazahl um die Höhe zu ändern! Schreibe \"Abbbrechen\" um abzubrechen."));
                    }
                    if(displayName.contains("X")) {
                        chatPlayersType.put(getPlayer().getUniqueId(), ChatType.X);
                        chatPlayers.put(getPlayer().getUniqueId(), this);
                        getPlayer().closeInventory();
                        getPlayer().sendMessage(Component.text("Schreibe eine Fließkommazahl um die Seite X zu ändern! Schreibe \"Abbbrechen\" um abzubrechen."));
                    }
                    if(displayName.contains("Z")) {
                        chatPlayersType.put(getPlayer().getUniqueId(), ChatType.Z);
                        chatPlayers.put(getPlayer().getUniqueId(), this);
                        getPlayer().closeInventory();
                        getPlayer().sendMessage(Component.text("Schreibe eine Fließkommazahl um die Seite Z zu ändern! Schreibe \"Abbbrechen\" um abzubrechen."));
                    }
                }
            }
        }
        super.onItemClick(event, clickedItem);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if(event.getInventory().equals(getInventory())) {
            if(!removed) {
                if(block != null) {
                    Entity armorStand = block.getWorld().getEntity(vehicleData.getArmorStand());
                    armorStand.teleport(block.getLocation().add(0.5 + vehicleData.getX(), vehicleData.getHeight(), 0.5 + vehicleData.getZ()));
                    FuchsUtility.addBlockMetadata(block, BlockMetaData.CHAIR_DATA, BaseEncoding.base16().encode(SerializationUtils.serialize(vehicleData)));
                }
            }
        }
    }

    @EventHandler
    public void ChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if(chatPlayersType.containsKey(player.getUniqueId())) {
            if(!event.getMessage().equalsIgnoreCase("abbrechen")) {
                event.setCancelled(true);
                Bukkit.getScheduler().runTask(PapierFuchs.INSTANCE, () -> {
                    ChatType type = chatPlayersType.get(player.getUniqueId());
                    ChairVehicleInventory vehicleInventory = chatPlayers.get(player.getUniqueId());
                    try {
                        if (vehicleInventory != null) {
                            switch (type) {
                                case HEIGHT -> {
                                    if (Pattern.matches("[+-]?([0-9]*[.])?[0-9]+", event.getMessage())) {
                                        vehicleInventory.vehicleData.setHeight(Float.parseFloat(event.getMessage()));
                                        vehicleInventory.setVehicleData(vehicleInventory.vehicleData);
                                        if (vehicleInventory.block != null) {
                                            Entity armorStand = vehicleInventory.block.getWorld().getEntity(vehicleInventory.vehicleData.getArmorStand());
                                            armorStand.teleport(vehicleInventory.block.getLocation().add(0.5, vehicleInventory.vehicleData.getHeight(), 0.5));
                                        }
                                        vehicleInventory.createAndOpen(player);
                                    } else {
                                        player.sendMessage(Component.text("Das ist keine Fließkommazahl! Probeiere es nochmal! Oder schreibe \"Abbbrechen\" um abzubrechen."));
                                    }
                                }
                                case X -> {
                                    if (Pattern.matches("[+-]?([0-9]*[.])?[0-9]+", event.getMessage())) {
                                        vehicleInventory.vehicleData.setX(Float.parseFloat(event.getMessage()));
                                        vehicleInventory.setVehicleData(vehicleInventory.vehicleData);
                                        if (vehicleInventory.block != null) {
                                            Entity armorStand = vehicleInventory.block.getWorld().getEntity(vehicleInventory.vehicleData.getArmorStand());
                                            armorStand.teleport(vehicleInventory.block.getLocation().add(0.5 + vehicleInventory.vehicleData.getX(), vehicleInventory.vehicleData.getHeight(), 0.5 + vehicleInventory.vehicleData.getZ()), PlayerTeleportEvent.TeleportCause.PLUGIN);
                                        }
                                        vehicleInventory.createAndOpen(player);
                                    } else {
                                        player.sendMessage(Component.text("Das ist keine Fließkommazahl! Probeiere es nochmal! Oder schreibe \"Abbbrechen\" um abzubrechen."));
                                    }
                                }
                                case Z -> {
                                    if (Pattern.matches("[+-]?([0-9]*[.])?[0-9]+", event.getMessage())) {
                                        vehicleInventory.vehicleData.setZ(Float.parseFloat(event.getMessage()));
                                        vehicleInventory.setVehicleData(vehicleInventory.vehicleData);
                                        if (vehicleInventory.block != null) {
                                            Entity armorStand = vehicleInventory.block.getWorld().getEntity(vehicleInventory.vehicleData.getArmorStand());
                                            armorStand.teleport(vehicleInventory.block.getLocation().add(0.5 + vehicleInventory.vehicleData.getX(), vehicleInventory.vehicleData.getHeight(), 0.5 + vehicleInventory.vehicleData.getZ()), PlayerTeleportEvent.TeleportCause.PLUGIN);
                                        }
                                        vehicleInventory.createAndOpen(player);
                                    } else {
                                        player.sendMessage(Component.text("Das ist keine Fließkommazahl! Probeiere es nochmal! Oder schreibe \"Abbbrechen\" um abzubrechen."));
                                    }
                                }
                            }
                            chatPlayersType.remove(player.getUniqueId());
                            chatPlayers.remove(player.getUniqueId());
                            vehicleInventory.createAndOpen(player);
                        }
                    } catch (Exception e) {
                        player.sendMessage("Irgentwas ist passiert!");
                    }
                });
            } else {
                event.setCancelled(true);
                chatPlayers.remove(player.getUniqueId());
                chatPlayersType.remove(player.getUniqueId());
            }
        }
    }


    @Override
    public InventorySize getSizeEnum() {
        return InventorySize.THIRTY_SIX;
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return FuchsLanguageString.c("Fahrzeug");
    }
}
