package org.wojo.wojosToolbelt.Handlers;

package org.wojo.wojosToolbelt.Handlers;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.inventory.Inventory;
import com.hypixel.hytale.server.core.inventory.container.CombinedItemContainer;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.wojo.wojosToolbelt.Components.QuickAccessComponent;
import org.wojo.wojosToolbelt.Events.FindItemEvent;
import org.wojo.wojosToolbelt.Events.SwapItemEvent;
import org.wojo.wojosToolbelt.WojosQuickAccessPlugin;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class FindItemHandler implements Consumer<FindItemEvent> {
    @Override
    public void accept(FindItemEvent event) {
        WojosQuickAccessPlugin.LOGGER.atInfo().log("EVENT: (Swap Weapon Event) - A player weapon Swap has just happened! We got the event! User swapped from "+event.oldItemUUID()+" to "+event.newItemUUID());

        // TODO: If we have penalties to a weapon swap they would happen here. (Like Take player stamina, Or Animation, or Particles)

        Ref<EntityStore> player =  event.playerRef();
        String newItem = event.itemUUID();

        Store<EntityStore> store = player.getStore();
        QuickAccessComponent qaComp = store.getComponent(player, QuickAccessComponent.getComponentType());
        Player playerComp = store.getComponent(player, Player.getComponentType());

        if (qaComp != null && playerComp != null){
            qaComp.getItemInArray(qaComp.getSwapTargetLocation());

            int srcInventoryType = Inventory.STORAGE_SECTION_ID;
            ItemContainer mainInventory = playerComp.getInventory().getStorage();
            
            int targetInventoryType = Inventory.STORAGE_SECTION_HOTBAR_ID;
            ItemContainer hotbar = playerComp.getInventory().getHotbar();


            // Search main inventory for item we wanted to swap into the hotbar
            mainInventory.forEach( (position, itemStack ) -> {
                if (itemStack.getItemId().equals(newItem)) {
                    int foundPositionInt = foundPosition.intValue();
                    short foundPositionShort = (short) foundPositionInt;

                    int targetPositionInt = qaComp.getSwapTargetLocation();
                    short targetPositionShort = (short) targetPositionInt;

                    // Dispatch SwapItemEvent
                    SwapItemEvent.dispatch(
                        player,
                        srcInventoryType, foundPositionShort,
                        targetInventoryType, targetPositionInt           
                    );
                    
                    // Edits Lambda, Allows us to search other inventory types if we want.
                    return;
                }
            });

            // TODO: Add backpack search here? Only search other areas if its a creative qa component?
        }
    }
}

