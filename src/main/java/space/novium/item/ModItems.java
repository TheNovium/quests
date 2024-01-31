package space.novium.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import space.novium.DreamscapeQuests;

public class ModItems {
    public static final Item QUEST_SCROLL = register(
            new QuestScroll(new FabricItemSettings()
                    .maxCount(1)
                    .group(ItemGroup.TOOLS)),
            "quest_scroll"
    );
    
    private static <T extends Item> T register(T item, String ID){
        Identifier itemID = DreamscapeQuests.modName(ID);
        return Registry.register(Registry.ITEM, itemID, item);
    }
    
    public static void init(){
        DreamscapeQuests.LOGGER.info("Registering items for " + DreamscapeQuests.MODID);
    }
}
