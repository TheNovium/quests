package space.novium.client.gui.widget;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import space.novium.util.GUIUtil;

@Environment(EnvType.CLIENT)
public class SpriteWithTextWidget extends DrawableHelper implements Drawable, Element, Selectable {
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
    protected float alpha;
    protected int bgColor;
    protected int bgColorHover;
    protected int bgColorPressed;
    protected GUIUtil.GenericPressAction onPress;
    
    public SpriteWithTextWidget(int x, int y, int width, int height, Identifier icon, Text text, GUIUtil.GenericPressAction onPress, int bgColor, int bgColorHover, int bgColorPressed){
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
    
    }
    
    @Override
    public SelectionType getType() {
        return null;
    }
    
    @Override
    public void appendNarrations(NarrationMessageBuilder builder) {
    
    }
}
