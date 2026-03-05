package org.wojo.wojosToolbelt.Commands;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;

// WojosQuickAccess                 - wqa
//      component                       - comp  (C)
//          addToItem                       - add      (A)         // wqa comp add      <arg: invId, invPos> (Add Quick Access Component to item in inventory)
//          removeFromItem                  - rm       (R)         // wqa comp rm       <arg: invID, invPos> (Remove Quick Access Component from item in inventory)
//          printComponentData              - print    (P)         // wqa comp print    <arg: invID, invPos> (Print Quick Access Component info from item in inventory)
//      item                            - item  (I)
//          swap                            - swap     (S)         // wqa item swap     <arg: qaInvId, qaInvPos, qaItemPos> (Swap Hotabar item with Item Stack in Quick Access)
//          print                           - print    (P)         // wqa item print    <arg: invID (Default: Hotbar=-1) + invPos (Default: 0)> (print item container data & Quick access comp data
//      gui                              -guis  (G)
//          itemSelectionPage               - select   (SEL)       // wqa guis select    <arg: qaInvId, qaInvPos, (o/c)> (Open or close item selection page tied to set Quick Access Item)
//          itemStoragePage                 - store    (STO)       // wqa guis store     <arg: qaInvId, qaInvPos, (o/c)> (Open or close item storage page tied to set Quick Access Item)
//          settingsPage                    - settings (SET)       // wqa guis settings  <arg: qaInvId, qaInvPos, (o/c)> (Open or close item settings page tied to quick access item)
//          helpPage                        - help     (HEL)       // wqa guis help      <arg: (o/c)>                    (Open or close global help page)
//      user                             -user  (U)
//          settingsPage                    - settings (S)         // wqa user settings  <arg: qaInvId, qaInvPos, (o/c)> (Open or close user settings page)
//      help                             -help  (H)
//          guide                           - guide    (G)         // wqa help guide <arg: none> (Calls: wqa guis help)

public class WojosQuickAccessCommandCollection extends AbstractCommandCollection {
    public WojosQuickAccessCommandCollection() {
        super("WojosQuickAccess","All commands associated with Wojo's Quick Access Items (Toolbelts, slings, and more)");
//        this.addSubCommand(new ComponentCollection());
//        this.addSubCommand(new ItemCollections());
//        this.addSubCommand(new GuiCollection());
//        this.addSubCommand(new AdminCollection());
//        this.addSubCommand(new UserCollection());
//        this.addSubCommand(new HelpCollection());

        addAliases("wqa","WQA","wojoqa", "WojoQa", "WOJOqa", "WOJOQA");
    }
}
