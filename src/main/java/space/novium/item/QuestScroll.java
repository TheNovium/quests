package space.novium.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import space.novium.client.gui.screen.QuestGUIScreen;

public class QuestScroll extends Item {
    public QuestScroll(Item.Settings settings){
        super(settings);
    }
    
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand){
        if(player.getEntityWorld().isClient){
            loadQuestScreen(player);
        }
        return TypedActionResult.success(this.getDefaultStack());
    }
    
    @Environment(EnvType.CLIENT)
    private void loadQuestScreen(PlayerEntity player){
        MinecraftClient.getInstance().setScreen(new QuestGUIScreen(player));
    }
}
