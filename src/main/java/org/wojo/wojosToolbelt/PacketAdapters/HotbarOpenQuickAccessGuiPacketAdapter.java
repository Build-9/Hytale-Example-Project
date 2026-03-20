package org.wojo.wojosToolbelt.PacketAdapters;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.InteractionType;
import com.hypixel.hytale.protocol.Packet;
import com.hypixel.hytale.protocol.packets.interaction.SyncInteractionChain;
import com.hypixel.hytale.protocol.packets.interaction.SyncInteractionChains;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandManager;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.io.adapter.PlayerPacketFilter;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

// Send Packet to player to tell them they are actually holding the original slected item not hotbar 9
import com.hypixel.hytale.protocol.packets.inventory.SetActiveSlot;
import com.hypixel.hytale.server.core.inventory.Inventory;
import org.wojo.wojosToolbelt.Components.QuickAccessComponent;
import org.wojo.wojosToolbelt.Config.QuickAccessConfig;
import org.wojo.wojosToolbelt.WojosQuickAccessPlugin;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

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
        SyncInteractionChains syncPacket = (SyncInteractionChains) packet;
        
        Ref<EntityStore> entityRef = playerRef.getReference();
        if (entityRef != null && entityRef.isValid()) {

            Store<EntityStore> store = entityRef.getStore();
            World world = store.getExternalData().getWorld();

            // Check if player has a quick access component eqiupped
            // TODO: Cant pull data from component store due to thread safty
            Player player = store.getComponent(entityRef, Player.getComponentType());
            UUIDComponent component = store.getComponent(ref, UUIDComponent.getComponentType());
            UUID playerUuid = component.getUuid();

            boolean isEnabled = quickAccessBtnEnabledMap.get(playerUuid);
            int equippedHotbarPos = quickAccessGuiBtnMap.get(playerUuid);

            if (isEnabled == false){return false;}

            // Check is user is trying to swap to equipped position
            for (SyncInteractionChain chain : syncPacket.updates) {
                WojosQuickAccessPlugin.LOGGER.atInfo().log("Looping Sync interaction chain");

               if ( (chain.interactionType == InteractionType.SwapFrom || chain.interactionType == InteractionType.SwapTo)
                       && chain.data != null // data exists
                       && chain.data.targetSlot == equippedHotbarPos // slot to switch too
                       && chain.initial // start of new chain
                   ){
                    WojosQuickAccessPlugin.LOGGER.atInfo().log("In interaction Chain");

                    // Interacting with following comps required us to be threadsafe so update on world thread not network thread.
                    world.execute(() -> {
                        // Revert Selected Hotbar Item
                        revertSelectedHotbarItem(chain.activeHotbarSlot, playerRef);
                        // Open UI
                        openQuickAccessUI(playerRef, equippedHotbarPos);
                    });

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
        Ref<EntityStore> entityRef = playerRef.getReference();
        if (entityRef == null || !entityRef.isValid()){
            WojosQuickAccessPlugin.LOGGER.atInfo().log("Bad entity ref when reverting sleected hotbar item.");
            return;
        }
        Store<EntityStore> store = entityRef.getStore();
        // Update server-side state
        Player player = store.getComponent(entityRef, Player.getComponentType());
        if (player == null || player.getInventory() == null){
            WojosQuickAccessPlugin.LOGGER.atInfo().log("Bad player when getting component");
            return;
        }
        player.getInventory().setActiveHotbarSlot((byte) originalHotbarSlot);
        
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
    private void openQuickAccessUI(PlayerRef playerRef, short hotbar_position){
        playerRef.sendMessage(Message.raw("Showing UI Page with position "+String.valueOf(hotbar_position)));
        // Open QuickAccess UI by using a command
        CommandManager.get().handleCommand(playerRef, "select --event open --pos "+String.valueOf(hotbar_position));
    }
}
