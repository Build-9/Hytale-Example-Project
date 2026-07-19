package org.wojo.wojosToolbelt.Components;

import com.hypixel.hytale.codec.ExtraInfo;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.ItemStackItemContainer;
import org.bson.BsonDocument;
import org.wojo.wojosToolbelt.Config.QuickAccessConfig;

public class QuickAccessItemComponentFactory {

    public static QuickAccessItemComponent createQuickAccessItemComponent(ItemStack item_stack) {
        QuickAccessItemComponent comp = new QuickAccessItemComponent();


        BsonDocument containerBSON = item_stack.getFromMetadataOrNull(ItemStackItemContainer.CONTAINER_CODEC);
        Short containerCapacity = ItemStackItemContainer.CAPACITY_CODEC.getOrNull(containerBSON, new ExtraInfo());
        if (containerCapacity == null){
            containerCapacity = 0;
        }

        comp.setItemType(QuickAccessConfig.getQuickAccessItemType(item_stack.getItemId()).getId());
        comp.setItemTier(QuickAccessConfig.getQuickAccessItemTier(item_stack.getItemId()).getId());
        comp.setQuickAccessSize(QuickAccessConfig.getQuickAccessSize(comp));
        comp.setContainerSize(containerCapacity);

        return comp;
    }
}