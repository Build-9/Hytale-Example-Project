package org.wojo.wojosToolbelt.Commands.gui;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;


public class GuiCommandCollection extends AbstractCommandCollection {
    public GuiCommandCollection(){
        super("gui","All GUI Specific commands");

        this.addSubCommand(new ItemSelectionPageCommand());
        this.addSubCommand(new PlayerSettingsPageCommand());

        addAliases("guis","G");
    }
}
