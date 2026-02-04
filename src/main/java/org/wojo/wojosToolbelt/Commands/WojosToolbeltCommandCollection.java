package org.wojo.wojosToolbelt.Commands;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;
import org.wojo.wojosToolbelt.Commands.Collections.ToolbeltSelectionGuiCommandCollection;
// WojosQuickAccess                 - wqa
//      Components                      - comp    (C)
//          addToPlayer                     - add     (A)         // wqa comp add      <arg: None>
//          removeFromPlayer                - rm      (R)         // wqa comp rm       <arg: None>
//          printComponentData              - print   (P)         // wqa comp print    <arg: None>
//      items                           - item    (I)
//          addItemToComponent              -add      (A)         // wqa items add     <arg: uuid, pos(optional)>
//          removeItemFromComponent         -rm       (R)         // wqa items rm      <arg: pos, uuid(optional)>
//          swapItem                        -swap     (S)         // wqa items swap    <arg: [uuid(optional), pos(optional)](One Required), targetpos(optional)>
//      gui                              -guis    (G)
//          itemSelectionPage               -select   (ISE)      // wqa guis select    <arg: (o/c)>
//          itemStoragePage                 -store    (IST)      // wqa guis store     <arg: (o/c)>
//          helpPage                        -help      (H)       // wqa guis help      <arg: (o/c)>
//      admin                            -admin   (A)
//          settingsPage                    -settings  (S)       // wqa admin settings <arg: (o/c | true/false | 1/0)>
//      user                             -user    (U)
//          settingsPage                    -settings  (S)       // wqa user settings  <arg: (o/c)>

public class WojosToolbeltCommandCollection extends AbstractCommandCollection {
    public WojosToolbeltCommandCollection() {
        super("WojosToolbelt","All commands associated with Wojo's Toolbelt");
        this.addSubCommand(new ToolbeltSelectionGuiCommandCollection());
    }
}
