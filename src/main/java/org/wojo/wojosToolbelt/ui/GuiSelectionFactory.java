package org.wojo.wojosToolbelt.ui;

import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.wojo.wojosToolbelt.Components.QuickAccessPlayerComponent;
import org.wojo.wojosToolbelt.Config.QuickAccessConfig;
import org.wojo.wojosToolbelt.ui.Radials.RadialGui2;
import org.wojo.wojosToolbelt.ui.Radials.RadialGui3;
import org.wojo.wojosToolbelt.ui.Radials.RadialGui4;
import org.wojo.wojosToolbelt.ui.Radials.RadialGui6;

public class GuiSelectionFactory {

    //public RadialGui2(@NonNullDecl PlayerRef player_ref, Store<EntityStore> store, ItemStack quick_access_item, Integer quick_access_item_hotbar_position)

    public static GenericRadialSelectionUi createGui(@NonNullDecl PlayerRef player_ref, Store<EntityStore> store, ItemStack quick_access_item, Integer quick_access_item_hotbar_position) {
        QuickAccessPlayerComponent quickAccessPlayerComponent = store.getComponent(player_ref.getReference(),QuickAccessPlayerComponent.getComponentType());

        if (quickAccessPlayerComponent != null) {
            String guiFile = quickAccessPlayerComponent.getGuiFile();

            return switch (guiFile) {
                case QuickAccessConfig.SELECTION_GUI_FILE_RADIAL_FOUR ->
                        new RadialGui4(player_ref, store, quick_access_item, quick_access_item_hotbar_position);

                default -> new RadialGui2(player_ref, store, quick_access_item, quick_access_item_hotbar_position);
            };
        }

        // Default is Radial 2 Gui
        return new RadialGui2(player_ref, store, quick_access_item, quick_access_item_hotbar_position);
    }
}
