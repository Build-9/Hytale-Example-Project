package org.wojo.wojosToolbelt.Handlers;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.entity.UUIDComponent;
import com.hypixel.hytale.server.core.event.events.player.PlayerConnectEvent;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.wojo.wojosToolbelt.Components.QuickAccessPlayerComponent;

import java.util.UUID;
import java.util.function.Consumer;

import static org.wojo.wojosToolbelt.Components.QuickAccessPlayerComponent.*;

// PlayerConnectEvent vs PlayerReadyEvent (One might be better?)
public class PlayerReadyEventHandler implements Consumer<PlayerReadyEvent> {
    @Override
    public void accept(PlayerReadyEvent player_ready_event){
        // TODO: When a player connects, either disable or enable the QuickAccessEnabled feature for them.
        Ref<EntityStore> ref = player_ready_event.getPlayerRef();
        Store<EntityStore> store = ref.getStore();

        QuickAccessPlayerComponent qaPlayerComponent = store.getComponent(ref, QuickAccessPlayerComponent.getComponentType());
        if (qaPlayerComponent == null){
            QuickAccessPlayerComponent newQaComp = new QuickAccessPlayerComponent();
            store.addComponent(ref, QuickAccessPlayerComponent.getComponentType(), newQaComp);
        }else{
            UUIDComponent uuidComponent = store.getComponent(ref, UUIDComponent.getComponentType());
            UUID playerUuid = uuidComponent.getUuid();

            quickAccessBtnEnabledMap.put(playerUuid, qaPlayerComponent.getIsEnabled());
            quickAccessHotbarLocationEquipMap.put(playerUuid, qaPlayerComponent.getEquippedPosition());
            quickAccessPlayerUuidMap.putIfAbsent(ref, playerUuid);
        }
    }

    public static void handle(PlayerReadyEvent player_ready_event){
        // TODO: When a player connects, either disable or enable the QuickAccessEnabled feature for them.
        Ref<EntityStore> ref = player_ready_event.getPlayerRef();
        Store<EntityStore> store = ref.getStore();

        QuickAccessPlayerComponent qaPlayerComponent = store.getComponent(ref, QuickAccessPlayerComponent.getComponentType());
        if (qaPlayerComponent == null){
            QuickAccessPlayerComponent newQaComp = new QuickAccessPlayerComponent();
            store.addComponent(ref, QuickAccessPlayerComponent.getComponentType(), newQaComp);
        }else{
            UUIDComponent uuidComponent = store.getComponent(ref, UUIDComponent.getComponentType());
            UUID playerUuid = uuidComponent.getUuid();

            quickAccessBtnEnabledMap.put(playerUuid, qaPlayerComponent.getIsEnabled());
            quickAccessHotbarLocationEquipMap.put(playerUuid, qaPlayerComponent.getEquippedPosition());
            quickAccessPlayerUuidMap.putIfAbsent(ref, playerUuid);
            // TODO: have check for both directions on UUID & Ref
        }
    }
}