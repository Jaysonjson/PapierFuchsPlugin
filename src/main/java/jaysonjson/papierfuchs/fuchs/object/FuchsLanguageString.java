package jaysonjson.papierfuchs.fuchs.object;

import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.bukkit.ChatColor;

import java.io.Serial;
import java.io.Serializable;

public class FuchsLanguageString implements Serializable {

    @Serial
    private transient static final long serialVersionUID = 0L;

    private String main = "";
    private String language = "";
    private String color = "";
    public FuchsLanguageString() {

    }

    public FuchsLanguageString(String main, String language) {
        this.main = main;
        this.language = language;
    }

    public FuchsLanguageString(String color, String main, String language) {
        this.main = main;
        this.language = language;
        this.color = color;
    }

    public FuchsLanguageString(ChatColor color, String main, String language) {
        this.main = main;
        this.language = language;
        this.color = color.toString();
    }

    public FuchsLanguageString(ChatColor color, String main) {
        this.main = main;
        this.color = color.toString();
    }

    public FuchsLanguageString(String main) {
        this.main = main;
    }

    public String getMain() {
        return main;
    }

    public String getLanguage() {
        return language;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String get(FuchsPlayer fuchsPlayer) {
        return FuchsUtility.getStringFromLanguage(fuchsPlayer, this);
    }

    @Override
    public String toString() {
        return main;
    }

    public static FuchsLanguageString c() {
        return new FuchsLanguageString();
    }

    public static FuchsLanguageString c(String main) {
        return new FuchsLanguageString(main);
    }

    public static FuchsLanguageString c(String main, String language) {
        return new FuchsLanguageString(main, language);
    }

    public static FuchsLanguageString c(ChatColor chatColor, String main) {
        return new FuchsLanguageString(chatColor, main);
    }

    public static FuchsLanguageString c(ChatColor chatColor, String main, String language) {
        return new FuchsLanguageString(chatColor, main, language);
    }

    public static FuchsLanguageString c(String chatColor, String main, String language) {
        return new FuchsLanguageString(chatColor, main, language);
    }

    public String getColor() {
        return color;
    }
}
