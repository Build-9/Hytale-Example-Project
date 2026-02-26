package org.wojo.wojosToolbelt.Handlers;

import com.hypixel.hytale.codec.ExtraInfo;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.ItemStackItemContainer;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.bson.BsonDocument;
import org.wojo.wojosToolbelt.Components.QuickAccessComponent;
import org.wojo.wojosToolbelt.Config.QuickAccessConfig;
import org.wojo.wojosToolbelt.Events.SwapQuickAccessItemEvent;
import java.util.function.Consumer;

public class SwapQuickAccessItemEventHandler implements Consumer<SwapQuickAccessItemEvent> {
    @Override
    public void accept(SwapQuickAccessItemEvent swapQuickAccessItemEvent) {
        Ref<EntityStore> playerRef = swapQuickAccessItemEvent.playerRef();
        Store<EntityStore> store = swapQuickAccessItemEvent.store();
        short sourceInventoryPosition = swapQuickAccessItemEvent.sourceInventoryPosition();
        short targetHotbarPostion = 0;

        Player player = store.getComponent(playerRef, Player.getComponentType());
        ItemStack quickAccessItemStack = player.getInventory().getHotbar().getItemStack((short)8);

        // ------ Get Currently Stored Items ------
        // Get current hotbar 0 item
        ItemStack equippedItem = player.getInventory().getHotbar().getItemStack((short)0);

        // Get Item in Quick Access Component Storage to swap into hotbar
        BsonDocument containerBSON = quickAccessItemStack.getFromMetadataOrNull(ItemStackItemContainer.CONTAINER_CODEC);
        ItemStack[] containerItems = ItemStackItemContainer.ITEMS_CODEC.getOrNull(containerBSON, new ExtraInfo());
        ItemStack itemStoredInQaComp = containerItems[sourceInventoryPosition];

        if (itemStoredInQaComp != null){
            // ------ Set Container Items ------
            // Set Quick Access item to hotbar item
            player.getInventory().getHotbar().removeItemStackFromSlot(targetHotbarPostion);
            player.getInventory().getHotbar().setItemStackForSlot(targetHotbarPostion, itemStoredInQaComp);

            // Set Hotbar Item to quickaccess Item
            // NOTE: QuickAccessComponent updates when the UI is opened. No need to update it here we only need to update the container
            if (equippedItem != null){
                containerItems[sourceInventoryPosition] = equippedItem;
                ItemStackItemContainer.ITEMS_CODEC.put(containerBSON, containerItems, new ExtraInfo());
                ItemStack UpdatedQuickAccessItem = quickAccessItemStack.withMetadata(ItemStackItemContainer.CONTAINER_CODEC, containerBSON);
                player.getInventory().getHotbar().removeItemStackFromSlot((short)8);
                player.getInventory().getHotbar().setItemStackForSlot((short)8, UpdatedQuickAccessItem);
            }else{

                containerItems[sourceInventoryPosition] = null;
                ItemStackItemContainer.ITEMS_CODEC.put(containerBSON, containerItems, new ExtraInfo());
                ItemStack UpdatedQuickAccessItem = quickAccessItemStack.withMetadata(ItemStackItemContainer.CONTAINER_CODEC, containerBSON);
                player.getInventory().getHotbar().removeItemStackFromSlot((short)8);
                player.getInventory().getHotbar().setItemStackForSlot((short)8, UpdatedQuickAccessItem);
            }
        }
    }
}
