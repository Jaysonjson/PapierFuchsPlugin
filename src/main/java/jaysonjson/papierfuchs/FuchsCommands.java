package jaysonjson.papierfuchs;

import jaysonjson.papierfuchs.paper.commands.*;
import jaysonjson.papierfuchs.paper.commands.area.AreaCommand;
import jaysonjson.papierfuchs.paper.commands.area.CreateAreaCommand;
import jaysonjson.papierfuchs.paper.commands.guild.GuildCommands;

public class FuchsCommands {

    protected static void register() {
        registerCommands(
                new SpigotCommand("areas", new AreaCommand()),
                new SpigotCommand("area", new CreateAreaCommand()),
                new SpigotCommand("guild", new GuildCommands()),
                new SpigotCommand("guild banner", new SetGuildBannerCommand()),
                new SpigotCommand("cosmetic", new CosmeticCommand()),
                new SpigotCommand("settings", new FuchsSingleCommand()),
                new SpigotCommand("recipelist", new RecipeInventoryCommand()),
                new SpigotCommand("admin", new AdminCommand()),
                new SpigotCommand("drops", new DropInventoryCommand()),
                new SpigotCommand("report", new ReportCommand())
                );
    }

    private static void registerCommands(SpigotCommand... commands) {
        for(SpigotCommand command : commands) {
            PapierFuchs.INSTANCE.getCommand(command.getCommand()).setExecutor(command.getCommandExecutor());
        }
    }

}
