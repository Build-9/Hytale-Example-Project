// Get a UUID for the held item
public class getItemIdCommand extends AbstractPlayerCommand {
  
    // Constructor
    public getItemIdCommand(){
        super("WQA_GetItemId", "Get a unique UUID for the held itemstack");
        addAliases("wqa_GII");
    };
  
    @Override
    protected void execute(@NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store, @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world) {
        WojosQuickAccessPlugin.LOGGER.atInfo().log("Getting ID of held item.");
        // TODO: Get held item
        // TODO: Get Id of held item
        // TODO: Create UUID if ones doesn't exist & Add to Item Stack
        // TODO: Print UUID to terminal and log
    }
}
