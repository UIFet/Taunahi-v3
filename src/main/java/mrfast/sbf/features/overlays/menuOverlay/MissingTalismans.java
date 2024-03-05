package mrfast.sbf.features.overlays.menuOverlay;

import java.util.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.realmsclient.gui.ChatFormatting;

import mrfast.sbf.SkyblockFeatures;
import mrfast.sbf.core.PricingData;
import mrfast.sbf.events.GuiContainerEvent;
import mrfast.sbf.events.GuiContainerEvent.TitleDrawnEvent;
import mrfast.sbf.utils.NetworkUtils;
import mrfast.sbf.utils.GuiUtils;
import mrfast.sbf.utils.Utils;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class MissingTalismans {
    boolean inAccessoryBag = false;
    JsonArray MissingTalismans = null;
    @SubscribeEvent
    public void onCloseWindow(GuiContainerEvent.CloseWindowEvent event) {
        MissingTalismans = null;
        thread=false;
    }
    boolean thread = false;
    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if(!SkyblockFeatures.config.showMissingAccessories) return;

        if(Utils.GetMC().currentScreen != null) {
            if(Utils.GetMC().currentScreen instanceof GuiChest) {
                GuiChest gui = (GuiChest) Utils.GetMC().currentScreen;
                ContainerChest chest = (ContainerChest) gui.inventorySlots;
                IInventory inv = chest.getLowerChestInventory();
                String chestName = inv.getDisplayName().getUnformattedText().trim();
                inAccessoryBag = chestName.contains("Accessory Bag");
            }
        } else {
            inAccessoryBag = false;
        }
        if(inAccessoryBag) {
            if(MissingTalismans==null && !thread) {
                thread = true;
                new Thread(() -> {
                    String username = Utils.GetMC().thePlayer.getName();
                    String uuid = NetworkUtils.getUUID(username);
                    // Find stats of latest profile
                    String latestProfile = NetworkUtils.getLatestProfileID(uuid);
                    if (latestProfile == null) {
                        return;
                    }

                    String profileURL = "https://sky.shiiyu.moe/api/v2/profile/"+username+"#accessoriesOverlay";
                    JsonObject profileResponse = NetworkUtils.getJSONResponse(profileURL);
                    profileResponse = profileResponse.get("profiles").getAsJsonObject();
                    MissingTalismans = profileResponse.get(latestProfile).getAsJsonObject().get("data").getAsJsonObject().get("missingAccessories").getAsJsonObject().get("missing").getAsJsonArray();
                    thread = false;
                }).start();
            }
        }
    }

    @SubscribeEvent
    public void onDrawContainerTitle(TitleDrawnEvent event) {
        if(!SkyblockFeatures.config.showMissingAccessories) return;

        if (event.gui instanceof GuiChest) {
            if(inAccessoryBag) {
                if(MissingTalismans==null) {
                    GuiUtils.drawSideMenu(Collections.singletonList(ChatFormatting.RED + "Waiting for SkyCrypt.."),GuiUtils.TextStyle.DROP_SHADOW);
                    return;
                }
                List<String> formatted = new ArrayList<>();

                formatted.add(ChatFormatting.WHITE+"Missing Talismans ("+MissingTalismans.size()+")");
                LinkedHashMap<String,Integer> accessories = new LinkedHashMap<>();
                LinkedHashMap<String,Integer> sortedMap = new LinkedHashMap<>();

                for(JsonElement item:MissingTalismans) {
                    String id = item.getAsJsonObject().get("name").getAsString();
                    String name = item.getAsJsonObject().get("display_name").getAsString();
                    if(PricingData.lowestBINs.get(id)!=null) {
                        int value = PricingData.lowestBINs.get(id).intValue();
                        accessories.put(name, value);
                    } else {
                        accessories.put(name, 0);
                    }
                }
                accessories.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
                for(String itemName:sortedMap.keySet()) {
                    String price = ChatFormatting.GOLD+" ("+(Utils.nf.format(sortedMap.get(itemName)))+")";
                    if(sortedMap.get(itemName)==0) price=ChatFormatting.RED+" No Price Found";
                    formatted.add(itemName+price);
                }
                GuiUtils.drawSideMenu(formatted, GuiUtils.TextStyle.DROP_SHADOW);
            }
        }
    }
}
