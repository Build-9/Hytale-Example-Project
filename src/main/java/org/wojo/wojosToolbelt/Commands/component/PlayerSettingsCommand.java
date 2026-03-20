package org.wojo.wojosToolbelt.Commands.component;

// wqa comp player   <arg: enable, equipPos, targetPos> (Update players quick access comp with new settings)
public class PlayerSettingsCommand extends AbstractPlayerCommand {
    private final DefaultArg<boolean> _enable;
    private final DefaultArg<Integer> _equipPos;
    private final DefaultArg<Integer> _targetPos;

    PlayerSettingsCommand() 
    {
        super("playerSettings","Configure the players Quick-Access-Component");
        addAliases("player","PS");

        this._enable = this.withDefaultArg(
            "enable", "Enable or disable the players QuickAccessComponent. This enables the hotbar button", 
            ArgTypes.BOOLEAN,
            false, "Hotbar button to open gui is disabled (false) by default."
        );

        this._equipPos = this.withDefaultArg(
            "equip-pos", "The position that the quick-access-item needs to be placed in and the button used to open the Quick Swap radial menu",
            ArgTypes.INTEGER,
            8, "Hotbar position 8 (Button #9)"
        );

        this._targetPos = this.withDefaultArg(
            "target-pos", "Hotbar location that Quick Access items get swaped into. (-1 swaps items to active hotbar slot)",
            ArgTypes.INTEGER,
            0, "Hotbar position 0 (Button #1)."
        );
    }

    @Override
    protected void execute(
        @NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store, 
        @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, 
        @NonNullDecl World world) 
    {
        boolean enabled = commandContext.get(PlayerSettingsCommand._enable);
        Integer equipped = commandContext.get(PlayerSettingsCommand._equipPos);
        Integer target = commandContext.get(PlayerSettingsCommand._targetPos);

        QuickAccessPlayerComponent newQuickAccessPlayerComponent = new QuickAccessPlayerComponent(enabled, equipped, target);

        store.setComponent(ref, QuickAccessPlayerComponent.getComponentType(), newQuickAccessPlayerComponent);
    }
}