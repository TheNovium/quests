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
import space.novium.data.DataStorage;
import space.novium.quest.QuestTree;
import space.novium.util.registration.ModItems;
import space.novium.util.FileHelper;
import space.novium.util.GUIUtil;

@Environment(EnvType.CLIENT)
public class QuestGUIScreen extends Screen {
    private ButtonWidget majorExitButton;
    private ClickableSpriteWidget closeButton;
    private ClickableSpriteWidget saveButton;
    private ClickableSpriteWidget addChapterButton;
    private ClickableSpriteWidget addQuestButton;
    
    private int buttonBackgroundColor = 0x00000000;
    private int buttonHoverColor = 0x77ff00aa;
    private int buttonClickColor = 0xaaff77bb;
    
    private boolean creativeMode;
    
    private PlayerEntity player;
    
    public QuestGUIScreen(PlayerEntity player){
        super(new TranslatableText(ModItems.QUEST_SCROLL.getTranslationKey()));
        this.player = player;
        creativeMode = player.isCreative() && player.hasPermissionLevel(2);
    }
    
    @Override
    protected void init() {
        int drawY = 0;
        QuestTree questTree = DataStorage.INSTANCE.getQuestTree();
        client.keyboard.setRepeatEvents(false);
        closeButton = addDrawableChild(new ClickableSpriteWidget(width - 14, 0, 12, 12, FileHelper.loadImageByID("gui/close_button"), buttonBackgroundColor, buttonHoverColor, buttonClickColor, GUIUtil.CLOSE_ACTION, new LiteralText("Close")));
        if(creativeMode){
            drawY += 15;
            saveButton = addDrawableChild(new ClickableSpriteWidget(width / 2 - 16, 0, 15, 15, FileHelper.loadImageByID("gui/save_button"), buttonBackgroundColor, buttonHoverColor, buttonClickColor, DataStorage.SAVE_ACTION, new LiteralText("Save")));
            addChapterButton = addDrawableChild(new ClickableSpriteWidget(0, 0, 15, 15, FileHelper.loadImageByID("gui/add_button"), buttonBackgroundColor, buttonHoverColor, buttonClickColor, DataStorage.ADD_CHAPTER_ACTION, new LiteralText("Add Chapter")));
        }
    }
    
    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        DrawableHelper.fill(matrices, 15, 0, width, height, 0x809f88ff);
        DrawableHelper.fill(matrices, 0, 0, 15, height, 0x709088f0);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
