package org.wojo.wojosToolbelt.Systems;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.dependency.Dependency;
import com.hypixel.hytale.component.dependency.Order;
import com.hypixel.hytale.component.dependency.SystemDependency;
import com.hypixel.hytale.component.dependency.SystemGroupDependency;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.RefChangeSystem;
import com.hypixel.hytale.server.core.entity.UUIDComponent;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.modules.entity.player.PlayerSystems;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.wojo.wojosToolbelt.Components.QuickAccessPlayerComponent;
import org.wojo.wojosToolbelt.WojosQuickAccessPlugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

public class QuickAccessPlayerSystem extends RefChangeSystem<EntityStore, Player> {

    ComponentType<EntityStore, Player> _playerComponentType;
    public QuickAccessPlayerSystem(ComponentType<EntityStore, Player> player_component_type){
        this._playerComponentType = player_component_type;
    }

    @NonNullDecl
    @Override
    public ComponentType<EntityStore, Player> componentType() {
        return Player.getComponentType();
    }

    @Override
    public void onComponentAdded(@Nonnull Ref<EntityStore> ref,
                                 @Nonnull Player component,
                                 @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer)
    {
        // A player was added to the game, Add QuickAccessPlayerComponent to them if needed

        QuickAccessPlayerComponent quickAccessPlayerComponent = store.getComponent(ref, QuickAccessPlayerComponent.getComponentType());
        if (quickAccessPlayerComponent == null) {
            quickAccessPlayerComponent = new QuickAccessPlayerComponent();
            store.addComponent(ref, QuickAccessPlayerComponent.getComponentType(), quickAccessPlayerComponent);
            WojosQuickAccessPlugin.LOGGER.atInfo().log("INFO: quickAccessPlayerSystem.onComponentAdded - We added a comp to a player!");
        }

        WojosQuickAccessPlugin.LOGGER.atInfo().log("INFO: quickAccessPlayerSystem.onComponentAdded - Player allready had a QuickAccessPlayerComponent!");
    }

    @Override
    public void onComponentRemoved(@Nonnull Ref<EntityStore> ref,
        @Nonnull Player component,
        @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer)
        {
    }

    @Override
    public void onComponentSet(@Nonnull Ref<EntityStore> ref,
                               @Nullable Player old_component, @Nonnull Player new_component,
                               @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer)
        {
    }

    @Override
    public boolean test(ComponentRegistry<EntityStore> componentRegistry, Archetype<EntityStore> archetype) {
        return super.test(componentRegistry, archetype);
    }

    @Nullable
    @Override
    public Query<EntityStore> getQuery() {
        return Query.and();
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

//    @NonNullDecl
//    @Override
//    public Set<Dependency<EntityStore>> getDependencies() {
//        return new Set<Dependency<>>;
//        return Set.of(
//                new SystemDependency<>(Order.AFTER, PlayerSystems.PlayerAddedSystem.class)
//        );
//    }

}