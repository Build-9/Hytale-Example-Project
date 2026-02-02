package org.wojo.wojosToolbelt.Config;

public class ConfigSettings {
    // Item Tiers (Crude=0, Common=1, Rare=2, Epic=3, Legendary=4, Mythic=5)
    
    // The max number of items any toolbelt could possibly hold. (Used to define array size in Component)
    // NOTE: UI's are made with pre-defined component counts. Editing these values may require making new UI files. 
    static final int MAX_QA_ITEMS = 20;
    
    // Toolbelts can only Items with tool tag
    static final int TOOLBELT_ARRAY = {3,4,5,6,7,10};

    // Builders puch can hold any building block
    static final int BUILDERS_POUCH_ARRAY = {10,12,14,16,18,20};

    // Slings can only hold weapons
    static final int SLING_ARRAY = {2,3,4,5,6,8};

    // Bandoleers can only hold consumables (Food, Bombs, Potions, but cant hold arrows)
    static final int BANDOLIER_ARRAY = {6,8,10,12,14,18};
    
    // Quivers can only hold arrows
    static final int QUIVER_ARRAY = {3,5,7,9,11,15};
    
    // Creative item that can hold up to max of any item
    static final int CREATIVE_QUICK_ACCESS = this.MAX_QA_ITEMS_SELECTABLE; 
}
