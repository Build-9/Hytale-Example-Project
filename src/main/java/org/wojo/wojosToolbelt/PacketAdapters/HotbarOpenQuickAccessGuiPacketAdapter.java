package org.wojo.wojosToolbelt.PacketAdapters;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.InteractionType;
import com.hypixel.hytale.protocol.Packet;
import com.hypixel.hytale.protocol.packets.interaction.SyncInteractionChain;
import com.hypixel.hytale.protocol.packets.interaction.SyncInteractionChains;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandManager;
import com.hypixel.hytale.server.core.entity.UUIDComponent;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.inventory.InventoryComponent;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.io.adapter.PlayerPacketFilter;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

// Send Packet to player to tell them they are actually holding the original slected item not hotbar 9
import com.hypixel.hytale.protocol.packets.inventory.SetActiveSlot;
import org.wojo.wojosToolbelt.Components.QuickAccessPlayerComponent;
import org.wojo.wojosToolbelt.QuickAccessUtils.QuickAccessUtils;
import org.wojo.wojosToolbelt.WojosQuickAccessPlugin;
import org.wojo.wojosToolbelt.ui.ItemSelectionGui;

import java.util.UUID;

import static org.wojo.wojosToolbelt.Components.QuickAccessPlayerComponent.*;

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

            if (!quickAccessPlayerUuidMap.containsKey(entityRef)){
                world.execute(() -> {
                    final UUIDComponent component = store.getComponent(entityRef, UUIDComponent.getComponentType());
                    final UUID playerUuid = component.getUuid();
                    quickAccessPlayerUuidMap.put(entityRef, playerUuid);
                });
                return false;
            }

            // Check if player has quick swap enabled
            final UUID playerUuid = quickAccessPlayerUuidMap.get(entityRef);

            // If player doesnt exist in hash map, add them then don't block packet
            if (!quickAccessBtnEnabledMap.containsKey(playerUuid) || !quickAccessHotbarLocationEquipMap.containsKey(playerUuid)){
                quickAccessBtnEnabledMap.put(playerUuid, false);
                quickAccessHotbarLocationEquipMap.put(playerUuid, 8);
                return false;

            // Player exists but button disabled so don't block packed
            } else if (!quickAccessBtnEnabledMap.get(playerUuid)) {
                return false;
            }

            Integer equippedHotbarPos = quickAccessHotbarLocationEquipMap.get(playerUuid);
            // Check is user is trying to swap to quick access equipped position
            for (SyncInteractionChain chain : syncPacket.updates) {
               if ( (chain.interactionType == InteractionType.SwapFrom || chain.interactionType == InteractionType.SwapTo)
                       && chain.data != null // data exists
                       && chain.data.targetSlot == equippedHotbarPos // slot to switch too
                       && chain.initial // start of new chain
                   ){
                    // Interacting with following comps required us to be threadsafe so update on world thread not network thread.
                    world.execute(() -> {
                        // Revert Selected Hotbar Item
                        revertSelectedHotbarItem(chain.activeHotbarSlot, playerRef);

                        // Open UI
                        //openQuickAccessUI(playerRef, equippedHotbarPos.shortValue());
                        QuickAccessUtils.openQuickAccessUI(world.getEntityStore().getStore(), playerRef.getReference());
                    });

                    // Block Packet as we don't want player to actually change to hotbar 9
                   return true;
               } // End chain type
            } // End chain packet for loop
        }// Bad entity Ref

        // Something went wrong or user wasn't pressing key 9, so don't block sync packet.
        return false;
    }

    // Don't let player select hotbar 9 as its to be used to open the gui
    // Params:
    // - originalHotbarSlot: Hotbar slot the player was swapping from when pressing (open Gui button)
    // - playerRef: Reference to the player entity that hit the open GUI button.
    private void revertSelectedHotbarItem(Integer originalHotbarSlot, PlayerRef playerRef) {
        Ref<EntityStore> entityRef = playerRef.getReference();
        if (entityRef == null || !entityRef.isValid()){
            WojosQuickAccessPlugin.LOGGER.atInfo().log("Bad entity ref when reverting selected hotbar item.");
            return;
        }
        Store<EntityStore> store = entityRef.getStore();
        // Update server-side state
        Player player = store.getComponent(entityRef, Player.getComponentType());
        if (player == null || player.getInventory() == null){
            WojosQuickAccessPlugin.LOGGER.atInfo().log("Bad player when getting component");
            return;
        }

        //Intentory playerInventory = store.getComponent(entityRef, Inventory.getComponentType());
        InventoryComponent.Hotbar hotbar = (InventoryComponent.Hotbar) store.getComponent(entityRef, InventoryComponent.getComponentTypeById(InventoryComponent.HOTBAR_SECTION_ID));
        byte hotbarPos = originalHotbarSlot.byteValue();
        hotbar.setActiveSlot(hotbarPos);
        
        // Send packet to force client to the correct slot
        SetActiveSlot setActiveSlotPacket = new SetActiveSlot(
            InventoryComponent.HOTBAR_SECTION_ID,   // -1 indicates the hotbar
            originalHotbarSlot                      // The slot index to select
        );
        playerRef.getPacketHandler().write(setActiveSlotPacket);
    }

    // Open the Quick Access Gui 
    // Params:
    // - PlayerRef playerRef: Refrence to the player entity. 
    private void openQuickAccessUI(PlayerRef playerRef, short hotbar_position) {
        playerRef.sendMessage(Message.raw("Showing UI Page with position " + String.valueOf(hotbar_position)));
        // Open QuickAccess UI by using a command
        //CommandManager.get().handleCommand(playerRef, "wqa gui select --event open");
    }
}
