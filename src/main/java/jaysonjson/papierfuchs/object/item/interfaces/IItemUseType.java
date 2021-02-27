package jaysonjson.papierfuchs.object.item.interfaces;

public interface IItemUseType {
    String getLoreText();
    String getLoreTextFromLanguage();

    default boolean hasLanguageLore() {
        return !getLoreTextFromLanguage().equals("");
    }
}
