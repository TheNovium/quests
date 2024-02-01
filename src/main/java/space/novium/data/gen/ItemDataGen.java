package space.novium.data.gen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;
import space.novium.DreamscapeQuests;
import space.novium.util.registration.ModItems;

public class ItemDataGen extends FabricTagProvider.ItemTagProvider {
    public ItemDataGen(FabricDataGenerator dataGenerator, @Nullable BlockTagProvider blockTagProvider) {
        super(dataGenerator, blockTagProvider);
    }
    
    private static final TagKey<Item> QUEST_ITEMS = TagKey.of(Registry.ITEM_KEY, DreamscapeQuests.modName("quest_items"));
    
    @Override
    protected void generateTags() {
        getOrCreateTagBuilder(QUEST_ITEMS)
                .add(ModItems.QUEST_SCROLL);
    }
}
