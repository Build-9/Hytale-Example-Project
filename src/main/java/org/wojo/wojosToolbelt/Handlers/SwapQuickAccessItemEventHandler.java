package org.wojo.wojosToolbelt.Handlers;

import com.hypixel.hytale.codec.ExtraInfo;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.inventory.InventoryComponent;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.ItemStackItemContainer;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.bson.BsonDocument;
import org.wojo.wojosToolbelt.Components.QuickAccessItemComponent;
import org.wojo.wojosToolbelt.Components.QuickAccessPlayerComponent;
import org.wojo.wojosToolbelt.Events.SwapQuickAccessItemEvent;
import org.wojo.wojosToolbelt.QuickAccessUtils.QuickAccessUtils;
import org.wojo.wojosToolbelt.WojosQuickAccessPlugin;

import java.util.function.Consumer;

public class SwapQuickAccessItemEventHandler implements Consumer<SwapQuickAccessItemEvent> {
    @Override
    public void accept(SwapQuickAccessItemEvent swapQuickAccessItemEvent) {
        WojosQuickAccessPlugin.LOGGER.atInfo().log("[DEBUG] Running Swap event handler");
        Ref<EntityStore> playerRef = swapQuickAccessItemEvent.playerRef();
        Store<EntityStore> store = swapQuickAccessItemEvent.store();
        short sourceInventoryPosition = swapQuickAccessItemEvent.sourceInventoryPosition();
        short equippedPosition = swapQuickAccessItemEvent.equippedPosition();
        short targetPosition = swapQuickAccessItemEvent.targetPosition();
        
        InventoryComponent.Hotbar hotbar = (InventoryComponent.Hotbar) store.getComponent(playerRef, InventoryComponent.getComponentTypeById(InventoryComponent.HOTBAR_SECTION_ID));
        ItemStack quickAccessItemStack = hotbar.getInventory().getItemStack(equippedPosition);
        WojosQuickAccessPlugin.LOGGER.atInfo().log("[DEBUG] Handler Data: \n - Target Pos: "+targetPosition+"\n - Source Pos: "+sourceInventoryPosition+"\n - Equipped Pos: "+equippedPosition);

        // ------ Get Currently Stored Items ------
        // Get current target hotbar item
        ItemStack equippedItem = hotbar.getInventory().getItemStack(targetPosition);

        // Get Item in Quick Access Component Storage to swap into hotbar
        BsonDocument containerBSON = quickAccessItemStack.getFromMetadataOrNull(ItemStackItemContainer.CONTAINER_CODEC);
        ItemStack[] containerItems = ItemStackItemContainer.ITEMS_CODEC.getOrNull(containerBSON, new ExtraInfo());
        ItemStack itemStoredInQaComp = containerItems[sourceInventoryPosition];

        // ------ Set Container Items ------
        // Set Quick Access item to hotbar item
        hotbar.getInventory().removeItemStackFromSlot(targetPosition);
        if (itemStoredInQaComp != null) {
            hotbar.getInventory().setItemStackForSlot(targetPosition, itemStoredInQaComp);
        }

        // Set Hotbar Item to quickaccess Item
        // NOTE: QuickAccessComponent updates when the UI is opened. No need to update it here we only need to update the container
        if (equippedItem != null){
            containerItems[sourceInventoryPosition] = equippedItem;
            ItemStackItemContainer.ITEMS_CODEC.put(containerBSON, containerItems, new ExtraInfo());
            ItemStack updatedQuickAccessItem = quickAccessItemStack.withMetadata(ItemStackItemContainer.CONTAINER_CODEC, containerBSON);
            hotbar.getInventory().removeItemStackFromSlot(equippedPosition);
            hotbar.getInventory().setItemStackForSlot(equippedPosition, updatedQuickAccessItem);

            ComponentType componentType = InventoryComponent.getComponentTypeById(InventoryComponent.HOTBAR_SECTION_ID);
            store.replaceComponent(playerRef, componentType, hotbar);
        }else{
            containerItems[sourceInventoryPosition] = null;
            ItemStackItemContainer.ITEMS_CODEC.put(containerBSON, containerItems, new ExtraInfo());
            ItemStack updatedQuickAccessItem = quickAccessItemStack.withMetadata(ItemStackItemContainer.CONTAINER_CODEC, containerBSON);
            hotbar.getInventory().removeItemStackFromSlot(equippedPosition);
            hotbar.getInventory().setItemStackForSlot(equippedPosition, updatedQuickAccessItem);

            ComponentType componentType = InventoryComponent.getComponentTypeById(InventoryComponent.HOTBAR_SECTION_ID);
            store.replaceComponent(playerRef, componentType, hotbar);
        }
    }
}
