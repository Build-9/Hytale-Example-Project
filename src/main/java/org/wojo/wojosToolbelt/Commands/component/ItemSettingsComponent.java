package org.wojo.wojosToolbelt.Commands.component;

// wqa comp item     <arg: tier, contSize, type, qaSize, gui> (Update held items quick access settings)
public class ItemSettingsCommand extends AbstractPlayerCommand {
    private final DefaultArg<Integer> tier;     // Item Tier (Uncommon, Common, Rare, Epic...)
    private final DefaultArg<Integer> contSize; // Container Size (Size of storage for items)
    private final DefaultArg<Integer> type;     // Item Type (Unrestricted, Toolbelt, Sling, ...)
    private final DefaultArg<Integer> qaSize;   // Quick Swap Size (Number of enabled buttons on UI)
    private final DefaultArg<String>  gui;      // GUI String

    PlayerSettingsCommand() {
        super("itemSettings", "Configure the players held Quick-Access-Item");
        addAliases("item","IS");

        this.tier = this.withDefaultArg(
            "tier", "Set the item tier [UNKNOWN:0|COMMON:1|UNCOMMON:2|RARE:3|EPIC:4|LEGENDARY:5|MYTHIC:6|CREATIVE:7]",
            ArgTypes.INTEGER,
            1, "COMMON"
        );

        this.contSize = this.withDefaultArg(
            "cont-size", "Set the items container size.",
            ArgTypes.INTEGER,
            10, "Ten items total."
        );

        this.type = this.withDefaultArg(
            "type", "Set the type of the quick access item. [UNKNOWN:0|TOOLBELT:1|BUILDERS_POUCH:2|WEAPON_SLING:3|BANDOLIER:4|QUIVER:5|UNRESTRICTED:6|CUSTOM:7]",
            ArgTypes.INTEGER,
            6, "Unrestricted"
        );

        this.qaSize = this.withDefaultArg(
            "qa-size", "Number of enabled quick access buttons",
            ArgTypes.INTEGER,
            2, "COMMON's default number of enabled items is 2 buttons."
        );

        this.gui = this.withDefaultArg(
            "gui", "Path to UI To use. If player wants to use UI that has",
            ArgTypes.STRING,
            "Pages/ThreeByThreeQuickAccess.ui", "Basic UI file with 8 total buttons."
        );
    }
}

