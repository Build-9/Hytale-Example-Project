package org.wojo.wojosToolbelt.Commands.GuiCommands;

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
import org.wojo.wojosToolbelt.ui.ToolSelectionGui;
// import org.wojo.wojosToolbelt.ui.ToolSelectionGui;

public class OpenToolbeltSelectionGuiCommand extends AbstractPlayerCommand {
    // Constructor
    public OpenToolbeltSelectionGuiCommand(){
        super("Open", "Opening the tool selection GUI");
        addAliases("o");
    };

    // Run the command
    // conetext - info about who ran the command. Server console?, Some player?
    @Override
    protected void execute(@NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store, @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world) {
        commandContext.sendMessage(Message.raw("Opening the Tool Selection GUI"));

        Player player = commandContext.senderAs(Player.class);
        ToolSelectionGui guiPage = new ToolSelectionGui(playerRef);

        player.getPageManager().openCustomPage(ref, store, guiPage);
        playerRef.sendMessage(Message.raw("UI Page Shown"));

//        CompletableFuture.runAsync(() -> {
//        }, world);
    }
}
