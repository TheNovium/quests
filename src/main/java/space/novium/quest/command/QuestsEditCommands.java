package space.novium.quest.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import space.novium.DreamscapeQuests;
import space.novium.data.DataStorage;

public class QuestsEditCommands {
    public static void register(CommandDispatcher<ServerCommandSource> source){
        source.register(CommandManager.literal("quests").then(CommandManager.literal("addchapter").executes(QuestsEditCommands::addBlankChapter)).requires((src) -> {
            return src.hasPermissionLevel(2);
        }));
        source.register(CommandManager.literal("quests").then(CommandManager.literal("save").executes(QuestsEditCommands::save)).requires((src) -> {
            return src.hasPermissionLevel(2);
        }));
        source.register(CommandManager.literal("quests").then(CommandManager.literal("clear").then(CommandManager.literal("all").executes(QuestsEditCommands::clear))).requires((src) -> {
            return src.hasPermissionLevel(2);
        }));
    }
    
    private static int addBlankChapter(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) {
        DataStorage.INSTANCE.getQuestTree().addBlankChapter();
        return 0;
    }
    
    private static int save(CommandContext<ServerCommandSource> serverCommandSourceCommandContext){
        DataStorage.INSTANCE.save();
        return 0;
    }
    
    private static int clear(CommandContext<ServerCommandSource> serverCommandSourceCommandContext){
        DataStorage.INSTANCE.clear();
        return 0;
    }
}
