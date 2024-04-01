package jaysonjson.papierfuchs.fuchs.object.intern.inventory;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.IFuchsDisplayName;
import jaysonjson.papierfuchs.fuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.fuchs.registry.RegistryType;
import jaysonjson.papierfuchs.fuchs.utility.References;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class FuchsInventory extends FuchsObject implements IFuchsInventory, IFuchsDisplayName, Listener {

    private Player player;
    private FuchsPlayer fuchsPlayer;
    private Inventory inventory;
    private int size = 0;

    public FuchsInventory(String id) {
        super(id, RegistryType.INVENTORY);
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSize() {
        setSize(getSizeEnum().getAsInt());
    }

    public int getSize() {
        return size;
    }

    @Override
    public void openInventory() {
        setContents();
        player.openInventory(getInventory());
    }

    @Override
    public void openInventory(int page) {
        setContents();
        player.openInventory(getInventory());
    }

    public void createAndOpen(Player player) {
        create(player);
        openInventory();
    }

    public Player getPlayer() {
        return player;
    }

    public FuchsPlayer getFuchsPlayer() {
        return fuchsPlayer;
    }

    public void setFuchsPlayer(FuchsPlayer fuchsPlayer) {
        this.fuchsPlayer = fuchsPlayer;
    }

    @Override
    public void create(Player player) {
        if(getSizeEnum() != null) {
            setSize();
        }
        this.fuchsPlayer = References.data.getPlayer(player);
        this.inventory = Bukkit.createInventory(player, getSize(), Component.text(getDisplayName().get(fuchsPlayer)));
        this.player = player;
        Bukkit.getPluginManager().registerEvents(this, PapierFuchs.INSTANCE);
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public void fillWithGlass() {
        fillWith(Material.GLASS_PANE);
    }

    public void fillWithFiller() {
        ItemStack stack = new ItemStack(Material.FEATHER);
        ItemMeta itemMeta = stack.getItemMeta();
        itemMeta.setDisplayName("\u200C");
        itemMeta.setCustomModelData(117);
        stack.setItemMeta(itemMeta);
        for (int i = 0; i < getSize(); i++) {
            getInventory().setItem(i, stack);
        }
    }

    public void fillWith(Material material) {
        for (int i = 0; i < getSize(); i++) {
            getInventory().setItem(i, new ItemStack(material));
        }
    }

    @EventHandler
    public void onItemClickEvent(InventoryClickEvent event) {
        if(event.getInventory().equals(getInventory())) {
            if(event.getCurrentItem() != null) {
                onItemClick(event, event.getCurrentItem());
            }
        }
    }

    @Override
    public boolean isDisplayNameChangeable() {
        return false;
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return FuchsLanguageString.c("Unbennantes FuchsInventar");
    }
}
