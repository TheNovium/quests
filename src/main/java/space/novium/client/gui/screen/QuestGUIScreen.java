package space.novium.client.gui.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import space.novium.item.ModItems;

@Environment(EnvType.CLIENT)
public class QuestGUIScreen extends Screen {
    private ButtonWidget majorExitButton;
    
    private PlayerEntity player;
    
    public QuestGUIScreen(PlayerEntity player){
        super(new TranslatableText(ModItems.QUEST_SCROLL.getTranslationKey()));
        this.player = player;
    }
    
    @Override
    protected void init() {
        client.keyboard.setRepeatEvents(false);
        this.majorExitButton = (ButtonWidget) addDrawableChild(new ButtonWidget(width - 16, 2, 14, 14, new LiteralText("X"), (button) -> {
            close();
        }));
    }
    
    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        DrawableHelper.fill(matrices, 15, 0, width, height, 0x809f88ff);
        DrawableHelper.fill(matrices, 0, 0, 15, height, 0x709088f0);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
