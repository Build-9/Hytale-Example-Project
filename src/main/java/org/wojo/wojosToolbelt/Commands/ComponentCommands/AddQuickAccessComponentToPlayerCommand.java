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

public class AddQuickAccessComponentToPlayerCommand extends AbstractPlayerCommand {
    // Constructor

    public AddQuickAccessComponentToPlayerCommand(){
        super("addQAcomp", "Add Quick Access Component to person running command");
        addAliases("aqa");
    };

    // Run the command
    // commandContext - info about who ran the command. Server console?, Some player?
    @Override
    protected void execute(@NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store, @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world) {
        commandContext.sendMessage(Message.raw("Adding QuickAccess component to player!"));

        QuickAccessComponent qaComp = new QuickAccessComponent();
        qaComp.setSlingType(QuickAccessComponent.QUICK_ACCESS_ITEM_TYPE.CREATIVE_TOOL_SLING);
        qaComp.setMaxNumTotalSlingItems(500);

        List<ItemStack> validQaToolsAry = new ArrayList<>();
        
        // Get player items
        Player player = store.getComponent(ref, Player.getComponentType());
        Inventory inventory = player.getInventory();
        ItemContainer storageContainer = inventory.getStorage();
        List<ItemStack> items = inventory.getCombinedEverything();

        // Search Items for tools
        for (ItemStack stack : items){
            Item item = stack.getItem();
            if (item.hasTag("tool") || item.hasTag("Tool") || item.hasTag("TOOL")) {
                validQaToolsAry.add(item);
            }
        }
        
        qaComp.setItemAry(validQaToolsAry);
        // TODO: This should prob be in command buffer somehow
        store.addComponent(ref, WojosQuickAccessPlugin.get().getQuickAccessComponentType(), qaComp);
    }
}
