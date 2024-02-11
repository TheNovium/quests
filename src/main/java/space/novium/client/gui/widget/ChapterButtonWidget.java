package space.novium.client.gui.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import space.novium.util.GUIUtil;

@Environment(EnvType.CLIENT)
public class ChapterButtonWidget extends DrawableHelper implements Drawable, Element, Selectable {
    protected Identifier texture;
    protected Text text;
    protected int width;
    protected int height;
    public int x;
    public int y;
    protected boolean hovered;
    public boolean active = true;
    public boolean pressed = false;
    public boolean visible = true;
    private boolean focused;
    protected float alpha;
    protected int bgColor;
    protected int bgColorHover;
    protected int bgColorPressed;
    protected GUIUtil.GenericPressAction onPress;
    
    public ChapterButtonWidget(int x, int y, int width, int height, Identifier icon, Text text, GUIUtil.GenericPressAction onPress, int bgColor, int bgColorHover, int bgColorPressed){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.texture = icon;
        this.text = text;
        this.onPress = onPress;
        this.bgColor = bgColor;
        this.bgColorHover = bgColorHover;
        this.bgColorPressed = bgColorPressed;
    }
    
    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        if(visible){
            hovered = isMouseOver(mouseX, mouseY);
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
            fill(matrices, x, y, x + width, y + height, color);
            drawTexture(matrices, x + 1, y + 1, 0, 0, width - 2, height - 2, width - 2, height - 2);
        }
    }
    
    @Override
    public SelectionType getType() {
        return focused ? SelectionType.FOCUSED : hovered ? SelectionType.HOVERED : SelectionType.NONE;
    }
    
    @Override
    public void appendNarrations(NarrationMessageBuilder builder) {
    
    }
    
    public boolean isMouseOver(double mouseX, double mouseY){
        return mouseX >= (double)x && mouseY >= (double)y && mouseX < (double)(x + width) && mouseY < (double)(y + height);
    }
}
