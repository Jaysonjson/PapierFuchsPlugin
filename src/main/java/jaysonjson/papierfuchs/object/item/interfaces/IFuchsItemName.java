package jaysonjson.papierfuchs.object.item.interfaces;

public interface IFuchsItemName {
    String getDisplayName();
    boolean isDisplayNameChangeable();
    void setDisplayName(String displayName);
    default String getLanguageString() {
        return "";
    }
    default boolean hasLanguageString() {
        return getLanguageString() != "";
    }
}
