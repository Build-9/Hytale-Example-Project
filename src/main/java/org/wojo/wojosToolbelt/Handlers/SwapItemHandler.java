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
        WojosQuickAccessPlugin.LOGGER.atInfo().log("EVENT: (Swap Weapon Event) - A player weapon Swap has just happened!");

        // TODO: If we have penalties to a weapon swap they would happen here. (Like Take player stamina, Or Animation, or Particles)

        Ref<EntityStore> player =  event.playerRef();

        // TODO: Add getters for positions
        int sourceInventory = event.sourceInventoryType();
        short sourcePos = event.sourceInventoryPosition();

        int destInventory = event.destInventoryType();
        short destPos = event.destInventoryPosition();



        Store<EntityStore> store = player.getStore();
        QuickAccessComponent qaComp = store.getComponent(player, QuickAccessComponent.getComponentType());
        Player playerComp = store.getComponent(player, Player.getComponentType());

        if (qaComp != null && playerComp != null){
            qaComp.getItemInArray(qaComp.getSwapTargetLocation());

            // TODO: Update to verify input types are pointing at correct location
            ItemContainer mainInventory = playerComp.getInventory().getStorage();
            ItemContainer hotbar = playerComp.getInventory().getHotbar();

            mainInventory.swapItems(sourcePos, hotbar, destPos, Inventory.DEFAULT_HOTBAR_CAPACITY);
            WojosQuickAccessPlugin.LOGGER.atInfo().log("EVENT: (Swap Weapon Event) - Swapped from Inventory: "+sourcePos+" to Hotbar: "+destPos);
        }
    }
}
