package org.wojo.wojosToolbelt.Commands.component;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.inventory.InventoryComponent;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.bson.BsonDocument;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.wojo.wojosToolbelt.Components.QuickAccessItemComponent;
import org.wojo.wojosToolbelt.Components.QuickAccessItemComponentFactory;
import org.wojo.wojosToolbelt.Config.QuickAccessConfig;
import org.wojo.wojosToolbelt.QuickAccessUtils.QuickAccessUtils;
import org.wojo.wojosToolbelt.WojosQuickAccessPlugin;

public class PrintItemComponentCommand extends AbstractPlayerCommand {
    PrintItemComponentCommand() {
        super("printi","Print the held items quick access component");
        addAliases("PI","printitem", "printItem");
    }

    @Override
    protected void execute(
            @NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store,
            @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef,
            @NonNullDecl World world)
    {
        Player player = store.getComponent(ref, Player.getComponentType());
        InventoryComponent.Hotbar hotbar = (InventoryComponent.Hotbar) store.getComponent(ref, InventoryComponent.getComponentTypeById(InventoryComponent.HOTBAR_SECTION_ID));
        ItemStack heldItem = hotbar.getActiveHotbarItem();

        if(QuickAccessUtils.isQuickAccessItem(heldItem)){
            QuickAccessItemComponent qaItemComp = QuickAccessItemComponentFactory.createQuickAccessItemComponent(heldItem);
            commandContext.sendMessage(Message.raw("qaItemComp: \n"+qaItemComp.getPrintableString()));
            WojosQuickAccessPlugin.LOGGER.atInfo().log("DEBUG: \n"+qaItemComp.getPrintableString());
        }else{
            commandContext.sendMessage(Message.raw("ERROR: Held item does not have a Quick-Access component."));
        }
    }
}