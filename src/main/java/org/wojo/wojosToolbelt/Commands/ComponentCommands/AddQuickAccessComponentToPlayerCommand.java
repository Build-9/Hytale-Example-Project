package org.wojo.wojosToolbelt.Commands.ComponentCommands;

import com.hypixel.hytale.assetstore.AssetExtraInfo;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.asset.type.item.config.Item;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.inventory.Inventory;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.CombinedItemContainer;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.wojo.wojosToolbelt.Components.QuickAccessComponent;
import org.wojo.wojosToolbelt.WojosQuickAccessPlugin;

import java.util.ArrayList;
import java.util.List;

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
        qaComp.setSlingType(QuickAccessComponent.QUICK_ACCESS_ITEM_TYPES[1]);
        qaComp.setMaxNumTotalSlingItems(500);


        List<ItemStack> validQaToolsAry = new ArrayList<>();

        // Get player items
        Player player = store.getComponent(ref, Player.getComponentType());
        Ref<EntityStore> refPlayer = player.getReference();
        Inventory inventory = player.getInventory();

        // Get Container of every player item.
        //ItemContainer storageContainer = inventory.getStorage();
        //ItemContainer hotbar = inventory.getStorage();
        CombinedItemContainer items = inventory.getCombinedEverything();

        ArrayList<String> found_items = new ArrayList<>();

        // Add any item id to storage
        items.forEach( (slot, stack ) -> {
            String id = stack.getItem().getId();
            AssetExtraInfo.Data itemData = stack.getItem().getData();

            WojosQuickAccessPlugin.LOGGER.atInfo().log("Found item in inventory with\n - ID: "+id+"\n - Data: "+itemData.toString());
        });


        // qaComp.addItemToList(found_items);

        // TODO: This should prob be in command buffer somehow
        store.addComponent(ref, QuickAccessComponent.getComponentType(), qaComp);

        // Verify Hashmap is up to date for all items with a QuickAccessComponent 
        WojosQuickAccessPlugin.hasQuickAccessComponentMap.put(refPlayer, true);
    }
}
