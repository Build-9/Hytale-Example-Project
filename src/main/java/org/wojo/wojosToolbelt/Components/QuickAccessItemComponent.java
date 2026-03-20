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

public class QuickAccessItemComponent implements Component<EntityStore> {
    // ============================= json data =============================
    // (data stored in items json)
    private int _itemTier = 0;        // Tier of Quick Access Item (Common, Uncommon, Rare, Epic, etc)
    private int _containerSize = 10;  // Size of the container field in the item

    // ============================= config data =============================
    //  (data stored in QuickAccessConfig bassed on item ID [Item Type & Tier])
    private int _itemType = 0;          // Type of Quick Access Item this is. (Quiver, Toolbelt, Unrestricted, etc)
    private int _quickAccessSize = 2;   // Current number of enabled buttons the item has

    private String _selectionGui = "Pages/ThreeByThreeQuickAccess.ui"

    public QuickAccessItemComponent(){
    }

    public QuickAccessItemComponent(int item_tier, int container_size, int item_type, int quick_access_size, String selection_gui){
        this._itemTier;
        this._containerSize;
        this._itemType;
        this._quickAccessSize;
        this._selectionGui;
    }

    public QuickAccessItemComponent(QuickAccessComponent original){
        this._itemTier = original._itemTier;
        this._containerSize = original._containerSize;
        this._itemType = original._itemType;
        this._quickAccessSize = original._quickAccessSize;
        this._selectionGui = original._selectionGui;
    }

    @NullableDecl
    @Override
    public Component<EntityStore> clone() {
        QuickAccessItemComponent copy = new QuickAccessItemComponent();
        copy._itemTier = this._itemTier;
        copy._containerSize = this._containerSize;
        copy._itemType = this._itemType;
        copy._quickAccessSize = this._quickAccessSize;
        copy._selectionGui = this._selectionGui;
        return copy;
    }

    public static final BuilderCodec<QuickAccessItemComponent> CODEC = BuilderCodec
        .builder(QuickAccessItemComponent.class, QuickAccessItemComponent::new)
        .append(
            new KeyedCodec<>("QuickAccessItemTier", Codec.INTEGER),
            (component, value) -> component._itemTier = value,
            component -> component._itemTier
        ).add()
        .append(
            new KeyedCodec<>("QuickAccessContainerSize", Codec.INTEGER),
            (component, value) -> component._containerSize = value,
            component -> component._containerSize
        ).add()
        .append(
            new KeyedCodec<>("QuickAccessItemType", Codec.INTEGER),
            (component, value) -> component._itemType = value,
            component -> component._itemType
        ).add()
        .append(
            new KeyedCodec<>("QuickAccessSize", Codec.INTEGER),
            (component, value) -> component._quickAccessSize = value,
            component -> component._quickAccessSize
        ).add()
        .append(
            new KeyedCodec<>("QuickAccessSelectionUi", Codec.STRING),
            (component, value) -> component._selectionGui = value,
            component -> component._selectionGui
        ).add()
        .build();


    // ============ Getters and Setters ============
    // --- QAItemComp Tier ---
    public int getItemTier() {
        return this._itemTier;
    }
    public void setItemTier(int tier) {
        this._itemTier = tier;
    }
    
    // --- QAItemComp Type ---
    public int getItemType() {
        return this._itemType;
    }
    public void setItemType(int type){
        this._itemType = type;
    }

    // --- Container size ---
    public int getContainerSize() {
        return this._containerSize;
    }
    public void setContainerSize(int size){
        this._containerSize = size;
    }

    // --- quick access size ---
    public int getQuickAccessSize() {
        return this._quickAccessSize;
    }
    public void setQuickAccessSize(int size){
        this._quickAccessSize = size;
    }

    // --- item Selection Ui
    public String getItemSelectionUi(){
        return this._selectionGui;
    }
    public void setItemSelectionUi(String ui_path){
        this._selectionGui = ui_path;
    }

    // -- Debug Output --
    public String getPrintableString(){
        String debugResult = String.format(
            "[DEBUG] Quick Access Component Data:\n"+
            "- Quick Access Item Type: %d \n"+
            "- Quick Access Tier: %d \n" +
            "- Container Size: %d \n" +
            "- Quick Access Size: %d \n"+
            "- Item Selection UI: %s \n"
            this.getItemType(),
            this.getItemTier(),
            this.getContainerSize(),
            this.getQuickAccessSize(),
            this.getItemSelectionUi()
        );

        return debugResult;
    }

    // ================ Component Type info ==================
    private static ComponentType<EntityStore, QuickAccessItemComponent> _quick_access_item_component_type;
    public static ComponentType<EntityStore, QuickAccessItemComponent> getComponentType(){
        return _quick_access_item_component_type;
    }
    public static void setComponentType(ComponentType<EntityStore, QuickAccessItemComponent> type){
        QuickAccessItemComponent._quick_access_item_component_type = type;
    }
}
