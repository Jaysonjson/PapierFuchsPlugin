package jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.io.data.DataHandler;
import jaysonjson.papierfuchs.fuchs.io.data.area.data.FuchsArea;
import jaysonjson.papierfuchs.fuchs.io.data.area.data.FuchsAreaProperty;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.FuchsInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.InventoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.lists.AreaListInventory;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class AreaInventory extends FuchsInventory {

    HashMap<Player, FuchsArea> chatPlayers = new HashMap<>();
    HashMap<Player, ChatType> chatPlayersType = new HashMap<>();

    enum ChatType {
        NAME, PRIORITY
    }

    FuchsArea area;
    AreaListInventory areaListInventory;
    public AreaInventory(String id) {
        super(id);
        Bukkit.getPluginManager().registerEvents(this, PapierFuchs.INSTANCE);
    }

    public FuchsArea getArea() {
        return area;
    }

    public void setArea(FuchsArea area) {
        this.area = area;
    }

    public void setAreaListInventory(AreaListInventory areaListInventory) {
        this.areaListInventory = areaListInventory;
    }

    public AreaListInventory getAreaListInventory() {
        return areaListInventory;
    }

    @Override
    public void setContents() {
        fillWithGlass();
        getInventory().setItem(10, FuchsUtility.createInventoryStack(Material.PAPER, 1, area.getDisplayName()));
        int prioItemAmount = area.getPriority();
        if(area.getPriority() < 1) {
            prioItemAmount = 1;
        } else if(area.getPriority() > 64) {
            prioItemAmount = 64;
        }

        ArrayList<String> locationLore = new ArrayList<>();
        locationLore.add("x: " + area.getLocation().getX());
        locationLore.add("y: " + area.getLocation().getY());
        locationLore.add("z: " + area.getLocation().getZ());
        locationLore.add("Distanz: " + getPlayer().getLocation().distance(area.createLocation()));

        getInventory().setItem(11, FuchsUtility.createInventoryStack(Material.SLIME_BALL, prioItemAmount,"Priorität: " + area.getPriority()));
        getInventory().setItem(12, FuchsUtility.createInventoryStack(Material.PAPER, 1, "Lage", locationLore));
        getInventory().setItem(19, FuchsUtility.createInventoryWoolColor(area.isBreakBlocks(), "Blöcke Zerstören", 1));
        getInventory().setItem(20, FuchsUtility.createInventoryWoolColor(area.isPlaceBlocks(), "Blöcke Platzieren", 1));
        getInventory().setItem(21, FuchsUtility.createInventoryWoolColor(area.isSpawnMobs(), "Spawn Mobs", 1));
        getInventory().setItem(22, FuchsUtility.createInventoryWoolColor(area.isAllowOverlap(), "Überlappung Erlauben", 1));
        getInventory().setItem(23, FuchsUtility.createInventoryWoolColor(area.isAllowOwnerOverlap(), "Besitzer Überlappung Erlauben", 1));
        getInventory().setItem(49, FuchsUtility.createInventoryStack(Material.PAPER, 1, "Zurück"));
    }

    @Override
    public void onItemClick(InventoryClickEvent event, ItemStack clickedItem) {
        if (clickedItem.hasItemMeta()) {
            String itemName = clickedItem.getItemMeta().getDisplayName();
            if (itemName.equalsIgnoreCase(area.getDisplayName())) {
                chatPlayers.put(getPlayer(), area);
                chatPlayersType.put(getPlayer(), ChatType.NAME);
                getPlayer().sendMessage("Schreibe eine Naricht in den Chat um den Namen zu ändern. Schreibe \"Abbrechen\" um Abzubrechen");
                getPlayer().closeInventory();
            }
            if (itemName.equalsIgnoreCase("Priorität: " + area.getPriority())) {
                chatPlayers.put(getPlayer(), area);
                chatPlayersType.put(getPlayer(), ChatType.PRIORITY);
                getPlayer().sendMessage("Schreibe eine Zahl in den Chat, um die Priorität zu ändern. Schreibe \"Abbrechen\" um Abzubrechen");
                getPlayer().closeInventory();
            }

            if (itemName.equalsIgnoreCase("Lage") && getPlayer().isOp()) {
                getPlayer().teleport(area.createTeleportLocation());
            }

            switch (itemName) {
                case "Blöcke Zerstören" -> area.toggleProperty(FuchsAreaProperty.BREAK_BLOCKS);
                case "Blöcke Platzieren" -> area.toggleProperty(FuchsAreaProperty.PLACE_BLOCKS);
                case "Spawn Mobs" -> area.toggleProperty(FuchsAreaProperty.SPAWN_MOBS);
                case "Überlappung Erlauben" -> area.toggleProperty(FuchsAreaProperty.ALLOW_OVERLAP);
                case "Besitzer Überlappung Erlauben" -> area.toggleProperty(FuchsAreaProperty.ALLOW_OWNER_OVERLAP);
            }

            DataHandler.saveArea(area);

            if (itemName.equalsIgnoreCase("Zurück")) {
                if (areaListInventory != null) {
                    areaListInventory.openInventory(0);
                } else {
                    AreaListInventory listInventory = InventoryList.areaList.copy();
                    listInventory.create(getPlayer());
                    openInventory();
                }
            }
            setContents();
        }
        super.onItemClick(event, clickedItem);
    }

    @EventHandler
    public void ChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if(chatPlayers.containsKey(player)) {
            if(!event.getMessage().equalsIgnoreCase("abbrechen")) {
                event.setCancelled(true);
                Bukkit.getScheduler().runTask(PapierFuchs.INSTANCE, () -> {
                    ChatType type = chatPlayersType.get(player);
                    FuchsArea area = chatPlayers.get(player);
                    boolean success = false;
                    switch (type) {
                        case NAME:
                            if(!FuchsUtility.areaExists(event.getMessage())) {
                                area.setDisplayName(event.getMessage());
                                success = true;
                            } else {
                                player.sendMessage("Gebietname exisitert bereits...");
                            }
                            break;
                        case PRIORITY:
                            if(Pattern.matches("^[0-9]+$", event.getMessage())) {
                                area.setPriority(Integer.parseInt(event.getMessage()));
                                success = true;
                            } else {
                                player.sendMessage("Dies ist keine Zahl! Versuche es nochmal...");
                            }
                            break;
                        default:
                            //Sicherhaltshaber
                            success = true;
                    }

                    if(success) {
                        chatPlayers.remove(player);
                        chatPlayersType.remove(player);
                    }
                    DataHandler.saveArea(area);
                    openInventory();
                });
            } else {
                event.setCancelled(true);
                chatPlayers.remove(player);
                chatPlayersType.remove(player);
            }
        }
    }

}
