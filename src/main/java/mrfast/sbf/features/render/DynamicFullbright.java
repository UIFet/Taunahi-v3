package mrfast.sbf.features.render;

import mrfast.sbf.SkyblockFeatures;
import mrfast.sbf.core.SkyblockInfo;
import mrfast.sbf.features.overlays.maps.CrystalHollowsMap;
import mrfast.sbf.utils.Utils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class DynamicFullbright {
    @SubscribeEvent
    public void onTick(ClientTickEvent event) {
        if(SkyblockFeatures.config.DynamicFullbright) {
            String loc = SkyblockInfo.getMap();
            try {
                if(loc.equals("Dynamic") || CrystalHollowsMap.inCrystalHollows|| Utils.inDungeons) {
                    Utils.GetMC().gameSettings.gammaSetting=((float) SkyblockFeatures.config.DynamicFullbrightDisabled /10);
                } else {
                    Utils.GetMC().gameSettings.gammaSetting= (float) SkyblockFeatures.config.DynamicFullbrightElsewhere /10;
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        if(SkyblockFeatures.config.fullbright) {
            Utils.GetMC().gameSettings.gammaSetting=100;
        }
    }
}
