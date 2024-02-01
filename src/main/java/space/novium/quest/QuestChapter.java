package space.novium.quest;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtString;
import net.minecraft.util.Identifier;
import space.novium.util.NbtUtils;

import java.util.List;

public class QuestChapter {
    private NbtCompound nbt;
    private String title;
    private String desc;
    private Identifier icon;
    private List<Quest> quests;
    private boolean dirty;
    
    public QuestChapter(NbtCompound nbt){
        this.nbt = nbt;
        title = NbtUtils.getOrDefault(nbt, "title", "ERROR READING TITLE");
        desc = NbtUtils.getOrDefault(nbt, "desc", "ERROR READING DESC");
        icon = new Identifier(NbtUtils.getOrDefault(nbt, "icon", "dreamscapequests:gui/close_button"));
        dirty = false;
    }
    
    public static QuestChapter createBlankChapter(){
        NbtCompound chapterInfo = new NbtCompound();
        chapterInfo.putString("title", "New Chapter");
        chapterInfo.putString("desc", "Temporary Description");
        chapterInfo.putString("icon", "minecraft:nether_star");
        chapterInfo.put("quests", new NbtCompound());
        QuestChapter chapter = new QuestChapter(chapterInfo);
        chapter.setDirty();
        return chapter;
    }
    
    public boolean isDirty() {
        return dirty;
    }
    
    private void setDirty(){
        dirty = true;
    }
    
    public NbtCompound save(){
        if(dirty){
            nbt.put("title", NbtString.of(title));
            nbt.put("desc", NbtString.of(desc));
            nbt.put("icon", NbtString.of(icon.toString()));
            nbt.put("quests", new NbtCompound());
        }
        return nbt;
    }
    
    public boolean isValidChapter(){
        return NbtUtils.containsAll(nbt, "title", "desc", "icon", "quests");
    }
}
