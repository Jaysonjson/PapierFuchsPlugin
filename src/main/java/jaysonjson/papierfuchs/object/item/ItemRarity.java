package jaysonjson.papierfuchs.object.item;

import jaysonjson.papierfuchs.object.item.interfaces.IItemRarity;

@Deprecated
public enum ItemRarity implements IItemRarity {
    COMMON("common", "Oft"),
    RARE("rare", "Selten"),
    LEGENDARY("legendary", "Legendär");

    String id;
    String lore;
    ItemRarity(String id, String lore) {
        this.lore = lore;
    }

    @Override
    public String getLoreText() {
        return lore;
    }

    @Override
    public String getLoreTextFromLanguage() {
        return lore;
    }

    @Override
    public String getID() {
        return id;
    }
}
