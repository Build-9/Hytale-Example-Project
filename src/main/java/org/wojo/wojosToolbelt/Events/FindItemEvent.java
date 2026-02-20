package org.wojo.wojosToolbelt.Events;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.event.IEvent;
import com.hypixel.hytale.event.IEventDispatcher;
import com.hypixel.hytale.server.core.HytaleServer;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;

public record FindItemEvent(
        @Nonnull Ref<EntityStore> playerRef,
        String itemUUID
) implements IEvent<Void> { // No Return

    public static void dispatch(Ref<EntityStore> playerRef, String itemUUID) {
        IEventDispatcher<FindItemEvent, FindItemEvent> dispatcher =
                HytaleServer.get().getEventBus().dispatchFor(FindItemEvent.class);

        if (dispatcher.hasListener()) {
            dispatcher.dispatch(
                    new FindItemEvent(playerRef, itemUUID)
            );
        }
    }
}
