package org.wojo.wojosToolbelt.Events;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.event.IEvent;
import com.hypixel.hytale.event.IEventDispatcher;
import com.hypixel.hytale.server.core.HytaleServer;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;

public record AddItemEvent(
        @Nonnull Ref<EntityStore> playerRef,
        String itemUUID, // Optional arg as item can be found through QAComp as location swapping to.
        Integer itemPosition

) implements IEvent<Void> { // No Return
    public static void dispatch(Ref<EntityStore> playerRef, String itemUUID, Integer itemPosition) {
        IEventDispatcher<AddItemEvent, AddItemEvent> dispatcher =
                HytaleServer.get().getEventBus().dispatchFor(AddItemEvent.class);

        if (dispatcher.hasListener()) {
            dispatcher.dispatch(
                    new AddItemEvent(playerRef, itemUUID, itemPosition)
            );
        }
    }
}
