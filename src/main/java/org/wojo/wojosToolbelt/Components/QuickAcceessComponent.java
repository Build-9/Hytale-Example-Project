package org.wojo.wojosToolbelt.Components;

import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.flock.FlockMembershipSystems;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.ArrayList;
import java.util.List;

public class QuickAcceessComponent implements Component<EntityStore> {
    public enum QUICK_ACCESS_ITEM_TYPE {
        UNKNOWN,
        CREATIVE_TOOL_SLING
    }

    private QUICK_ACCESS_ITEM_TYPE _quick_access_item_type = QUICK_ACCESS_ITEM_TYPE.UNKNOWN;
    private int _max_num_total_items = 0;
    private List<FlockMembershipSystems.EntityRef> _items_in_quck_access = new ArrayList<FlockMembershipSystems.EntityRef>();

    @NullableDecl
    @Override
    public Component<EntityStore> clone() {
        ToolbeltComponent copy = new ToolbeltComponent();
        copy._quick_access_item_type = this._quick_access_item_type;
        copy._max_num_total_items = this._max_num_total_items;
        copy._items_in_quck_access.addAll(this._items_in_quck_access);
        return copy;
    }

    // ============ Getters and Setters ============

    public QUICK_ACCESS_ITEM_TYPE getSlingType() {
        return this._quick_access_item_type;
    }
    public void setSlingType(QUICK_ACCESS_ITEM_TYPE type){
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
    public List<FlockMembershipSystems.EntityRef> getStoredItemEntityList() {
        return _items_in_quck_access;
    }
    public List<FlockMembershipSystems.EntityRef> getStoredItemEntityListCopy(){
        return new List<FlockMembershipSystems.EntityRef>(this._items_in_quck_access);
    }
}
