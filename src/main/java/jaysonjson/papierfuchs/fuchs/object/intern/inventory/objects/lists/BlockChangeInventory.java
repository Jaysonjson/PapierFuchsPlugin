package jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.lists;

import jaysonjson.papierfuchs.fuchs.io.data.server.data.FWBlockChange;
import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.generic.ListInventory;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BlockChangeInventory extends ListInventory {

    public ArrayList<FWBlockChange> blockChanges = new ArrayList<>();
    public BlockChangeInventory(String id) {
        super(id);
    }

    public void setBlockChanges(ArrayList<FWBlockChange> blockChanges) {
        this.blockChanges = blockChanges;
    }

    @Override
    public ArrayList<ItemStack> getStacks() {
        ArrayList<ItemStack> itemStacks = new ArrayList<>();
        for (FWBlockChange blockChange : blockChanges) {
            //Material woolColor = type.equals(FWBlockChange.ChangeType.ADD) ? Material.GREEN_WOOL : Material.RED_WOOL;
            ItemStack itemStack = new ItemStack(blockChange.getMaterial());
            ItemMeta itemMeta = itemStack.getItemMeta();
            ArrayList<Component> lore = new ArrayList<>();
            ChatColor typeColor = blockChange.getType().equals(FWBlockChange.ChangeType.ADD) ? ChatColor.GREEN : ChatColor.RED;
            boolean hasPlayer = blockChange.getPlayer() != null;
            if(hasPlayer) {
                itemMeta.displayName(Component.text(Bukkit.getOfflinePlayer(blockChange.getPlayer()).getName()));
            } else {
                itemMeta.displayName(Component.text(blockChange.getMaterial().toString()));
            }
            lore.add(Component.text(ChatColor.RESET + "Typ: " + typeColor + blockChange.getType().toString()));
            lore.add(Component.text(ChatColor.RESET + "Grund: " + ChatColor.GREEN + blockChange.getCause()));
            if(hasPlayer) {
                lore.add(Component.text(ChatColor.RESET + "Spieler: " + ChatColor.AQUA + Bukkit.getOfflinePlayer(blockChange.getPlayer()).getName()));
            }
            lore.add(Component.text(ChatColor.RESET + "Block: " + ChatColor.YELLOW + blockChange.getMaterial()));
            lore.add(Component.text(ChatColor.RESET + "Zeit: " + ChatColor.BLUE + blockChange.getTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SS"))));
            itemMeta.lore(lore);
            itemStack.setItemMeta(itemMeta);
            itemStacks.add(itemStack);
        }
        return itemStacks;
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return FuchsLanguageString.c("BlockChange");
    }
}
