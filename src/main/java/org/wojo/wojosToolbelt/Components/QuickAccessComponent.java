package org.wojo.wojosToolbelt.Components;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.wojo.wojosToolbelt.Config.QuickAccessConfig;

import java.util.Arrays;

public class QuickAccessComponent implements Component<EntityStore> {
    // ==========================================================================
    // --------------------------- Component Data -------------------------------
    private int _item_tier = 0;        // Tier of Quick Access Item (Common, Uncommon, Rare, Epic, etc)
    private int _item_type = 0;        // Type of Quick Access Item this is. (Quiver, Toolbelt, Unrestricted, etc)

    private int _equipped_hotbar_location = 8;      // Hotbar location item needs to be placed in to be 'equipped' (8 == button 9)
    private int _target_hotbar_location = 0;        // Hotbar location items get swapped into (0==1 and -1 == eqipped hotbar)

    private boolean _is_quick_swap_enabled = false; // Can the user press equpped slot to open GUI (true) or do they need to use item interaction
    
    // There are pre defined Quick-Access radial GUI's, make sure using correct gui for comps number of items
    private String _item_selection_ui = "Pages/ThreeByThreeQuickAccess.ui";   // What UI file to use

    // ========================= End Component Data =============================

    public QuickAccessComponent(){
    }

    public QuickAccessComponent(int item_tier, int item_type, int equipped_location, int target_location, boolean is_swap_enabled, String item_selection_file){
        this._item_tier = item_tier;
        this._item_type = item_type;
        this._equipped_hotbar_location = equipped_location;
        this._target_hotbar_location = target_location;
        this._is_quick_swap_enabled = is_swap_enabled;
        this._item_selection_ui = item_selection_file;
    }

    public QuickAccessComponent(QuickAccessComponent original){
        this._item_tier = original._item_tier;
        this._item_type = original._item_type;
        this._equipped_hotbar_location = original._equipped_hotbar_location;
        this._target_hotbar_location = original._target_hotbar_location;
        this._is_quick_swap_enabled = original._is_quick_swap_enabled;
        this._item_selection_ui = original._item_selection_ui;
    }

    @NullableDecl
    @Override
    public Component<EntityStore> clone() {
        QuickAccessComponent copy = new QuickAccessComponent();
        copy._item_tier = this._item_tier;
        copy._item_type = this._item_type;
        copy._equipped_hotbar_location = this._equipped_hotbar_location;
        copy._target_hotbar_location = this._target_hotbar_location;
        copy._is_quick_swap_enabled = this._is_quick_swap_enabled;
        copy._item_selection_ui = this._item_selection_ui;
        return copy;
    }

    public static final BuilderCodec<QuickAccessComponent> CODEC = BuilderCodec
        .builder(QuickAccessComponent.class, QuickAccessComponent::new)
        .append(
            new KeyedCodec<>("QuickAccessItemTier", Codec.INTEGER),
            (component, value) -> component._item_tier = value,
            component -> component._item_tier
        ).add()
        .append(
            new KeyedCodec<>("QuickAccessItemType", Codec.INTEGER),
            (component, value) -> component._item_type = value,
            component -> component._item_type
        ).add()
        .append(
            new KeyedCodec<>("QuickAccessEquippedHotbarLocation", Codec.INTEGER),
            (component, value) -> component._equipped_hotbar_location = value,
            component -> component._equipped_hotbar_location
        ).add()
        .append(
            new KeyedCodec<>("QuickAccessTargetHotbarLocation", Codec.INTEGER),
            (component, value) -> component._target_hotbar_location = value,
            component -> component._target_hotbar_location
        ).add()
        .append(
            new KeyedCodec<>("QuickAccessItemIsQuickSwapEnabled", Codec.BOOLEAN),
            (component, value) -> component._is_quick_swap_enabled = value,
            component -> component._is_quick_swap_enabled
        ).add()
        .append(
            new KeyedCodec<>("ItemSelectionUi", Codec.STRING),
            (component, value) -> component._item_selection_ui = value,
            component -> component._item_selection_ui
        ).add()
        .build();


    // ============ Getters and Setters ============
    // --- QAItemComp Tier ---
    public int getItemTier() {
        return this._item_tier;
    }
    public void setItemTier(int tier) {
        this._item_tier = tier;
    }
    
    // --- QAItemComp Type ---
    public int getItemType() {
        return this._item_type;
    }
    public void setItemType(int type){
        this._item_type = type;
    }

    // --- Equipped Hotbar Loc ---
    public int getEquippedLocation(){
        return this._equipped_hotbar_location;
    }
    public void setEquippedLocation(int loc){
        this._equipped_hotbar_location = loc;
    }

    // --- Target Hotbar Loc ---
    public int getTargetLocation(){
        return this._target_hotbar_location;
    }
    public void setTargetLocation(int loc){
        this._target_hotbar_location = loc;
    }

    // --- is QuickSwap Enabled ---
    public boolean getIsQuickSwapEnabled(){
        return this._is_quick_swap_enabled;
    }
    public void setItQuickSwapEnabled(boolean is_enabled){
        this._is_quick_swap_enabled = is_enabled;
    }

    // --- item Selection Ui
    public String getItemSelectionUi(){
        return this._item_selection_ui;
    }
    public void setItemSelectionUi(String ui_path){
        this._item_selection_ui = ui_path;
    }

    // -- Debug Output --
    public String getPrintableString(){
        String debugResult = String.format(
            "[DEBUG] Quick Access Component Data:\n"+
            "- Quick Access Item Type: %d \n"+
            "- Quick Access Tier: %d \n" +
            "- Equipped Hotbar Loc: %d \n" +
            "- Target Hotbar Loc: %d \n"+
            "- Is Quick Swap Enabled: %d \n",
            "- Item Selection UI: %s \n"
            this.getItemType(),
            this.getItemTier(),
            this.getEquippedLocation(),
            this.getTargetLocation(),
            this.getIsQuickSwapEnabled(),
            this.getItemSelectionUi()
        );

        return debugResult;
    }

    // ================ Component Type info ==================
    private static ComponentType<EntityStore, QuickAccessComponent> _quick_access_component_type;
    public static ComponentType<EntityStore, QuickAccessComponent> getComponentType(){
        return _quick_access_component_type;
    }
    public static void setComponentType(ComponentType<EntityStore, QuickAccessComponent> type){
        QuickAccessComponent._quick_access_component_type = type;
    }
}
