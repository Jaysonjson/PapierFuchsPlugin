package jaysonjson.papierfuchs.fuchs.utility;

import jaysonjson.papierfuchs.PapierFuchs;
import org.bukkit.ChatColor;

public class FuchsLog {

    /*public static void log(LogType logType, String log) {
        System.out.println("[" + FuchsAnsi.CYAN + "PapierFuchs " + FuchsAnsi.RESET + "{" + FuchsAnsi.BLUE + logType.toString() + FuchsAnsi.RESET + "}] " + log);
    }*/

    public static void log(LogType logType, String log) {
        System.out.println("[" + ChatColor.AQUA + "PapierFuchs" + ChatColor.WHITE + "/" + ChatColor.RED + PapierFuchs.getBuild() + FuchsAnsi.RESET+ "] " + log);
    }

    public static void error(LogType logType, String log) {
        log(logType, FuchsAnsi.RED + log);
    }

    public static void error(String log) {
        log(FuchsAnsi.RED + log);
    }

    public static void log(String log) {
        log(LogType.OTHER, log);
    }

    public static void warn(String log) {
        log(FuchsAnsi.YELLOW + log);
    }

}
