package org.wojo.wojosToolbelt.Events;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.event.IEvent;
import com.hypixel.hytale.event.IEventDispatcher;
import com.hypixel.hytale.server.core.HytaleServer;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;

public record SwapQuickAccessItemEvent (
        @Nonnull Ref<EntityStore> playerRef,
        Store<EntityStore> store,
        short sourceInventoryPosition,
        short equippedPosition,
        short targetPosition
) implements IEvent<Void> { // No Return

    public static void dispatch(Ref<EntityStore> playerRef,
                                Store<EntityStore> store,
                                short sourceInventoryPosition,
                                short equippedPosition,
                                short targetPosition) {

        IEventDispatcher<SwapQuickAccessItemEvent, SwapQuickAccessItemEvent> dispatcher =
                HytaleServer.get().getEventBus().dispatchFor(SwapQuickAccessItemEvent.class);

        if (dispatcher.hasListener()) {
            dispatcher.dispatch(new SwapQuickAccessItemEvent(playerRef, store, sourceInventoryPosition, equippedPosition, targetPosition));
        }
    }
}
