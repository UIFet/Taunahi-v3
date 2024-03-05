package mrfast.sbf.features.trackers;

import com.mojang.realmsclient.gui.ChatFormatting;
import mrfast.sbf.SkyblockFeatures;
import mrfast.sbf.core.PricingData;
import mrfast.sbf.events.SecondPassedEvent;
import mrfast.sbf.events.SkyblockMobEvent;
import mrfast.sbf.features.overlays.maps.CrystalHollowsMap;
import mrfast.sbf.gui.components.Point;
import mrfast.sbf.gui.components.UIElement;
import mrfast.sbf.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AutomatonTracker {
    private static final Minecraft mc = Minecraft.getMinecraft();
    static int Control = 0;
    static int FTX = 0;
    static int Electron = 0;
    static int Robotron = 0;
    static int Superlite = 0;
    static int Synthetic = 0;

    static boolean hidden = true;
    static int kills = 0;
    static int oldKills = 0;
    static int seconds = 0;
    static int totalSeconds = 0;

    public void resetParts() {
        Control = 0;
        FTX = 0;
        Electron = 0;
        Robotron = 0;
        Superlite = 0;
        Synthetic = 0;
    }
    @SubscribeEvent
    public void onload(WorldEvent.Load event) {
        try {
            seconds = 0;
            kills = 0;
            oldKills = 0;
            hidden = true;
            totalSeconds = 0;
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @SubscribeEvent
    public void onSecond(SecondPassedEvent event) {
        if(Minecraft.getMinecraft().thePlayer != null && Minecraft.getMinecraft().theWorld != null && SkyblockFeatures.config.AutomatonTracker) {
            if(oldKills == 0) {
                oldKills = kills;
            }
            if(!hidden) {
                totalSeconds++;
                seconds++;
            }
            if(seconds >= 60) {
                Utils.sendMessage(oldKills +" "+ kills);
                if(oldKills == kills) {
                    hidden = true;
                    totalSeconds=0;
                }
                oldKills = kills;
                seconds = 0;
            }
        }
    }
    @SubscribeEvent
    public void onSbMobSpawn(SkyblockMobEvent.Death event) {
        if(SkyblockFeatures.config.AutomatonTracker && Utils.GetMC().thePlayer.canEntityBeSeen(event.getSbMob().skyblockMob) && event.getSbMob().skyblockMobId.equals("Automaton")) {
            hidden = false;
            kills++;
        }
    }

    @SubscribeEvent
    public void onSecond2(SecondPassedEvent event) {
        if(Utils.GetMC().thePlayer == null || !Utils.inSkyblock || !SkyblockFeatures.config.AutomatonTracker) return;
        resetParts();
        for(int i=0;i<Utils.GetMC().thePlayer.inventory.mainInventory.length;i++) {
            if(Utils.GetMC().thePlayer.inventory.mainInventory[i] != null) {
                ItemStack stack = Utils.GetMC().thePlayer.inventory.mainInventory[i];
                String name = Utils.cleanColor(stack.getDisplayName());
                String item_id = PricingData.getIdentifier(stack);
                if(item_id != null && PricingData.bazaarPrices.containsKey(item_id)) {
                    if(name.contains("Control")) {
                        Control+=stack.stackSize;
                    }
                    if(name.contains("FTX")) {
                        FTX+=stack.stackSize;
                    }
                    if(name.contains("Electron")) {
                        Electron+=stack.stackSize;
                    }
                    if(name.contains("Robotron")) {
                        Robotron+=stack.stackSize;
                    }
                    if(name.contains("Superlite")) {
                        Superlite+=stack.stackSize;
                    }
                    if(name.contains("Synthetic")) {
                        Synthetic += stack.stackSize;
                    }
                }
            }
        }
    }
    static {
        new AutomatonTrackerGui();
    }

    public static class AutomatonTrackerGui extends UIElement {
        public AutomatonTrackerGui() {
            super("Automaton Loot Tracker", new Point(0.0f, 0.49722221f));
            SkyblockFeatures.GUIMANAGER.registerElement(this);
        }

        @Override
        public void drawElement() {
            if (!hidden) {
                String[] lines = {
                    ChatFormatting.GRAY+""+ChatFormatting.BOLD+"Automaton Loot Tracker",
                    ChatFormatting.GREEN+"  Time Elapsed: §r"+Utils.secondsToTime(totalSeconds),
                    ChatFormatting.GREEN+"  Automatons Killed: §r"+Utils.nf.format(kills),
                    ChatFormatting.YELLOW+""+ChatFormatting.BOLD+" Parts",
                    ChatFormatting.BLUE+"  • Control Switch: §r"+Control,
                    ChatFormatting.BLUE+"  • FTX 3070: §r"+FTX,
                    ChatFormatting.BLUE+"  • Electron Transmitter: §r"+Electron,
                    ChatFormatting.BLUE+"  • Robotron Reflector: §r"+Robotron,
                    ChatFormatting.BLUE+"  • Superlite Motor: §r"+Superlite,
                    ChatFormatting.BLUE+"  • Synthetic Heart: §r"+Synthetic
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
            String[] lines = {
                    ChatFormatting.GRAY+""+ChatFormatting.BOLD+"Automaton Loot Tracker",
                    ChatFormatting.GREEN+"  Time Elapsed: §r50m 26s",
                    ChatFormatting.GREEN+"  Automatons Killed: §r"+494,
                    ChatFormatting.YELLOW+""+ChatFormatting.BOLD+" Parts",
                    ChatFormatting.BLUE+"  • Control Switch: §r"+5,
                    ChatFormatting.BLUE+"  • FTX 3070: §r"+9,
                    ChatFormatting.BLUE+"  • Electron Transmitter: §r"+3,
                    ChatFormatting.BLUE+"  • Robotron Reflector: §r"+5,
                    ChatFormatting.BLUE+"  • Superlite Motor: §r"+2,
                    ChatFormatting.BLUE+"  • Synthetic Heart: §r"+5
            };
            int lineCount = 0;
            for(String line:lines) {
                Utils.GetMC().fontRendererObj.drawStringWithShadow(line, 0, lineCount*(mc.fontRendererObj.FONT_HEIGHT),0xFFFFFF);
                lineCount++;
            }
        }

        @Override
        public boolean getToggled() {
            return SkyblockFeatures.config.AutomatonTracker;
        }

        @Override
        public boolean getRequirement() {
            return CrystalHollowsMap.inCrystalHollows && Utils.inSkyblock;
        }

        @Override
        public int getHeight() {
            return Utils.GetMC().fontRendererObj.FONT_HEIGHT*10;
        }

        @Override
        public int getWidth() {
            return Utils.GetMC().fontRendererObj.getStringWidth("  • Electron Transmitter: §r3.");
        }
    }
}
