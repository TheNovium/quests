package space.novium.client.gui.widget;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import space.novium.quest.QuestTree;

import java.util.LinkedList;
import java.util.List;

@Environment(EnvType.CLIENT)
public class ChapterMenu {
    private List<ChapterButtonWidget> chapters;
    private QuestTree tree;
    private boolean canEdit;
    
    public ChapterMenu(QuestTree tree, boolean canEdit){
        chapters = new LinkedList<>();
        this.tree = tree;
        this.canEdit = canEdit;
    }
    
    private void buildGUI(){
    
    }
}
