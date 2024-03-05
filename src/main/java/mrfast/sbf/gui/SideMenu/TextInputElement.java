package mrfast.sbf.gui.SideMenu;

import mrfast.sbf.utils.Utils;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class TextInputElement extends CustomElement {
    public GuiTextField textField;
    public TextInputElement(int x, int y,String text, int width, int height) {
        super(x, y, width, height, null, null);
        this.textField = new GuiTextField(-1, Utils.GetMC().fontRendererObj,x,y,width,height);

        if(text!=null) {
            textField.setText(text);
            textField.setCursorPosition(0);
        }
    }

    @Override
    public void onClick(int mouseX, int mouseY, int mouseButton) {
        textField.setFocused(true);
        textField.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void onKeyTyped(char character,int key) {
        textField.textboxKeyTyped(character, key);
    }
    int updateCheck = 0;
    @Override
    public void render() {
        GL11.glColor4f(1f,1f,1f,1f);
        textField.xPosition = this.x;
        textField.yPosition = this.y;

        // Handle this differently because it wont draw at 0,0 for some reason
        GlStateManager.translate(-this.parent.x,-this.parent.y,0);
        textField.drawTextBox();
        GlStateManager.translate(this.parent.x,this.parent.y,0);

        updateCheck++;
        if(updateCheck%15==0) {
            textField.updateCursorCounter();
            updateCheck = 0;
        }
    }
}