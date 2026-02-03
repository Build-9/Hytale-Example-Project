package org.wojo.wojosToolbelt.Events;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.event.IEvent;
import com.hypixel.hytale.event.IEventDispatcher;
import com.hypixel.hytale.server.core.HytaleServer;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;

public record SwapItemEvent(
        @Nonnull Ref<EntityStore> playerRef,
        int sourceInventoryType,
        short sourceInventoryPosition,
        int destInventoryType,
        short destInventoryPosition
) implements IEvent<Void> { // No Return

    public static void dispatch(Ref<EntityStore> playerRef,
                                int sourceInventoryType, short sourceInventoryPosition,
                                int destInventoryType, short destInventoryPosition) {

        IEventDispatcher<SwapItemEvent, SwapItemEvent> dispatcher =
                HytaleServer.get().getEventBus().dispatchFor(SwapItemEvent.class);

        if (dispatcher.hasListener()) {
            dispatcher.dispatch(new SwapItemEvent(playerRef, sourceInventoryType, sourceInventoryPosition, destInventoryType,destInventoryPosition));
        }
    }
}
