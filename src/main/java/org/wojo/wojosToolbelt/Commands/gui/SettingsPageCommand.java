package org.wojo.wojosToolbelt.Commands.gui;

import com.hypixel.hytale.codec.ExtraInfo;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.asset.type.item.config.Item;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.inventory.Inventory;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.ItemStackItemContainer;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.bson.BsonDocument;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.wojo.wojosToolbelt.Commands.WojosQuickAccessCommandCollection;
import org.wojo.wojosToolbelt.Components.QuickAccessComponent;
import org.wojo.wojosToolbelt.Config.QuickAccessConfig;
import org.wojo.wojosToolbelt.WojosQuickAccessPlugin;
import org.wojo.wojosToolbelt.ui.ItemSelectionGui;

import java.sql.Array;

public class SettingsPageCommand extends AbstractPlayerCommand {
    private final DefaultArg<String> eventArg;

    // Constructor
    public ItemSelectionPageCommand(){
        super("settingsPage", "Do something with Quick Access Item's settings GUI");
        addAliases("settings", "SET");

        this.eventArg = this.withDefaultArg("event", "Run gui event like open/close/reset", ArgTypes.STRING, "open", "Default is to open the gui");
    };

    // Run the command
    // conetext - info about who ran the command. Server console?, Some player?
    @Override
    protected void execute(@NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store, @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world) {
        Player player = store.getComponent(ref,Player.getComponentType());

        // ------ Get Item Info ------
        ItemStack quickAccessItem = player.getInventory().getActiveHotbarItem();
        
        ItemSelectionGui guiPage = new ItemSelectionGui(playerRef, player, quickAccessItem);
        player.getPageManager().openCustomPage(ref, store, guiPage);
    }
}
