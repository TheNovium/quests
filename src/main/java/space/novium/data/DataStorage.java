package space.novium.data;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIo;
import space.novium.DreamscapeQuests;
import space.novium.quest.QuestTree;

import java.io.File;
import java.io.IOException;

public class DataStorage {
    public static final File FILE = new File("mods/dreamscapequests/quest_data.nbt");
    public static final DataStorage INSTANCE = new DataStorage();
    private final NbtCompound nbt;
    private final QuestTree questTree;
    
    private DataStorage(){
        NbtCompound temp = new NbtCompound();
        boolean shouldSave = false;
        try {
            FILE.getParentFile().mkdirs();
            if(!FILE.exists()){
                temp = new NbtCompound();
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
        if(shouldSave){
            save();
        }
        questTree = new QuestTree(nbt);
    }
    
    public static void init(){
        DreamscapeQuests.LOGGER.info("Loading questing information");
    }
    
    public void save(){
        try {
            NbtIo.writeCompressed(nbt, FILE);
        } catch (IOException e){
            DreamscapeQuests.LOGGER.error("Unable to write Quest NBT data to file!");
        }
    }
    
    public QuestTree getQuestTree() {
        return questTree;
    }
    
    public enum ErrorCodes {
        FAILED_TO_OPEN;
    }
}
