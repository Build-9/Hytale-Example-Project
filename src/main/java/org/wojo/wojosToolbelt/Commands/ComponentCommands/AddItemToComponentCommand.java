// Add a UUID to a location in the component array. 
public class AddItemToComponentCommand extends AbstractPlayerCommand {
    private RequiredArg<String> _item_UUID = "";
    private OptionalArg<int> _array_index;
  
    // Constructor
    public SwapItemsCommand(){
        super("WQA_AddItem", "Add Item UUID to component");
        addAliases("wqa_AI");
        
        this._item_UUID = this.withRequiredArg("uuid", "Item UUID - Get an items UUID by holding item and running WQA_GetItemUUID", ArgTypes.STRING);
        this._array_index = this.withOptionalArg("index", "Component Array Index - Specify what button you want to use to pull item to hotbar", ArgTypes.INT);
    };
  
    @Override
    protected void execute(@NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store, @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world) {
        WojosQuickAccessPlugin.LOGGER.atInfo().log("Trying to add item to QuickAccessComponent.\n - UUID: "+this._item_UUID+"\n - TYPE: "+_item_type);
        if (!this._item_uuid){
            commandContext.sendMessage(Message.raw("Invalid Input Args. Must unclude UUID argument"));
            return;
        }
        // Add the following in the system?
        // TODO: get copy of data from component
        // TODO: Create new Component from copy, 
        // TODO: Add new item UUID to component
        // TODO: Update QuickAccessComponent on player
    }
}
