package org.wojo.wojosToolbelt.ui.Radials;

import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.wojo.wojosToolbelt.Config.QuickAccessConfig;
import org.wojo.wojosToolbelt.ui.GenericRadialSelectionUi;
import org.wojo.wojosToolbelt.ui.GuiButtonData;

public class RadialGui8 extends GenericRadialSelectionUi {

    @Override
    protected void constructFileSpecificData() {
        this._NUM_QA_BUTTONS = 8;
        this._guiFile = QuickAccessConfig.SELECTION_GUI_FILE_THREE_BY_THREE;
        this._defaultBgFile = "";
        this._currentBgFile = "";

        for (int i=0; i < _NUM_QA_BUTTONS; i++) {
            GuiButtonData btnData = new GuiButtonData(null,"","","","true","", "#QuickAccessButton"+String.valueOf(i),"#QuickAccessButton"+String.valueOf(i)+"Img","HoverImg","PressImg");
            this._quickAccessButtons.addLast(btnData);
        }
    }

    public RadialGui8(@NonNullDecl PlayerRef player_ref, Store<EntityStore> store, ItemStack quick_access_item, Integer quick_access_item_hotbar_position) {
        super(player_ref, store, quick_access_item, quick_access_item_hotbar_position);
    }
}
