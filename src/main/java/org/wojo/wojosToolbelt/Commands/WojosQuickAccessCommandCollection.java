package org.wojo.wojosToolbelt.Commands;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;
import org.wojo.wojosToolbelt.Commands.component.ComponentCommandCollection;
import org.wojo.wojosToolbelt.Commands.gui.GuiCommandCollection;
import org.wojo.wojosToolbelt.Commands.item.ItemCommandCollection;

// WojosQuickAccess                 - wqa
//      component                       - comp  (C)
//          playerSettings                  - player   (PS)        // wqa comp player   <arg: enable, equipPos, targetPos> (Update player comp with new settings)
//          printPlayer                     - printp   (PP)        // wqa comp printp   <arg: None> (Print player comp data)
//          printItem                       - printi   (PI)        // wqa comp printi   <arg: None> (Print held item's comp data)
//      item                            - item  (I)
//          swap                            - swap     (S)         // wqa item swap     <arg: qaInvId, qaInvPos, qaItemPos> (Swap Hotabar item with Item Stack in Quick Access)
//          print                           - print    (P)         // wqa item print    <arg: invID (Default: Hotbar=-1) + invPos (Default: 0)> (print item container data & Quick access comp data
//      gui                              -guis  (G)
//          itemSelectionPage               - select   (SEL)       // wqa guis select    <arg: qaInvId, qaInvPos, event> (Open or close item selection page tied to set Quick Access Item)
//          itemStoragePage                 - store    (STO)       // wqa guis store     <arg: qaInvId, qaInvPos, event> (Open or close item storage page tied to set Quick Access Item)
//          settingsPage                    - settings (SET)       // wqa guis settings  <arg: qaInvId, event>           (Open or close item settings page tied to quick access item)
//          helpPage                        - help     (HEL)       // wqa guis help      <arg: event>                    (Open or close global help page)

public class WojosQuickAccessCommandCollection extends AbstractCommandCollection {
    public WojosQuickAccessCommandCollection() {
        super("WojosQuickAccess","All commands associated with Wojo's Quick Access Items (Toolbelts, slings, and more)");
        this.addSubCommand(new ComponentCommandCollection());
        this.addSubCommand(new ItemCommandCollection());
        this.addSubCommand(new GuiCommandCollection());
//        this.addSubCommand(new AdminCollection());
//        this.addSubCommand(new UserCollection());
//        this.addSubCommand(new HelpCollection());

        addAliases("wqa","WQA","wojoqa", "WojoQa", "WOJOqa", "WOJOQA");
    }
}
