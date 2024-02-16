package space.novium.quest.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import space.novium.DreamscapeQuests;
import space.novium.data.DataStorage;

public class QuestsEditCommands {
    public static void register(CommandDispatcher<ServerCommandSource> source){
        final LiteralArgumentBuilder<ServerCommandSource> lab = CommandManager.literal("dsq").requires((src) -> {
            return src.hasPermissionLevel(2);
        });
        lab.then(CommandManager.literal("addchapter")).executes(QuestsEditCommands::addBlankChapter);
        lab.then(CommandManager.literal("save")).executes(QuestsEditCommands::save);
        lab.then(CommandManager.literal("clear")).executes(QuestsEditCommands::clear);
        lab.then(CommandManager.literal("edit").then(CommandManager.argument("editMode", BoolArgumentType.bool()).executes((src) -> {
            return toggleEditMode(src.getSource(), BoolArgumentType.getBool(src, "editMode"));
        })));
        source.register(lab);
        
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
    
    private static int toggleEditMode(ServerCommandSource src, boolean canEdit){
        try {
            DataStorage.INSTANCE.setEdit(src.getPlayer(), canEdit);
        } catch(Exception e){
            DreamscapeQuests.LOGGER.error("Unable to change player edit permissions!");
            return -1;
        }
        return 0;
    }
}
