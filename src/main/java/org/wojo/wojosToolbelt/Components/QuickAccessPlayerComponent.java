package org.wojo.wojosToolbelt.Components;

// Quick access settings that are tied to each player

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.bson.types.Code;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class QuickAccessPlayerComponent implements Component<EntityStore> {
    // Hashmaps used by packet adapter to know to block packet or not
    public static ConcurrentHashMap<UUID, Boolean> quickAccessBtnEnabledMap = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<UUID, Integer> quickAccessHotbarLocationEquipMap = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<Ref<EntityStore>, UUID> quickAccessPlayerUuidMap = new ConcurrentHashMap<>();

    Boolean _isEnabled = false;  // Allow hotbar button to opens the quickswap UI
    Integer _equippedPosition = 8;   // Hotbar location that quick access items need to be placed in / Button used to open swap UI
    Integer _targetPosition = 0;     // Where items get quickswapped into (-1 means to target players active hotbar slot instead)
    String _selectionGui = "Pages/ThreeByThreeQuickAccess.ui"; // Quick Accesss UI file

    public QuickAccessPlayerComponent() {
    }

    public QuickAccessPlayerComponent(QuickAccessPlayerComponent component){
        this._isEnabled = component._isEnabled;
        this._equippedPosition = component._equippedPosition;
        this._targetPosition = component._targetPosition;
        this._selectionGui = component._selectionGui;
    }

    public QuickAccessPlayerComponent(Boolean is_enabled, Integer equipped_position, Integer target_position, String selection_gui) {
        this._isEnabled = is_enabled;
        this._equippedPosition = equipped_position;
        this._targetPosition = target_position;
        this._selectionGui = selection_gui;
    }

    @NullableDecl
    @Override
    public Component<EntityStore> clone() {
        QuickAccessPlayerComponent copy = new QuickAccessPlayerComponent();
        copy._isEnabled = this._isEnabled;
        copy._equippedPosition = this._equippedPosition;
        copy._targetPosition = this._targetPosition;
        copy._selectionGui = this._selectionGui;
        return copy;
    }

    public static final BuilderCodec<QuickAccessPlayerComponent> CODEC = BuilderCodec
        .builder(QuickAccessPlayerComponent.class, QuickAccessPlayerComponent::new)
        .append(
            new KeyedCodec<>("QuickAccessIsEnabled", Codec.BOOLEAN),
            (component, value) -> component._isEnabled = value,
            component -> component._isEnabled
        ).add()
        .append(
            new KeyedCodec<>("QuickAccessEquippedPosition", Codec.INTEGER),
            (component, value) -> component._equippedPosition = value,
            component -> component._equippedPosition
        ).add()
        .append(
            new KeyedCodec<>("QuickAccessTargetPosition", Codec.INTEGER),
            (component, value) -> component._targetPosition = value,
            component -> component._targetPosition
        ).add()
        .append(
                new KeyedCodec<>("QuickAccessSelectionGui", Codec.STRING),
                (component, value) -> component._selectionGui = value,
                component -> component._selectionGui
        ).add()
        .build();

    // ------------ Getters ------------
    public boolean getIsEnabled() {
        return this._isEnabled;
    }

    public int getEquippedPosition() {
        return this._equippedPosition;
    }

    public int getTargetPosition() {
        return this._targetPosition;
    }

    public String getGuiFile() { return this._selectionGui;}

    // ------------ Setters ------------
    public void setIsEnabled(boolean is_enabled){
        this._isEnabled = is_enabled;
    }

    public void setEquippedPosition(int equipped_position){
        this._equippedPosition = equipped_position;
    }

    public void setTargetPosition(int target_position){
        this._targetPosition = target_position;
    }

    public void setGuiFile(String gui_file) { this._selectionGui = gui_file;}

    // ------------ Debug ------------
    public String getPrintableString(){
        String debugResult = String.format(
            "[DEBUG] Quick-Access Player Component Data:\n"+
            "- Is Enabled: %b \n"+
            "- Equipped Pos: %d \n" +
            "- Target Pos: %d \n"+
            "- Gui File: %s",
            this.getIsEnabled(),
            this.getEquippedPosition(),
            this.getTargetPosition(),
            this.getGuiFile()
        );

        return debugResult;
    }
    // ================ Component Type info ==================
    public static final String QUICK_ACCESS_PLAYER_COMPONENT_ID = "WojosQuickAccess_Player_Component_ID";
    private static ComponentType<EntityStore, QuickAccessPlayerComponent> _quick_access_player_component_type;
    public static ComponentType<EntityStore, QuickAccessPlayerComponent> getComponentType(){
        return _quick_access_player_component_type;
    }
    public static void setComponentType(ComponentType<EntityStore, QuickAccessPlayerComponent> type){
        QuickAccessPlayerComponent._quick_access_player_component_type = type;
    }
}