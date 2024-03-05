package mrfast.sbf.core;

import java.util.*;

import org.apache.commons.lang3.time.StopWatch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import mrfast.sbf.SkyblockFeatures;
import mrfast.sbf.utils.NetworkUtils;
import mrfast.sbf.utils.ItemUtils;
import mrfast.sbf.utils.Utils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class PricingData {
    public static final HashMap<String, Double> lowestBINs = new HashMap<>();
    public static final List<ItemUtils.Attribute> attributeAuctions = new ArrayList<>();
    public static final HashMap<String, Double> averageLowestBINs = new HashMap<>();
    public static final HashMap<String, Double> bazaarPrices = new HashMap<>();
    public static final HashMap<String, Integer> npcSellPrices = new HashMap<>();

    static JsonObject auctionPricesJson = null;
    public static final StopWatch reloadTimer = new StopWatch();

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static int getTier(String str) {
        int tier = 0;
        switch (str) {
            case "UNCOMMON":
                tier = 1;
                break;
            case "RARE":
                tier = 2;
                break;
            case "EPIC":
                tier = 3;
                break;
            case "LEGENDARY":
                tier = 4;
                break;
            case "MYTHIC":
                tier = 5;
                break;
        }
        return tier;
    }

    public static String getIdentifier(ItemStack item) {
        NBTTagCompound extraAttr = ItemUtils.getExtraAttributes(item);
        if (extraAttr == null) return null;

        String id = ItemUtils.getSkyBlockItemID(extraAttr);
        if (id == null) return null;

        switch (id) {
            case "PET":
                if (extraAttr.hasKey("petInfo")) {
                    JsonObject petInfo = gson.fromJson(extraAttr.getString("petInfo"), JsonObject.class);
                    if (petInfo !=null && petInfo.has("type") && petInfo.has("tier")) {
                        id = petInfo.get("type").getAsString() + ";" + getTier(petInfo.get("tier").getAsString());
                    }
                }
                break;
            case "ENCHANTED_BOOK":
                if (extraAttr.hasKey("enchantments")) {
                    NBTTagCompound enchants = extraAttr.getCompoundTag("enchantments");
                    if (!enchants.hasNoTags()) {
                        String enchant = enchants.getKeySet().iterator().next();
                        id = "ENCHANTMENT_" + enchant.toUpperCase(Locale.US) + "_" + enchants.getInteger(enchant);
                    }
                }
                break;
            case "UNIQUE_RUNE":
            case "RUNE":
                if(extraAttr.hasKey("runes")) {
                    String runeType = Optional.ofNullable(extraAttr.getCompoundTag("runes"))
                            .map(NBTTagCompound::getKeySet)
                            .flatMap(keys -> keys.stream().findFirst())
                            .orElse(null);
                    id=runeType+"_RUNE;"+extraAttr.getCompoundTag("runes").getInteger(runeType);
                }
                break;
        }

        return id;
    }

    public static JsonObject getItemAuctionInfo(String internalname) {
        if (auctionPricesJson == null) return null;
        JsonElement e = auctionPricesJson.get(internalname);
        if (e == null) {
            return null;
        }
        return e.getAsJsonObject();
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START || !Utils.inSkyblock || Utils.GetMC().theWorld == null) return;
        int reloadTime = 5 * 60 * 1000;
        if (reloadTimer.getTime() >= reloadTime || !reloadTimer.isStarted()) {
            if (reloadTimer.getTime() >= reloadTime) reloadTimer.reset();
            reloadTimer.start();

            // Load lowest bins - Taken from NotEnoughUpdates
            new Thread(() -> {
                JsonObject data = NetworkUtils.getJSONResponse("https://moulberry.codes/lowestbin.json");
                for (Map.Entry<String, JsonElement> items : data.entrySet()) {
                    lowestBINs.put(items.getKey(), Math.floor(items.getValue().getAsDouble()));
                }
                // Use 1 day average for average price because its more accurate
                AuctionUtil.getMyApiGZIPAsync("https://moulberry.codes/auction_averages_lbin/1day.json.gz", (jsonObject) -> {
                    for (Map.Entry<String, JsonElement> items : jsonObject.entrySet()) {
                        averageLowestBINs.put(items.getKey(), Math.floor(items.getValue().getAsDouble()));
                    }
                }, () -> {
                });
            }).start();

            // Get extra auction data, like sales per day
            if (SkyblockFeatures.config.auctionGuis || SkyblockFeatures.config.aucFlipperEnabled) {
                new Thread(() -> {
                    AuctionUtil.getMyApiGZIPAsync("https://moulberry.codes/auction_averages/3day.json.gz", (jsonObject) -> {
                        auctionPricesJson = jsonObject;
                    }, () -> {
                    });
                }).start();
            }

//            DISABLED UNTIL SBU DEV
//            if(attributeAuctions.isEmpty()) {
//                new Thread(() -> {
//                    String[] headers = new String[]{"UUID="+Utils.GetMC().thePlayer.getUniqueID().toString(),"IGN="+Utils.GetMC().thePlayer.getName(),"User-Agent=SBU"};
//                    JsonObject data = APIUtils.getJSONResponse("https://mastermindgolem.pythonanywhere.com/?auctions=mb",headers,true);
//                    if(data==null) {
//                        Utils.sendMessage("Failed to load attribute prices");
//                        return;
//                    }
//                    JsonArray auctions = data.get("auctions").getAsJsonArray();
//                    for (JsonElement entry : auctions) {
//                        JsonObject product = entry.getAsJsonObject();
//                        Double sellPrice = Math.floor(product.get("starting_bid").getAsDouble());
//                        String item_bytes = product.get("item_bytes").getAsString();
//                        Base64InputStream is = new Base64InputStream(new ByteArrayInputStream(item_bytes.getBytes(StandardCharsets.UTF_8)));
//                        NBTTagCompound nbt = null;
//                        try {
//                            nbt = CompressedStreamTools.readCompressed(is);
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                        NBTTagCompound extraAttributes = nbt.getTagList("i", 10).getCompoundTagAt(0).getCompoundTag("tag").getCompoundTag("ExtraAttributes");
//
//                        for (ItemUtils.Attribute attribute : ItemUtils.getAttributes(extraAttributes)) {
//                            attribute.value = sellPrice.longValue();
//                            attribute.pricePerTier = (long) (attribute.value/Math.pow(2,attribute.lvl));
//                            attributeAuctions.add(attribute);
//                        }
//                    }
//                }).start();
//            }

            // Get bazaar prices
            if (bazaarPrices.isEmpty()) {
                new Thread(() -> {
                    JsonObject data = NetworkUtils.getJSONResponse("https://api.hypixel.net/skyblock/bazaar",new String[]{},true,false);
                    JsonObject products = data.get("products").getAsJsonObject();
                    for (Map.Entry<String, JsonElement> entry : products.entrySet()) {
                        if (entry.getValue().isJsonObject()) {
                            JsonObject product = entry.getValue().getAsJsonObject();
                            JsonObject quickStatus = product.get("quick_status").getAsJsonObject();
                            Double sellPrice = Math.floor(quickStatus.get("sellPrice").getAsDouble());
                            String id = quickStatus.get("productId").toString().split(":")[0];
                            bazaarPrices.put(id.replace("\"", ""), sellPrice);
                        }
                    }
                }).start();
            }
            // Get NPC sell prices
            if (npcSellPrices.isEmpty()) {
                Utils.setTimeout(() -> {
                    new Thread(() -> {
                        JsonObject data = NetworkUtils.getJSONResponse("https://api.hypixel.net/resources/skyblock/items#NpcSellPrices",new String[]{},true,false);
                        JsonArray items = data.get("items").getAsJsonArray();
                        for (JsonElement item : items) {
                            JsonObject json = item.getAsJsonObject();
                            if (json.has("npc_sell_price") && json.has("id")) {
                                npcSellPrices.put(json.get("id").getAsString(), json.get("npc_sell_price").getAsInt());
                            }
                        }
                    }).start();
                }, 1000);
            }
        }
    }
}
