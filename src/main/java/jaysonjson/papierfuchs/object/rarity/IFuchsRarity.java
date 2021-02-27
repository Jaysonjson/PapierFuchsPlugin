package jaysonjson.papierfuchs.object.rarity;

public interface IFuchsRarity {
    String getLoreText();
    String getLoreTextFromLanguage();
    default boolean hasLanguageLore() {
        return !getLoreTextFromLanguage().equals("");
    }
}
