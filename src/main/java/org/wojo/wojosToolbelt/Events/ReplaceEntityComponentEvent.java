package org.wojo.wojosToolbelt.Events;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.event.IEvent;
import com.hypixel.hytale.event.IEventDispatcher;
import com.hypixel.hytale.server.core.HytaleServer;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.wojo.wojosToolbelt.Components.QuickAccessComponent;

import javax.annotation.Nonnull;

public record ReplaceEntityComponentEvent(
        Ref<EntityStore> entityRef,
        QuickAccessComponent newComponent
) implements IEvent<Void> {
    public static void dispatch(Ref<EntityStore> entityRef, QuickAccessComponent newComponent) {
        IEventDispatcher<ReplaceEntityComponentEvent, ReplaceEntityComponentEvent> dispatcher =
                HytaleServer.get().getEventBus().dispatchFor(ReplaceEntityComponentEvent.class);

        if (dispatcher.hasListener()) {
            dispatcher.dispatch(
                    new ReplaceEntityComponentEvent(entityRef, newComponent)
            );
        }
    }
}
