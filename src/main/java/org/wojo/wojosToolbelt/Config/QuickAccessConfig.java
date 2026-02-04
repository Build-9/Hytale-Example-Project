package org.wojo.wojosToolbelt.Config;

import com.hypixel.hytale.server.core.inventory.Inventory;

public class QuickAccessConfig {
    // The max number of items any toolbelt could possibly hold. (Used to define array size in Component)
    // NOTE: UI's are made with pre-defined component counts. Editing these values may require making new UI files. 
    public static final int MAX_QA_ITEMS = 10;
    public static final int HOTBAR_GUI_BUTTON = 8; // Button 9
    public static final int HOTBAR_SWAP_LOCATION = 0; // Button 1

    // TODO: change item to be its own inventory rather then searching players inventory for item?
    public static final int MAX_SEARCHABLE_CONTAINERS = 50; // Max number of different inventory types on a player that an item search could look for swap item.
    // TODO: Set to -1 to swap to currently equipped location??
    
    // Possible Item Tiers. Mythic is not craftable.
    public static enum ITEM_TIER {
        UNKNOWN(0),
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
            return UNKNOWN;
        }
    }

    public static enum ITEM_TYPE {
        UNKNOWN(0), 
        TOOLBELT(1),
        BUILDERS_POUCH(2),
        WEAPON_SLING(3),
        BANDOLIER(4),
        QUIVER(5),
        CREATIVE(6),
        TOOLBELT_PREMADE(7),
        WEAPONSLING_PREMADE;

        private final int id;
        ITEM_TYPE(int id) {this.id = id;}
        public int getId() {return id;}
        public static ITEM_TYPE fromId(int id) {
            for (ITEM_TYPE t : values()) {
                if (t.id == id) {return t;}
            }
            return UNKNOWN;
        }
    }

    // What inventories can be searched for item to swap into hotbar. (Default only checks player inventory | Hotbar, Backpack & Utility are also possible additional options)
    public static final Integer[] SEARCHABLE_CONTAINERS = {Inventory.STORAGE_SECTION_ID};

    // GUI's are premade instead of dynamic. The currently defined GUI's are as follows. 
    public static final Integer[] STORAGE_ITEM_GUIS =      {2};

    // ============= Quick Access Item Type Arrays =============
    // The following are configs that correspond to the number of items the QA storage can hold bassed on the item tier
    // EX: {0,1,3,10,15,16,17,20} -> Unknown(0), Crude(1), Common(3), Rare(10), Epic(15), Legendary(16), Mythic(17), Creative(20)
    // As stated above, there's only a set number of UI files made so if the number is larger then the largest desiged UI file it will not display
    //    - the additional items. Its best to have the numbers correspond to one of the desiged UI files so there's not invalid buttons that the user
    //    - can see and interact with. 
    
    // Toolbelts can only Items with tool tag
    public static final Integer[] TOOLBELT_ARRAY =         {2,2,2,2,2,2,2,2};
    // Premade toolbelt possible items (6 Options)
    public static final String[] TOOLBELT_PREMADE_AXE_OPTIONS = {"Weapon_Axe_Crude","Weapon_Axe_Common"};
    public static final String[] TOOLBELT_PREMADE_PICKAXE_OPTIONS = {"Weapon_Pickaxe_Crude","Weapon_Pickaxe_Common"};
    public static final String[] TOOLBELT_PREMADE_SHOVEL_OPTIONS = {};
    public static final String[] TOOLBELT_PREMADE_HAMMER_OPTIONS = {};
    public static final String[] TOOLBELT_PREMADE_HOE_OPTIONS = {};
    public static final String[] TOOLBELT_PREMADE_LIGHTING_OPTIONS = {};

    // Builders pouch can hold any building block
    public static final Integer[] BUILDERS_POUCH_ARRAY =   {2,2,2,2,2,2,2,2};

    // Slings can only hold weapons
    public static final Integer[] WEAPON_SLING_ARRAY =     {2,2,2,2,2,2,2,2};

    // Bandoleers can only hold consumables (Food, Bombs, Potions, but cant hold arrows)
    public static final Integer[] BANDOLIER_ARRAY =        {2,2,2,2,2,2,2,2};
    
    // Quivers can only hold arrows
    public static final Integer[] QUIVER_ARRAY =           {2,2,2,2,2,2,2,2};

    // Creative can hold anything
    public static final Integer[] CREATIVE_ARRAY =         {2,2,2,2,2,2,2,2};

    // TODO: Add map to allow server owners to whitelist or blacklist specific Item ID's as specific types. 
    //    This allows an easy way to add modded items to an item type and or allow specific items to be used in multiple quick access items. 
    // ITEM_ACCESS_MAP<String, boolean[ITEM_TYPE.length()]> = new ConcurrentHashMap(String, new Array());
}
