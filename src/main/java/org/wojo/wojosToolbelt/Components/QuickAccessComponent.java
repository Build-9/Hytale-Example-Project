package org.wojo.wojosToolbelt.Components;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.flock.FlockMembershipSystems;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.wojo.wojosToolbelt.Config.QuickAccessConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuickAccessComponent implements Component<EntityStore> {
    // ==========================================================================
    // --------------------------- Component Data -------------------------------
    private int _quick_access_item_tier = 0;        // Tier of Quick Access Item
    private int _quick_access_item_type = 0;        // Type of Quick Access Item this is.
    private final int _quick_access_config_total_items = QuickAccessConfig.MAX_QA_ITEMS;           // Max number of total items this QuickAccess Component can store
    private int _quick_access_max_total_items = 0;  // Custom number of items this comp can store. Must be less then config
    private int _quick_access_gui_button = QuickAccessConfig.MAX_QA_ITEMS;    // Hotbar position that opens gui
    private int _quick_access_target_location = QuickAccessConfig.HOTBAR_SWAP_LOCATION;  // Hotbar position to swap item into

    // Can't get item ref's of items in containers only item id & position. (UUID == ItemId for the time being)
    // - First design will use Item ID so multiple of the same item will return first instance.
    // - TODO: Use actual UUID for items added so player can select specific item in inventory 
    private String[] _quick_access_item_uuids = new String[QuickAccessConfig.MAX_QA_ITEMS]; // Array of item UUIDs where array position correlates to UI button that pulls it to players hotbar
    private int[] _quick_access_searchable_item_containers = new int[QuickAccessConfig.MAX_SEARCHABLE_CONTAINERS]; // Array of inventory types to look for the items that can be swapped into the hotbar.
    // ========================= End Component Data =============================

    public QuickAccessComponent(){
    }

    public QuickAccessComponent(int item_tier, int item_type, int max_items, int gui_button, int target_location, String[] item_uuids, int[] searchable_contianers){
        this._quick_access_item_tier = item_tier;
        this._quick_access_item_type = item_type;
        this._quick_access_max_total_items = max_items;
        this._quick_access_gui_button = gui_button;
        this._quick_access_target_location = target_location;
        this._quick_access_item_uuids = item_uuids.clone();
        this._quick_access_searchable_item_containers = searchable_contianers.clone();
    }

    public QuickAccessComponent(QuickAccessComponent original){
        this._quick_access_item_tier = original._quick_access_item_tier;
        this._quick_access_item_type = original._quick_access_item_type;
        this._quick_access_max_total_items = original._quick_access_max_total_items;
        this._quick_access_gui_button = original._quick_access_gui_button;
        this._quick_access_target_location = original._quick_access_target_location;
        this._quick_access_item_uuids = original._quick_access_item_uuids.clone();
        this._quick_access_searchable_item_containers = original._quick_access_searchable_item_containers.clone();
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
            new KeyedCodec<>("QuickAccessSearchableItemContainers", Codec.INT_ARRAY),
            (component, value) -> component._quick_access_searchable_item_containers = value,
            component -> component._quick_access_searchable_item_containers
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
        copy._quick_access_searchable_item_containers = this._quick_access_searchable_item_containers.clone();
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
    public void setItemInArray(String item_uuid, int position){
        this._quick_access_item_uuids[position] = item_uuid;
    }
    public String getItemInArray(int position){
        return this._quick_access_item_uuids[position];
    }

    // -- Debug Output --
    public String getPrintableString(){
        String debugResult = String.format(
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

        debugResult += "- Item Array: "+ Arrays.toString(this._quick_access_item_uuids)+"\n - Searchable Inventories: "+Arrays.toString(this._quick_access_searchable_item_containers)+"\n";

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
