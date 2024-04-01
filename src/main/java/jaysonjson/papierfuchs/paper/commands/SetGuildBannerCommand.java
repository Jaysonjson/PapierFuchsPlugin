package jaysonjson.papierfuchs.paper.commands;

import jaysonjson.papierfuchs.fuchs.io.data.guild.data.FuchsGuild;
import jaysonjson.papierfuchs.fuchs.io.data.guild.obj.zGuildBanner;
import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.fuchs.utility.References;
import net.kyori.adventure.text.Component;
import org.bukkit.block.banner.Pattern;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.BannerMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SetGuildBannerCommand implements CommandExecutor {
   
	@Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
	    Player player = (Player) commandSender;
        if(player.getInventory().getItemInMainHand().getItemMeta() instanceof BannerMeta im) {
            FuchsPlayer fuchsPlayer = References.data.getPlayer(player);
            if(fuchsPlayer.isInGuild()) {
                FuchsGuild guild = fuchsPlayer.getGuild();
                if(guild.getMember(player).isOwner()) {
                    zGuildBanner banner = new zGuildBanner();
                    banner.color = im.getBaseColor();
                    banner.pattern = (ArrayList<Pattern>) im.getPatterns();
                    guild.setBanner(banner);
                } else {
                    player.sendMessage(Component.text("Keine Rechte"));
                }
            } else {
                player.sendMessage(Component.text("Du bist in keiner Guilde!"));
            }
            return true;
        }


        /*Player player = (Player) commandSender;
        player.getInventory().setContents(FuchsUtility.removeCurrency(CurrencyList.FUCHSO.copy().getID(), 100.50, (Player) commandSender, true));
      */

      /* BannerMeta im = (BannerMeta) player.getInventory().getItemInMainHand().getItemMeta();
        zGuild zGuild = DataHandler.loadGuild(UUID.fromString("54a8dd44-c1ce-458a-9f34-beffb1b72d65"));
        im.setPatterns(zGuild.banner.pattern);
        player.getInventory().getItemInMainHand().setItemMeta(im);
        player.getInventory().setHelmet(player.getInventory().getItemInMainHand());

       */
        return false;
    }
	
}
