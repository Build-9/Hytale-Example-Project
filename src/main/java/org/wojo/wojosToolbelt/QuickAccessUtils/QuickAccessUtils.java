package org.wojo.wojosToolbelt.QuickAccessUtils;

import com.hypixel.hytale.codec.ExtraInfo;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.ItemStackItemContainer;
import org.bson.BsonDocument;
import org.wojo.wojosToolbelt.Components.QuickAccessComponent;
import org.wojo.wojosToolbelt.Config.QuickAccessConfig;

public class QuickAccessUtils {
  public static boolean hasQuickAccessComponent(ItemStack itemStack) {
    QuickAccessComponent comp = itemStack.getFromMetadataOrNull(QuickAccessConfig.QUICK_ACCESS_COMPONENT_ID, QuickAccessComponent.CODEC);
    if (comp != null) {
      return true;
    }
    return false;
  }
  
  // Get a quickAccessComponent from an item if it exists, else get null
  public static QuickAccessComponent getItemsQuickAccessComponent (ItemStack itemStack) {
    //TODO: if value doesnt exist, set comp on item
    return itemStack.getFromMetadataOrNull(QuickAccessConfig.QUICK_ACCESS_COMPONENT_ID, QuickAccessComponent.CODEC);
  }

  // Get array of items in a container if item has container field, else get null. 
  public static ItemStack[] getContainerComponentItems(ItemStack itemStack) {
    BsonDocument containerBSON = itemStack.getFromMetadataOrNull(ItemStackItemContainer.CONTAINER_CODEC);
    return ItemStackItemContainer.ITEMS_CODEC.getOrNull(containerBSON, new ExtraInfo());
  }

  public static ItenStack getTargetItem(Player player, QuickAccessComponent quickAccessComponent){
    if (player == null || quickAccessComponent == null ) {return null;}

    if (quickAccessComponent.getTargetLocation() == -1){
      // get players active gotbar slot
      return player.getHotbar().getActiveHotbarItem();
    } else {
      return player.getHotbar().getItem(quickAccessComponent.getTargetLocation());
    }
  }
}
