package jaysonjson.papierfuchs.commands;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.data.DataHandler;
import jaysonjson.papierfuchs.data.server.data.EntityMetadataSetter;
import jaysonjson.papierfuchs.data.server.data.FuchsServer;
import jaysonjson.papierfuchs.object.EntityMetaData;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class ArmorStandMetadata implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if(player.isOp()) {
                Location location = player.getLocation();
                location.setY(location.getY() - 1d);
                ArmorStand armorStand = player.getWorld().spawn(location, ArmorStand.class);
                armorStand.setCustomName("Crafting");
                armorStand.setInvisible(true);
                armorStand.setCustomNameVisible(true);
                FuchsServer fuchsServer = DataHandler.loadServer();
                armorStand.setMetadata(EntityMetaData.ARMORSTAND_GENERAL_CRAFTING, new FixedMetadataValue(PapierFuchs.INSTANCE, "s"));
                fuchsServer.ENTITY_METADATA.add(new EntityMetadataSetter(armorStand.getUniqueId(), EntityMetaData.ARMORSTAND_GENERAL_CRAFTING, "s"));
                DataHandler.saveServer(fuchsServer);
            }
        }
        return false;
    }



}
