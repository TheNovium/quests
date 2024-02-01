package space.novium;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.novium.data.DataStorage;
import space.novium.item.ModItems;

public class DreamscapeQuests implements ModInitializer {
	public static final String MODID = "dreamscapequests";
	public static final String NAME = "Dreamscape Quests";
	
    public static final Logger LOGGER = LoggerFactory.getLogger("dreamscapequests");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Loading Dreamscape Quest Components");
		ModItems.init();
		DataStorage.init();
	}
	
	public static Identifier modName(String ID){
		return new Identifier(MODID, ID);
	}
}