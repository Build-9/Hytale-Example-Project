package org.wojo.wojosToolbelt.Config;

import com.hypixel.hytale.server.core.inventory.Inventory;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import org.wojo.wojosToolbelt.Components.QuickAccessItemComponent;
import org.wojo.wojosToolbelt.Components.QuickAccessItemComponentFactory;
import org.wojo.wojosToolbelt.QuickAccessUtils.QuickAccessUtils;
import org.wojo.wojosToolbelt.WojosQuickAccessPlugin;

import java.util.Set;

public class QuickAccessConfig {
    // Default Hytale Qualities:
    // - [Common, Uncommon, Rare, Epic, Legendary, Debug]
    public static final Set<String> QUICK_ACCESS_ITEM_IDS = Set.of(
        "Quick_Access_Item_Common_Unrestricted",
        "Quick_Access_Item_Uncommon_Unrestricted",
        "Quick_Access_Item_Rare_Unrestricted",
        "Quick_Access_Item_Epic_Unrestricted",
        "Quick_Access_Item_Legendary_Unrestricted",
        "Quick_Access_Item_Debug_Unrestricted"
    );
    
    // The max number of items any toolbelt could possibly hold. (Used to define array size in Component)
    // NOTE: UI's are made with pre-defined component counts. Editing these values may require making new UI files.
    // TODO: These can be changed so set names to 'DEFAULT'
    public static int HOTBAR_GUI_BUTTON = 8;            // Button 9
    public static int HOTBAR_SWAP_LOCATION = 0;         // Button 1

    public static final String[] SELECTION_GUI_FILES = {"Pages/ThreeByThreeQuickAccess.ui", "Pages/FourByFourQuickAccess.ui"};
    
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
    // EX: {0,1,3,10,15,16,17,20} -> Unknown(0), Crude(1), Common(3), Rare(10), Epic(15), Legendary(16), Mythic(17), Creative(20)
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
    public static Integer[] UNRESTRICTED_ARRAY =     {0,2,3,4,5,6,8,8};

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
            case "Quick_Access_Item_Common_Unrestricted":
            case "Quick_Access_Item_Uncommon_Unrestricted":
            case "Quick_Access_Item_Rare_Unrestricted":
            case "Quick_Access_Item_Epic_Unrestricted":
            case "Quick_Access_Item_Legendary_Unrestricted":
            case "Quick_Access_Item_Debug_Unrestricted":
                return ITEM_TYPE.UNRESTRICTED;
            default:
                return ITEM_TYPE.UNKNOWN;
        }
    }

    public static ITEM_TIER getQuickAccessItemTier(String item_id) {
        switch (item_id){
            case "Quick_Access_Item_Common_Unrestricted":
                return ITEM_TIER.COMMON;
            case "Quick_Access_Item_Uncommon_Unrestricted":
                return ITEM_TIER.UNCOMMON;
            case "Quick_Access_Item_Rare_Unrestricted":
                return ITEM_TIER.RARE;
            case "Quick_Access_Item_Epic_Unrestricted":
                return ITEM_TIER.EPIC;
            case "Quick_Access_Item_Legendary_Unrestricted":
                return ITEM_TIER.LEGENDARY;
            case "Quick_Access_Item_Debug_Unrestricted":
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
