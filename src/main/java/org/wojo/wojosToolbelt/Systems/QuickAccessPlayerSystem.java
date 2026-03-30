package org.wojo.wojosToolbelt.Systems;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.dependency.Dependency;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.RefChangeSystem;
import com.hypixel.hytale.server.core.entity.UUIDComponent;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.wojo.wojosToolbelt.Components.QuickAccessPlayerComponent;
import org.wojo.wojosToolbelt.WojosQuickAccessPlugin;

public class QuickAccessPlayerComponentSystem extends RefChangeSystem<EntityStore, Player> {

    private final ComponentType<EntityStore, Player> playerComponentType;
    public QuickAccessPlayerComponentSystem(ComponentType<EntityStore, Player> player_component_type){
        this.playerComponentType = player_component_type;
    }

    @NonNullDecl
    @Override
    public ComponentType<EntityStore, QuickAccessPlayerComponent> componentType() {
        return playerComponentType.getComponentType();
    }

    @Override
    public void onComponentAdded(@Nonnull Ref<EntityStore> ref,
                                 @Nonnull QuickAccessPlayerComponent component,
                                 @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer)
    {
        super.onComponentAdded(ref, component, store, commandBuffer);

        // A player was added to the game, Add QuickAccessPlayerComponent to them if needed

        QuickAccessPlayerComponent quickAccessPlayerComponent = store.getComponentType(ref, QuickAccessPlayerComponent.getComponentType());
        if (quickAccessPlayerComponent == null) {
            quickAccessPlayerComponent = new QuickAccessPlayerComponent();
            store.componentAdd(ref, QuickAccessPlayerComponent.getComponentType(), quickAccessPlayerComponent);
            WojosQuickAccessPlugin.LOGGER.atInfo().log("INFO: quickAccessPlayerSystem.onComponentAdded - We added a comp to a player!");
        }

        WojosQuickAccessPlugin.LOGGER.atInfo().log("INFO: quickAccessPlayerSystem.onComponentAdded - Player allready had a QuickAccessPlayerComponent!");
    }

    @Override
    public void onComponentRemoved(@Nonnull Ref<EntityStore> ref,
        @Nonnull QuickAccessPlayerComponent component, 
        @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer)
        {
        super.onComponentRemoved(ref, component, commandBuffer);
    }

    @Override
    public void onComponentSet(@Nonnull Ref<EntityStore> ref,
                               @Nullable QuickAccessPlayerComponent old_component, @Nonnull QuickAccessPlayerComponent new_component,
                               @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer)
        {
            super.onComponentSet(ref, old_component, new_component, store, commandBuffer);
    }

    @Override
    public boolean test(ComponentRegistry<EntityStore> componentRegistry, Archetype<EntityStore> archetype) {
        return super.test(componentRegistry, archetype);
    }

    @Nullable
    @Override
    public Query<EntityStore> getQuery() {
        return Query.and(playerComponentComponentType);
    }

    @Override
    public void onSystemRegistered() {
        super.onSystemRegistered();
    }

    @Override
    public void onSystemUnregistered() {
        super.onSystemUnregistered();
    }

    @NullableDecl
    @Override
    public SystemGroup<EntityStore> getGroup() {
        return super.getGroup();
    }

    @NonNullDecl
    @Override
    public Set<Dependency<EntityStore>> getDependencies() {
        return super.getDependencies();
    }

}