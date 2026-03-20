public class PrintItemComponentCommand extends AbstractPlayerCommand {
    PrintPlayerComponentCommand() {
        super("printi","Print the held items quick access compoenent");
        addAlias("PI","printitem", "printItem");
    }

    @Override
    protected void execute(
        @NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store, 
        @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, 
        @NonNullDecl World world) 
    {
        Player player = store.getComponent(ref, Player.getComponentType());
        ItemStack heldItem = player.getActiveHotbarItem();

        if(heldItem != null && QuickAccessUtils.isQuickAccessItem()){
            BsonDocument qaCompBSON = quickAccessItemStack.getFromMetadataOrNull(QuickAccessItemComponent.CODEC);
            if (qaCompBSON){
                commandContext.sendMessage(Message.raw("qaItemComp: \n"+qaCompBSON.toString()));
                WojosQuickAccessPlugin.LOGGER.atInfo().log("DEBUG: \n"+qaCompBSON.toString());
            }else{
                commandContext.sendMessage(Message.raw("ERROR: Failed to get QA comp data from quick-access item"));
                WojosQuickAccessPlugin.LOGGER.atInfo().log("ERROR: Failed to get QA comp data from quick-access item");
            }
        }else{
            commandContext.sendMessage(Message.raw("ERROR: Held item does not have a Quick-Access component."));
        }
    }
}