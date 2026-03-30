package org.wojo.wojosToolbelt.QuickAccessUtils;

import com.hypixel.hytale.codec.ExtraInfo;
import com.hypixel.hytale.component.Ref;
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

  public static ItemStack addQuickAccessItemComponent(ItemStack item_stack) {
    if (isQuickAccessItem(item_stack)) {
      QuickAccessItemComponent comp = item_stack.getFromMetadataOrNull(QuickAccessItemComponent.QUICK_ACCESS_ITEM_COMPONENT_ID, QuickAccessItemComponent.CODEC);
      if (comp == null) {
        comp = QuickAccessItemComponentFactory.createQuickAccessItemComponent(item_stack.getItemId());
        item_stack.withMetadata(
                QuickAccessItemComponent.KEY,
                QuickAccessItemComponent.CODEC,
                comp
        );
        WojosQuickAccessPlugin.LOGGER.atInfo().log("Item Info:\n" + item_stack.getItem().getData().toString());
      }
    }
    return item_stack;
  }

  public static QuickAccessPlayerComponent getAndAddQuickAccessPlayerComponent(Ref<EntityStore> playerRef, Store<EntityStore> store) {
    QuickAccessPlayerComponent quickAccessPlayerComponent = null;
    Player player = store.getComponent(playerRef, Player.getComponentType());
    if (player != null) {
      quickAccessPlayerComponent = store.getComponent(playerRef, QuickAccessPlayerComponent.getComponentType());
      if (quickAccessPlayerComponent == null) {
        quickAccessPlayerComponent = new QuickAccessPlayerComponent();
        store.addComponent(playerRef, QuickAccessPlayerComponent.getComponentType(), quickAccessPlayerComponent);
      }
    }
    return quickAccessPlayerComponent;
  }

  public static short getQuickAccessItemEquippedLocationOrDefault(Ref<EntityStore> playerRef, Store<EntityStore> store) {
    QuickAccessPlayerComponent quickAccessPlayerComponent = store.getComponent(playerRef, QuickAccessPlayerComponent.getComponentType());
    if (quickAccessPlayerComponent != null){
      int intPos = quickAccessPlayerComponent.getEquippedPosition();
      return (short) intPos;
    }
    return 8; // Return default of 8
  }

  public static QuickAccessItemComponent getQuickAccessItemComponentOrNull(ItemStack item){
    if (!isQuickAccessItem(item)){
      return null;}

    return item.getFromMetadataOrNull(QuickAccessItemComponent.KEY, QuickAccessItemComponent.CODEC);
  }

  public static ItemStack getEquippedQaItemOrNull(PlayerRef player_ref, Store<EntityStore> store) {
    WojosQuickAccessPlugin.LOGGER.atInfo().log("QuickAccessUtils.getEquippedQaItemOrNull");
    if (player_ref == null || player_ref.getReference() == null || !player_ref.isValid()) {
      WojosQuickAccessPlugin.LOGGER.atInfo().log("WARN: Player is NULL");
      return null;
    }

    QuickAccessPlayerComponent quickAccessPlayerComp = store.getComponent(player_ref.getReference(), QuickAccessPlayerComponent.getComponentType());
    Player player = store.getComponent(player_ref.getReference(), Player.getComponentType());
    if (player == null) {
      WojosQuickAccessPlugin.LOGGER.atInfo().log("WARN: Player Comp is NULL");
      return null;
    } else if (quickAccessPlayerComp == null) {
      WojosQuickAccessPlugin.LOGGER.atInfo().log("WARN: QaPlayerComp is NULL, Adding one to player.");
      quickAccessPlayerComp = new QuickAccessPlayerComponent();
      store.addComponent(player_ref.getReference(), QuickAccessPlayerComponent.getComponentType(), quickAccessPlayerComp);
    }

    int equippedPosition = quickAccessPlayerComp.getEquippedPosition();
    ItemStack quickAccessItem = player.getInventory().getHotbar().getItemStack((short) equippedPosition);

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

    int targetPosition = playerComponent.getTargetPosition();
    ItemStack targetItem;
    if (targetPosition == -1) {
      targetItem = player.getInventory().getActiveHotbarItem();
    } else {
      targetItem = player.getInventory().getHotbar().getItemStack((short) targetPosition);
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

  public Class QuickAccessItemData {
    QuickAccessConfig.ITEM_TIER tier;        // Tier of Quick Access Item (Common, Uncommon, Rare, Epic, etc)
    Integer containerSize;

    // ============================= config data =============================
    //  (data stored in QuickAccessConfig bassed on item ID [Item Type & Tier])
    QuickAccessConfig.ITEM_TYPE type;        // Type of Quick Access Item this is. (Quiver, Toolbelt, Unrestricted, etc)
    Integer quickAccessSize;   // Current number of enabled buttons the item has
  }

  public static QuickAccessConfig.ITEM_TYPE getQuickAccessItemType(Item){

  }
}
