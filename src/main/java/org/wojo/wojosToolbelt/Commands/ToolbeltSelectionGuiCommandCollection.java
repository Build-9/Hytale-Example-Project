package org.wojo.wojosToolbelt.Commands;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;

public class ToolbeltSelectionGuiCommandCollection extends AbstractCommandCollection {
    public ToolbeltSelectionGuiCommandCollection() {
        super("ToolbeltSelectionGui", "Commands to interact with the selection gui");

        addSubCommand(new OpenToolbeltSelectionGuiCommand());
        addSubCommand(new CloseToolbeltSelectionGuiCommand());

        addAliases("tsg");
    }
}
