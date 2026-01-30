package org.wojo.wojosToolbelt.Commands.Collections;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;
import org.wojo.wojosToolbelt.Commands.GuiCommands.CloseToolbeltSelectionGuiCommand;
import org.wojo.wojosToolbelt.Commands.GuiCommands.OpenToolbeltSelectionGuiCommand;

public class ToolbeltSelectionGuiCommandCollection extends AbstractCommandCollection {
    public ToolbeltSelectionGuiCommandCollection() {
        super("ToolbeltSelectionGui", "Commands to interact with the selection gui");

        addSubCommand(new OpenToolbeltSelectionGuiCommand());
        addSubCommand(new CloseToolbeltSelectionGuiCommand());

        addAliases("tsg");
    }
}
