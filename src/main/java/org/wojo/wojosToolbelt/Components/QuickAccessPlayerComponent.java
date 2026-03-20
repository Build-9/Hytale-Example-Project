package wojo.wojosToolbelt.Components;

// Quick access settings that are tied to each player

public class QuickAccessPlayerComponent implements Component<EntityStore> {
    boolean _isEnabled = false;  // Allow hotbar button to opens the quickswap UI
    int _equippedPosition = 8;   // Hotbar location that quick access items need to be placed in / Button used to open swap UI
    int _targetPosition = 0;     // Where items get quickswapped into (-1 means to target players active hotbar slot instead)

    public QuickAccessPlayerComponent() {
    }

    public QuickAccessPlayerComponent(QuickAccessPlayerComponent component){
        this._isEnabled = component._isEnabled;
        this._equippedPosition = component._equippedPosition;
        this._targetPosition = component._targetPosition;
    }

    public QuickAccessComponent(boolean is_enabled, int equipped_position, int target_position) {
        this._isEnabled = is_enabled;
        this._equippedPosition = equipped_position;
        this._targetPosition = target_position;
    }

    public 
    @NullableDecl
    @Override
    public Component<EntityStore> clone() {
        QuickAccessPlayerComponent copy = new QuickAccessPlayerComponent();
        copy._isEnabled = this._isEnabled;
        copy._equippedPosition = this._equippedPosition;
        copy._targetPosition = this._targetPosition;
        return copy;
    }

    public static final BuilderCodec<QuickAccessComponent> CODEC = BuilderCodec
        .builder(QuickAccessComponent.class, QuickAccessComponent::new)
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
        .build();

    // ------------ Getters ------------
    public boolean getIsEnabled() {
        return this._isEnabled;
    }

    public int getEquippedPosition() {
        return this._equippedPosition
    }

    public int getTargetPosition() {
        return this._targetPosition;
    }

    // ------------ Setters ------------
    public void setIsEnabled(boolean is_enabled){
        this._isEnabled = is_enabled;
    }

    public void setEquippedPosition(int equipped_location){
        this._equippedPosition = equipped_position;
    }

    public void getTargetPosition(int target_position){
        this._targetPosition = target_position;
    }

    // ------------ Debug ------------
    public String getPrintableString(){
        String debugResult = String.format(
            "[DEBUG] Quick-Access Player Component Data:\n"+
            "- Is Enabled: %d \n"+
            "- Equipped Pos: %d \n" +
            "- Target Pos: %d \n"
            this.getIsEnabled(),
            this.getEquippedPosition(),
            this.getTargetPosition()
        );

        return debugResult;
    }
    // ================ Component Type info ==================
    private static ComponentType<EntityStore, QuickAccessPlayerComponent> _quick_access_player_component_type;
    public static ComponentType<EntityStore, QuickAccessPlayerComponent> getComponentType(){
        return _quick_access_player_component_type;
    }
    public static void setComponentType(ComponentType<EntityStore, QuickAccessPlayerComponent> type){
        QuickAccessPlayerComponent._quick_access_player_component_type = type;
    }
}