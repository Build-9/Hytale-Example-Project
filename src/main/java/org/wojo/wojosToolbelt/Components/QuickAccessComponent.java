package org.wojo.wojosToolbelt.Components;

import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.flock.FlockMembershipSystems;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.ArrayList;
import java.util.List;

public class QuickAccessComponent implements Component<EntityStore> {
    public enum QUICK_ACCESS_ITEM_TYPE {
        UNKNOWN,
        CREATIVE_TOOL_SLING
    }

    private QUICK_ACCESS_ITEM_TYPE _quick_access_item_type = QUICK_ACCESS_ITEM_TYPE.UNKNOWN;
    private int _max_num_total_items = 0;
    private List<FlockMembershipSystems.EntityRef> _items_in_quick_access = new ArrayList<FlockMembershipSystems.EntityRef>();

    @NullableDecl
    @Override
    public Component<EntityStore> clone() {
        QuickAccessComponent copy = new QuickAccessComponent();
        copy._quick_access_item_type = this._quick_access_item_type;
        copy._max_num_total_items = this._max_num_total_items;
        copy._items_in_quick_access.addAll(this._items_in_quick_access);
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
        return _items_in_quick_access;
    }
    public List<FlockMembershipSystems.EntityRef> getStoredItemEntityListCopy(){
        return new ArrayList<>(this._items_in_quick_access);
    }
    public void addItemToList(FlockMembershipSystems.EntityRef item){
        _items_in_quick_access.add(item);
    }
    public void replaceItemsInList(List<FlockMembershipSystems.EntityRef> list){
        _items_in_quick_access.clear();
        _items_in_quick_access.addAll(list);
    }
    public void clearItemsInList(){
        _items_in_quick_access.clear();
    }

    // Debug Output
    public String getPrintableString(){
        return "Sling Type: " + this.getSlingType() +"\nMax Num Total Items: "+this.getMaxNumTotalSlingItems()+"\n"+this.getStoredItemEntityListCopy().toString();
    }
}
