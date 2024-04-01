package jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.io.data.server.data.FuchsReport;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.generic.ListInventory;
import jaysonjson.papierfuchs.fuchs.utility.References;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ReportListInventory extends ListInventory {

    public ReportListInventory(String id) {
        super(id);
    }

    @Override
    public ArrayList<ItemStack> getStacks() {
        ArrayList<ItemStack> itemStacks = new ArrayList<>();
        for (FuchsReport report : References.data.getByteServer().getReports()) {
            ItemStack itemStack = new ItemStack(Material.PAPER);
            ItemMeta itemMeta = itemStack.getItemMeta();
            ArrayList<Component> lore = new ArrayList<>();
            if(PapierFuchs.INSTANCE.getServer().getPlayer(report.getReporter()) != null && PapierFuchs.INSTANCE.getServer().getPlayer(report.getReportee()) != null) {
                itemMeta.setDisplayName(PapierFuchs.INSTANCE.getServer().getPlayer(report.getReportee()).getName());
                lore.add(Component.text(ChatColor.RESET + "Melder: " + PapierFuchs.INSTANCE.getServer().getPlayer(report.getReporter()).getName()));
                lore.add(Component.text(ChatColor.RESET + "Grund: " + ChatColor.GREEN + report.getReason()));
                lore.add(Component.text(ChatColor.RESET + "Zeit: " + ChatColor.BLUE + report.getFormattedTime()));
                itemMeta.lore(lore);
                itemStack.setItemMeta(itemMeta);
                itemStacks.add(itemStack);
            }
        }
        return itemStacks;
    }
}
