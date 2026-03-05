package org.wojo.wojosToolbelt.Components;

import org.wojo.wojosToolbelt.Config.QuickAccessConfig;
import org.wojo.wojosToolbelt.Components.QuickAccessComponent;

class QuickAccessComponentFactory {
    public static QuickAccessComponent createQuickAccessComponent(String item_id) {
        QuickAccessComponent comp = new QuickAccessComponent();

        switch (item_id){
            case "Quick_Access_Item_Crude_Unrestricted":
                comp.setItemTier(QuickAccessConfig.ITEM_TIER.COMMON.getId());
                comp.setItemType(QuickAccessConfig.ITEM_TYPE.UNRESTRICTED.getId());
        }
        return comp;
    }
}