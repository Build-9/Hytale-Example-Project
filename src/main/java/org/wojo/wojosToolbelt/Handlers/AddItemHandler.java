package org.wojo.wojosToolbelt.Handlers;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.wojo.wojosToolbelt.Components.QuickAccessComponent;
import org.wojo.wojosToolbelt.Config.QuickAccessConfig;
import org.wojo.wojosToolbelt.Events.AddItemEvent;
import org.wojo.wojosToolbelt.WojosQuickAccessPlugin;

import java.util.function.Consumer;

public class AddItemHandler implements Consumer<AddItemEvent> {
    @Override
    public void accept(AddItemEvent event) {
        WojosQuickAccessPlugin.LOGGER.atInfo().log("EVENT: (Add Item Event) - Adding "+event.itemUUID()+" to position "+event.itemPosition());

        String itemID = event.itemUUID();
        Integer itemPosition = event.itemPosition();

        Ref<EntityStore> player = event.playerRef();
        Store<EntityStore> store = player.getStore();
        QuickAccessComponent qaComp = store.getComponent(player, QuickAccessComponent.getComponentType());

        if (qaComp != null && !itemID.isEmpty() && itemPosition >= 0 && itemPosition < QuickAccessConfig.MAX_QA_ITEMS){
            QuickAccessComponent qaCompCpy = new QuickAccessComponent(qaComp);
            qaCompCpy.setItemInIdArray(itemID,itemPosition);

            // TODO: This needs to be done in correct thread somehow
            store.replaceComponent(player, QuickAccessComponent.getComponentType(), qaCompCpy);
        }
    }
}
