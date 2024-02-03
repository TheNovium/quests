package space.novium.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Element;

public class GUIUtil {
    public static final GenericPressAction CLOSE_ACTION = new CloseAction();
    public static final GenericPressAction EMPTY_ACTION = new EmptyAction();
    
    @Environment(EnvType.CLIENT)
    public interface GenericPressAction{
        <T extends Element> void onPress(T widget);
    }
    
    private static class CloseAction implements GenericPressAction {
        @Override
        public <T extends Element> void onPress(T widget) {
            MinecraftClient.getInstance().setScreen(null);
        }
    }
    
    private static class EmptyAction implements GenericPressAction {
        @Override
        public <T extends Element> void onPress(T widget) {
        
        }
    }
}
