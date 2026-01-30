package org.wojo.wojosToolbelt.Handlers;

import org.wojo.wojosToolbelt.Events.SwapWeaponEvent;
import org.wojo.wojosToolbelt.WojosQuickAccessPlugin;

import java.util.function.Consumer;

public class SwapItemHandler implements Consumer<SwapItemEvent> {
    @Override
    public void accept(SwapWeaponEvent event) {
        WojosQuickAccessPlugin.LOGGER.atInfo().log("A player weapon Swap has just happened! We got the event! User swapped from "+event.oldItemUUID+" to "+newItemUUID);

        // TODO: If we have penalties to a weapon swap they would happen here. (Like Take player stamina)
    }
}
