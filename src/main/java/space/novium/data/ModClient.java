package space.novium.data;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import space.novium.client.gui.screen.QuestGUIScreen;

public class ModClient implements ClientModInitializer {
    private static KeyBinding openQuestBook = KeyBindingHelper.registerKeyBinding(
            new KeyBinding(
                    "key.dreamscapequests.open_quests",
                    InputUtil.Type.KEYSYM,
                    GLFW.GLFW_KEY_F4,
                    "category.dreamscapequests.name"
            )
    );
    
    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register((client) -> {
            while(openQuestBook.wasPressed()) {
                client.setScreen(new QuestGUIScreen(client.player));
            }
        });
    }
}
