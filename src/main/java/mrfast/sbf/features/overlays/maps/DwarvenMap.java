package mrfast.sbf.features.overlays.maps;

import java.util.HashMap;

import mrfast.sbf.utils.GuiUtils;
import org.lwjgl.opengl.GL11;

import mrfast.sbf.SkyblockFeatures;
import mrfast.sbf.core.SkyblockInfo;
import mrfast.sbf.gui.components.Point;
import mrfast.sbf.gui.components.UIElement;
import mrfast.sbf.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
public class DwarvenMap {
    public static final ResourceLocation map = new ResourceLocation("skyblockfeatures","map/dwarven.png");
    public static final ResourceLocation playerIcon = new ResourceLocation("skyblockfeatures","map/mapIcon.png");

    static boolean loaded = false;
    static int ticks = 0;
    public static HashMap<String,BlockPos> locations = new HashMap<>();
    @SubscribeEvent
    public void onLoad(WorldEvent.Load event) {
        locations.clear();
        loaded = false;
        ticks = 0;
    }
    
    
    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if(Minecraft.getMinecraft().thePlayer != null && Minecraft.getMinecraft().theWorld != null && SkyblockFeatures.config.dwarvenMinesMap) {
            ticks++;
            if(ticks >= 40) {
                loaded = true;
                ticks = 0;
            }
        }
    }

    static double lastPlayerX = 0;
    static double lastPlayerZ = 0;
    static double lastPlayerR = 0;

    static {
        new DwarvenMinesMapGui();
    }   
    public static class DwarvenMinesMapGui extends UIElement {
        public DwarvenMinesMapGui() {
            super("Dwarven Mines Map", new Point(0.86041665f, 0.0f));
            SkyblockFeatures.GUIMANAGER.registerElement(this);
        }

        @Override
        public void drawElement() {
            try {
                if (loaded) {
                    GlStateManager.pushMatrix(); 
                        GlStateManager.enableBlend();
                        GlStateManager.color(1, 1, 1, 1);
                        GlStateManager.pushMatrix();
                            Utils.GetMC().getTextureManager().bindTexture(map);
                            GuiUtils.drawTexturedRect(0, 0, 735/4,804/4, 0, 1, 0, 1, GL11.GL_NEAREST);
                        GlStateManager.popMatrix();
                        GlStateManager.pushMatrix();
                        GlStateManager.popMatrix();
                        EntityPlayerSP player = Utils.GetMC().thePlayer;
                        double x = lastPlayerX;
                        double z = lastPlayerZ;
                        double rotation = lastPlayerR;
                        System.out.println();
                        double newX = Math.round((player.posX+245)/2.5);
                        double newZ = Math.round((player.posZ+170)/2.5);
                        double newRotation = player.rotationYawHead;

                        double deltaX = newX-x;
                        double deltaZ = newZ-z;
                        double deltaR = newRotation-rotation;

                        x+=deltaX/50;
                        z+=deltaZ/50;
                        rotation+=deltaR/50;

                        lastPlayerX = x;
                        lastPlayerZ = z;
                        lastPlayerR = rotation;

                        GlStateManager.color(1, 1, 1, 1);
                        Utils.GetMC().getTextureManager().bindTexture(playerIcon);
                        GlStateManager.pushMatrix();
                            GlStateManager.translate(x, z, 0);
                            GlStateManager.rotate(player.rotationYawHead-180, 0, 0, 1);
                            GlStateManager.translate(-x, -z, 0);
                            GuiUtils.drawTexturedRect((float)(x-2.5),(float) (z-3.5), 5, 7, 0, 1, 0, 1, GL11.GL_NEAREST);
                        GlStateManager.popMatrix();
                    GlStateManager.popMatrix();
                }
            } catch (Exception e) {
                e.printStackTrace();
                //TODO: handle exception
            }
        }
        @Override
        public void drawElementExample() {
            GlStateManager.pushMatrix();
                GlStateManager.enableBlend();
                GlStateManager.color(1, 1, 1, 1);
                GlStateManager.pushMatrix();
                    Utils.GetMC().getTextureManager().bindTexture(map);
                    GuiUtils.drawTexturedRect(0, 0, 735/4,804/4, 0, 1, 0, 1, GL11.GL_NEAREST);
                GlStateManager.popMatrix();

                double x = Math.round((323-202)/4.9);
                double z = Math.round((621-202)/4.9);
                GlStateManager.pushMatrix();
                    Utils.GetMC().getTextureManager().bindTexture(playerIcon);
                    GlStateManager.translate(x, z, 0);
                    GlStateManager.rotate(-128, 0, 0, 1);
                    GlStateManager.translate(-x, -z, 0);
                    GuiUtils.drawTexturedRect((float)(x-2.5),(float) (z-3.5), 5, 7, 0, 1, 0, 1, GL11.GL_NEAREST);
                GlStateManager.popMatrix();
            GlStateManager.popMatrix();
        }

        @Override
        public boolean getToggled() {
            return SkyblockFeatures.config.dwarvenMinesMap;
        }

        @Override
        public boolean getRequirement() {
            return Utils.inSkyblock && SkyblockInfo.map.equals("Dwarven Mines");
        }

        @Override
        public int getHeight() {
            return 804/4;
        }

        @Override
        public int getWidth() {
            return 735/4;
        }
    }
}