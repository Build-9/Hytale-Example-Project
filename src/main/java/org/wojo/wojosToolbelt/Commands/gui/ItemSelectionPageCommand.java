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
import org.wojo.wojosToolbelt.WojosQuickAccessPlugin;
import org.wojo.wojosToolbelt.ui.ItemSelectionGui;

import java.sql.Array;

public class ItemSelectionPageCommand extends AbstractPlayerCommand {
    private final DefaultArg<String> eventArg;
    private final DefaultArg<Integer> itemHotbarPosition;

    // Constructor
    public ItemSelectionPageCommand(){
        super("itemSelectionPage", "Do something with the tool selection GUI");
        addAliases("select", "SEL");

        this.eventArg = this.withDefaultArg("event", "Run gui event like open/close/reset", ArgTypes.STRING, "open", "Default is to open the gui");
        this.itemHotbarPosition = this.withDefaultArg("pos", "Quick Access Item's position in the hotbar", ArgTypes.INTEGER, 8, "Default position is 8 (Button 9)");
    };

    // Run the command
    // conetext - info about who ran the command. Server console?, Some player?
    @Override
    protected void execute(@NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store, @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world) {
        // ------ Get Data ------
        Player player = store.getComponent(ref, Player.getComponentType());
        QuickAccessPlayerComponent qaPlayerComp = store.getComponent(ref, QuickAccessPlayerComponent.getComponentType());

        Integer hotbarPos = commandContext.get(itemHotbarPosition);
        short itemHotbarPosition = hotbarPos.shortValue();

        // ------ Check For Quick Access Item ------
        ItemStack heldItem = player.getInventory().getActiveHotbarItem();
        ItemStack equippedItem = player.getInventory().getHotbar().getItemStack(qaPlayerComp.getEquippedPosition());
        
        // ------ Verify item is Quick Access Item ------
        Boolean isItemHeld = false;
        if (!QuickAccessUtils.isQuickAccessItem(heldItem) && !QuickAccessUtils.isQuickAccessItem(equippedItem)){
            WojosQuickAccessPlugin.LOGGER.atInfo().log("[ERROR]: Item held or equipped is not a QuickAccess Item");
            return;
        }else if (QuickAccessUtils.isQuickAccessItem(heldItem)){
            WojosQuickAccessPlugin.LOGGER.atInfo().log("[DEBUG]: Opening Held Items Quick Access Selection Gui");
            isItemHeld = true;
        }else{
            WojosQuickAccessPlugin.LOGGER.atInfo().log("[DEBUG]: Opening Equipped Items Quick Access Selection Gui");
        }

        // ------ Run GUI event ------
        ItemSelectionGui guiPage = new ItemSelectionGui(playerRef, store, isItemHeld);
        player.getPageManager().openCustomPage(ref, store, guiPage);
    }
}
