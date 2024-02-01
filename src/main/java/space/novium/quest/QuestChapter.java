package space.novium.quest;

import net.minecraft.nbt.NbtCompound;
import space.novium.util.NbtUtils;

public class QuestChapter {
    private NbtCompound nbt;
    
    public QuestChapter(NbtCompound nbt){
        this.nbt = nbt;
    }
    
    public static QuestChapter createBlankChapter(){
        NbtCompound chapterInfo = new NbtCompound();
        chapterInfo.putString("title", "New Chapter");
        chapterInfo.putString("desc", "Temporary Description");
        chapterInfo.putInt("version", 1);
        chapterInfo.putString("icon", "minecraft:nether_star");
        chapterInfo.put("quests", new NbtCompound());
        return new QuestChapter(chapterInfo);
    }
    
    public boolean isValidChapter(){
        return NbtUtils.containsAll(nbt, "title", "desc", "version", "icon", "quests");
    }
}
