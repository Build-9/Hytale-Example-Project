package org.wojo.wojosToolbelt.Commands.component;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.wojo.wojosToolbelt.Components.QuickAccessComponent;

public class PrintQuickAccessComponentInfo extends AbstractPlayerCommand {

    public PrintQuickAccessComponentInfo(){
        super("pQAcomp", "Print QuickAccessComponent data to log");
        addAliases("pqc");
    };

    // Run the command
    // commandContext - info about who ran the command. Server console?, Some player?
    @Override
    protected void execute(@NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store, @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world) {

        commandContext.sendMessage(Message.raw("Printing QuickAccess component of player!"));
        QuickAccessComponent qacomp = store.getComponent(ref, QuickAccessComponent.getComponentType());
        if (qacomp == null){
            commandContext.sendMessage(Message.raw("Player Does not have a QA Comp!"));
        }else{
            commandContext.sendMessage(Message.raw("Data: \n"+qacomp.getPrintableString()));
        }
    }
}
