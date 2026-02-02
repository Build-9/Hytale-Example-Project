package org.wojo.wojosToolbelt.Commands.ItemCommands;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.OptionalArg;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.wojo.wojosToolbelt.WojosQuickAccessPlugin;

// Add a UUID to a location in the component array.
public class AddItemToComponentCommand extends AbstractPlayerCommand {
    private RequiredArg<String> _item_UUID;
    private OptionalArg<Integer> _array_index;
  
    // Constructor
    public AddItemToComponentCommand(){
        super("WQA_AddItem", "Add Item UUID to component");
        addAliases("wqa_AI");
        
        this._item_UUID = this.withRequiredArg("uuid", "Item UUID - Get an items UUID by holding item and running WQA_GetItemUUID", ArgTypes.STRING);
        this._array_index = this.withOptionalArg("index", "Component Array Index - Specify what button you want to use to pull item to hotbar", ArgTypes.INTEGER);
    };
  
    @Override
    protected void execute(@NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store, @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world) {
        WojosQuickAccessPlugin.LOGGER.atInfo().log("Trying to add item to QuickAccessComponent.\n - UUID: "+commandContext.get(this._item_UUID)+"\n - Pos: "+ commandContext.get(this._array_index));
        if (commandContext.get(this._array_index) != null){
            commandContext.sendMessage(Message.raw("Invalid Input Args. Must unclude UUID argument"));
            return;
        }
        // Get Copy of Component
        // TODO: Add item UUID, to component at desired array position (Position correlates to button thats used to equip item)
        // TODO: Update QuickAccessComponent on player
    }
}
