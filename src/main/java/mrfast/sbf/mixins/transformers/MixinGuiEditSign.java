package mrfast.sbf.mixins.transformers;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import mrfast.sbf.events.DrawSignEvent;
import mrfast.sbf.utils.Utils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraftforge.common.MinecraftForge;

@Mixin(GuiEditSign.class)
public class MixinGuiEditSign extends GuiScreen {
    @Inject(method = "drawScreen", at = @At("RETURN"))
    private void onDrawScreen(int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {
        try {
            MinecraftForge.EVENT_BUS.post(new DrawSignEvent());
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
