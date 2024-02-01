package space.novium.quest.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import space.novium.data.DataStorage;

public class AddBlankChapterCommand {
    public static void register(CommandDispatcher<ServerCommandSource> source, CommandManager.RegistrationEnvironment env){
        source.register(CommandManager.literal("quests").then(CommandManager.literal("addchapter").executes(AddBlankChapterCommand::run)));
    }
    
    private static int run(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) {
        DataStorage.INSTANCE.getQuestTree().addBlankChapter();
        return 0;
    }
}
