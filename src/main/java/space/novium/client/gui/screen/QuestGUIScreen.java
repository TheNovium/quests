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
import space.novium.client.gui.widget.ClickableSpriteWidget;
import space.novium.util.registration.ModItems;
import space.novium.util.FileHelper;
import space.novium.util.GUIUtil;

@Environment(EnvType.CLIENT)
public class QuestGUIScreen extends Screen {
    private ButtonWidget majorExitButton;
    private ClickableSpriteWidget testWidget;
    
    private PlayerEntity player;
    
    public QuestGUIScreen(PlayerEntity player){
        super(new TranslatableText(ModItems.QUEST_SCROLL.getTranslationKey()));
        this.player = player;
    }
    
    @Override
    protected void init() {
        client.keyboard.setRepeatEvents(false);
        testWidget = addDrawableChild(new ClickableSpriteWidget(width - 14, 1, 12, 12, FileHelper.loadImageByID("gui/close_button"), 0x0000000, 0x55ff0000, 0x99ffaaaa, GUIUtil.CLOSE_ACTION, new LiteralText("Close")));
    }
    
    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        DrawableHelper.fill(matrices, 15, 0, width, height, 0x809f88ff);
        DrawableHelper.fill(matrices, 0, 0, 15, height, 0x709088f0);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
