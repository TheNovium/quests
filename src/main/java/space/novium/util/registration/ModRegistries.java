package space.novium.util.registration;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import space.novium.quest.command.QuestsEditCommands;

public class ModRegistries {
    public static void registerModRegistries(){
        registerCommands();
    }
    
    private static void registerCommands(){
        CommandRegistrationCallback.EVENT.register((dispatcher, y) -> {
            QuestsEditCommands.register(dispatcher);
        });
    }
}
