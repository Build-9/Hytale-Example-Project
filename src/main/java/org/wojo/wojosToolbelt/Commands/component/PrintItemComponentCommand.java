package org.wojo.wojosToolbelt.Commands.component;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.bson.BsonDocument;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.wojo.wojosToolbelt.Components.QuickAccessItemComponent;
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
        ItemStack heldItem = player.getInventory().getActiveHotbarItem();

        if(QuickAccessUtils.isQuickAccessItem(heldItem)){
            BsonDocument qaCompBSON = BsonDocument.parse(heldItem.getFromMetadataOrNull(QuickAccessItemComponent.KEY,QuickAccessItemComponent.CODEC).toString());
            if (qaCompBSON != null){
                commandContext.sendMessage(Message.raw("qaItemComp: \n"+qaCompBSON.toString()));
                WojosQuickAccessPlugin.LOGGER.atInfo().log("DEBUG: \n"+qaCompBSON.toString());
            }else{
                commandContext.sendMessage(Message.raw("ERROR: Failed to get QA comp data from quick-access item"));
                WojosQuickAccessPlugin.LOGGER.atInfo().log("ERROR: Failed to get QA comp data from quick-access item");
            }
        }else{
            commandContext.sendMessage(Message.raw("ERROR: Held item does not have a Quick-Access component."));
        }
    }
}