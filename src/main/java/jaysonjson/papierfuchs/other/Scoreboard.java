package jaysonjson.papierfuchs.other;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.References;
import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.data.area.data.zArea;
import jaysonjson.papierfuchs.data.area.obj.zLocation;
import jaysonjson.papierfuchs.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.object.item.CurrencyType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.ScoreboardManager;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Scoreboard {

    public static void updateScoreboard(Player player) {
        sendScoreboard(player, References.data.getPlayer(player.getUniqueId()), Utility.countCurrency(player, CurrencyType.HACKSILVER.getId(), true), Utility.countCurrency(player, CurrencyType.ZORYHA_SHARD.getId(), true), Utility.getNearestArea(player.getWorld().getEnvironment(), player.getLocation()), Utility.getNearestAreaDistance(player.getWorld().getEnvironment(), player.getLocation()));
    }

    public static void updateScoreboard(Player player, FuchsPlayer fuchsPlayer) {
        sendScoreboard(player, fuchsPlayer, Utility.countCurrency(player, CurrencyType.HACKSILVER.getId(), true), Utility.countCurrency(player, CurrencyType.ZORYHA_SHARD.getId(), true), Utility.getNearestArea(player.getWorld().getEnvironment(), player.getLocation()), Utility.getNearestAreaDistance(player.getWorld().getEnvironment(), player.getLocation()));
    }

    public static void updateScoreboard(Player player, FuchsPlayer fuchsPlayer, ArrayList<zArea> areas) {
        sendScoreboard(player, fuchsPlayer, Utility.countCurrency(player, CurrencyType.HACKSILVER.getId(), true), Utility.countCurrency(player, CurrencyType.ZORYHA_SHARD.getId(), true), areas, Utility.getNearestAreaDistance(player.getWorld().getEnvironment(), player.getLocation()));
    }
    public static void sendScoreboard(Player player, FuchsPlayer fuchsPlayer, double money, double zoryhaShardValue, zArea areas, zLocation areaDistance) {
        ArrayList<zArea> areaList = new ArrayList<>();
        sendScoreboard(player, fuchsPlayer, money, zoryhaShardValue, areaList, areaDistance);
    }
    public static void sendScoreboard(Player player, FuchsPlayer fuchsPlayer, double money, double zoryhaShardValue, ArrayList<zArea> areas, zLocation areaDistance) {
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        org.bukkit.scoreboard.Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective score = scoreboard.registerNewObjective("PapierFuchs", "dummy", "PapierFuchs");

        score.setDisplayName(player.getDisplayName() + " | PapierFuchs " + PapierFuchs.version);
        score.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score lineSpace0 = score.getScore("-0------=0=------0-");
        String moneySTR = money + "";
        String zoryhaShardSTR = zoryhaShardValue + "";

        if(money > 10000) {
            moneySTR = Utility.formatInteger((int) money);
        }

        if(zoryhaShardValue > 10000) {
            zoryhaShardSTR = Utility.formatInteger((int) zoryhaShardValue);
        }
        Score hackSilver1Score = score.getScore("-//-");
        Score zoryhaShard1Score = score.getScore("-//-");
       /* if(Utility.convertZoryhaShardToHacksilver(zoryhaShardValue) > 0) {
            hackSilver1Score = score.getScore(ChatColor.GRAY + "(" + ChatColor.RESET + (Utility.formatInteger((int) (money + Utility.convertZoryhaShardToHacksilver(zoryhaShardValue)))) + ChatColor.RESET + ChatColor.GRAY + ")");
        }*/
        Score hackSilverScore = score.getScore(ChatColor.GRAY + "Hacksilber" + ChatColor.DARK_GRAY + ": " + ChatColor.GOLD + moneySTR + "Φ");
        /*if(Utility.convertHacksilverToZoryhaShard(money) > 0) {
            zoryhaShard1Score = score.getScore(ChatColor.GRAY + "(" + ChatColor.RESET + (Utility.formatInteger((int) (zoryhaShardValue + Utility.convertHacksilverToZoryhaShard(money)))) + ChatColor.RESET + ChatColor.GRAY + ")");
        }
        */
        Score zoryhaShardScore = score.getScore(ChatColor.AQUA + "Zoryha Bruckstücke" + ChatColor.DARK_GRAY + ": " + ChatColor.GOLD + zoryhaShardSTR + "¢");
        Score lineSpace1 = score.getScore("-0------=1=------0-");
        String areaColor = ChatColor.GOLD + "";
        Score nextAreaScore = score.getScore(ChatColor.RED + "in der nähe");
        Score areaScore = score.getScore(ChatColor.RED + "Keine Gebiete");
        if(areas.size() > 0) {
            zArea currentArea = areas.get(0);
            boolean inArea = false;
            for (zArea area : areas) {
                inArea = Utility.isInArea(area, player);
                if (inArea) {
                    currentArea = area;
                    //zArea nextArea = Utility.getNearestAreaPlayerExceptCurrent(player, currentArea);
                    areaColor = ChatColor.DARK_PURPLE + "";
                }
            }
            zArea nextArea = Utility.getNearestAreaPlayerExceptCurrent(player, currentArea);
            zLocation nextAreaLoc = Utility.getNearestAreaDistanceOutsidePlayerExceptCurrent(player, currentArea);
            nextAreaScore = score.getScore(ChatColor.GOLD + nextArea.getDisplayName() + ChatColor.RESET + " (" + ChatColor.BOLD + Utility.formatInteger((int) nextAreaLoc.x) + ChatColor.RESET + ", " + ChatColor.BOLD + Utility.formatInteger((int) nextAreaLoc.z) + ChatColor.RESET + ")");
            areaScore = score.getScore(areaColor + currentArea.getDisplayName() + ChatColor.RESET + " (" + ChatColor.BOLD + Utility.formatInteger((int) areaDistance.x) + ChatColor.RESET + ", " + ChatColor.BOLD + Utility.formatInteger((int) areaDistance.z) + ChatColor.RESET + ")");
        }
        Score lineSpace2 = score.getScore("-0------=2=------0-");
        Score levelScore = score.getScore("LvL: " + fuchsPlayer.getLevel().level);
        Score alcoholScore = null;
        if(fuchsPlayer.getPlayerSpecial().getAlcohol() > 0) {
           alcoholScore = score.getScore(ChatColor.RED + "Alkohol: " + ChatColor.GOLD + new DecimalFormat("#.##").format(fuchsPlayer.getPlayerSpecial().getAlcohol()));
        }
        Score lineSpace3 = score.getScore("-0------=3=------0-");
        Score classSore = score.getScore(ChatColor.RED + fuchsPlayer.getPlayerClass().current.getName());
        setScores(
                classSore,
                lineSpace3,
                alcoholScore,
                levelScore,
                lineSpace2,
                nextAreaScore,
                areaScore,
                lineSpace1,
                hackSilver1Score,
                hackSilverScore,
                zoryhaShard1Score,
                zoryhaShardScore,
                lineSpace0
        );
        player.setScoreboard(scoreboard);
    }

    private static void setScores(Score... scores) {
        int i = 0;
        for (Score score : scores) {
            if(score != null) {
                score.setScore(i);
                i++;
            }
        }
    }
}
