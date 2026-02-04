package org.wojo.wojosToolbelt.Commands;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;
import org.wojo.wojosToolbelt.Commands.Collections.ToolbeltSelectionGuiCommandCollection;
// WojosQuickAccess                 - wqa
//      Components                      - comp   (C)
//          addToPlayer                     - add     (A)         // wqa comp add
//          removeFromPlayer                - rm      (R)         // wqa comp rm
//          printComponentData              - print   (P)         // wqa comp print
//      items                            -item    (I)
//          addItemToComponent              -add      (A)         // wqa items add 
//          removeItemFromComponent         -rm       (R)         // wqa items rm
//          swapItem                        -swap     (S)         // wqa items swap
//      gui                              -guis    (G)
//          openItemSelectionPage           -ois       (OIS)      // wqa guis ois
//          closeItemSelectionPage          -cis       (CIS)      // wqa guis cis
//          openItemPlacementPage           -oip       (OIP)      // wqa guis oip
//          closeItemPlacementPage          -cip       (CIP)      // wqa guis cip
//      admin
//          openSettingsPage                -osp       (OSP)      // wqa admin osp
//          closeSettingsPage               -csp       (CSP)      // wqa admin csp
//      user
//          openHelpPage                    -ohp       (OHP)      // wqa user ohp
//          closeHelpPage                   -chp       (CHP)      // wqa user chp

public class WojosToolbeltCommandCollection extends AbstractCommandCollection {
    public WojosToolbeltCommandCollection() {
        super("WojosToolbelt","All commands associated with Wojo's Toolbelt");
        this.addSubCommand(new ToolbeltSelectionGuiCommandCollection());
    }
}
