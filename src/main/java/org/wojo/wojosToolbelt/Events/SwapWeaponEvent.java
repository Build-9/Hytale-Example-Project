package org.wojo.wojosToolbelt.Events;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.event.IEvent;
import com.hypixel.hytale.event.IEventDispatcher;
import com.hypixel.hytale.server.core.HytaleServer;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;

public record SwapWeaponEvent(
        @Nonnull Ref<EntityStore> playerRef,
        Ref<EntityStore> itemSwappedFrom,
        Ref<EntityStore> newlyEquippedItem

) implements IEvent<Void> { // No Return
    public static void dispatch(Ref<EntityStore> playerRef, Ref<EntityStore> itemSwappedFrom, Ref<EntityStore> newlyEquippedItem) {
        IEventDispatcher<SwapWeaponEvent, SwapWeaponEvent> dispatcher =
                HytaleServer.get().getEventBus().dispatchFor(SwapWeaponEvent.class);

        if (dispatcher.hasListener()) {
            dispatcher.dispatch(new SwapWeaponEvent(playerRef, itemSwappedFrom, newlyEquippedItem));
        }
    }
}
