package org.wojo.wojosToolbelt.Commands.component;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.DefaultArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

import org.wojo.wojosToolbelt.Components.QuickAccessPlayerComponent;

// wqa comp player   <arg: enable, equipPos, targetPos> (Update players quick access comp with new settings)
public class PlayerSettingsCommand extends AbstractPlayerCommand {
    private final DefaultArg<Boolean> _enable;
    private final DefaultArg<Integer> _equipPos;
    private final DefaultArg<Integer> _targetPos;
    private final DefaultArg<String> _guiFile;

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

        this._guiFile = withDefaultArg(
            "gui-file", "The file used to change max number of possible quick access buttons.",
            ArgTypes.STRING,
            "Pages/ThreeByThreeQuickAccess.ui", "Default UI has 3x3 grid with 8 sleections"
        );
    }

    @Override
    protected void execute(
            @NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store,
            @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef,
            @NonNullDecl World world)
    {
        boolean enabled = commandContext.get(this._enable);
        Integer equipped = commandContext.get(this._equipPos);
        Integer target = commandContext.get(this._targetPos);
        String guiFile = commandContext.get(this._guiFile);

        QuickAccessPlayerComponent newQuickAccessPlayerComponent = new QuickAccessPlayerComponent();
        newQuickAccessPlayerComponent.setEquippedPosition(equipped);
        newQuickAccessPlayerComponent.setTargetPosition(target);
        newQuickAccessPlayerComponent.setIsEnabled(enabled);
        newQuickAccessPlayerComponent.setGuiFile(guiFile);

        QuickAccessPlayerComponent existingComp = store.getComponent(ref, QuickAccessPlayerComponent.getComponentType());
        if (existingComp == null) {
            store.addComponent(ref, QuickAccessPlayerComponent.getComponentType(), newQuickAccessPlayerComponent);
        }else{
            store.replaceComponent(ref, QuickAccessPlayerComponent.getComponentType(), newQuickAccessPlayerComponent);
        }
    }
}