package org.wojo.wojosToolbelt.Handlers;

import com.hypixel.hytale.server.core.inventory.container.ItemContainer;
import org.wojo.wojosToolbelt.WojosQuickAccessPlugin;

import java.util.function.Consumer;

/**
 * Desc:
 *  When a player EQUIPS a QuickAccess item from hotbar Location
 *      - Copy the items QuickAccessComponent to the player
 *  When a player REMOVES a QuickAccess item from Hotbar Location
 *      - Copy the players QuickAccessComponent to the item
 *      - Remove the players QuickAccessComponent
 *  When an Item moves inside a QuickAccessComponent Storage UI
 *      - Update the Items QuickAccessComponent with what's in each location
 */
public class QuickAccessItemEquippedHandler implements Consumer<ItemContainer.ItemContainerChangeEvent> {
    @Override
    public void accept(ItemContainer.ItemContainerChangeEvent itemContainerChangeEvent) {
        WojosQuickAccessPlugin.LOGGER.atInfo().log(itemContainerChangeEvent.toString());
        
    }
}
