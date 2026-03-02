class ComponentUtils {
  public static ItemStack updateQuickAccessComponent(ItemStack quickAccessItemStack) {
      // Update Quick Access Component Arrays from item Data
  }

  // Get a quickAccessComponent from an item if it exists, else get null
  private QuickAccessComponent getItemsQuickAccessComponent (ItemStack itemStack) {
        return itemStack.getFromMetadataOrNull(QuickAccessConfig.QUICK_ACCESS_COMPONENT_ID, QuickAccessComponent.CODEC);
  }

  // Get array of items in a container if item has container field, else get null. 
  private ItemStack[] getContainerComponentItems(ItemStack itemStack) {
        BsonDocument containerBSON = itemStack.getFromMetadataOrNull(ItemStackItemContainer.CONTAINER_CODEC);
        return ItemStackItemContainer.ITEMS_CODEC.getOrNull(containerBSON, new ExtraInfo());
  }
}
