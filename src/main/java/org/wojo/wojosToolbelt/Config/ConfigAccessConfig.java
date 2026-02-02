package org.wojo.wojosToolbelt.Config;

public class QuickAccessConfig {
    // The max number of items any toolbelt could possibly hold. (Used to define array size in Component)
    // NOTE: UI's are made with pre-defined component counts. Editing these values may require making new UI files. 
    static final int MAX_QA_ITEMS = 3;
    static final int HOTBAR_GUI_BUTTON = 8; // Button 9
    static final int HOTBAR_SWAP_LOCATION = 0; // Button 1 
    // TODO: Set to -1 to swap to currently equipped location??
    
    // Possible Item Tiers. Mythic is not craftable.
    public static final enum ITEM_TIER {
        CRUDE,
        COMMON,
        RARE,
        EPIC,
        LEGENDARY,
        MYTHIC
    }

    public static final enum ITEM_TYPE {
        TOOLBELT,
        BUILDERS_POUCH,
        WEAPON_SLING,
        BANDOLIER,
        QUIVER,
        CREATIVE
    }
    
    
    // Toolbelts can only Items with tool tag
    static final Integer[] TOOLBELT_ARRAY = {3,3,3,3,3,3};

    // Builders pouch can hold any building block
    static final Integer[] BUILDERS_POUCH_ARRAY = {3,3,3,3,3,3};

    // Slings can only hold weapons
    static final Integer[] WEAPON_SLING_ARRAY = {3,3,3,3,3,3};

    // Bandoleers can only hold consumables (Food, Bombs, Potions, but cant hold arrows)
    static final Integer[] BANDOLIER_ARRAY = {3,3,3,3,3,3};
    
    // Quivers can only hold arrows
    static final Integer[] QUIVER_ARRAY = {3,3,3,3,3,3};

    // Creative can hold anything
    static final Integer[] CREATIVE_ARRAY = {3,3,3,3,3,3}    
}
