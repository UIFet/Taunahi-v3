package mrfast.sbf.gui.components;

import mrfast.sbf.SkyblockFeatures;
import mrfast.sbf.gui.GuiManager;

/**
 * Modified from Skytils 0.x under GNU Affero General Public License v3.0
 * https://github.com/Skytils/SkytilsMod/tree/0.x
 *
 * @author Sychic
 */
public abstract class UIElement {
    String name;
    Point pos;

    public UIElement(String name, Point fp) {
        this.name = name;
        this.pos = GuiManager.GuiPositions.getOrDefault(name, fp);
    }

    public abstract void drawElement();

    public abstract void drawElementExample();

    public abstract boolean getToggled();

    public abstract boolean getRequirement();

    public Point getPos() {
        return this.pos;
    }

    public void setPos(float x, float y) {
        this.pos = new Point(x, y);
    }

    public String getName() {
        return this.name;
    }

    public float getX() {
        return getPos().getX();
    }

    public float getY() {
        return getPos().getY();
    }

    public abstract int getHeight();

    public abstract int getWidth();

    public void register() {
        SkyblockFeatures.GUIMANAGER.registerElement(this);
    }
}
