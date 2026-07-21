package org.wojo.wojosToolbelt.QuickAccessUtils;

import com.hypixel.hytale.codec.ExtraInfo;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.entity.UUIDComponent;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.InventoryComponent;
import com.hypixel.hytale.server.core.inventory.container.ItemStackItemContainer;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.bson.BsonDocument;
import org.wojo.wojosToolbelt.Components.QuickAccessPlayerComponent;
import org.wojo.wojosToolbelt.Config.QuickAccessConfig;
import org.wojo.wojosToolbelt.WojosQuickAccessPlugin;
import org.wojo.wojosToolbelt.ui.GenericRadialSelectionUi;
import org.wojo.wojosToolbelt.ui.GuiSelectionFactory;
import org.wojo.wojosToolbelt.ui.Radials.RadialGui2;
import org.wojo.wojosToolbelt.ui.Radials.RadialGui8;
import org.wojo.wojosToolbelt.ui.SelectionUiThreeByThree;

import java.util.Arrays;
import java.util.UUID;

import static org.wojo.wojosToolbelt.ui.GuiSelectionFactory.createGui;

public class QuickAccessUtils {

  public static QuickAccessPlayerComponent validateQuickAccessPlayerComponent(QuickAccessPlayerComponent component){
    if (component.getEquippedPosition() < 0 || component.getEquippedPosition() > 8) {
      WojosQuickAccessPlugin.LOGGER.atInfo().log("[ERROR]: QuickAccessComponent.equipedPosition is out of range. Setting to default (8)");
      component.setTargetPosition(0); // Reset to default value
    }

    if (component.getTargetPosition() < 0 || component.getTargetPosition() > 8) {
      WojosQuickAccessPlugin.LOGGER.atInfo().log("[ERROR]: QuickAccessComponent.targetPosition is out of range. Setting to default (0).");
      component.setTargetPosition(0); // Reset to default value
    }else if ( component.getTargetPosition() == component.getEquippedPosition()) {
      WojosQuickAccessPlugin.LOGGER.atInfo().log("[ERROR]: QuickAccessComponent.targetPosition is same as equippedPosition. Moving target location to (0 or 1 if taken)");
      if (component.getTargetPosition() == 0) {
        component.setTargetPosition(1);
      }else{
        component.setTargetPosition(0);
      }
    }

    String guiFile = component.getGuiFile();
    if (!Arrays.asList(QuickAccessConfig.ALL_SELECTION_GUI_FILES).contains(guiFile)){
      WojosQuickAccessPlugin.LOGGER.atInfo().log("[ERROR]: QuickAccessComponent.guiFile is not one of the expected. Resetting to default (Three-By-Three Radial)");
      component.setGuiFile(QuickAccessConfig.DEFAULT_SELECTION_GUI_FILE);
    }
    return component;
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

    InventoryComponent.Hotbar hotbar = (InventoryComponent.Hotbar) store.getComponent(player_ref.getReference(), InventoryComponent.getComponentTypeById(InventoryComponent.HOTBAR_SECTION_ID));
    int equippedPosition = quickAccessPlayerComp.getEquippedPosition();
    ItemStack quickAccessItem = hotbar.getInventory().getItemStack((short) equippedPosition);

    if (!QuickAccessUtils.isQuickAccessItem(quickAccessItem)) {
      return null;
    }
    return quickAccessItem;
  }

