package mrfast.sbf.events;

import mrfast.sbf.SkyblockFeatures;
import mrfast.sbf.core.DataManager;
import mrfast.sbf.utils.Utils;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;
import java.util.List;

public class ChatEventListener {
    boolean firstLaunch = true;

    @SubscribeEvent(receiveCanceled = true, priority = EventPriority.HIGHEST)
    public void onChat(ClientChatReceivedEvent event) {
        if (!Utils.isOnHypixel()) return;
        String unformatted = Utils.cleanColor(event.message.getUnformattedText());

        if (event.message.getFormattedText().contains(": ")) {
            if (SkyblockFeatures.config.DisguisePlayersAs == 8 && SkyblockFeatures.config.playerDiguiser && Utils.inSkyblock) {
                String name = event.message.getFormattedText().split(": ")[0];
                String message = event.message.getFormattedText().split(": ")[1];
                StringBuilder monkiMessage = new StringBuilder();
                for (String word : message.split(" ")) {
                    List<String> words = Arrays.asList("Ooh", "ooh", "ah", "Ee", "Hoo", "Grrr", "uuh");
                    monkiMessage.append(words.get(Utils.randomNumber(0, 6))).append(" ");
                }
                event.setCanceled(true);
                Utils.GetMC().thePlayer.addChatMessage(new ChatComponentText(name + ": " + monkiMessage));
            }
        }

        if (unformatted.startsWith("You have joined ") && unformatted.contains("party!") && SkyblockFeatures.config.autoPartyChat) {
            Utils.GetMC().thePlayer.sendChatMessage("/chat p");
            Utils.setTimeout(() -> {
                Utils.sendMessage(EnumChatFormatting.YELLOW + "Auto Joined Party Chat.");
            }, 10);
        }

        // Welcome message
        if (DataManager.dataJson.has("firstLaunch") && firstLaunch) {
            firstLaunch = (boolean) DataManager.getData("firstLaunch");
        }
        if (firstLaunch && unformatted.equals("Welcome to Hypixel SkyBlock!")) {
            Utils.sendMessage("§bThank You for downloading Skyblock Features!§e Do /sbf for config!");
            DataManager.saveData("firstLaunch", false);
        }


        String pattern = "\\b\\w+\\b has sent you a trade request\\. Click here to accept!.*";
        if (unformatted.matches(pattern) && SkyblockFeatures.config.betterTrading && SkyblockFeatures.config.easierTrading) {
            String tradeRequest = unformatted.split(" ")[0];

            event.setCanceled(true);
            IChatComponent notificationText = new ChatComponentText(
                    EnumChatFormatting.GREEN + tradeRequest + " has sent you a trade request." + EnumChatFormatting.AQUA +
                            " Click here " + EnumChatFormatting.GREEN + "to accept!")
                    .setChatStyle(new ChatStyle()
                            .setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/trade " + tradeRequest))
                            .setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText(EnumChatFormatting.GREEN +
                                    "Click here to accept!"))));
            Utils.GetMC().thePlayer.addChatMessage(notificationText);
        }

        if (unformatted.startsWith("The /trade request from") && unformatted.endsWith("expired!") && SkyblockFeatures.config.betterTrading && SkyblockFeatures.config.hideExpired) {
            event.setCanceled(true);
        }
    }
}
