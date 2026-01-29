package org.wojo.wojosToolbelt.Commands.ComponentCommands;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.wojo.wojosToolbelt.Components.QuickAccessComponent;
import org.wojo.wojosToolbelt.WojosQuickAccessPlugin;

public class RemoveQuickAccessComponentFromPlayerCommand extends AbstractPlayerCommand {
    // Constructor

    public RemoveQuickAccessComponentFromPlayerCommand(){
        super("rmQAcomp", "Remove QuickAccessComponent from player");
        addAliases("rqc");
    };

    // Run the command
    // commandContext - info about who ran the command. Server console?, Some player?
    @Override
    protected void execute(@NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store, @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world) {
        commandContext.sendMessage(Message.raw("Removing QuickAccess component from player!"));

        QuickAccessComponent qacomp = store.getComponent(ref, WojosQuickAccessPlugin.get().getQuickAccessComponentType());
        if (qacomp == null){
            commandContext.sendMessage(Message.raw("Player Does not have a QA Comp!"));
        }

        store.removeComponent(ref, WojosQuickAccessPlugin.get().getQuickAccessComponentType());


    }
}
