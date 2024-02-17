package space.novium.data;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.Element;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIo;
import space.novium.DreamscapeQuests;
import space.novium.client.gui.screen.QuestGUIScreen;
import space.novium.quest.QuestTree;
import space.novium.util.GUIUtil;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class DataStorage {
    //File Information
    public static final File FILE = new File("mods/dreamscapequests/quest_data.nbt");
    public static final DataStorage INSTANCE = new DataStorage();
    
    //Button Actions
    public static final GUIUtil.GenericPressAction SAVE_ACTION = new SaveAction();
    
    //Internal variables
    private final NbtCompound nbt;
    private final QuestTree questTree;
    
    private DataStorage(){
        NbtCompound temp = new NbtCompound();
        boolean shouldSave = false;
        try {
            FILE.getParentFile().mkdirs();
            if(!FILE.exists()){
                temp = QuestTree.generateDefaultNbt();
                shouldSave = true;
            } else {
                temp = NbtIo.readCompressed(FILE);
            }
        } catch (Exception e){
            DreamscapeQuests.LOGGER.error("Unable to read or create Quest NBT data file!");
            temp.putInt("Error", ErrorCodes.FAILED_TO_OPEN.ordinal());
            shouldSave = true;
        }
        nbt = temp;
        questTree = new QuestTree(nbt);
        if(shouldSave){
            save();
        }
    }
    
    public static void init(){
        DreamscapeQuests.LOGGER.info("Loading questing information");
    }
    
    public void save(){
        questTree.save();
        try {
            NbtIo.writeCompressed(nbt, FILE);
        } catch (IOException e){
            DreamscapeQuests.LOGGER.error("Unable to write Quest NBT data to file!");
        }
    }
    
    public QuestTree getQuestTree() {
        return questTree;
    }
    
    public void clear(){
        questTree.clear();
        save();
    }
    
    public void setEdit(PlayerEntity player, boolean canEdit){
        questTree.setEdit(player.getUuid(), canEdit);
    }
    
    public enum ErrorCodes {
        FAILED_TO_OPEN;
    }
    
    private static class SaveAction implements GUIUtil.GenericPressAction {
        @Override
        public <T extends Element> void onPress(T widget) {
            INSTANCE.save();
        }
    }
    
    public static class AddChapterAction implements GUIUtil.GenericPressAction {
        private QuestGUIScreen screen;
        
        public AddChapterAction(QuestGUIScreen screen){
            this.screen = screen;
        }
        
        @Override
        public <T extends Element> void onPress(T widget) {
            INSTANCE.getQuestTree().addBlankChapter();
            updateClient();
        }
        
        @Environment(EnvType.CLIENT)
        private void updateClient(){
            screen.refreshChapters(true);
        }
    }
}
