public class PrintPlayerComponentCommand extends AbstractPlayerCommand {
    PrintPlayerComponentCommand() {
        super("printp","Print the players Quick-Access component");
        addAlias("PP","printplayer", "printPlayer");
    }

    @Override
    protected void execute(
        @NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store, 
        @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, 
        @NonNullDecl World world) 
    {
        QuickAccessPlayerComponent qaComp = store.getComponent(ref, QuickAccessPlayerComponent.getComponentType());

        if (qaComp){
            String debugString = qaComp.getPrintableString();
            commandContext.sendMessage(Message.raw(debugString));
            WojosQuickAccessPlugin.LOGGER.atInfo().log(debugString);
        }else{
            commandContext.sendMessage(Message.raw("ERROR: Player does not have a Quick-Access component"));
        }
    }
}