package space.novium.util;

import net.minecraft.util.Identifier;
import space.novium.DreamscapeQuests;

public class FileHelper {
    public static Identifier loadImageByID(String ID){
        return loadImageByID(DreamscapeQuests.MODID, ID);
    }
    
    public static Identifier loadImageByID(String mod, String ID){
        return new Identifier(mod, "textures/" + ID + ".png");
    }
}
