package org.wojo.wojosToolbelt.Config;

import com.hypixel.hytale.server.core.inventory.ItemStack;
import org.wojo.wojosToolbelt.Components.QuickAccessItemComponent;
import org.wojo.wojosToolbelt.Components.QuickAccessItemComponentFactory;
import org.wojo.wojosToolbelt.WojosQuickAccessPlugin;

import java.util.Set;

public class QuickAccessConfig {
    // Default Hytale Qualities:
    // - [Common, Uncommon, Rare, Epic, Legendary, Debug]
    public static final Set<String> QUICK_ACCESS_ITEM_IDS = Set.of(
        "Quick_Access_Item_Unrestricted_Common",
        "Quick_Access_Item_Unrestricted_Uncommon",
        "Quick_Access_Item_Unrestricted_Rare",
        "Quick_Access_Item_Unrestricted_Epic",
        "Quick_Access_Item_Unrestricted_Legendary",
        "Quick_Access_Item_Unrestricted_Debug"
    );
    
    
    public static final int HOTBAR_EQIPPED_LOCATION_DEFAULT = 8;    // Button 9 - Hotbar location to eqip and use the qa-item
    public static final int HOTBAR_TARGET_LOCATION_DEFAULT = 0;     // Button 1 - Hotbar location to send stored qa-item to

    public static final String SELECTION_GUI_FILE_GRID_THREE_BY_THREE = "Pages/WojosQuickAccess/Grids/ThreeByThreeQuickAccess.ui";
    public static final String SELECTION_GUI_FILE_GRID_FIVE_BY_FIVE = "Pages/WojosQuickAccess/Grids/FiveByFiveQuickAccess.ui";
    public static final String SELECTION_GUI_FILE_GRID_SEVEN_BY_SEVEN = "Pages/WojosQuickAccess/Grids/SevenBySevenQuickAccess.ui";
    public static final String[] SELECTION_GUI_FILES_GRID = {
        SELECTION_GUI_FILE_GRID_THREE_BY_THREE, 
        SELECTION_GUI_FILE_GRID_FIVE_BY_FIVE,
        SELECTION_GUI_FILE_GRID_SEVEN_BY_SEVEN
    };

    // Radial selection files, names for number of selectable options
    public static final String SELECTION_GUI_FILE_RADIAL_TWO = "Pages/WojosQuickAccess/Radials/RadialTwo.ui";
    public static final String SELECTION_GUI_FILE_RADIAL_THREE = "Pages/WojosQuickAccess/Radials/RadialThree.ui";
    public static final String SELECTION_GUI_FILE_RADIAL_FOUR = "Pages/WojosQuickAccess/Radials/RadialFour.ui";
    public static final String SELECTION_GUI_FILE_RADIAL_SIX = "Pages/WojosQuickAccess/Radials/RadialSix.ui";
    public static final String SELECTION_GUI_FILE_RADIAL_EIGHT = "Pages/WojosQuickAccess/Radials/RadialEight.ui";
    public static final String SELECTION_GUI_FILE_RADIAL_NINE = "Pages/WojosQuickAccess/Radials/RadialNine.ui";
    public static final String SELECTION_GUI_FILE_RADIAL_TWELVE = "Pages/WojosQuickAccess/Radials/RadialTwelve.ui";
    public static final String[] SELECTION_GUI_FILES_RADIAL = {
        SELECTION_GUI_FILE_RADIAL_TWO,
        SELECTION_GUI_FILE_RADIAL_THREE,
        SELECTION_GUI_FILE_RADIAL_FOUR,
        SELECTION_GUI_FILE_RADIAL_SIX,
        SELECTION_GUI_FILE_RADIAL_EIGHT,
        SELECTION_GUI_FILE_RADIAL_NINE,
        SELECTION_GUI_FILE_RADIAL_TWELVE
    };

    public static final String[] ALL_SELECTION_GUI_FILES = {
        SELECTION_GUI_FILE_GRID_THREE_BY_THREE, 
        SELECTION_GUI_FILE_GRID_FIVE_BY_FIVE,
        SELECTION_GUI_FILE_GRID_SEVEN_BY_SEVEN,
        SELECTION_GUI_FILE_RADIAL_TWO,
        SELECTION_GUI_FILE_RADIAL_THREE,
        SELECTION_GUI_FILE_RADIAL_FOUR,
        SELECTION_GUI_FILE_RADIAL_SIX,
        SELECTION_GUI_FILE_RADIAL_EIGHT,
        SELECTION_GUI_FILE_RADIAL_NINE,
        SELECTION_GUI_FILE_RADIAL_TWELVE
    };

    public static final String DEFAULT_SELECTION_GUI_FILE = SELECTION_GUI_FILE_RADIAL_THREE;
    public static final String SETTINGS_GUI_FILE = "Pages/WojosQuickAccess/QuickAccessSettings.ui";
    public static final String HELP_GUI_FILE = "Pages/WojosQuickAccess/QuickAccessHelp.ui";

    // Possible Item Tiers. Mythic is not craftable.
    public static enum ITEM_TIER {
        UNKNOWN(0),
        COMMON(1),
        UNCOMMON(2),
        RARE(3),
        EPIC(4),
        LEGENDARY(5),
        DEBUG(6),
        NUM_TIERS(7);

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
        NUM_TYPES(7);

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
    // EX: {0,4,8,12,16,20,24} -> Unknown(0), Common(4), Uncommon(8), Rare(12), Epic(16), Legendary(20), Debug(24)
    // As stated above, there's only a set number of UI files made so if the number is larger then the largest desiged UI file it will not display
    //    - the additional items. Its best to have the numbers correspond to one of the desiged UI files so there's not invalid buttons that the user
    //    - can see and interact with. 
    