  public static ItemStack getHeldQaItemOrNull(PlayerRef player_ref, Store<EntityStore> store) {
    WojosQuickAccessPlugin.LOGGER.atInfo().log("QuickAccessUtils.getHeldQaItemOrNull");
    if (player_ref == null || player_ref.getReference() == null || !player_ref.isValid()) {
      WojosQuickAccessPlugin.LOGGER.atInfo().log("WARN: Player is NULL");
      return null;
    } else if (store.getComponent(player_ref.getReference(), QuickAccessPlayerComponent.getComponentType()) == null) {
      WojosQuickAccessPlugin.LOGGER.atInfo().log("WARN: QaPlayerComp is NULL, Adding one to player.");
      QuickAccessPlayerComponent quickAccessPlayerComponent = new QuickAccessPlayerComponent();
      store.addComponent(player_ref.getReference(), QuickAccessPlayerComponent.getComponentType(), quickAccessPlayerComponent);
    }

    InventoryComponent.Hotbar hotbar = (InventoryComponent.Hotbar) store.getComponent(player_ref.getReference(), InventoryComponent.getComponentTypeById(InventoryComponent.HOTBAR_SECTION_ID));
    ItemStack quickAccessItem = hotbar.getActiveItem();

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
    InventoryComponent.Hotbar hotbar = (InventoryComponent.Hotbar) store.getComponent(player_ref.getReference(), InventoryComponent.getComponentTypeById(InventoryComponent.HOTBAR_SECTION_ID));
    if (targetPosition == -1) {
      targetItem = hotbar.getActiveItem();
    } else {
      targetItem = hotbar.getInventory().getItemStack((short) targetPosition);
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

  public static void openQuickAccessUI(Store<EntityStore>store, Ref<EntityStore> ref) {
    // ------ Get Needed Data Stores ------
    Player player = store.getComponent(ref, Player.getComponentType());
    QuickAccessPlayerComponent qaPlayerComp = store.getComponent(ref, QuickAccessPlayerComponent.getComponentType());

    // ------ Get Quick Access Item ------
    InventoryComponent.Hotbar hotbar = (InventoryComponent.Hotbar) store.getComponent(ref, InventoryComponent.getComponentTypeById(InventoryComponent.HOTBAR_SECTION_ID));
    ItemStack heldItem = hotbar.getActiveItem();
    ItemStack equippedItem = hotbar.getInventory().getItemStack((short)qaPlayerComp.getEquippedPosition());
    ItemStack quickAccessItem = null;
    Integer qaItemHotbarPosition = 0;

    // ------ Verify Held or Equipped Item is a QuickAccessItem ------
    boolean isItemHeld = false;
    if (!QuickAccessUtils.isQuickAccessItem(heldItem) && !QuickAccessUtils.isQuickAccessItem(equippedItem)){
      WojosQuickAccessPlugin.LOGGER.atInfo().log("[ERROR]: Item held or equipped is not a QuickAccess Item");
      return;
    }else if (QuickAccessUtils.isQuickAccessItem(heldItem)){
      // Priority to use held item over equipped item
      WojosQuickAccessPlugin.LOGGER.atInfo().log("[DEBUG]: Opening Held Items Quick Access Selection Gui");
      qaItemHotbarPosition = (int) hotbar.getActiveSlot();
      quickAccessItem = heldItem;
    }else{
      WojosQuickAccessPlugin.LOGGER.atInfo().log("[DEBUG]: Opening Equipped Items Quick Access Selection Gui");
      qaItemHotbarPosition = qaPlayerComp.getEquippedPosition();
      quickAccessItem = equippedItem;
    }

    // ------ TODO: Update Selection GUI's to have a object for each UI instead of single monolithic UI file with multiple switch cases
    // ------ Get Needed GUI Object ------
    // -- Have a different class for each file type instead of a single UI file --
    // -- Get ANy Needed Vars needed for GUI File --
    PlayerRef playerRef = getPlayerRef(store, ref);
    // InteractiveCustomUIPage<ItemSelectionGui.SelectionUiData> quickAccessSelectionUI = QuickAccessUtils.getSelectorGui(qaPlayerComp, playerRef, store, isItemHeld);
    // -- Open UI --
    GenericRadialSelectionUi guiPage = GuiSelectionFactory.createGui(playerRef, store, quickAccessItem, qaItemHotbarPosition);
    //RadialGui2 guiPage = new RadialGui2(playerRef, store, quickAccessItem, qaItemHotbarPosition);
    // player.getPageManager().openCustomPage(ref, store, quickAccessSelectionUI)
    
    // ------ Run GUI event ------
    // PlayerRef playerRef = getPlayerRef(store, ref);
    //RadialGui8 guiPage = new RadialGui8(playerRef, store,);
    // SelectionUiThreeByThree guiPage = new SelectionUiThreeByThree(playerRef,store,isItemHeld);
    player.getPageManager().openCustomPage(ref, store, guiPage);
  }

  public static PlayerRef getPlayerRef(Store<EntityStore>store, Ref<EntityStore> ref) {
    UUIDComponent uuidComponent = store.getComponent(ref, UUIDComponent.getComponentType());
    UUID uuid = uuidComponent.getUuid();
    return Universe.get().getPlayer(uuid);
  }
}
