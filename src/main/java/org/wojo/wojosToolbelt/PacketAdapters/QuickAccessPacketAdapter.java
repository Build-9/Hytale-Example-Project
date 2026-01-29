package org.wojo.wojosToolbelt.PacketAdapters;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.InteractionType;
import com.hypixel.hytale.protocol.Packet;
import com.hypixel.hytale.protocol.packets.interaction.SyncInteractionChain;
import com.hypixel.hytale.protocol.packets.interaction.SyncInteractionChains;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.io.adapter.PlayerPacketFilter;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

// Send Packet to player to tell them they are actually holding the original slected item not hotbar 9
import com.hypixel.hytale.protocol.packets.inventory.SetActiveSlot;
import com.hypixel.hytale.server.core.inventory.Inventory;
import org.wojo.wojosToolbelt.WojosQuickAccessPlugin;

// Update the hotbar interaction to check if the player it swaping to the same item tey have equipped.
// If they are then open UI.
// NOTE: Use Packet watcher vs Filter to not block the player from swapping weapons.
//      We're adding features to swapping tools
public class HotbarOpenQuickAccessGuiPacketAdapter implements PlayerPacketFilter {
    private static final int ABILITY_SLOT = 8;  // Slot index 8 = Key "9"

    static int counter = 0;
    // Returns boolean - "blockPacket"
    //    - True: Block Packet
    //    - False: Let Packet Through
    @Override
    public boolean test(PlayerRef playerRef, Packet packet) {

        // 290 == SyncInteractionCain packet ID
        if (packet.getId() != 290 ){
            return false;
        }
        
        // Recast packet as type we care about.
        packet = (SyncInteractionChains) packet;
        
        Ref<EntityStore> entityRef = playerRef.getReference();
        if (entityRef != null && entityRef.isValid()) {

            // TODO: Add check if player has QucikAccessComponent
            Store<EntityStore> store = entityRef.getStore();
            if (store.getComponent(entityRef, WojosQuickAccessPlugin.get().getQuickAccessComponentType()) == null){
                // Try not to spam log
                if (counter % 10 == 0) {
                    WojosQuickAccessPlugin.LOGGER.atInfo().log("Player Does not have a Quick AccessComponent so letting packets through");
                }
                counter++;
                return false;
            }

            playerRef.sendMessage(Message.raw("Good Packet! : " + packet.getId() + " | " + packet.getClass().getName()));
    
            // Check is user is trying to swap to item 9
            for (SyncInteractionChain chain : syncPacketChains.updates) {
               if ( (chain.interactionType == InteractionType.SwapFrom || chain.interactionType == InteractionType.SwapTo)
                       && chain.data != null // data exists
                       && chain.data.targetSlot == ABILITY_SLOT // slot to switch too
                       &&!chain.initial // start of new chain
                   ){
                   playerRef.sendMessage(Message.raw("In interaction Chain"));
                   // && chain.data.targetSlot == chain.activeHotbarSlot We dont care about active slot other then keeping it the same?

                    // Interacting with world comp required us to be threadsafe so update on world thread not network thread.
                    //    - This may not be needed as we dont actually set any world data.
                    //    - we just get data and send a packet to client to update selected item.
                    Store<EntityStore> store = entityRef.getStore();
                    World world = store.getExternalData().getWorld();
                    world.execute(() -> {
                        // Revert Selected Hotbar Item
                        revertSelectedHotbarItem(chain.activeHotbarSlot, playerRef);

                        // Open UI
                        openQuickAccessUI(playerRef);
                    }); // end Threadsafty
                    
                    // Block Packet as we don't want player to actually change to hotbar 9
                    return true;
               } // End chain type
           } // End chain packet for loop
        }// Bad entity Ref

        // Something went wrong or user wasnt pressing key 9, so dont block sync packet. 
        return false;
    }

    // Don't let player select hotbar 9 as its to be used to open the gui
    // Params:
    // - originalHotbarSlot: Hotbar slot the player was swapping from when pressing (open Gui button)
    // - playerRef: Reference to the player entity that hit the open GUI button.
    private void revertSelectedHotbarItem(int originalHotbarSlot, PlayerRef playerRef) {
        // Update server-side state
        Player playerComponent = playerRef.getReference().getStore().getComponent(playerRef, Player.getComponentType());
        playerComponent.getInventory().setActiveHotbarSlot((byte) originalSlot);
        
        // Send packet to force client to the correct slot
        SetActiveSlot setActiveSlotPacket = new SetActiveSlot(
            Inventory.HOTBAR_SECTION_ID,  // -1 indicates the hotbar
            originalHotbarSlot            // The slot index to select
        );
        playerRef.getPacketHandler().write(setActiveSlotPacket);
    }

    // Open the Quick Access Gui 
    // Params:
    // - PlayerRef playerRef: Refrence to the player entity. 
    private void openQuickAccessUI(PlayerRef playerRef){
        playerRef.sendMessage(Message.raw("Showing UI Page"));
        // Open QuickAccess UI by using a command
        CommandManager.get().handleCommand(playerRef, "WojosToolbelt tsg o");
    }
}
