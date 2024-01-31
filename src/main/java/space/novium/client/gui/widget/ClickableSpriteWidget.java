package space.novium.client.gui.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ClickableSpriteWidget extends DrawableHelper implements Drawable, Element, Selectable {
    protected Identifier texture;
    protected int width;
    protected int height;
    public int x;
    public int y;
    protected boolean hovered;
    public boolean active = true;
    public boolean visible = true;
    protected float alpha = 1.0f;
    private boolean focused;
    protected int bgColor;
    protected int bgColorHover;
    protected int bgColorActive;
    
    public ClickableSpriteWidget(int x, int y, int width, int height, Identifier sprite, int bgColor){
        this(x, y, width, height, sprite, bgColor, bgColor, bgColor);
    }
    
    public ClickableSpriteWidget(int x, int y, int width, int height, Identifier sprite, int bgColor, int bgColorHover){
        this(x, y, width, height, sprite, bgColor, bgColorHover, bgColorHover);
    }
    
    public ClickableSpriteWidget(int x, int y, int width, int height, Identifier sprite, int bgColor, int bgColorHover, int bgColorActive){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.texture = sprite;
        this.bgColor = bgColor;
        this.bgColorHover = bgColorHover;
        this.bgColorActive = bgColorActive;
    }
    
    public int getHeight(){
        return height;
    }
    
    protected int getYImage(boolean hovered){
        if(!this.active){
            return 0;
        } else if(hovered){
            return 2;
        }
        return 1;
    }
    
    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        if(visible){
            hovered = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;
            MinecraftClient client = MinecraftClient.getInstance();
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, texture);
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, alpha);
            int i = this.getYImage(isHovered());
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.enableDepthTest();
            int color = switch (i) {
                case 0 -> bgColorActive;
                case 1 -> bgColor;
                case 2 -> bgColorHover;
                default -> 0;
            };
            fill(matrices, x, y, x + width, y + height,  color);
        }
    }
    
    @Override
    public void appendNarrations(NarrationMessageBuilder builder) {
    
    }
    
    public boolean isHovered(){
        return hovered || focused;
    }
    
    @Override
    public SelectionType getType() {
        return focused ? SelectionType.FOCUSED : hovered ? SelectionType.HOVERED : SelectionType.NONE;
    }
}