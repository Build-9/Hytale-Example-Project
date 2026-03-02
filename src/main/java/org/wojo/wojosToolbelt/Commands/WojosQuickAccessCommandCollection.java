package org.wojo.wojosToolbelt.Commands;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;

// WojosQuickAccess                 - wqa
//      component                       - comp  (C)
//          addToItem                       - add      (A)         // wqa comp add      <arg: invId (Default: Hotbar=-1) + invPos (Default: 0)> (Add Quick Access Component to item in inventory)
//          removeFromItem                  - rm       (R)         // wqa comp rm       <arg: invID (Default: Hotbar=-1) + invPos (Default: 0)> (Remove Quick Access Component from item in inventory)
//          printComponentData              - print    (P)         // wqa comp print    <arg: invID (Default: Hotbar=-1) + invPos (Default: 0)> (Print Quick Access Component info from item in inventory)
//          update                          - update   (U)         // wqa comp update   <arg: invID (Default: Hotbar=-1) + invPos (Default: 0)> (Update QaComp with data from items container)
//      item                            - item  (I)
//          moveTo                          - mvto     (T)         // wqa item mvto     <arg: invId, invPos, qaInvId, qaInvPos, qaItemPos> (Move item from an iventory into Quick Access Item) 
//          moveFrom                        - mvFm     (F)         // wqa item mvfm     <arg: qaInvId, qaInvPos, qaItemPos, invId, invPos> (Move item from Quick Access Item into another invenotry)
//          remove                          - rm       (R)         // wqa item rm       <arg: qaInvId, qaInvPos, qaItemPos> (Remove an item stack from Quick Access Item) 
//          print                           - print    (P)         // wqa item print    <arg: invID (Default: Hotbar=-1) + invPos (Default: 0)> (print item container data & Quick access comp data
//      gui                              -guis  (G)
//          itemSelectionPage               - select   (ISE)       // wqa guis select    <arg: qaInvId, qaInvPos, (o/c)> (Open or close item selection page tied to set Quick Access Item)
//          itemStoragePage                 - store    (IST)       // wqa guis store     <arg: qaInvId, qaInvPos, (o/c)> (Open or close item storage page tied to set Quick Access Item)
//          settingsPage                    - settings (S)         // wqa guis settings  <arg: qaInvId, qaInvPos, (o/c)> (Open or close item settings page tied to quick access item)
//          helpPage                        - help     (H)         // wqa guis help      <arg: (o/c)>                    (Open or close global help page)
//      admin                            -admin (A)
//          settingsPage                    - settings (S)         // wqa admin settings <arg: (o/c | true/false | 1/0)> (Open or close global admin settings page)
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
