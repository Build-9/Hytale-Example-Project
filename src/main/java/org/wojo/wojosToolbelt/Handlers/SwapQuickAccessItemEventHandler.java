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
        short quickAccessItemHotbarPosition = swapQuickAccessItemEvent.hotbarPosition();
        short targetHotbarPostion = 0;

        Player player = store.getComponent(playerRef, Player.getComponentType());
        ItemStack quickAccessItemStack = player.getInventory().getHotbar().getItemStack(quickAccessItemHotbarPosition);
        QuickAccessComponent quickAccessComponent = QuickAccessUtils.getItemsQuickAccessComponent(quickAccessItemStack);
        targetHotbarPostion = quickAccessComponent.getTargetLocation();

        // ------ Get Currently Stored Items ------
        // Get current target hotbar item
        ItemStack equippedItem = player.getInventory().getHotbar().getItemStack(targetHotbarPostion);

        // Get Item in Quick Access Component Storage to swap into hotbar
        BsonDocument containerBSON = quickAccessItemStack.getFromMetadataOrNull(ItemStackItemContainer.CONTAINER_CODEC);
        ItemStack[] containerItems = ItemStackItemContainer.ITEMS_CODEC.getOrNull(containerBSON, new ExtraInfo());
        ItemStack itemStoredInQaComp = containerItems[sourceInventoryPosition];

        // ------ Set Container Items ------
        // Set Quick Access item to hotbar item
        player.getInventory().getHotbar().removeItemStackFromSlot(targetHotbarPostion);
        if (itemStoredInQaComp != null) {
            player.getInventory().getHotbar().setItemStackForSlot(targetHotbarPostion, itemStoredInQaComp);
        }

        // Set Hotbar Item to quickaccess Item
        // NOTE: QuickAccessComponent updates when the UI is opened. No need to update it here we only need to update the container
        if (equippedItem != null){
            containerItems[sourceInventoryPosition] = equippedItem;
            ItemStackItemContainer.ITEMS_CODEC.put(containerBSON, containerItems, new ExtraInfo());
            ItemStack updatedQuickAccessItem = quickAccessItemStack.withMetadata(ItemStackItemContainer.CONTAINER_CODEC, containerBSON);
            player.getInventory().getHotbar().removeItemStackFromSlot((short)8);
            player.getInventory().getHotbar().setItemStackForSlot((short)8, updatedQuickAccessItem);
        }else{
            containerItems[sourceInventoryPosition] = null;
            ItemStackItemContainer.ITEMS_CODEC.put(containerBSON, containerItems, new ExtraInfo());
            ItemStack updatedQuickAccessItem = quickAccessItemStack.withMetadata(ItemStackItemContainer.CONTAINER_CODEC, containerBSON);
            player.getInventory().getHotbar().removeItemStackFromSlot((short)8);
            player.getInventory().getHotbar().setItemStackForSlot((short)8, updatedQuickAccessItem);

        }
    }
    final short activeSlot = 0;
}
