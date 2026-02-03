package org.wojo.wojosToolbelt.Handlers;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.inventory.Inventory;
import com.hypixel.hytale.server.core.inventory.container.CombinedItemContainer;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.wojo.wojosToolbelt.Components.QuickAccessComponent;
import org.wojo.wojosToolbelt.Events.SwapItemEvent;
import org.wojo.wojosToolbelt.WojosQuickAccessPlugin;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class SwapItemHandler implements Consumer<SwapItemEvent> {
    @Override
    public void accept(SwapItemEvent event) {
        WojosQuickAccessPlugin.LOGGER.atInfo().log("EVENT: (Swap Weapon Event) - A player weapon Swap has just happened! We got the event! User swapped from "+event.oldItemUUID()+" to "+event.newItemUUID());

        // TODO: If we have penalties to a weapon swap they would happen here. (Like Take player stamina, Or Animation, or Particles)

        Ref<EntityStore> player =  event.playerRef();

        String originalItem = event.oldItemUUID();
        String newItem = event.newItemUUID();

        Store<EntityStore> store = player.getStore();
        QuickAccessComponent qaComp = store.getComponent(player, QuickAccessComponent.getComponentType());
        Player playerComp = store.getComponent(player, Player.getComponentType());

        if (qaComp != null && playerComp != null){
            qaComp.getItemInArray(qaComp.getSwapTargetLocation());

            ItemContainer mainInventory = playerComp.getInventory().getStorage();
            ItemContainer hotbar = playerComp.getInventory().getHotbar();

            int inventoryType = Inventory.STORAGE_SECTION_ID;
            AtomicBoolean foundItem = new AtomicBoolean(false);
            AtomicInteger foundPosition = new AtomicInteger();

            // Only search main inventory for items to swap in & get needed postion info
            mainInventory.forEach( (position, itemStack ) -> {
                if (itemStack.getItemId().equals(newItem)){
                    foundItem.set(true);
                    foundPosition.set(position);
                };
            });


            if (foundItem.get()){
                int foundPositionInt = foundPosition.intValue();
                short foundPositionShort = (short) foundPositionInt;

                int targetPositionInt = qaComp.getSwapTargetLocation();
                short targetPositionShort = (short) targetPositionInt;


                mainInventory.swapItems(foundPositionShort, hotbar, targetPositionShort, Inventory.DEFAULT_HOTBAR_CAPACITY);
            }


        }
    }
}
