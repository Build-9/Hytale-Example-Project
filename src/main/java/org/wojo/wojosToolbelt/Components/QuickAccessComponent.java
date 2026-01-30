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

    // Int to string conversion
    public static final String[] QUICK_ACCESS_ITEM_TYPES = {
        "UNKNOWN",
        "CRUDE_TOOLBELT"
    };

    // ==========================================================================
    // --------------------------- Component Data -------------------------------
    private String _quick_access_item_type = QUICK_ACCESS_ITEM_TYPES[0];
    private int _max_num_total_items = 0;

    // Can't get item ref's of things in containers. Need a different way to get the info
    private String[] _item_uuids = new String[20]; // Used to save data between login/logout as using item refrence may not work. //TODO: Get max possible from config

    // ========================= End Component Data =============================

    public QuickAccessComponent(){
    }

    public QuickAccessComponent(String item_type, int max_items, String[] item_uuids){
        this._quick_access_item_type = item_type;
        this._max_num_total_items = max_items;
        this._item_uuids = item_uuids.clone();
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

    public String getSlingType() {
        return this._quick_access_item_type;
    }
    public void setSlingType(String type){
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
