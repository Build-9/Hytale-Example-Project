package org.wojo.wojosToolbelt.HotbarAdapter;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.protocol.Packet;
import com.hypixel.hytale.protocol.packets.interaction.SyncInteractionChain;
import com.hypixel.hytale.protocol.packets.interaction.SyncInteractionChains;
import com.hypixel.hytale.protocol.packets.inventory.InventoryAction;
import com.hypixel.hytale.protocol.packets.inventory.SwitchHotbarBlockSet;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.io.adapter.PlayerPacketFilter;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.bouncycastle.util.Pack;

// Update the hotbar interaction to check if the player it swaping to the same item tey have equipped.
// If they are then open UI.
// NOTE: Use Packet watcher vs Filter to not block the player from swapping weapons.
//      We're adding features to swapping tools
public class HotbarToolbeltPacketAdapter implements PlayerPacketFilter {
    private static final int ABILITY_SLOT = 0;  // Slot index 0 = Key "1"

    @Override
    public boolean test(PlayerRef playerRef, Packet packet) {

        Ref<EntityStore> entityRef = playerRef.getReference();
        if (entityRef != null && entityRef.isValid()) {
            // Ignore Pong packet
            if (packet.getId() != 3 && packet.getId() != 108){
                playerRef.sendMessage(Message.raw("Packet Received: " + packet.getId() + " | " + packet.getClass().getName()));
            }

            if (packet.getId() == 290) {
                playerRef.sendMessage(Message.raw("Good Packet! : " + packet.getId() + " | " + packet.getClass().getName()));
            }
        }else{
            // Bad Ref
        }







        // Step 1, Verify packet it of type we care about
//        if (packet instanceof SyncInteractionChains ||
//            packet instanceof SwitchHotbarBlockSet ||
//            packet instanceof InventoryAction ||
//            packet instanceof SyncInteractionChain) {
//        }


        // Step 2, If packet is a swap packet and we're swapping to an already equipped item. Display toolbelt GUI.
//        for (SyncInteractionChain chain : syncPacketChains.updates) {
//            if ( (chain.interactionType == InteractionType.SwapFrom || chain.interactionType == InteractionType.SwapTo)
//                    && chain.data != null // data exists
//                    && chain.data.targetSlot == ABILITY_SLOT // slot to switch too
//                    && chain.data.targetSlot == chain.activeHotbarSlot // Swaping to what's already equipped?
//                    && !chain.initial
//                ){ //&& chain.initial) { // start of new chain (Don't think we care about this?)
//
//                if (entityRef != null && entityRef.isValid()) {
//                    playerRef.sendMessage(Message.raw("In interaction Chain"));
//
//                    playerRef.sendMessage(Message.raw("Showing UI Page"));
//                    CommandManager.get().handleCommand(playerRef, "WojosToolbelt tsg o");
//                }
//            }
//        }
        return false;
    }


}
