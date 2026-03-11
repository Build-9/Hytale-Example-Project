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
    private final DefaultArg<Integer> srcInventoryPostition;
    private final DefaultArg<Integer> quickAccessItemHotbarPosition;

    public SwapItemCommand(){
        super("swapItem","Swap Item from QuickAccessItem(stored at hotbar 9) sub container (positon 0) into hotbar slot 0");
        this.srcInventoryPostition = this.withDefaultArg("src-pos","Inventory position in QuickAccess Item to pull item from",
                ArgTypes.INTEGER,
                0, "Pull from first position in inventory by default");

        this.quickAccessItemHotbarPosition = this.withDefaultArg("hotbar-pos","Hotbar position in QuickAccess Item",
                ArgTypes.INTEGER,
                8, "Default equipped hotbar position 8 (Key 9)");

        addAliases("swap", "S");
    }

    @Override
    protected void execute(@NonNullDecl CommandContext context, @NonNullDecl Store<EntityStore> store, @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world) {
        Integer sourcePosition = context.get(srcInventoryPostition);
        Integer hotbarPosition = context.get(quickAccessItemHotbarPosition);
        SwapQuickAccessItemEvent.dispatch(playerRef.getReference(), store, sourcePosition.shortValue(), hotbarPosition.shortValue());
    }
}
