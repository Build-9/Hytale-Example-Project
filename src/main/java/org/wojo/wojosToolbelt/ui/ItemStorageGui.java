package org.wojo.wojosToolbelt.ui;

import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.server.core.entity.entities.player.pages.BasicCustomUIPage;
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.flock.FlockMembershipSystems;
import org.wojo.wojosToolbelt.Components.QuickAccessComponent;
import org.wojo.wojosToolbelt.Config.QuickAccessConfig;
import org.wojo.wojosToolbelt.WojosQuickAccessPlugin;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

// UI page to pick what each button on Item Selection corresponds to. 
// This UI's whole job is to update the QuickAccessComponent._quick_access_item_uuids array with specific enums at different places.
// Those values then get loaded into the defined buttons on the itemSelectionGui

// TODO: Get Currently Stored items in _quick_access_item_uuids as defaults
// TODO: Get list of possible items that can be stored from players hotbar & inventory
// TODO: Create drowndown selection options from the list of strings
// TODO: Open GUI 
// TODO: Handle user dropdown selection for local Component
// TODO: Handle user confirm to updat player component
public class ItemStorageGui extends BasicCustomUIPage {
    private final int MAX_DOPDOWNS = QuickAccessConfig.MAX_QA_ITEMS;
    private QuickAccessComponent componentCopy;
    
    public ItemStorageGui(@Nonnull PlayerRef playerRef) {
        super(playerRef, CustomPageLifetime.CanDismissOrCloseThroughInteraction);

        Ref<EntityStore> ref = playerRef.getReference();

        if(ref != null){
            Store<EntityStore> store = ref.getStore();
            QuickAccessComponent qa_comp = store.getComponent(ref, QuickAccessComponent.getComponentType());
            if (qa_comp != null){
                this.componentCopy = new QuickAccessComponent(qa_comp);
            }
        }
    }

    @Override
    public void build(@Nonnull UICommandBuilder uiCommandBuilder) {
        // Path is relative to:
        //      src/main/resources/Common/UI/Custom
        uiCommandBuilder.append("Pages/ItemSelectionUI.ui");
        if(this._num_displayed_items > QuickAccessConfig.STORAGE_ITEM_GUIS[QuickAccessConfig.STORAGE_ITEM_GUIS.length - 1]){
            WojosQuickAccessPlugin.LOGGER.atInfo().log("WARNING: Trying to display more items then we have a GUI for. Some items won't be selectable");
        }
        // TODO: Load different displays bassed on ComponentType and Tier
    }

    // TODO: Handle button press interaction on GUI.
}
