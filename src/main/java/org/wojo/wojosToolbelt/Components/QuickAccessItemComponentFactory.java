package org.wojo.wojosToolbelt.Components;

import com.hypixel.hytale.server.core.asset.type.item.config.Item;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import org.wojo.wojosToolbelt.Config.QuickAccessConfig;
import org.wojo.wojosToolbelt.Components.QuickAccessItemComponent;

public class QuickAccessItemComponentFactory {
    public static QuickAccessItemComponent createQuickAccessItemComponent(String item_id) {
        QuickAccessItemComponent comp = new QuickAccessItemComponent();

        switch (item_id){
            case "Quick_Access_Item_Crude_Unrestricted":
            comp.setItemTier(QuickAccessConfig.ITEM_TIER.COMMON.getId());
            comp.setItemType(QuickAccessConfig.ITEM_TYPE.UNRESTRICTED.getId());
            comp.setContainerSize(QuickAccessConfig);
            comp.setQuickAccessSize();
        }
        return comp;
    }

    public static QuickAccessItemComponent createQuickAccessItemComponent(ItemStack item_stack){
        QuickAccessItemComponent comp = new QuickAccessItemComponent();
        Item item = item_stack.getItem();
        item.;
        item.

        switch (item.getId()){
            case "Quick_Access_Item_Crude_Unrestricted":
                comp.setItemTier(QuickAccessConfig.ITEM_TIER.COMMON.getId());
                comp.setItemType(QuickAccessConfig.ITEM_TYPE.UNRESTRICTED.getId());
                comp.setContainerSize(item.getItem().getData());
                comp.setQuickAccessSize();
        }
        return comp;
    }
}