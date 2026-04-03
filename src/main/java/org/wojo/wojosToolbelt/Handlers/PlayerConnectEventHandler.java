package org.wojo.wojosToolbelt.Handlers;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.entity.UUIDComponent;
import com.hypixel.hytale.server.core.event.events.player.PlayerConnectEvent;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.wojo.wojosToolbelt.Components.QuickAccessPlayerComponent;

import java.util.UUID;
import java.util.function.Consumer;

import static org.wojo.wojosToolbelt.Components.QuickAccessPlayerComponent.*;

// PlayerConnectEvent vs PlayerReadyEvent (One might be better?)
public class PlayerConnectEventHandler implements Consumer<PlayerConnectEvent> {
    @Override
    public void accept(PlayerConnectEvent player_connect_event){
        // TODO: When a player connects, either disable or enable the QuickAccessEnabled feature for them.
        PlayerRef playerRef = player_connect_event.getPlayerRef();
        Ref<EntityStore> ref = playerRef.getReference();
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
}