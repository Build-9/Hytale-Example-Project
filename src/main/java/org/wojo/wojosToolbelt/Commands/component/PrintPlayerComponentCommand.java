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
import org.wojo.wojosToolbelt.WojosQuickAccessPlugin;
import org.wojo.wojosToolbelt.Components.QuickAccessPlayerComponent;

public class PrintPlayerComponentCommand extends AbstractPlayerCommand {
    PrintPlayerComponentCommand() {
        super("printp","Print the players Quick-Access component");
        addAliases("PP","printplayer", "printPlayer");
    }

    @Override
    protected void execute(
            @NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store,
            @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef,
            @NonNullDecl World world)
    {
        QuickAccessPlayerComponent qaComp = store.getComponent(ref, QuickAccessPlayerComponent.getComponentType());

        if (qaComp != null){
            String debugString = qaComp.getPrintableString();
            commandContext.sendMessage(Message.raw(debugString));
            WojosQuickAccessPlugin.LOGGER.atInfo().log(debugString);
        }else{
            commandContext.sendMessage(Message.raw("ERROR: Player does not have a Quick-Access component"));
        }
    }
}