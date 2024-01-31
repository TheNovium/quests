package space.novium.data.gen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import space.novium.item.ModItems;

public class LanguageDataGen extends FabricLanguageProvider {
    public LanguageDataGen(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }
    
    @Override
    public void generateTranslations(TranslationBuilder builder) {
        builder.add(ModItems.QUEST_SCROLL, "Quest Scroll");
    }
}
