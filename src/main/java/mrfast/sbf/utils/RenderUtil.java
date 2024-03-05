package mrfast.sbf.utils;

import java.awt.Color;
import java.util.List;

import net.minecraft.client.renderer.*;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

public class RenderUtil {
    public static void drawOutlinedFilledBoundingBox(AxisAlignedBB aabb, Color color, float partialTicks) {
        if(aabb==null) return;
        aabb = aabb.offset(-0.001, -0.001, -0.001);
        aabb = aabb.expand(0.002, 0.002, 0.002);
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);

        double width = Math.max(1-(Utils.GetMC().thePlayer.getDistance(aabb.minX, aabb.minY, aabb.minZ)/10-2),2);
        RenderUtil.drawBoundingBox(aabb, color, partialTicks);
        RenderUtil.drawOutlinedBoundingBox(aabb.offset(-0.001, -0.001, -0.001).expand(0.002, 0.002, 0.002), color, (float)width, partialTicks);
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    public static void drawOutlinedFilledBoundingBox(BlockPos pos, Color color, float partialTicks) {
        AxisAlignedBB aabb = new AxisAlignedBB(pos,pos.add(1, 1, 1));
        drawOutlinedFilledBoundingBox(aabb,color,partialTicks);
    }
    public static void drawWaypoint(BlockPos pos,Color color,String label,float partialTicks,boolean throughWalls) {
        // Beacon
        AxisAlignedBB aabb2 = new AxisAlignedBB(pos.getX()+0.5, pos.getY()+1, pos.getZ()+0.5, pos.getX()+0.5, pos.getY()+100, pos.getZ()+0.5);

        GlStateManager.pushMatrix();
        GlStateManager.pushAttrib();
        if(throughWalls) GlStateManager.disableDepth();
        drawOutlinedFilledBoundingBox(aabb2, color, partialTicks);
        drawOutlinedFilledBoundingBox(pos,color,partialTicks);
        renderWaypointText(pos.add(0, 3, 0),label);
        if(throughWalls) GlStateManager.enableDepth();
        GlStateManager.popAttrib();
        GlStateManager.popMatrix();
    }


