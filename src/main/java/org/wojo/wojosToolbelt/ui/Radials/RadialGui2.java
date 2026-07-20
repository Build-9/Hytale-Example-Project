package org.wojo.wojosToolbelt.ui.Radials;

import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.wojo.wojosToolbelt.Config.QuickAccessConfig;
import org.wojo.wojosToolbelt.ui.GenericRadialSelectionUi;
import org.wojo.wojosToolbelt.ui.GuiButtonData;

public class RadialGui2 extends GenericRadialSelectionUi {

    @Override
    protected void constructFileSpecificData() {
        this._NUM_QA_BUTTONS = 2;
        this._guiFile = QuickAccessConfig.SELECTION_GUI_FILE_RADIAL_TWO;
        this._defaultBgFile = "../Radials/Images/Two/QuickAccessRadialMenuDrawing_Two.png";
        this._currentBgFile = "../Radials/Images/Two/QuickAccessRadialMenuDrawing_Two.png";

        for (int i=0; i < this._NUM_QA_BUTTONS; i++) {
            Message msg = null;
            String btnText = "";
            String tooltipText = "";
            String buttonItemIdForIcon = "";
            String isDisabled = "true";
            String style = "";
            String htmlBtnId = "#QuickAccessButton"+String.valueOf(i);
            String htmlIconId = "#QuickAccessButton"+String.valueOf(i)+"Img";
            String hoverImg = "QuickAccessRadialMenuDrawing_Two_"+String.valueOf(i)+".png";
            String pressImg = "";

            GuiButtonData btnData = new GuiButtonData(
                msg, btnText, tooltipText, buttonItemIdForIcon, isDisabled,
                style, htmlBtnId, htmlIconId, hoverImg, pressImg
            );

            this._quickAccessButtons.add(btnData);
        }

        this._equipedItemButton.buttonHighlightImage = "../Radials/Images/Two/QuickAccessRadialMenuDrawing_Two_Equipped.png";
    }

    public RadialGui2(@NonNullDecl PlayerRef player_ref, Store<EntityStore> store, ItemStack quick_access_item, Integer quick_access_item_hotbar_position) {
        super(player_ref, store, quick_access_item, quick_access_item_hotbar_position);
    }
}
