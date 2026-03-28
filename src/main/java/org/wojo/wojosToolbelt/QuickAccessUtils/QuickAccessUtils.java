package org.wojo.wojosToolbelt.QuickAccessUtils;

import com.hypixel.hytale.codec.ExtraInfo;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.asset.type.item.config.Item;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.ItemStackItemContainer;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.bson.BsonDocument;
import org.wojo.wojosToolbelt.Components.QuickAccessItemComponentFactory;
import org.wojo.wojosToolbelt.Components.QuickAccessItemComponent;
import org.wojo.wojosToolbelt.Components.QuickAccessPlayerComponent;
import org.wojo.wojosToolbelt.Config.QuickAccessConfig;
import org.wojo.wojosToolbelt.WojosQuickAccessPlugin;

import javax.annotation.Nonnull;

public class QuickAccessUtils {

  public static ItemStack addQuickAccessComponent(ItemStack item_stack) {
    ItemStack itemStack = item_stack;
    if (isQuickAccessItem(itemStack)) {
      QuickAccessItemComponent comp = itemStack.getFromMetadataOrNull(QuickAccessItemComponent.QUICK_ACCESS_ITEM_COMPONENT_ID, QuickAccessItemComponent.CODEC);
      if (comp == null) {
        comp = QuickAccessItemComponentFactory.createQuickAccessItemComponent(item_stack.getItemId());
        item_stack.withMetadata(
                QuickAccessItemComponent.KEY,
                QuickAccessItemComponent.CODEC,
                comp
        );
        WojosQuickAccessPlugin.LOGGER.atInfo().log("Item Info:\n" + itemStack.getItem().getData().toString());
      }
    }
    return itemStack;
  }

  public static QuickAccessItemComponent getQuickAccessItemComponentOrNull(ItemStack item){
    if (!isQuickAccessItem(item)){
      return null;}

    return item.getFromMetadataOrNull(QuickAccessItemComponent.KEY, QuickAccessItemComponent.CODEC);
  }

  public static ItemStack getEquippedQaItemOrNull(PlayerRef player_ref, Store<EntityStore> store) {
    if (player_ref == null || player_ref.getReference() == null || !player_ref.isValid()) {
      return null;
    }

    QuickAccessPlayerComponent playerComponent = store.getComponent(player_ref.getReference(), QuickAccessPlayerComponent.getComponentType());
    Player player = store.getComponent(player_ref.getReference(), Player.getComponentType());
    if (playerComponent == null || player == null) {
      return null;
    }

    Integer equippedPosition = playerComponent.getEquippedPosition();
    ItemStack quickAccessItem = player.getInventory().getHotbar().getItemStack(equippedPosition.shortValue());

    if (!QuickAccessUtils.isQuickAccessItem(quickAccessItem)) {
      return null;
    }
    return quickAccessItem;
  }

  public static ItemStack getEquippedTargetItemOrNull(PlayerRef player_ref, Store<EntityStore> store) {
    if (player_ref == null || player_ref.getReference() == null || !player_ref.isValid()) {
      return null;
    }

    QuickAccessPlayerComponent playerComponent = store.getComponent(player_ref.getReference(), QuickAccessPlayerComponent.getComponentType());
    Player player = store.getComponent(player_ref.getReference(), Player.getComponentType());
    if (playerComponent == null || player == null) {
      return null;
    }

    Integer targetPosition = playerComponent.getTargetPosition();
    ItemStack targetItem;
    if (targetPosition == -1) {
      targetItem = player.getInventory().getActiveHotbarItem();
    } else {
      targetItem = player.getInventory().getHotbar().getItemStack(targetPosition.shortValue());
    }

    return targetItem;
  }

  public static Boolean isQuickAccessItem(String item_id) {
    return QuickAccessConfig.QUICK_ACCESS_ITEM_IDS.contains(item_id);
  }

  public static boolean isQuickAccessItem(ItemStack item_stack) {
    if (item_stack != null) {
      String itemId = item_stack.getItemId();
      return QuickAccessConfig.QUICK_ACCESS_ITEM_IDS.contains(itemId);
    }
    return false;
  }

  // Get array of items in a container if item has container field, else get null.
  public static ItemStack[] getContainerItems(ItemStack itemStack) {
    BsonDocument containerBSON = itemStack.getFromMetadataOrNull(ItemStackItemContainer.CONTAINER_CODEC);
    return ItemStackItemContainer.ITEMS_CODEC.getOrNull(containerBSON, new ExtraInfo());
  }

}
