package mrfast.sbf.features.trackers;

import com.mojang.realmsclient.gui.ChatFormatting;

import mrfast.sbf.SkyblockFeatures;
import mrfast.sbf.core.SkyblockInfo;
import mrfast.sbf.events.SecondPassedEvent;
import mrfast.sbf.gui.components.Point;
import mrfast.sbf.gui.components.UIElement;
import mrfast.sbf.utils.GuiUtils;
import mrfast.sbf.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;

public class PowderTracker {
    private static final Minecraft mc = Minecraft.getMinecraft();

    static int treasureChestsFound = 0;
    static int mithrilPowderGained = 0;
    static int gemstonePowderGained = 0;

    static boolean hidden = true;
    static int seconds = 0;
    static int totalSeconds = 0;
    @SubscribeEvent
    public void onload(WorldEvent.Load event) {
        seconds = 0;
        hidden = true;
        treasureChestsFound = 0;
        mithrilPowderGained = 0;
        gemstonePowderGained = 0;
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if(!SkyblockFeatures.config.powderTracker) return;
        String raw = event.message.getUnformattedText();
        if(raw.contains("You uncovered a treasure chest!")) {
            seconds = 300;
            hidden = false;
            treasureChestsFound++;
        }
        if(raw.contains("You received") && raw.contains("Gemstone Powder.")) {
            gemstonePowderGained+= (int) Double.parseDouble(raw.replaceAll("[^0-9]", ""));
        }
        if(raw.contains("You received") && raw.contains("Mithril Powder.")) {
            mithrilPowderGained+= (int) Double.parseDouble(raw.replaceAll("[^0-9]", ""));
        }
    }

    @SubscribeEvent
    public void onSecond(SecondPassedEvent event) {
        if(Minecraft.getMinecraft().thePlayer != null && Minecraft.getMinecraft().theWorld != null && SkyblockFeatures.config.powderTracker) {
            if(!hidden) {
                seconds--;
            }
            if(seconds <= 0) {
                hidden = true;
                treasureChestsFound = 0;
            } else {
                totalSeconds++;
            }
        }
    }

    static {
        new PowderTrackerGui();
    }

    public static class PowderTrackerGui extends UIElement {
        public PowderTrackerGui() {
            super("Powder Mining Tracker", new Point(0.00f, 0.34166667f));
            SkyblockFeatures.GUIMANAGER.registerElement(this);
        }

        @Override
        public void drawElement() {
            if (!hidden && totalSeconds>0) {
                String gemPowderPerHour = Utils.nf.format(Math.floor(((double) 3600 /totalSeconds)*gemstonePowderGained));
                String mithrilPowderPerHour = Utils.nf.format(Math.floor(((double) 3600 /totalSeconds)*mithrilPowderGained));

                String[] lines = {
                    ChatFormatting.YELLOW+""+ChatFormatting.BOLD+"Powder Tracker",
                    ChatFormatting.GREEN+"  Time Elapsed: §r"+Utils.secondsToTime(totalSeconds),
                    ChatFormatting.GREEN+"  Treasure Chests Found: §r"+Utils.nf.format(treasureChestsFound),
                    ChatFormatting.AQUA+""+ChatFormatting.BOLD+" Drops",
                    ChatFormatting.LIGHT_PURPLE+"  • Gemstone Powder: §r"+Utils.nf.format(gemstonePowderGained),
                    ChatFormatting.LIGHT_PURPLE+"  • GP / Hour: §r"+gemPowderPerHour,
                    ChatFormatting.DARK_GREEN+"  • Mithril Powder: §r"+Utils.nf.format(mithrilPowderGained),
                    ChatFormatting.DARK_GREEN+"  • MP / Hour: §r"+mithrilPowderPerHour,
                };
                int lineCount = 0;
                for(String line:lines) {
                    Utils.GetMC().fontRendererObj.drawStringWithShadow(line, 0, lineCount*(mc.fontRendererObj.FONT_HEIGHT),0xFFFFFF);
                    lineCount++;
                }
            }
        }
        @Override
        public void drawElementExample() {
            String gemPowderPerHour = Utils.nf.format(Math.floor(((double) 3600 /937)*283242));
            String mithrilPowderPerHour = Utils.nf.format(Math.floor(((double) 3600 /937)*65424));
            String[] lines = {
                    ChatFormatting.YELLOW+""+ChatFormatting.BOLD+"Powder Tracker",
                    ChatFormatting.GREEN+"  Time Elapsed: §r"+Utils.secondsToTime(937),
                    ChatFormatting.GREEN+"  Treasures Found: §r"+Utils.nf.format(204),
                    ChatFormatting.AQUA+""+ChatFormatting.BOLD+" Drops",
                    ChatFormatting.LIGHT_PURPLE+"  • Gemstone Powder: §r"+Utils.nf.format(283242),
                    ChatFormatting.LIGHT_PURPLE+"  • GP / Hour: §r"+gemPowderPerHour,
                    ChatFormatting.DARK_GREEN+"  • Mithril Powder: §r"+Utils.nf.format(65424),
                    ChatFormatting.DARK_GREEN+"  • MP / Hour: §r"+mithrilPowderPerHour,
            };
            GuiUtils.drawTextLines(Arrays.asList(lines),0,0, GuiUtils.TextStyle.DROP_SHADOW);
        }

        @Override
        public boolean getToggled() {
            return SkyblockFeatures.config.powderTracker;
        }

        @Override
        public boolean getRequirement() {
            return SkyblockInfo.getMap().equals("Crystal Hollows") && Utils.inSkyblock;
        }

        @Override
        public int getHeight() {
            return Utils.GetMC().fontRendererObj.FONT_HEIGHT*9;
        }

        @Override
        public int getWidth() {
            return Utils.GetMC().fontRendererObj.getStringWidth("Treasure Chest Found: §r1234123");
        }
    }
}
