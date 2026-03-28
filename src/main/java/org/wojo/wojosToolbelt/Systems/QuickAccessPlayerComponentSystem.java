package org.wojo.wojosToolbelt.Systems;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.dependency.Dependency;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.RefChangeSystem;
import com.hypixel.hytale.server.core.entity.UUIDComponent;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.wojo.wojosToolbelt.Components.QuickAccessPlayerComponent;
import org.wojo.wojosToolbelt.WojosQuickAccessPlugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;
import java.util.UUID;

import static org.wojo.wojosToolbelt.Components.QuickAccessPlayerComponent.quickAccessBtnEnabledMap;
import static org.wojo.wojosToolbelt.Components.QuickAccessPlayerComponent.quickAccessGuiBtnMap;

public class QuickAccessPlayerComponentSystem extends RefChangeSystem<EntityStore, QuickAccessPlayerComponent> {

    @NonNullDecl
    @Override
    public ComponentType<EntityStore, QuickAccessPlayerComponent> componentType() {
        return QuickAccessPlayerComponent.getComponentType();
    }

    @Override
    public void onComponentAdded(@Nonnull Ref<EntityStore> ref,
                                 @Nonnull QuickAccessPlayerComponent component,
                                 @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer)
        {
        
        // Quick Access component was added to a new entity

        UUIDComponent uuidComponent = store.getComponent(ref, UUIDComponent.getComponentType());
        UUID playerUuid = uuidComponent.getUuid();

        quickAccessBtnEnabledMap.put(playerUuid, component.getIsEnabled());
        quickAccessGuiBtnMap.put(playerUuid, component.getEquippedPosition());

        WojosQuickAccessPlugin.LOGGER.atInfo().log("INFO: quickAccessPlayerSystem.onComponentAdded");
    }

    @Override
    public void onComponentRemoved(@Nonnull Ref<EntityStore> ref,
        @Nonnull QuickAccessPlayerComponent component, 
        @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer)
        {
        
        // Quick Access Component was removed from entity

        UUIDComponent uuidComponent = store.getComponent(ref, UUIDComponent.getComponentType());
        UUID playerUuid = uuidComponent.getUuid();

        quickAccessBtnEnabledMap.put(playerUuid, component.getIsEnabled());
        quickAccessGuiBtnMap.put(playerUuid, component.getEquippedPosition());

            WojosQuickAccessPlugin.LOGGER.atInfo().log("INFO: quickAccessPlayerSystem.onComponentRemoved");
    }

    @Override
    public void onComponentSet(@Nonnull Ref<EntityStore> ref,
                               @Nullable QuickAccessPlayerComponent old_component, @Nonnull QuickAccessPlayerComponent new_component,
                               @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer)
        {

        // Quick Access Component replaced on a player that already has it
        UUIDComponent uuidComponent = store.getComponent(ref, UUIDComponent.getComponentType());
        UUID playerUuid = uuidComponent.getUuid();
        
        quickAccessBtnEnabledMap.put(playerUuid, new_component.getIsEnabled());
        quickAccessGuiBtnMap.put(playerUuid, new_component.getEquippedPosition());
    }

    @Override
    public boolean test(ComponentRegistry<EntityStore> componentRegistry, Archetype<EntityStore> archetype) {
        return super.test(componentRegistry, archetype);
    }

    @Nullable
    @Override
    public Query<EntityStore> getQuery() {
        return Query.and(Player.getComponentType());
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