    public static void renderWaypointText(BlockPos pos, String text) {
        double x = pos.getX() + 0.5 - Utils.GetMC().getRenderManager().viewerPosX;
        double y = pos.getY() - Utils.GetMC().getRenderManager().viewerPosY;
        double z = pos.getZ() + 0.5 - Utils.GetMC().getRenderManager().viewerPosZ;
    
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;
        double distance = Minecraft.getMinecraft().thePlayer.getDistanceSq(pos.getX(), pos.getY(), pos.getZ());
        double scaleFactor = 0.005 * Math.sqrt(distance); // Adjust the scaleFactor as needed for proper text size
    
        GlStateManager.pushMatrix();
        GlStateManager.pushAttrib();
    
        GlStateManager.disableLighting(); // Disable lighting temporarily
        GlStateManager.disableDepth(); // Disable depth testing for the background rectangle
        GlStateManager.disableTexture2D(); // Disable texture for the background rectangle
        GlStateManager.enableBlend(); // Enable blending for transparency
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    
        GlStateManager.translate(x, y, z);
        GlStateManager.rotate(-Utils.GetMC().getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(Utils.GetMC().getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale(-scaleFactor, -scaleFactor, scaleFactor);
    
        // Background rectangle
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        int stringWidth = fontRenderer.getStringWidth(text);
        int padding = 2;
        int rectWidth = stringWidth + padding * 2;
        int rectHeight = 8 + padding * 2;
        int alpha = 160;
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos(-rectWidth / 2, -1, 0).color(0, 0, 0, alpha).endVertex();
        worldrenderer.pos(-rectWidth / 2, rectHeight - 1, 0).color(0, 0, 0, alpha).endVertex();
        worldrenderer.pos(rectWidth / 2, rectHeight - 1, 0).color(0, 0, 0, alpha).endVertex();
        worldrenderer.pos(rectWidth / 2, -1, 0).color(0, 0, 0, alpha).endVertex();
        tessellator.draw();
    
        GlStateManager.enableTexture2D();
        fontRenderer.drawString(text, -fontRenderer.getStringWidth(text) / 2, padding, 0xFFFFFF);
    
        String distanceText = "(" + (int) Math.sqrt(distance) + "m)";
        int distanceWidth = fontRenderer.getStringWidth(distanceText);
        fontRenderer.drawString(distanceText, -(distanceWidth / 2), padding + rectHeight - 1, 0xFFFFFF);
    
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.enableLighting(); // Restore lighting settings

        GlStateManager.popAttrib();
        GlStateManager.popMatrix();
    
    }
    
    public static void draw3DString(Vec3 pos, String text, float partialTicks) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.thePlayer;
        double x = (pos.xCoord - player.lastTickPosX) + ((pos.xCoord - player.posX) - (pos.xCoord - player.lastTickPosX)) * partialTicks;
        double y = (pos.yCoord - player.lastTickPosY) + ((pos.yCoord - player.posY) - (pos.yCoord - player.lastTickPosY)) * partialTicks;
        double z = (pos.zCoord - player.lastTickPosZ) + ((pos.zCoord - player.posZ) - (pos.zCoord - player.lastTickPosZ)) * partialTicks;
        RenderManager renderManager = mc.getRenderManager();

        float f = 1.6F;
        float f1 = 0.016666668F * f;
        int width = mc.fontRendererObj.getStringWidth(text) / 2;
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        GL11.glNormal3f(0f, 1f, 0f);
        GlStateManager.rotate(-renderManager.playerViewY, 0f, 1f, 0f);
        GlStateManager.rotate(renderManager.playerViewX, 1f, 0f, 0f);
        GlStateManager.scale(-f1, -f1, -f1);
        GlStateManager.disableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        mc.fontRendererObj.drawString(text, -width, 0, 0xFFFFFF);
        GlStateManager.popMatrix();
    }

    public static void draw3DStringWithShadow(Vec3 pos, String str, float partialTicks) {
        EntityPlayer player = Utils.GetMC().thePlayer;
        double x = (pos.xCoord - player.lastTickPosX) + ((pos.xCoord - player.posX) - (pos.xCoord - player.lastTickPosX)) * partialTicks;
        double y = (pos.yCoord - player.lastTickPosY) + ((pos.yCoord - player.posY) - (pos.yCoord - player.lastTickPosY)) * partialTicks;
        double z = (pos.zCoord - player.lastTickPosZ) + ((pos.zCoord - player.posZ) - (pos.zCoord - player.lastTickPosZ)) * partialTicks;
        RenderManager renderManager = Utils.GetMC().getRenderManager();

        FontRenderer fontrenderer = Utils.GetMC().fontRendererObj;
        float f = 1.6F;
        float f1 = 0.016666668F * f;
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x + 0.0F, (float)y, (float)z);
        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale(-f1, -f1, f1);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(false);
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        int i = 0;
        int j = fontrenderer.getStringWidth(str) / 2;
        GlStateManager.disableTexture2D();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos(-j - 1, -1 + i, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
        worldrenderer.pos(-j - 1, 8 + i, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
        worldrenderer.pos(j + 1, 8 + i, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
        worldrenderer.pos(j + 1, -1 + i, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        // fontrenderer.drawStringWithShadow(str, -fontrenderer.getStringWidth(str) / 2, i, 553648127);
        GlStateManager.enableDepth();
        GlStateManager.depthMask(true);
        fontrenderer.drawStringWithShadow(str, -fontrenderer.getStringWidth(str) / 2, i, -1);
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.popMatrix();
    }
    
    public static void drawOutlinedBoundingBox(AxisAlignedBB aabb, Color color, float width, float partialTicks) {
        Entity render = Minecraft.getMinecraft().getRenderViewEntity();

        double realX = render.lastTickPosX + (render.posX - render.lastTickPosX) * partialTicks;
        double realY = render.lastTickPosY + (render.posY - render.lastTickPosY) * partialTicks;
        double realZ = render.lastTickPosZ + (render.posZ - render.lastTickPosZ) * partialTicks;

        GlStateManager.pushMatrix();
        GlStateManager.translate(-realX, -realY, -realZ);

        GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		GlStateManager.disableTexture2D();
        GL11.glLineWidth(width);

        RenderGlobal.drawOutlinedBoundingBox(aabb, color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        GlStateManager.translate(realX, realY, realZ);
        GlStateManager.popMatrix();
    }
    
    public static void drawBoundingBox(AxisAlignedBB aa,Color c, float partialTicks) {
        Entity render = Minecraft.getMinecraft().getRenderViewEntity();
        aa = aa.offset(-0.002, -0.001, -0.002);
        aa = aa.expand(0.004, 0.005, 0.004);
        double realX = render.lastTickPosX + (render.posX - render.lastTickPosX) * partialTicks;
        double realY = render.lastTickPosY + (render.posY - render.lastTickPosY) * partialTicks;
        double realZ = render.lastTickPosZ + (render.posZ - render.lastTickPosZ) * partialTicks;
        GlStateManager.pushMatrix();
        GlStateManager.translate(-realX, -realY, -realZ);
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableLighting();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);

        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        int color = c.getRGB();
        float a = (float)(color >> 24 & 255) / 255.0F;
        a = (float)((double)a * 0.15D);
        float r = (float)(color >> 16 & 255) / 255.0F;
        float g = (float)(color >> 8 & 255) / 255.0F;
        float b = (float)(color & 255) / 255.0F;
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).color(r, g, b, a).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).color(r, g, b, a).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).color(r, g, b, a).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).color(r, g, b, a).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).color(r, g, b, a).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).color(r, g, b, a).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).color(r, g, b, a).endVertex();
        tessellator.draw();

        GlStateManager.translate(realX, realY, realZ);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.popMatrix();
    }

    /**
     * Taken from Danker's Skyblock Mod under GPL 3.0 license
     * https://github.com/bowser0000/SkyblockMod/blob/master/LICENSE
     * @author bowser0000
     */
    public static void draw3DLine(Vec3 pos1, Vec3 pos2, int width, Color color, float partialTicks) {
        Entity render = Minecraft.getMinecraft().getRenderViewEntity();
        WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();

        double realX = render.lastTickPosX + (render.posX - render.lastTickPosX) * partialTicks;
        double realY = render.lastTickPosY + (render.posY - render.lastTickPosY) * partialTicks;
        double realZ = render.lastTickPosZ + (render.posZ - render.lastTickPosZ) * partialTicks;

        GlStateManager.pushMatrix();
        GlStateManager.translate(-realX, -realY, -realZ);
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.disableLighting();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GL11.glLineWidth(width);
        GlStateManager.color(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
        worldRenderer.begin(GL11.GL_LINE_STRIP, DefaultVertexFormats.POSITION);

        worldRenderer.pos(pos1.xCoord, pos1.yCoord, pos1.zCoord).endVertex();
        worldRenderer.pos(pos2.xCoord, pos2.yCoord, pos2.zCoord).endVertex();
        Tessellator.getInstance().draw();

        GlStateManager.translate(realX, realY, realZ);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableLighting();
        GlStateManager.enableTexture2D();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.popMatrix();
    }

    public static void draw3DArrowLine(Vec3 pos1, Vec3 pos2, Color color) {
        double distance = pos1.distanceTo(pos2);
        if (distance > 1.5) {
            Vec3 direction = new Vec3(pos2.xCoord - pos1.xCoord, pos2.yCoord - pos1.yCoord, pos2.zCoord - pos1.zCoord).normalize();
            double arrowSpacing = 1.5; // Spacing between arrow markers
            int numArrows = (int) (distance / arrowSpacing);

            for (int i = 0; i < numArrows; i++) {
                double arrowX = pos1.xCoord + direction.xCoord * (arrowSpacing * i);
                double arrowY = pos1.yCoord + direction.yCoord * (arrowSpacing * i);
                double arrowZ = pos1.zCoord + direction.zCoord * (arrowSpacing * i);
                Vec3 arrowPos = new Vec3(arrowX, arrowY, arrowZ);
                float yaw = (float) MathHelper.wrapAngleTo180_double(Math.atan2(direction.zCoord, direction.xCoord) * 180.0 / Math.PI);
                float pitch = (float) MathHelper.wrapAngleTo180_double(Math.atan2(-direction.yCoord, Math.sqrt(direction.xCoord * direction.xCoord + direction.zCoord * direction.zCoord)) * 180.0 / Math.PI);

                RenderUtil.drawImageInWorld(arrowPos, pitch, yaw, color);
            }
        }
    }

    public static void renderItemStackOnScreen(ItemStack stack, float x, float y,float width,float height) {
        if (stack == null || stack.getItem() == null) {
            return;
        }

        GlStateManager.pushMatrix();
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.enableDepth();
        GlStateManager.depthFunc(GL11.GL_LEQUAL);

        GlStateManager.translate(x, y, 0);
        GlStateManager.scale(width/16f, height/16f, 1.0f);

        Minecraft.getMinecraft().getRenderItem().renderItemIntoGUI(stack, 0, 0);
        Minecraft.getMinecraft().getRenderItem().renderItemOverlays(Utils.GetMC().fontRendererObj, stack,0,0);

        RenderHelper.disableStandardItemLighting();
        GlStateManager.popMatrix();
    }
    private static final ResourceLocation IMAGE_TEXTURE = new ResourceLocation("skyblockfeatures", "path_arrow.png");
    public static void drawImageInWorld(Vec3 start, float pitch, float yaw, Color color) {
        Minecraft mc = Minecraft.getMinecraft();
        double x = start.xCoord;
        double y = start.yCoord;
        double z = start.zCoord;
        double scale = 2;

        GlStateManager.pushAttrib(); // Save OpenGL state
        GlStateManager.pushMatrix();
        GlStateManager.translate(x - mc.getRenderManager().viewerPosX, y - mc.getRenderManager().viewerPosY, z - mc.getRenderManager().viewerPosZ);
        GlStateManager.rotate(-yaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(pitch, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale(scale, scale, scale);
        mc.getTextureManager().bindTexture(IMAGE_TEXTURE);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_CULL_FACE); // Disable face culling

        float red = color.getRed() / 255.0f;
        float green = color.getGreen() / 255.0f;
        float blue = color.getBlue() / 255.0f;

        GL11.glColor4f(red, green, blue, 0.8f);

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2d(0, 1); // Invert the y-axis texture coordinate
        GL11.glVertex3d(0, 0, 0);
        GL11.glTexCoord2d(1, 1);
        GL11.glVertex3d(1, 0, 0);
        GL11.glTexCoord2d(1, 0);
        GL11.glVertex3d(1, 1, 0);
        GL11.glTexCoord2d(0, 0);
        GL11.glVertex3d(0, 1, 0);
        GL11.glEnd();

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_CULL_FACE); // Re-enable face culling
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glColor4f(1f, 1f, 1f, 1f);
        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }
}
