package space.novium.quest;

import net.minecraft.nbt.NbtCompound;
import space.novium.DreamscapeQuests;
import space.novium.util.NbtUtils;

import java.util.LinkedList;
import java.util.List;

public class QuestTree {
    private String name;
    private List<QuestChapter> chapters;
    private NbtCompound nbt;
    
    public QuestTree(NbtCompound nbt){
        this.nbt = nbt;
        int ch = NbtUtils.getOrDefault(nbt, "chapters", 0);
        chapters = new LinkedList<>();
        name = NbtUtils.getOrDefault(nbt, "title", "Dreamscape Quests");
        for(int i = 0; i < ch; i++){
            String temp = String.valueOf(i);
            if(nbt.contains(temp)){
                NbtCompound chapterInfo = nbt.getCompound(temp);
                QuestChapter tempChapter = new QuestChapter(chapterInfo);
                if(!tempChapter.isValidChapter()){
                    tempChapter = QuestChapter.createBlankChapter();
                }
                chapters.set(i, tempChapter);
            } else {
                DreamscapeQuests.LOGGER.warn("Failed to read chapter " + temp + " NBT data");
            }
        }
    }
    
    public void addBlankChapter(){
        chapters.add(QuestChapter.createBlankChapter());
    }
}
