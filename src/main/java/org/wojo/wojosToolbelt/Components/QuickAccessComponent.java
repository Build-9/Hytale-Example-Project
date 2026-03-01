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
    private int _quick_access_item_tier = 0;        // Tier of Quick Access Item
    private int _quick_access_item_type = 0;        // Type of Quick Access Item this is.
    private final int _quick_access_config_total_items = QuickAccessConfig.MAX_QA_ITEMS;           // Max number of total items this QuickAccess Component can store
    private int _quick_access_max_total_items = 0;  // Custom number of items this comp can store. Must be less than config
    private int _quick_access_gui_button = QuickAccessConfig.HOTBAR_GUI_BUTTON;    // Hotbar position that opens gui
    private int _quick_access_target_location = QuickAccessConfig.HOTBAR_SWAP_LOCATION;  // Hotbar position to swap item into

    // Can't get item ref's of items in containers only item id & position. (UUID == ItemId for the time being)
    // - First design will use Item ID so multiple of the same item will return first instance.
    // - TODO: Use actual UUID for items added so player can select specific item in inventory 
    private String[] _quick_access_item_uuids = new String[QuickAccessConfig.MAX_QA_ITEMS]; // Array of item UUIDs where array position correlates to UI button that pulls it to players hotbar
    private String[] _quick_access_item_names = new String[QuickAccessConfig.MAX_QA_ITEMS];
    private String[] _quick_quick_access_items_disabled = new String[QuickAccessConfig.MAX_QA_ITEMS];
    // ========================= End Component Data =============================

    public QuickAccessComponent(){
    }

    public QuickAccessComponent(int item_tier, int item_type, int max_items, int gui_button, int target_location, String[] item_uuids, String[] item_names, String[] items_disabled){
        this._quick_access_item_tier = item_tier;
        this._quick_access_item_type = item_type;
        this._quick_access_max_total_items = max_items;
        this._quick_access_gui_button = gui_button;
        this._quick_access_target_location = target_location;
        this._quick_access_item_uuids = item_uuids.clone();
        this._quick_access_item_names = item_names.clone();
        this._quick_quick_access_items_disabled = items_disabled.clone();
    }

    public QuickAccessComponent(QuickAccessComponent original){
        this._quick_access_item_tier = original._quick_access_item_tier;
        this._quick_access_item_type = original._quick_access_item_type;
        this._quick_access_max_total_items = original._quick_access_max_total_items;
        this._quick_access_gui_button = original._quick_access_gui_button;
        this._quick_access_target_location = original._quick_access_target_location;
        this._quick_access_item_uuids = original._quick_access_item_uuids.clone();
        this._quick_access_item_names = original._quick_access_item_names.clone();
        this._quick_quick_access_items_disabled = original._quick_quick_access_items_disabled.clone();
    }

    public static final BuilderCodec<QuickAccessComponent> CODEC = BuilderCodec
        .builder(QuickAccessComponent.class, QuickAccessComponent::new)
        .append(
            new KeyedCodec<>("QuickAccessItemTier", Codec.INTEGER),
            (component, value) -> component._quick_access_item_tier = value,
            component -> component._quick_access_item_tier
        ).add()
        .append(
            new KeyedCodec<>("QuickAccessItemType", Codec.INTEGER),
            (component, value) -> component._quick_access_item_type = value,
            component -> component._quick_access_item_type
        ).add()
        .append(
            new KeyedCodec<>("QuickAccessMaxTotalItems", Codec.INTEGER),
            (component, value) -> component._quick_access_max_total_items = value,
            component -> component._quick_access_max_total_items
        ).add()
        .append(
            new KeyedCodec<>("QuickAccessGuiButton", Codec.INTEGER),
            (component, value) -> component._quick_access_gui_button = value,
            component -> component._quick_access_gui_button
        ).add()
        .append(
            new KeyedCodec<>("QuickAccessTargetLocation", Codec.INTEGER),
            (component, value) -> component._quick_access_target_location = value,
            component -> component._quick_access_target_location
        ).add()
        .append(
            new KeyedCodec<>("QuickAccessItemUuids", Codec.STRING_ARRAY),
            (component, value) -> component._quick_access_item_uuids = value,
            component -> component._quick_access_item_uuids
        )
        .add()
        .append(
                new KeyedCodec<>("QuickAccessItemNames", Codec.STRING_ARRAY),
                (component, value) -> component._quick_access_item_names = value,
                component -> component._quick_access_item_names
        )
        .add()
        .append(
                new KeyedCodec<>("QuickAccessItemsDisabled", Codec.STRING_ARRAY),
                (component, value) -> component._quick_quick_access_items_disabled = value,
                component -> component._quick_quick_access_items_disabled
        )
        .add()
        .build();

    @NullableDecl
    @Override
    public Component<EntityStore> clone() {
        QuickAccessComponent copy = new QuickAccessComponent();
        copy._quick_access_item_tier = this._quick_access_item_tier;
        copy._quick_access_item_type = this._quick_access_item_type;
        copy._quick_access_max_total_items = this._quick_access_max_total_items;
        copy._quick_access_gui_button = this._quick_access_gui_button;
        copy._quick_access_target_location = this._quick_access_target_location;
        copy._quick_access_item_uuids = this._quick_access_item_uuids.clone();
        copy._quick_access_item_names = this._quick_access_item_names.clone();
        copy._quick_quick_access_items_disabled = this._quick_quick_access_items_disabled.clone();
        return copy;
    }

    // ============ Getters and Setters ============
    // --- QAItemComp Tier ---
    public int getItemTier() {
        return this._quick_access_item_tier;
    }
    public void setItemTier(int tier) {
        this._quick_access_item_tier = tier;
    }
    
    // --- QAItemComp Type ---
    public int getItemType() {
        return this._quick_access_item_type;
    }
    public void setItemType(int type){
        this._quick_access_item_type = type;
    }

    // --- QAItemComp MaxItems ---
    public int getMaxTotalItems(){
        return this._quick_access_max_total_items;
    }
    public void setMaxTotalItems(int max){
        this._quick_access_max_total_items = max;
    }

    // --- QAItemComp Gui Button ---
    public int getGuiButton(){
        return this._quick_access_gui_button;
    }
    public void setGuiButton(int hotbar_button){
        this._quick_access_gui_button = hotbar_button;
    }

    // --- QAItemComp TargetLocation ---
    
    public int getSwapTargetLocation(){
        return this._quick_access_target_location;
    }
    public void setSwapTargetLocation(int hotbar_button){
        this._quick_access_target_location = hotbar_button;
    }

    // -- Array Accessors ---
    public String[] getItemIdArray(){
        return this._quick_access_item_uuids;
    }
    public void setItemIdArray(String[] item_uuids){
        this._quick_access_item_uuids = item_uuids.clone();
    }
    public void setItemInIdArray(String item_uuid, int position){
        this._quick_access_item_uuids[position] = item_uuid;
    }
    public String getItemInIdArray(int position){
        return this._quick_access_item_uuids[position];
    }
    //
    public String[] getItemNameArray(){
        return this._quick_access_item_names;
    }
    public void setItemNameArray(String[] item_names){
        this._quick_access_item_names = item_names.clone();
    }
    public void setItemInNameArray(String item_name, int position){
        this._quick_access_item_names[position] = item_name;
    }
    public String getItemInNameArray(int position){
        return this._quick_access_item_names[position];
    }
    //
    public String[] getItemsDisabledArray(){
        return this._quick_quick_access_items_disabled;
    }
    public void setItemsDisabledArray(String[] item_bools){
        this._quick_quick_access_items_disabled = item_bools.clone();
    }
    public void setItemInDisabledArray(String item_bools, int position){
        this._quick_quick_access_items_disabled[position] = item_bools;
    }
    public String getItemInDisabledArray(int position){
        return this._quick_quick_access_items_disabled[position];
    }

    // -- Debug Output --
    public String getPrintableString(){
        String debugResult = String.format(
            "\n"+
            "Quick Access Item Type: %d \n"+
            "- Quick Access Tier: %d \n" +
            "- Max Num Total Items: %d \n" +
            "- GUI Button: %d \n"+
            "- Target Location: %d \n",
            this.getItemType(),
            this.getItemTier(),
            this.getMaxTotalItems(),
            this.getGuiButton(),
            this.getSwapTargetLocation()
        );

        debugResult += "- Item Array: "+ Arrays.toString(this._quick_access_item_uuids)+"\n";

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
