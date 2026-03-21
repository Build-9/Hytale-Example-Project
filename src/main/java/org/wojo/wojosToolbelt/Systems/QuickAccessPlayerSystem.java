package org.wojo.wojosToolbelt.Systems;

import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.system.RefChangeSystem;
import com.hypixel.hytale.server.core.entity.UUIDComponent;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.wojo.wojosToolbelt.Components.QuickAccessPlayerComponent;

import javax.annotation.Nonnull;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class QuickAccessPlayerSystem extends RefChangeSystem<EntityStore, QuickAccessPlayerComponent> {



    @Override
    public void onComponentAdded(@Nonnull Ref<EntityStore> ref,
                                 @Nonnull QuickAccessPlayerComponent component,
                                 @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer)
        {
        
        // Quick Access component was added to a new entity

        UUIDComponent component = store.getComponent(ref, UUIDComponent.getComponentType());
        UUID playerUuid = component.getUuid();
        quickAccessBtnEnabledMap.set(playerUuid, component.getIsEnabled());
        quickAccessGuiBtnMap.set(playerUuid, component.getEquippedPosition());
 
    }

    @Override
    public void onComponentRemoved(@Nonnull Ref<EntityStore> ref,
        @Nonnull QuickAccessPlayerComponent component, 
        @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer)
        {
        
        // Quick Access Component was removed from entity

        UUIDComponent component = store.getComponent(ref, UUIDComponent.getComponentType());
        UUID playerUuid = component.getUuid();
        quickAccessBtnEnabledMap.set(playerUuid, component.getIsEnabled());
        quickAccessGuiBtnMap.set(playerUuid, component.getEquippedPosition());

    }

    @Override
    public void onComponentSet(@Nonnull Ref<EntityStore> ref, 
        @Nullable QuickAccessPlayerComponent old_component, @Nonnull QuickAccessPlayerComponent new_component, 
        @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer) 
        {

        // Quick Access Component replaced on a player that already has it
        UUIDComponent component = store.getComponent(ref, UUIDComponent.getComponentType());
        UUID playerUuid = component.getUuid();
        
        quickAccessBtnEnabledMap.set(playerUuid, new_component.getIsEnabled());
        quickAccessGuiBtnMap.set(playerUuid, component.getEquippedPosition());
    }

    @Nullable
    @Override
    public Query<EntityStore> getQuery() {
        return Query.and(Player.getComponentType());
    }
}