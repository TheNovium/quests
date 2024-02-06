package space.novium.client.gui.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import space.novium.util.GUIUtil;

@Environment(EnvType.CLIENT)
public class ClickableSpriteWidget extends DrawableHelper implements Drawable, Element, Selectable {
    protected Identifier texture;
    protected int width;
    protected int height;
    public int x;
    public int y;
    protected boolean hovered;
    public boolean active = true;
    public boolean pressed = false;
    public boolean visible = true;
    protected float alpha = 1.0f;
    private boolean focused;
    protected int bgColor;
    protected int bgColorHover;
    protected int bgColorPressed;
    protected final GUIUtil.GenericPressAction onPress;
    protected Text tooltip;
    
    public ClickableSpriteWidget(int x, int y, int width, int height, Identifier sprite, int bgColor, GUIUtil.GenericPressAction onPress, Text tooltip){
        this(x, y, width, height, sprite, bgColor, bgColor, bgColor, onPress, tooltip);
    }
    
    public ClickableSpriteWidget(int x, int y, int width, int height, Identifier sprite, int bgColor, int bgColorHover, GUIUtil.GenericPressAction onPress, Text tooltip){
        this(x, y, width, height, sprite, bgColor, bgColorHover, bgColorHover, onPress, tooltip);
    }
    
    public ClickableSpriteWidget(int x, int y, int width, int height, Identifier sprite, int bgColor, int bgColorHover, int bgColorPressed, GUIUtil.GenericPressAction onPress, Text tooltip){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.texture = sprite;
        this.bgColor = bgColor;
        this.bgColorHover = bgColorHover;
        this.bgColorPressed = bgColorPressed;
        this.onPress = onPress;
        this.tooltip = tooltip;
    }
    
    public int getHeight(){
        return height;
    }
    
    protected int getYImage(boolean hovered){
        if(pressed){
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
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.enableDepthTest();
            int color = switch(getType()){
                case NONE -> bgColor;
                case HOVERED -> bgColorHover;
                case FOCUSED -> bgColorPressed;
            };
            if(!tooltip.getString().equals("") && hovered){
                renderTooltip(matrices, mouseX, mouseY);
            }
            fill(matrices, x, y, x + width, y + height,  color);
            drawTexture(matrices, x + 1, y + 1, 0, 0, width - 2, height - 2, width - 2, height - 2);
        }
    }
    
    @Override
    public void appendNarrations(NarrationMessageBuilder builder) {
    }
    
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if(clicked(mouseX, mouseY)){
            playDownSound();
            pressed = true;
            focused = true;
            return true;
        }
        return false;
    }
    
    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (clicked(mouseX, mouseY)) {
            onPress.onPress(this);
            focused = false;
            return true;
        }
        return false;
    }
    
    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        boolean temp = isMouseOver(mouseX, mouseY);
        hovered = temp;
        focused = temp;
        return true;
    }
    
    protected boolean clicked(double mouseX, double mouseY){
        return active && visible && isMouseOver(mouseX, mouseY);
    }
    
    public boolean isMouseOver(double mouseX, double mouseY){
        return mouseX >= (double)x && mouseY >= (double)y && mouseX < (double)(x + width) && mouseY < (double)(y + height);
    }
    
    public boolean isHovered(){
        return hovered || focused;
    }
    
    public void playDownSound(){
        MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0f));
    }
    
    @Override
    public SelectionType getType() {
        return focused ? SelectionType.FOCUSED : hovered ? SelectionType.HOVERED : SelectionType.NONE;
    }
    
    public void renderTooltip(MatrixStack matrices, int mouseX, int mouseY){
    }
}