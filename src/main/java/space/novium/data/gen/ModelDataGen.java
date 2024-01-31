package space.novium.data.gen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import space.novium.item.ModItems;

public class ModelDataGen extends FabricModelProvider {
    public ModelDataGen(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }
    
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
    
    }
    
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.QUEST_SCROLL, Models.GENERATED);
    }
}
