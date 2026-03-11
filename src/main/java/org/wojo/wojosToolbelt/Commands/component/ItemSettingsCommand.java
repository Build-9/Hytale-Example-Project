package org.wojo.wojosToolbelt.Commands.component;

// wqa comp item     <arg: tier, contSize, type, qaSize, gui> (Update held items quick access settings)
public class ItemSettingsCommand extends AbstractPlayerCommand {
    private static final DefaultArg<Integer> _tier;     // Item Tier (Uncommon, Common, Rare, Epic...)
    private static final DefaultArg<Integer> _contSize; // Container Size (Size of storage for items)
    private static final DefaultArg<Integer> _type;     // Item Type (Unrestricted, Toolbelt, Sling, ...)
    private static final DefaultArg<Integer> _qaSize;   // Quick Swap Size (Number of enabled buttons on UI)
    private static final DefaultArg<String>  _gui;      // GUI String

    PlayerSettingsCommand() {
        super("itemSettings", "Configure the players held Quick-Access-Item");
        addAliases("item","IS");

        this._tier = this.withDefaultArg(
            "tier", "Set the item tier [UNKNOWN:0|COMMON:1|UNCOMMON:2|RARE:3|EPIC:4|LEGENDARY:5|MYTHIC:6|CREATIVE:7]",
            ArgTypes.INTEGER,
            1, "COMMON"
        );

        this._contSize = this.withDefaultArg(
            "cont-size", "Set the items container size.",
            ArgTypes.INTEGER,
            10, "Ten items total."
        );

        this._type = this.withDefaultArg(
            "type", "Set the type of the quick access item. [UNKNOWN:0|TOOLBELT:1|BUILDERS_POUCH:2|WEAPON_SLING:3|BANDOLIER:4|QUIVER:5|UNRESTRICTED:6|CUSTOM:7]",
            ArgTypes.INTEGER,
            6, "Unrestricted"
        );

        this._qaSize = this.withDefaultArg(
            "qa-size", "Number of enabled quick access buttons",
            ArgTypes.INTEGER,
            2, "COMMON's default number of enabled items is 2 buttons."
        );

        this._gui = this.withDefaultArg(
            "gui", "Path to UI To use. If player wants to use UI that has",
            ArgTypes.STRING,
            "Pages/ThreeByThreeQuickAccess.ui", "Basic UI file with 8 total buttons."
        );
    }

    @Override
    protected void execute(
        @NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store, 
        @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, 
        @NonNullDecl World world) 
    {
        Integer tier = commandContext.get(this._tier);
        Integer type = commandContext.get(this._type);

        Integer contSize = commandContext.get(this._contSize);
        Integer qaSize = commandContext.get(this._qaSize);

        String guiPath = commandContext.get(this._gui);
        
        Player player = store.getComponent(ref, Player.getComponentType());
        ItemStack heldItem = player.getInventory().getActiveHotbarItem();
        
        if (QuickAccessUtils.isQuickAccessItem(heldItem)){
            QuickAccessItemComponent newComponent = new quickAccessItemComponent(tier, contSize, type, qaSize, gui);
            heldItem.withMetadata(
                QuickAccessComponent.CODEC,
                newComponent
            );

            short itemSlot = player.getActiveHotbarSlot();
            player.getInventory().getHotbar().removeItemStackFromSlot(itemSlot);
            player.getInventory().getHotbar().setItemStackForSlot(itemSlot, heldItem);

            WojosQuickAccessPlugin.LOGGER.atInfo.log("Updated held ItemStack with the following info:\n"+heldItem.getData().toString());

        }else{
            commandContext.sendMessage(Message.raw("Not holding a quick access item"));
        }
    }
}

