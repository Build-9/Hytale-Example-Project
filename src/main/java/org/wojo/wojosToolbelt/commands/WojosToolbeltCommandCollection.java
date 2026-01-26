package org.wojo.wojosToolbelt.commands;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;

// ToolbeltSelectionGuiCommandCollection (WojosToolbelt)
//                  |       (Commands to interact with tool selector)
//                  |------ToolbeltSelectionGuiCommandCollection (ToolbeltSelectionGui, tsg)
//                  |                           |---- OpenToolbeltSelectionGui (Open, o)
//                  |                           |---- CloseToolbeltSelectionGui (Close, c)
//                  |
//                  |       (Commands to pick what tools can be selected)
//                  |------ToolbeltPlacementGuiCommandCollection
//

public class WojosToolbeltCommandCollection extends AbstractCommandCollection {
    public WojosToolbeltCommandCollection() {
        super("WojosToolbelt","All commands associated with Wojo's Toolbelt");
        addSubCommand(new ToolbeltSelectionGuiCommandCollection());
    }
}
