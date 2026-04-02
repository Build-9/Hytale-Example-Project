import java.util.function.Consumer;
// PlayerConnectEvent vs PlayerReadyEvent (One might be better?)
public class SwapQuickAccessItemEventHandler implements Consumer<PlayerConnectEvent> {
    @Override
    public void accept(PlayerConnectEvent player_connect_event){
        // TODO: When a player connects, either disable or enable the QuickAccessEnabled feature for them.
    }
}