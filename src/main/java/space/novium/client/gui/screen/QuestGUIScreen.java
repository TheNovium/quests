package space.novium.client.gui.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import space.novium.client.gui.widget.ChapterButtonWidget;
import space.novium.client.gui.widget.ClickableSpriteWidget;
import space.novium.data.DataStorage;
import space.novium.quest.QuestChapter;
import space.novium.quest.QuestTree;
import space.novium.util.registration.ModItems;
import space.novium.util.FileHelper;
import space.novium.util.GUIUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Environment(EnvType.CLIENT)
public class QuestGUIScreen extends Screen {
    private ButtonWidget majorExitButton;
    private ClickableSpriteWidget closeButton;
    private ClickableSpriteWidget saveButton;
    private ClickableSpriteWidget addChapterButton;
    private ClickableSpriteWidget addQuestButton;
    private List<ChapterButtonWidget> chapterButtons;
    private int maxWidth = 0;
    private boolean displayChapterInfo;
    
    private int buttonBackgroundColor = 0x00000000;
    private int buttonHoverColor = 0x57ff00aa;
    private int buttonClickColor = 0xcaff77bb;
    
    private boolean editMode;
    
    private PlayerEntity player;
    
    public QuestGUIScreen(PlayerEntity player){
        super(new TranslatableText(ModItems.QUEST_SCROLL.getTranslationKey()));
        this.player = player;
        chapterButtons = new LinkedList<>();
        editMode = DataStorage.INSTANCE.getQuestTree().canEdit(player.getUuid());
    }
    
    @Override
    protected void init() {
        int drawY = 0;
        QuestTree questTree = DataStorage.INSTANCE.getQuestTree();
        client.keyboard.setRepeatEvents(false);
        closeButton = addDrawableChild(new ClickableSpriteWidget(width - 14, 0, 12, 12, FileHelper.loadImageByID("gui/close_button"), buttonBackgroundColor, buttonHoverColor, buttonClickColor, GUIUtil.CLOSE_ACTION, new LiteralText("Close")));
        if(editMode){
            drawY += 15;
            saveButton = addDrawableChild(new ClickableSpriteWidget(width / 2 - 16, 0, 15, 15, FileHelper.loadImageByID("gui/save_button"), buttonBackgroundColor, buttonHoverColor, buttonClickColor, DataStorage.SAVE_ACTION, new LiteralText("Save")));
            addChapterButton = addDrawableChild(new ClickableSpriteWidget(0, 0, 15, 15, FileHelper.loadImageByID("gui/add_button"), buttonBackgroundColor, buttonHoverColor, buttonClickColor, DataStorage.ADD_CHAPTER_ACTION, new LiteralText("Add Chapter")));
        }
        for(int i = 0; i < questTree.getChapterCount(); i++){
            QuestChapter chapter = questTree.getChapter(i);
            Text chText = new LiteralText(chapter.getTitle());
            MinecraftClient client = MinecraftClient.getInstance();
            TextRenderer textRenderer = client.textRenderer;
            if(maxWidth < textRenderer.getWidth(chText)){
                maxWidth = textRenderer.getWidth(chText);
            }
            ChapterButtonWidget chb = new ChapterButtonWidget(0, drawY, 15, 15, FileHelper.loadImageByID(chapter.getIcon().getNamespace(), "item/" + chapter.getIcon().getPath()), chText, new LoadChapterAction(chapter.getUUID()), buttonBackgroundColor, buttonHoverColor, buttonBackgroundColor);
            chapterButtons.add(chb);
            if(!chapter.isVisible() || editMode){
                addDrawableChild(chb);
                drawY += 15;
            }
        }
        if(editMode){
            maxWidth += 20;
        }
        for(ChapterButtonWidget chb : chapterButtons){
            chb.setTotalWidth(maxWidth + 17);
        }
    }
    
    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        DrawableHelper.fill(matrices, 0, 0, width, height, 0x709f88ff);
        DrawableHelper.fill(matrices, 0, 0, displayChapterInfo ? maxWidth + 17 : 15, height, 0x209088f0);
        if(displayChapterInfo){
            if(mouseX > maxWidth + 17){
                displayChapterInfo = false;
                for(ChapterButtonWidget cbw : chapterButtons){
                    cbw.hideText();
                }
            }
        } else {
            if(mouseX <= 15){
                displayChapterInfo = true;
                for(ChapterButtonWidget cbw : chapterButtons){
                    cbw.displayText();
                }
            }
        }
        super.render(matrices, mouseX, mouseY, delta);
    }
    
    private void loadChapter(UUID chapter){
    
    }
    
    private class LoadChapterAction implements GUIUtil.GenericPressAction {
        protected UUID chapter;
        
        public LoadChapterAction(UUID chapter){
            this.chapter = chapter;
        }
        
        @Override
        public <T extends Element> void onPress(T widget) {
            loadChapter(chapter);
        }
    }
}
