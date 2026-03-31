package org.wojo.wojosToolbelt.Commands.item;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.UUIDComponent;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.wojo.wojosToolbelt.Components.QuickAccessPlayerComponent;
import org.wojo.wojosToolbelt.WojosQuickAccessPlugin;

public class PrintItemCommand extends AbstractPlayerCommand {
    public PrintItemCommand(){
        super("print","Swap Item from QuickAccessItem(stored at hotbar 9) sub container (positon 0) into hotbar slot 0");
        addAliases("pi","PrintItem","printitem", "P");
    }

    @Override
    protected void execute(@NonNullDecl CommandContext context, @NonNullDecl Store<EntityStore> store, @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world) {
        Player playerComponent = store.getComponent(ref, Player.getComponentType());
        UUIDComponent uuidComponent = store.getComponent(ref,UUIDComponent.getComponentType());

        Integer equippedPosition = QuickAccessPlayerComponent.quickAccessHotbarLocationEquipMap.get(uuidComponent);

        ItemStack quickAccessItem = playerComponent.getInventory().getHotbar().getItemStack(equippedPosition.shortValue());
        if (quickAccessItem != null){
            if (QuickAccessUtils.isQuickAccessItem(quickAccessItem)){
                context.sendMessage(Message.raw(quickAccessItem.toString()));
                WojosQuickAccessPlugin.LOGGER.atInfo().log("[DEBUG]: Printing item data -- \n"+quickAccessItem.toString());
            }else{
                WojosQuickAccessPlugin.LOGGER.atInfo().log("[ERROR]: Item is not a Quick Access Item -- \n"+quickAccessItem.toString());
            }
        }else{
            context.sendMessage(Message.raw("[ERROR]: No Item Equipped in quick access hotbar location "+equippedPosition));
        }
    }
}