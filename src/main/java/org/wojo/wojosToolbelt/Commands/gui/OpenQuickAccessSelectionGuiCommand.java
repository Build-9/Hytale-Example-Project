package org.wojo.wojosToolbelt.Commands.gui;

import com.hypixel.hytale.codec.ExtraInfo;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.asset.type.item.config.Item;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.inventory.Inventory;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.ItemStackItemContainer;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.bson.BsonDocument;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.wojo.wojosToolbelt.Commands.WojosQuickAccessCommandCollection;
import org.wojo.wojosToolbelt.Components.QuickAccessComponent;
import org.wojo.wojosToolbelt.Config.QuickAccessConfig;
import org.wojo.wojosToolbelt.WojosQuickAccessPlugin;
import org.wojo.wojosToolbelt.ui.ItemSelectionGui;

import java.sql.Array;

public class OpenQuickAccessSelectionGuiCommand extends AbstractPlayerCommand {
    // Constructor
    public OpenQuickAccessSelectionGuiCommand(){
        super("Open", "Opening the tool selection GUI");
        addAliases("o", "open");
    };

    // Run the command
    // conetext - info about who ran the command. Server console?, Some player?
    @Override
    protected void execute(@NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store, @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world) {
        commandContext.sendMessage(Message.raw("Opening the Tool Selection GUI"));
        Player player = store.getComponent(ref,Player.getComponentType());

        // ------ Get Item Info ------
        ItemStack quickAccessItem = player.getInventory().getActiveHotbarItem();
        
        ItemSelectionGui guiPage = new ItemSelectionGui(playerRef, player, quickAccessItem, containerItems, quickAccessComponent, targetItem);
        player.getPageManager().openCustomPage(ref, store, guiPage);
    }

    private ItemStack[] getItemsInQuickAccessContainer(ItemStack quickAccessItemStack) {
        // TODO: Verify its a quick access item
        BsonDocument containerBSON = quickAccessItemStack.getFromMetadataOrNull(ItemStackItemContainer.CONTAINER_CODEC);
        if (containerBSON == null){return null;}
        WojosQuickAccessPlugin.LOGGER.atInfo().log(containerBSON.toString());

        ItemStack[] containerItems = ItemStackItemContainer.ITEMS_CODEC.getOrNull(containerBSON, new ExtraInfo());
        if (containerItems != null) {
            WojosQuickAccessPlugin.LOGGER.atInfo().log(containerItems.toString());
        }else{
            WojosQuickAccessPlugin.LOGGER.atInfo().log("No Items in container!");
            return null;
        }

        WojosQuickAccessPlugin.LOGGER.atInfo().log("Item ID's in Quick Access Item: ");
        for (ItemStack item : containerItems){
            if (item != null){
                WojosQuickAccessPlugin.LOGGER.atInfo().log(" - "+item.getItemId());
            }else{
                WojosQuickAccessPlugin.LOGGER.atInfo().log(" - null)");
            }
        }

        return containerItems;
    }

    private QuickAccessComponent getItemsQuickAccessComponent (ItemStack quickAccessItemStack) {
        QuickAccessComponent quickAccessComponent = quickAccessItemStack.getFromMetadataOrNull(QuickAccessConfig.QUICK_ACCESS_COMPONENT_ID, QuickAccessComponent.CODEC);
        if(quickAccessComponent == null){
            WojosQuickAccessPlugin.LOGGER.atInfo().log("Quick Access Comp Data failed to parse! Creating new Component");
            quickAccessComponent = new QuickAccessComponentFactory.createQuickAccessComponent(quickAccessItemStack.getItem().getId());
            return null;
        }
        WojosQuickAccessPlugin.LOGGER.atInfo().log("Quick Access Comp Data: \n"+quickAccessComponent.getPrintableString());
        return quickAccessComponent;
    }

    // Save the updated conponent data back to the players item in their inventory
    private void saveUpdatedComponent(QuickAccessComponent newComponent, Player player){
        WojosQuickAccessPlugin.LOGGER.atInfo().log("Updated Component Data: "+newComponent.getPrintableString());
        ItemStack heldItemStack = player.getInventory().getActiveHotbarItem();

        // Create new local item with updated info
        ItemStack newItemStack = heldItemStack.setMetadata(
                QuickAccessConfig.QUICK_ACCESS_COMPONENT_ID,
                QuickAccessComponent.CODEC,
                newComponent
                );
        
        // newItemStack.setMetadata(
        //     new NamespacedKey("WojosToolbelt", QuickAccessConfig.QUICK_ACCESS_COMPONENT_ID),
        //     newComponent
        // );

        // Update item in player inventory with new item
        player.getInventory().getHotbar().removeItemStackFromSlot(player.getInventory().getActiveSlot(Inventory.HOTBAR_SECTION_ID));
        player.getInventory().getHotbar().addItemStackToSlot(player.getInventory().getActiveSlot(Inventory.HOTBAR_SECTION_ID), newItemStack);
    }

}
