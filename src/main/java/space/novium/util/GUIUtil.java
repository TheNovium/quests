package space.novium.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import java.util.function.Consumer;

public class GUIUtil {
    public static final GenericPressAction CLOSE_ACTION = new CloseAction();
    
    @Environment(EnvType.CLIENT)
    public interface TooltipSupplier{
        <T extends ClickableWidget> void onTooltip(T widget, MatrixStack matrix, int mouseX, int mouseY);
        
        default void supply(Consumer<Text> consumer){}
    }
    
    @Environment(EnvType.CLIENT)
    public interface GenericPressAction{
        <T extends ClickableWidget> void onPress(T widget);
    }
    
    private static class CloseAction implements GenericPressAction {
        @Override
        public <T extends ClickableWidget> void onPress(T widget) {
            MinecraftClient.getInstance().setScreen(null);
        }
    }
}
