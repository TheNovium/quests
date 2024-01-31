package space.novium;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import space.novium.data.gen.ItemDataGen;
import space.novium.data.gen.LanguageDataGen;
import space.novium.data.gen.ModelDataGen;

public class DreamscapeQuestsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator gen) {
		gen.addProvider((p) -> {return new ItemDataGen(gen, null);});
		gen.addProvider((p) -> {return new LanguageDataGen(gen);});
		gen.addProvider((p) -> {return new ModelDataGen(gen);});
	}
}
