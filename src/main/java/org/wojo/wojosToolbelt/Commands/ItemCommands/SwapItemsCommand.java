package org.wojo.wojosToolbelt.Commands.ItemCommands;

// Swap an item with a given UUID to a specific hotbar location
// WQA_SwapItem <hotbar Position> <UUID> :: swap item in inventory with set UUID into given hotbar position
public class SwapItemsCommand extends AbstractPlayerCommand {
    private RequiredArg<String> _item_UUID = "";
  
    // Constructor
    public SwapItemsCommand(){
        super("WQA_SwapItem", "Swap an item with given UUID into hotbar slot 1");
        addAliases("wqa_SI");
        
        this._item_UUID = this.withRequiredArg("uuid", "Item UUID - Get an items UUID by holding item and running WQA_GetItemUUID", ArgTypes.STRING);
    };
  
    @Override
    protected void execute(@NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store, @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world) {
        WojosQuickAccessPlugin.LOGGER.atInfo().log("Trying to add item to QuickAccessComponent.\n - UUID: "+this._item_UUID+"\n - TYPE: "+_item_type);
        if (!this._item_uuid){
            commandContext.sendMessage(Message.raw("Invalid Input Args. Must unclude UUID argument"));
            return;
        }
        Item item = null;
        // TODO: Find item in inventory with given UUID
        if (item == null){
            commandContext.sendMessage(Message.raw("ERROR: No valid item found to add to QuickAccessComponent."));
        }
        // TODO: move current item from players hotbar slot 1 to somewhere in inventory
        // TODO: move found item into hotbar slot 1
    }
}
