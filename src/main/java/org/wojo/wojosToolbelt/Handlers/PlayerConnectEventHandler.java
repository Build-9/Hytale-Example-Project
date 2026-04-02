package org.wojo.wojosToolbelt.Handlers;
import com.hypixel.hytale.server.core.event.events.player.PlayerConnectEvent;

import java.util.function.Consumer;
// PlayerConnectEvent vs PlayerReadyEvent (One might be better?)
public class PlayerConnectEventHandler implements Consumer<PlayerConnectEvent> {
    @Override
    public void accept(PlayerConnectEvent player_connect_event){
        // TODO: When a player connects, either disable or enable the QuickAccessEnabled feature for them.
    }
}