package org.wojo.wojosToolbelt.Commands;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;
import org.wojo.wojosToolbelt.Commands.Collections.ToolbeltSelectionGuiCommandCollection;

// ToolbeltSelectionGuiCommandCollection (WojosToolbelt)
//                  |       (Commands to interact with tool selector)
//                  |------ToolbeltSelectionGuiCommandCollection (ToolbeltSelectionGui, tsg)
//                  |                           |---- OpenToolbeltSelectionGui (Open, o)
//                  |                           |---- CloseToolbeltSelectionGui (Close, c)
//                  |
//                  |       (Commands to pick what tools can be selected)
//                  |------ToolbeltPlacementGuiCommandCollection
//
// WojosQuickAccess                 -wqa
//      Components                      -c
//          addItemToPlayer                 -a
//          removeItemFromPlayer            -r
//          printComponentData              -p
//      items - i                       -i
//          addItemToComponent              -a
//          removeItemFromComponent         -r
//          swapItem                        -s
//      gui - g                         -g
//          openItemSelection               -o
//          closeItemSelection              -c

public class WojosToolbeltCommandCollection extends AbstractCommandCollection {
    public WojosToolbeltCommandCollection() {
        super("WojosToolbelt","All commands associated with Wojo's Toolbelt");
        this.addSubCommand(new ToolbeltSelectionGuiCommandCollection());
    }
}
