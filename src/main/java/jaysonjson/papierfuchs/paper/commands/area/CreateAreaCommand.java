package jaysonjson.papierfuchs.paper.commands.area;

import jaysonjson.papierfuchs.fuchs.utility.References;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.io.data.FuchsLocation;
import jaysonjson.papierfuchs.fuchs.io.data.FuchsSize;
import jaysonjson.papierfuchs.fuchs.io.data.area.data.FuchsArea;
import jaysonjson.papierfuchs.fuchs.io.data.DataHandler;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import java.util.UUID;

public class CreateAreaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player && commandSender.isOp()) {
            if(args.length >= 2) {
                if(!FuchsUtility.areaExists(args[0])) {
                    Player player = (Player) commandSender;
                    int size = Integer.parseInt(args[1]);
                    if(size < 50000) {
                        FuchsArea nearestArea = FuchsUtility.getNearestArea(player);
                        FuchsArea area = new FuchsArea();
                        area.setSize(new FuchsSize(size, size, size));
                        if(!area.canOverlap(player) && FuchsUtility.areaOverlap(player.getWorld(), area, nearestArea)) {
                            player.sendMessage("Konnte Gebiet nicht erstellen! Es würde " + nearestArea.getDisplayName() + " überlappen und dies Erlaubt " + nearestArea.getDisplayName() + " nicht!");
                        }
                        area.setOwner(((Player) commandSender).getUniqueId());
                        area.setUuid(UUID.randomUUID());
                        area.setDisplayName(args[0]);
                        Location location = player.getLocation();
                        area.setLocation(new FuchsLocation(location));
                        DataHandler.saveArea(area);
                        player.sendMessage("Gebiet " + args[0] + " erstellt!");
                        References.data.saveArea(area);
                        /*area.setWorld(FuchsWorld.OVERWORLD);
                        if(player.getWorld().getEnvironment() == World.Environment.NETHER) {
                            area.setWorld(FuchsWorld.NETHER);
                        }
                        if(player.getWorld().getEnvironment() == World.Environment.THE_END) {
                            area.setWorld(FuchsWorld.END);
                        }*/
                    } else {
                        commandSender.sendMessage("Gebiet ist zu groß! Max: 50000");
                    }
                } else {
                    commandSender.sendMessage("Gebiet existiert bereits!");
                }
                return true;
            }
        }
        return false;
    }
}
