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
        UNKONWN(0),
        CRUDE(1),
        COMMON(2),
        RARE(3),
        EPIC(4),
        LEGENDARY(5),
        MYTHIC(6),
        CREATIVE(7);

        private final int id;
        ITEM_TIER(int id) {this.id = id;}
        public int getId() {return id;}
        public static ITEM_TIER fromId(int id) {
            for (ITEM_TIER t : values()) {
                if (t.id == id) {return t;}
            }
        }
    }

    public static final enum ITEM_TYPE {
        UNKNOWN(0), 
        TOOLBELT(1),
        BUILDERS_POUCH(2),
        WEAPON_SLING(3),
        BANDOLIER(4),
        QUIVER(5),
        CREATIVE(6);

        private final int id;
        ITEM_TYPE(int id) {this.id = id;}
        public int getId() {return id;}
        public static ITEM_TIER fromId(int id) {
            for (ITEM_TYPE t : values()) {
                if (t.id == id) {return t;}
            }
        }
    }
    
    
    // Toolbelts can only Items with tool tag
    static final Integer[] TOOLBELT_ARRAY =         {0,3,3,3,3,3,3,3};

    // Builders pouch can hold any building block
    static final Integer[] BUILDERS_POUCH_ARRAY =   {0,3,3,3,3,3,3,3};

    // Slings can only hold weapons
    static final Integer[] WEAPON_SLING_ARRAY =     {0,3,3,3,3,3,3,3};

    // Bandoleers can only hold consumables (Food, Bombs, Potions, but cant hold arrows)
    static final Integer[] BANDOLIER_ARRAY =        {0,3,3,3,3,3,3,3};
    
    // Quivers can only hold arrows
    static final Integer[] QUIVER_ARRAY =           {0,3,3,3,3,3,3,3};

    // Creative can hold anything
    static final Integer[] CREATIVE_ARRAY =         {0,3,3,3,3,3,3,3};

    // TODO: Add map to allow server owners to whitelist or blacklist specific Item ID's as specific types. 
    //    This allows easy way to add Modded items as a type and allow items to be used in multiple quick access items. 
    // ITEM_ACCESS_MAP<String, boolean[ITEM_TYPE.length()]> = new ConcurrentHashMap(String, new Array());
}
