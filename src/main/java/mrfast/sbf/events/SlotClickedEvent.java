package mrfast.sbf.events;

import mrfast.sbf.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class SlotClickedEvent extends Event {
    public GuiChest chest;
    public Gui gui;
    public IInventory inventory;
    public String chestName;
    public Slot slot;
    public int slotId;
    public ItemStack item;

    public SlotClickedEvent(GuiContainer container, Slot slot2) {
        if (!(Minecraft.getMinecraft().currentScreen instanceof GuiChest)) {
            this.chest = null;
            this.gui=Minecraft.getMinecraft().currentScreen;
            this.chestName = "";
            this.inventory = Utils.GetMC().thePlayer.inventory;
        } else {
            GuiChest chest = (GuiChest) Minecraft.getMinecraft().currentScreen;
            this.chest = chest;
            this.chestName = ((ContainerChest) chest.inventorySlots).getLowerChestInventory().getName();
            this.inventory = ((ContainerChest) container.inventorySlots).getLowerChestInventory();
        }
        if(slot2!=null) {
            this.slot = slot2;
            this.slotId = slot2.getSlotIndex();
            this.item = slot2.getStack();
        }
    }

}