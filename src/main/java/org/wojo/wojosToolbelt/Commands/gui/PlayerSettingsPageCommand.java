package org.wojo.wojosToolbelt.Commands.gui;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.DefaultArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.wojo.wojosToolbelt.QuickAccessUtils.QuickAccessUtils;
import org.wojo.wojosToolbelt.ui.PlayerSettingsGui;

import java.sql.Array;

public class PlayerSettingsPageCommand extends AbstractPlayerCommand {
    private final DefaultArg<String> eventArg;

    // Constructor
    public PlayerSettingsPageCommand(){
        super("playerSettingsPage", "Do something with the tool selection GUI");
        addAliases("settings", "SET");

        this.eventArg = this.withDefaultArg("event", "Run gui event like open/close/reset", ArgTypes.STRING, "open", "Default is to open the gui");
    };

    // Run the command
    // conetext - info about who ran the command. Server console?, Some player?
    @Override
    protected void execute(@NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store, @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world) {
        // ------ Get Data ------
        Player player = store.getComponent(ref, Player.getComponentType());
        ItemStack quickAccessItem = player.getInventory().getActiveHotbarItem();
        
        // ------ Verify item is Quick Access Item ------
        if (!QuickAccessUtils.isQuickAccessItem(quickAccessItem)){
            return;
        }

        // ------ Run GUI event ------
        PlayerSettingsGui guiPage = new PlayerSettingsGui(playerRef, store);
        player.getPageManager().openCustomPage(ref, store, guiPage);
    }
}
