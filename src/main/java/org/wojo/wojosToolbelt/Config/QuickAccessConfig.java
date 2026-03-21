package org.wojo.wojosToolbelt.Config;

import com.hypixel.hytale.server.core.inventory.Inventory;
import org.wojo.wojosToolbelt.Components.QuickAccessItemComponent;

import java.util.Set;

public class QuickAccessConfig {
    public static final Set<String> QUICK_ACCESS_ITEM_IDS = Set.of(
        "Quick_Access_Item_Crude_Unrestricted",
        "Quick_Access_Item_Uncommon_Unrestricted",
        "Quick_Access_Item_Rare_Unrestricted",
        "Quick_Access_Item_Epic_Unrestricted",
        "Quick_Access_Item_Legendary_Unrestricted",
        "Quick_Access_Item_Mythic_Unrestricted",
        "Quick_Access_Item_Creative_Unrestricted"
    );
    
    // The max number of items any toolbelt could possibly hold. (Used to define array size in Component)
    // NOTE: UI's are made with pre-defined component counts. Editing these values may require making new UI files.
    public static final int MAX_QA_ITEMS = 8; // Should match item_json container field
    public static int HOTBAR_GUI_BUTTON = 8; // Button 9
    public static int HOTBAR_SWAP_LOCATION = 0; // Button 1
    
    // Possible Item Tiers. Mythic is not craftable.
    public static enum ITEM_TIER {
        UNKNOWN(0),
        COMMON(1),
        UNCOMMON(2),
        RARE(3),
        EPIC(4),
        LEGENDARY(5),
        MYTHIC(6),
        CREATIVE(7),
        NUM_TIERS(8);

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
        UNRESTRICTED(6),
        CUSTOM(7),
        NUM_TYPES(8);

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

    // ============= Quick Access Item Type Arrays =============
    // The following are configs that correspond to the number of items the QA storage can hold bassed on the item tier
    // EX: {0,1,3,10,15,16,17,20} -> Unknown(0), Crude(1), Common(3), Rare(10), Epic(15), Legendary(16), Mythic(17), Creative(20)
    // As stated above, there's only a set number of UI files made so if the number is larger then the largest desiged UI file it will not display
    //    - the additional items. Its best to have the numbers correspond to one of the desiged UI files so there's not invalid buttons that the user
    //    - can see and interact with. 
    
    // Toolbelts can only Items with tool tag
    public static Integer[] TOOLBELT_ARRAY =         {0,2,3,4,5,6,7,8};

    // Builders pouch can hold any building block
    public static Integer[] BUILDERS_POUCH_ARRAY =   {0,2,3,4,5,6,7,8};

    // Slings can only hold weapons
    public static Integer[] WEAPON_SLING_ARRAY =     {0,2,3,4,5,6,7,8};

    // Bandoleers can only hold consumables (Food, Bombs, Potions, but cant hold arrows)
    public static Integer[] BANDOLIER_ARRAY =        {0,2,3,4,5,6,7,8};
    
    // Quivers can only hold arrows
    public static Integer[] QUIVER_ARRAY =           {0,2,3,4,5,6,7,8};

    // Unrestricted array can hold anything
    public static Integer[] UNRESTRICTED_ARRAY =     {0,2,3,4,5,6,7,8};

    // Custom array can be edited to hold a set amount
    public static Integer[] CUSTOM_ARRAY =           {0,2,3,4,5,6,7,8};

    // Get the number of different items the QuickAccess Item can swap between
    public static int getItemCount(QuickAccessItemComponent item) {
        int tier = item.getItemTier();
        int type = item.getItemType();

        // validatate type and tier
        if (tier >= ITEM_TIER.NUM_TIERS.getId() || tier < 0 ||
           type > ITEM_TYPE.NUM_TYPES.getId() || type < 0 ){
            return 0;
        }
        
        Integer[] itemTypeArray = getItemArray(ITEM_TYPE.fromId(type));
        return itemTypeArray[tier];
    }

    public static Integer[] getItemArray(ITEM_TYPE type){
        return switch (type) {
            case TOOLBELT -> QuickAccessConfig.TOOLBELT_ARRAY;
            case BUILDERS_POUCH -> QuickAccessConfig.BUILDERS_POUCH_ARRAY;
            case WEAPON_SLING -> QuickAccessConfig.WEAPON_SLING_ARRAY;
            case BANDOLIER -> QuickAccessConfig.BANDOLIER_ARRAY;
            case QUIVER -> QuickAccessConfig.QUIVER_ARRAY;
            case CUSTOM -> QuickAccessConfig.CUSTOM_ARRAY;
            case UNRESTRICTED -> QuickAccessConfig.UNRESTRICTED_ARRAY;
            default -> new Integer[ITEM_TIER.NUM_TIERS.getId()];
        };
    }

    public static String[] getButtonsDisabledArray(QuickAccessItemComponent item) {
        String [] disabledArray = new String[MAX_QA_ITEMS];
        int currentItem = 0;
        for (int i=0; i<MAX_QA_ITEMS; i++){
            if ( item != null && currentItem < getItemCount(item)) {
                disabledArray[i]="false";
            }else{
                disabledArray[i]="true";
            }
        }
        return disabledArray;
    }
}
