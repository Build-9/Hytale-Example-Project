package org.wojo.wojosToolbelt.commands;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class ToolbeltSelectionGuiCommandCollection extends AbstractCommandCollection {
    public ToolbeltSelectionGuiCommandCollection() {
        super("ToolbeltSelectionGui", "Commands to interact with the selection gui");

        addSubCommand(new OpenToolbeltSelectionGuiCommand());
        addSubCommand(new CloseToolbeltSelectionGuiCommand());

        addAliases("tsg");
    }
}
