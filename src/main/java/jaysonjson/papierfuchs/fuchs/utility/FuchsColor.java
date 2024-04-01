package jaysonjson.papierfuchs.fuchs.utility;

import org.bukkit.ChatColor;

public class FuchsColor {

    public ChatColor chatColor;
    public net.md_5.bungee.api.ChatColor bungeeColor;
    public FuchsColor(ChatColor chatColor) {
        this.chatColor = chatColor;
    }

    public FuchsColor(net.md_5.bungee.api.ChatColor bungeeColor) {
        this.bungeeColor =bungeeColor;
    }

    public String get() {
        return chatColor.toString();
    }

    @Override
    public String toString() {
        if(chatColor != null) {
            return chatColor.toString();
        } else if (bungeeColor != null){
            return bungeeColor.toString();
        }
        return "";
    }

    public String asBold() {
        return ChatColor.BOLD + chatColor.toString();
    }

    public String asItalic() {
        return ChatColor.ITALIC + chatColor.toString();
    }

    public net.md_5.bungee.api.ChatColor getBungeeColor() {
        return bungeeColor;
    }
}
