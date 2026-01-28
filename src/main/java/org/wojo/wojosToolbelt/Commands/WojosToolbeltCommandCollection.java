package org.wojo.wojosToolbelt.Commands;

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
        this.addSubCommand(new ToolbeltSelectionGuiCommandCollection());
    }
}
