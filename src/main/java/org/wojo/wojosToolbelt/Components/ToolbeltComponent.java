package org.wojo.wojosToolbelt.Components;

import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.protocol.EntityUIComponent;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.flock.FlockMembershipSystems;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.ArrayList;
import java.util.List;

public class ToolbeltComponent implements Component<EntityStore> {
    public enum SLING_TYPE {
        BASIC_SLING,
    }

    private SLING_TYPE _sling_type = SLING_TYPE.BASIC_SLING;
    private int _max_num_total_sling_items = 0;
    private List<FlockMembershipSystems.EntityRef> _items_in_sling = new ArrayList<FlockMembershipSystems.EntityRef>();

    @NullableDecl
    @Override
    public Component<EntityStore> clone() {
        ToolbeltComponent copy = new ToolbeltComponent();
        copy._sling_type = this._sling_type;
        copy._max_num_total_sling_items = this._max_num_total_sling_items;
        copy._items_in_sling.addAll(this._items_in_sling);

        return copy;
    }

    public SLING_TYPE getSlingType() {
        return this._sling_type;
    }
    public void setSlingType(SLING_TYPE type){
        this._sling_type = type;
    }

    public int getMaxNumTotalSlingItems(){
        return this._max_num_total_sling_items;
    }
    public void setMaxNumTotalSlingItems(int max){
        this._max_num_total_sling_items = max;
    }

    public List<FlockMembershipSystems.EntityRef> getStoredItemEntities() {
        return _items_in_sling;
    }
}