    // Toolbelts can only Items with tool tag
    public static Integer[] TOOLBELT_ARRAY =         {0,2,3,4,5,6,8,8};

    // Builders pouch can hold any building block
    public static Integer[] BUILDERS_POUCH_ARRAY =   {0,2,3,4,5,6,8,8};

    // Slings can only hold weapons
    public static Integer[] WEAPON_SLING_ARRAY =     {0,2,3,4,5,6,8,8};

    // Bandoleers can only hold consumables (Food, Bombs, Potions, but cant hold arrows)
    public static Integer[] BANDOLIER_ARRAY =        {0,2,3,4,5,6,8,8};
    
    // Quivers can only hold arrows
    public static Integer[] QUIVER_ARRAY =           {0,2,3,4,5,6,8,8};

    // Unrestricted array can hold anything
    public static Integer[] UNRESTRICTED_ARRAY =     {0,2,3,4,6,8,12};

    // Get the number of different items the QuickAccess Item can swap between
    public static Integer getQuickAccessSize(QuickAccessItemComponent item) {
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

    public static ITEM_TYPE getQuickAccessItemType(String item_id) {
        switch (item_id){
            case "Quick_Access_Item_Unrestricted_Common":
            case "Quick_Access_Item_Unrestricted_Uncommon":
            case "Quick_Access_Item_Unrestricted_Rare":
            case "Quick_Access_Item_Unrestricted_Epic":
            case "Quick_Access_Item_Unrestricted_Legendary":
            case "Quick_Access_Item_Unrestricted_Debug":
                return ITEM_TYPE.UNRESTRICTED;
            default:
                return ITEM_TYPE.UNKNOWN;
        }
    }

    public static ITEM_TIER getQuickAccessItemTier(String item_id) {
        switch (item_id){
            case "Quick_Access_Item_Unrestricted_Common":
                return ITEM_TIER.COMMON;
            case "Quick_Access_Item_Unrestricted_Uncommon":
                return ITEM_TIER.UNCOMMON;
            case "Quick_Access_Item_Unrestricted_Rare":
                return ITEM_TIER.RARE;
            case "Quick_Access_Item_Unrestricted_Epic":
                return ITEM_TIER.EPIC;
            case "Quick_Access_Item_Unrestricted_Legendary":
                return ITEM_TIER.LEGENDARY;
            case "Quick_Access_Item_Unrestricted_Debug":
                return ITEM_TIER.DEBUG;
            default:
                return ITEM_TIER.UNKNOWN;
        }
    }

    public static Integer[] getItemArray(ITEM_TYPE type){
        return switch (type) {
            case TOOLBELT -> QuickAccessConfig.TOOLBELT_ARRAY;
            case BUILDERS_POUCH -> QuickAccessConfig.BUILDERS_POUCH_ARRAY;
            case WEAPON_SLING -> QuickAccessConfig.WEAPON_SLING_ARRAY;
            case BANDOLIER -> QuickAccessConfig.BANDOLIER_ARRAY;
            case QUIVER -> QuickAccessConfig.QUIVER_ARRAY;
            case UNRESTRICTED -> QuickAccessConfig.UNRESTRICTED_ARRAY;
            default -> new Integer[ITEM_TIER.NUM_TIERS.getId()];
        };
    }

    public static String getIsButtonDisabled(ItemStack quick_access_item, Integer button_id) {
        QuickAccessItemComponent qaItemComp = QuickAccessItemComponentFactory.createQuickAccessItemComponent(quick_access_item);

        QuickAccessConfig.ITEM_TYPE type = QuickAccessConfig.ITEM_TYPE.fromId(qaItemComp.getItemType());
        int tier = qaItemComp.getItemTier();
        // Invalid inputs check so disable button
        if (type.getId() >= ITEM_TYPE.NUM_TYPES.getId() || type.getId() < 0
                || tier < 0 || tier >= ITEM_TIER.NUM_TIERS.getId()){
            return "true";}

        WojosQuickAccessPlugin.LOGGER.atInfo().log("[DEBUG]: get Is button disabled - Tier:"+type.getId()+" Type:"+type.getId());

        Integer numEnabledButtons = 0;
        switch (type){
            case ITEM_TYPE.TOOLBELT:
                numEnabledButtons = TOOLBELT_ARRAY[tier];
                break;
            case ITEM_TYPE.BUILDERS_POUCH:
                numEnabledButtons = BUILDERS_POUCH_ARRAY[tier];
                break;
            case ITEM_TYPE.WEAPON_SLING:
                numEnabledButtons = WEAPON_SLING_ARRAY[tier];
                break;
            case ITEM_TYPE.BANDOLIER:
                numEnabledButtons = BANDOLIER_ARRAY[tier];
                break;
            case ITEM_TYPE.QUIVER:
                numEnabledButtons = QUIVER_ARRAY[tier];
                break;
            case ITEM_TYPE.UNRESTRICTED:
                numEnabledButtons = UNRESTRICTED_ARRAY[tier];
                break;
            default:
                return "true";
        }

        // Button is enabled: IsButtonDisabled = false
        if (button_id < numEnabledButtons){
            return "false";
        }
        return "true";
    }
}
