package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.generic;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsItemData;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.UseTypeList;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class FuchsBookItem extends FuchsItem {

    private final String content;
    private final String author;
    private final String title;
    private final FuchsLanguageString displayName;
    public FuchsBookItem(String id, Material material, FuchsLanguageString displayName, String content, String author, String title) {
        super(id, material, UseTypeList.USE_ABLE.copy());
        this.content = content;
        this.author = author;
        this.title = title;
        this.displayName = displayName;
    }


    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        FuchsItemData fuchsItemData = new FuchsItemData(this, player, stack);
        fuchsItemData.setItem();
        BookMeta bookMeta = (BookMeta) fuchsItemData.item.getItemMeta();
        bookMeta.setAuthor(author);
        bookMeta.setTitle(title);
        StringBuilder page = new StringBuilder();
        int j = 0;
        for (int i = 0; i < content.length(); i++) {
            page.append(content.charAt(i));
            if(j >= 256) {
                bookMeta.addPages(Component.text(page.toString()));
                j = 0;
                page = new StringBuilder();
            }
            j++;
        }
        fuchsItemData.item.setItemMeta(bookMeta);
        return fuchsItemData.item;
    }

    public String getContent() {
        return content;
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return displayName;
    }
}
