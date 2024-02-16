package space.novium.quest;

import net.minecraft.nbt.NbtByte;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtString;
import net.minecraft.util.Identifier;
import space.novium.util.NbtUtils;

import java.util.List;
import java.util.UUID;

public class QuestChapter {
    private NbtCompound nbt;
    private String title;
    private UUID uuid;
    private String desc;
    private Identifier icon;
    private boolean visible;
    private int chapterNum;
    private List<Quest> quests;
    private boolean dirty;
    
    public QuestChapter(NbtCompound nbt){
        this.nbt = nbt;
        title = NbtUtils.getOrDefault(nbt, "title", "ERROR READING TITLE");
        uuid = nbt.getUuid("uuid");
        desc = NbtUtils.getOrDefault(nbt, "desc", "ERROR READING DESC");
        icon = new Identifier(NbtUtils.getOrDefault(nbt, "icon", "dreamscapequests:gui/close_button"));
        chapterNum = NbtUtils.getOrDefault(nbt, "chapter_number", -1);
        dirty = false;
    }
    
    public static QuestChapter createBlankChapter(int chNum){
        NbtCompound chapterInfo = new NbtCompound();
        chapterInfo.putString("title", "New Chapter");
        chapterInfo.putUuid("uuid", UUID.randomUUID());
        chapterInfo.putString("desc", "Temporary Description");
        chapterInfo.putString("icon", "minecraft:nether_star");
        chapterInfo.putBoolean("visible", true);
        chapterInfo.putInt("chapter_number", chNum);
        chapterInfo.put("quests", new NbtCompound());
        QuestChapter chapter = new QuestChapter(chapterInfo);
        chapter.setDirty();
        return chapter;
    }
    
    public String getTitle(){
        return title;
    }
    
    public int getChapterNum() {
        return chapterNum;
    }
    
    public void setChapterNum(int n){
        chapterNum = n;
        setDirty();
    }
    
    public boolean isDirty() {
        return dirty;
    }
    
    public boolean isVisible() {
        return visible;
    }
    
    public Identifier getIcon(){
        return icon;
    }
    
    public UUID getUUID(){
        return uuid;
    }
    
    private void setDirty(){
        dirty = true;
    }
    
    public NbtCompound save(){
        if(dirty){
            nbt.put("title", NbtString.of(title));
            nbt.putUuid("uuid", uuid);
            nbt.put("desc", NbtString.of(desc));
            nbt.put("icon", NbtString.of(icon.toString()));
            nbt.put("quests", new NbtCompound());
            nbt.put("visible", NbtByte.of(visible));
        }
        return nbt;
    }
    
    public static boolean isValidChapter(NbtCompound data){
        return NbtUtils.containsAll(data, "title", "uuid", "desc", "icon", "visible", "chapter_number", "quests");
    }
}
