package org.wojo.wojosToolbelt.Events;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.event.IEvent;
import com.hypixel.hytale.event.IEventDispatcher;
import com.hypixel.hytale.server.core.HytaleServer;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;

public record ReplaceComponentEvent(
        Ref<EntityStore> playerRef,
        Ref<ChunkStore> itemRef,
        QuickAccessComponent newComponent,
) implements IEvent<Void> { // No Return
    public static void dispatch(Ref<EntityStore> playerRef, Ref<ChunkStore> itemRef, QuickAccessComponent newComponent) {
        IEventDispatcher<ReplaceComponentEvent, ReplaceComponentEvent> dispatcher =
                HytaleServer.get().getEventBus().dispatchFor(ReplaceComponentEvent.class);

        if (dispatcher.hasListener()) {
            dispatcher.dispatch(
                    new ReplaceComponentEvent(playerRef, itemRef, newComponent)
            );
        }
    }
}
