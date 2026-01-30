package org.wojo.wojosToolbelt.Commands.ItemCommands;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.asset.type.item.config.Item;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.wojo.wojosToolbelt.WojosQuickAccessPlugin;

// Swap an item with a given UUID to a specific hotbar location
// WQA_SwapItem <hotbar Position> <UUID> :: swap item in inventory with set UUID into given hotbar position
public class SwapItemsCommand extends AbstractPlayerCommand {
    private RequiredArg<String> _item_UUID;
    private OptionalArg<Integer> _hotbar_position;
  
    // Constructor
    public SwapItemsCommand(){
        super("WQA_SwapItem", "Swap an item with given UUID into hotbar slot 1");
        addAliases("wqa_SI");
        
        this._item_UUID = this.withRequiredArg("uuid", "Item UUID - Get an items UUID by holding item and running WQA_GetItemUUID", ArgTypes.STRING);
        this._hotbar_position = this.withOptionalArg("hotbar", "Integer (0-8) - Position in hotbar to swap item into", ArgTypes.INTEGER);
    };
  
    @Override
    protected void execute(@NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store, @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world) {
        WojosQuickAccessPlugin.LOGGER.atInfo().log("Trying to swap to item.\n - UUID: "+commandContext.get(this._item_UUID));

        Item item = null;
        if (item == null){
            commandContext.sendMessage(Message.raw("ERROR: No valid item found to add to QuickAccessComponent."));
        }
        
        // TODO: Get Item ID, Hotbar Pos, Inventory from component
        // TODO: Get item ID of item in hotbar (Empty) if empty
        // TODO: Swap Item in inventory
        // TODO: Create new component with new info & update component
    }
}
