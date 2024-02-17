package space.novium.quest;

import net.minecraft.nbt.NbtCompound;
import space.novium.util.NbtUtils;

import java.util.*;

public class QuestTree {
    private String title;
    private List<QuestChapter> chapters;
    private NbtCompound nbt;
    private Map<UUID, Boolean> whoCanEdit;
    
    public QuestTree(NbtCompound nbt){
        this.nbt = nbt;
        NbtCompound ch = NbtUtils.getOrDefault(nbt, "chapters", new NbtCompound());
        title = NbtUtils.getOrDefault(nbt, "title", "Dreamscape Quests");
        int num = ch.getSize();
        chapters = new ArrayList<>(num);
        for(int i = 0; i < num; i++){
            chapters.add(QuestChapter.createBlankChapter(-1));
        }
        List<QuestChapter> toAdd = new ArrayList<>();
        for(String s : ch.getKeys()){
            NbtCompound chapterInfo = ch.getCompound(s);
            if(QuestChapter.isValidChapter(chapterInfo)){
                QuestChapter c = new QuestChapter(chapterInfo);
                chapters.set(c.getChapterNum(), c);
            } else {
                toAdd.add(QuestChapter.createBlankChapter(-1));
            }
        }
        for(QuestChapter c : toAdd){
            c.setChapterNum(chapters.size());
            chapters.add(c);
        }
        whoCanEdit = new HashMap<>();
    }
    
    public void addBlankChapter(){
        chapters.add(QuestChapter.createBlankChapter(chapters.size()));
    }
    
    public boolean canEdit(UUID player){
        return whoCanEdit.getOrDefault(player, false);
    }
    
    public void setEdit(UUID player, boolean edit){
        whoCanEdit.put(player, edit);
    }
    
    public void save(){
        int i = 0;
        NbtCompound chapterNBT = nbt.getCompound("chapters");
        for(QuestChapter chapter : chapters){
            if(chapter.isDirty()){
                chapterNBT.put(chapter.getUUID().toString(), chapter.save());
            }
            i++;
        }
    }
    
    public int getChapterCount(){
        return chapters.size();
    }
    
    public QuestChapter getChapter(int c){
        if(c < chapters.size()){
            return chapters.get(c);
        }
        return QuestChapter.createBlankChapter(-1);
    }
    
    public void clear(){
        for(String s : nbt.getKeys()){
            nbt.remove(s);
        }
        NbtCompound newData = generateDefaultNbt();
        for(String s : newData.getKeys()){
            nbt.put(s, newData.get(s));
        }
        chapters = new ArrayList<>();
    }
    
    public static QuestTree generateBlankTree(){
        return new QuestTree(generateDefaultNbt());
    }
    
    public static NbtCompound generateDefaultNbt(){
        NbtCompound data = new NbtCompound();
        data.putInt("version", 1);
        data.putString("title", "New Quest Tree");
        data.putString("description", "Description of quest pack");
        data.put("chapters", new NbtCompound());
        return data;
    }
}
