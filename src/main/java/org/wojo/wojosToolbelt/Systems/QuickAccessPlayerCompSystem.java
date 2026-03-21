package org.wojo.wojosToolbelt.Systems;

import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.RefChangeSystem;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.wojo.wojosToolbelt.Components.QuickAccessPlayerComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class QuickAccessPlayerCompSystem extends RefChangeSystem<EntityStore, QuickAccessPlayerComponent> {

    @NonNullDecl
    @Override
    public ComponentType<EntityStore, QuickAccessPlayerComponent> componentType() {
        return QuickAccessPlayerComponent.getComponentType();
    }

    // When a player is added to the entity store, Add a quickAccess component to them if they dont already have it.
    @Override
    public void onComponentAdded(@Nonnull Ref<EntityStore> ref,
                                 @Nonnull QuickAccessPlayerComponent component,
                                 @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer)
        {
        
        // Player component was added to a new entity
        QuickAccessPlayerComponent basicQuickAccessPlayerComponent = new QuickAccessPlayerComponent();
        store.addComponent(ref, QuickAccessPlayerComponent.getComponentType(), basicQuickAccessPlayerComponent);
    }

    @Override
    public void onComponentSet(@NonNullDecl Ref<EntityStore> var1, @NullableDecl QuickAccessPlayerComponent var2, @NonNullDecl QuickAccessPlayerComponent var3, @NonNullDecl Store<EntityStore> var4, @NonNullDecl CommandBuffer<EntityStore> var5) {

    }

    @Override
    public void onComponentRemoved(@NonNullDecl Ref<EntityStore> var1, @NonNullDecl QuickAccessPlayerComponent var2, @NonNullDecl Store<EntityStore> var3, @NonNullDecl CommandBuffer<EntityStore> var4) {

    }

    @Nullable
    @Override
    public Query<EntityStore> getQuery() {
        return Query.not(QuickAccessPlayerComponent.getComponentType());
    }
}