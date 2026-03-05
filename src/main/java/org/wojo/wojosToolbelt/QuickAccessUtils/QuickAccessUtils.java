package org.wojo.wojosToolbelt.QuickAccessUtils;

import com.hypixel.hytale.codec.ExtraInfo;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.ItemStackItemContainer;
import org.bson.BsonDocument;
import org.wojo.wojosToolbelt.Components.QuickAccessComponent;
import org.wojo.wojosToolbelt.Config.QuickAccessConfig;

public class QuickAccessUtils {
  public static ItemStack updateQuickAccessComponent(ItemStack quickAccessItemStack) {
    // Update Quick Access Component Arrays from item Data
    if (quickAccessItemStack == null){
      return null;
    }
    return null;
  }

  public static boolean hasQuickAccessComponent(ItemStack itemStack) {
    QuickAccessComponent comp = itemStack.getFromMetadataOrNull(QuickAccessConfig.QUICK_ACCESS_COMPONENT_ID, QuickAccessComponent.CODEC);
    if (comp != null) {
      return true;
    }
    return false;
  }
  
  // Get a quickAccessComponent from an item if it exists, else get null
  public static QuickAccessComponent getItemsQuickAccessComponent (ItemStack itemStack) {
    return itemStack.getFromMetadataOrNull(QuickAccessConfig.QUICK_ACCESS_COMPONENT_ID, QuickAccessComponent.CODEC);
  }

  // Get array of items in a container if item has container field, else get null. 
  public static ItemStack[] getContainerComponentItems(ItemStack itemStack) {
    BsonDocument containerBSON = itemStack.getFromMetadataOrNull(ItemStackItemContainer.CONTAINER_CODEC);
    return ItemStackItemContainer.ITEMS_CODEC.getOrNull(containerBSON, new ExtraInfo());
  }

  // Get array of item names from an array of item stacks
  public static String[] getItemNamesArray(ItemStack[] items){
    String[] itemNames = new String[QuickAccessConfig.MAX_QA_ITEMS];
    for (int i = 0; i < QuickAccessConfig.MAX_QA_ITEMS; i++) {
      if (items!= null && i<items.length && items[i] != null) {
        itemNames[i] = items[i].getItemId();
      }else{
        itemNames[i] = "null";
      }
    }
    return itemNames;
  }

  // Get array of item ID's from an array of item Stacks
  public static String[] getItemIdArray(ItemStack[] items){
    String[] itemIds = new String[QuickAccessConfig.MAX_QA_ITEMS];
    for (int i = 0; i < QuickAccessConfig.MAX_QA_ITEMS; i++) {
      if (items!= null && i<items.length && items[i] != null) {
        itemIds[i] = items[i].getItemId();
      }else{
        itemIds[i] = "null";
      }
    }
    return itemIds;
  }
}
