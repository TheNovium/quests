package space.novium.data;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIo;
import space.novium.DreamscapeQuests;

import java.io.File;
import java.io.IOException;

public class DataStorage {
    public static final File FILE = new File("mods/dreamscapequests/quest_data.nbt");
    public static final DataStorage INSTANCE = new DataStorage();
    private final NbtCompound nbt;
    
    private DataStorage(){
        NbtCompound temp = new NbtCompound();
        try {
            FILE.getParentFile().mkdirs();
            if(!FILE.exists()){
                temp = new NbtCompound();
                save();
            } else {
                temp = NbtIo.readCompressed(FILE);
            }
        } catch (Exception e){
            DreamscapeQuests.LOGGER.error("Unable to read or create Quest NBT data file!");
            temp.putInt("Error", ErrorCodes.FAILED_TO_OPEN.ordinal());
        }
        nbt = temp;
    }
    
    public void save(){
        try {
            NbtIo.writeCompressed(nbt, FILE);
        } catch (IOException e){
            DreamscapeQuests.LOGGER.error("Unable to write Quest NBT data to file!");
        }
    }
    
    public enum ErrorCodes {
        FAILED_TO_OPEN;
    }
}
