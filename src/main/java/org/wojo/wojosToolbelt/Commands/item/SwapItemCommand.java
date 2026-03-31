package org.wojo.wojosToolbelt.Commands.item;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.DefaultArg;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.wojo.wojosToolbelt.Events.SwapQuickAccessItemEvent;
import org.wojo.wojosToolbelt.Handlers.SwapQuickAccessItemEventHandler;

public class SwapItemCommand extends AbstractPlayerCommand {
    private final DefaultArg<Integer> _qaContainerItemToSwapPostition;
    private final DefaultArg<Integer> _equippedQaItemHotbarPosition;
    private final DefaultArg<Integer> _targetQaItemHotbarPosition;

    public SwapItemCommand(){
        super("swap","Swap Item from QuickAccessItem(stored at hotbar 9) sub container (positon 0) into hotbar slot 0");
        this._qaContainerItemToSwapPostition = this.withDefaultArg("container-pos","Quick Access Container's Inventory position to swap to hotbar",
                ArgTypes.INTEGER,
                0, "Pull from first position in inventory by default");
        this._equippedQaItemHotbarPosition = this.withDefaultArg("equipped-pos", "Players Hotbar position the Quick Access Item is located",
                ArgTypes.INTEGER,
                8, "Default equipped location is at hotbar position 8 (Hotbar Key 9)");
        this._targetQaItemHotbarPosition = this.withDefaultArg("target-pos", "Players Hotbar position to place the item from the container",
                ArgTypes.INTEGER,
                0, "Default target location is at hotbar position 0 (Hotbar key 1)");
    }

    @Override
    protected void execute(@NonNullDecl CommandContext context, @NonNullDecl Store<EntityStore> store, @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world) {
        Integer equippedPosition = context.get(_equippedQaItemHotbarPosition);
        Integer targetPosition = context.get(_targetQaItemHotbarPosition);
        Integer containerPosition = context.get(_qaContainerItemToSwapPostition);

        if (equipped_pos == target_position){
            context.sendMessage(Message.raw("[ERROR]: Trying to swap item from Quick Access Container to hotbar location the container is in."))
        }
        SwapQuickAccessItemEvent.dispatch(playerRef.getReference(), store, containerPosition.shortValue(), equippedPosition.shortValue(), targetPosition.shortValue());
    }
}
