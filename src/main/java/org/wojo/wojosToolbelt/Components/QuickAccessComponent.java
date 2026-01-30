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

import java.util.ArrayList;
import java.util.List;

public class QuickAccessComponent implements Component<EntityStore> {

    // =========================================================================
    // --------------------------- Config Values -------------------------------
    // Way to use int as enums so builder codec can work.
    public class QUICK_ACCESS_ITEM_TYPES {
        static final int UNKNOWN = 0;
        static final int CRUDE_TOOLBELT = 1;
        static final int CREATIVE_TOOLBELT = 3;

        // Int to string conversion
        public static final String[] QUICK_ACCESS_ITEM_TYPE_STRING = {
            "UNKNOWN",
            "CRUDE_TOOLBELT",
            "CREATIVE_TOOLBELT"
        };
        
        private QUICK_ACCESS_ITEM_TYPES() {}; // Private constructor
    }

    // Max number of items the best Utility Item can hold TODO: Move this to config
    private final int MAX_ITEMS_EVER = 20;             

    // ==========================================================================
    // --------------------------- Component Data -------------------------------
    private int _quick_access_item_type = 0;
    private int _max_num_total_items = 0;

    // Can't get item ref's of items in containers only item id & position. (UUID == ItemId for the time being)
    // - The following should be its own data structure but can't store/load custom structures in BuilderCodec (As far as I can tell)
    // - Position in array corresponds to what button is used to grab the item
    private String[] _item_uuids = new String[20];     // TODO: Get max possible from config
    private Integer[] _inv_position = new Integer[20]; // Position in inv
    private Integer[] _inv_type = new Integer[20];     // Inventory location (Backpack, Utility, Hotbar, Etc) 
    private String _equiped_item_uuid = "";            // Item Currently Equpped, Will get swapped into uuid array with whatever value is pressed.

    // ========================= End Component Data =============================

    public QuickAccessComponent(){
    }

    public QuickAccessComponent(int item_type, int max_items, String[] item_uuids, Integer[] inv_position, Integer[] inv_type){
        this._quick_access_item_type = item_type;
        this._max_num_total_items = max_items;
        this._item_uuids = item_uuids.clone();
        this._inv_position = inv_position.clone();
        this._inv_type - inv_type.clone();
    }

    public static final BuilderCodec<QuickAccessComponent> CODEC = BuilderCodec
        .builder(QuickAccessComponent.class, QuickAccessComponent::new)
        .append(
            new KeyedCodec<>("_quick_access_item_type", Codec.STRING),
            (component, value) -> component._quick_access_item_type = value,
            component -> component._quick_access_item_type
        ).add()
        .append(
            new KeyedCodec<>("_max_num_total_items", Codec.INTEGER),
            (component, value) -> component._max_num_total_items = value,
            component -> component._max_num_total_items
        ).add()
        .append(
            new KeyedCodec<>("_item_uuids", Codec.STRING_ARRAY),
            (component, value) -> component._item_uuids = value,
            component -> component._item_uuids
        )
        .add()
        .build();

    @NullableDecl
    @Override
    public Component<EntityStore> clone() {
        QuickAccessComponent copy = new QuickAccessComponent();
        copy._quick_access_item_type = this._quick_access_item_type;
        copy._max_num_total_items = this._max_num_total_items;
        copy._item_uuids = this._item_uuids.clone();
        return copy;
    }

    // ============ Getters and Setters ============
    // --- QAItemComp Type ---
    public int getItemType() {
        return this._quick_access_item_type;
    }
    public void setItemType(int type){
        this._quick_access_item_type = type;
    }

    // --- Config Accessors ---
    public int getMaxNumTotalSlingItems(){
        return this._max_num_total_items;
    }
    public void setMaxNumTotalSlingItems(int max){
        this._max_num_total_items = max;
    }

    // -- Array Accessors ---
    // TODO: need to redo these

    // -- Debug Output --
    public String getPrintableString(){
        return "Sling Type: " + this.getSlingType() +"\nMax Num Total Items: "+this.getMaxNumTotalSlingItems();
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
