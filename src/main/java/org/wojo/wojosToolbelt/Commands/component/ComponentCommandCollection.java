package org.wojo.wojosToolbelt.Commands.component;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;
import org.wojo.wojosToolbelt.Commands.component.ItemSettingsCommand;
import org.wojo.wojosToolbelt.Commands.component.PlayerSettingsCommand;
import org.wojo.wojosToolbelt.Commands.component.PrintItemComponentCommand;
import org.wojo.wojosToolbelt.Commands.component.PrintPlayerComponentCommand;

public class ComponentCommandCollection extends AbstractCommandCollection {
    public ComponentCommandCollection(){
        super("component","All Component Specific comamnds");

        this.addSubCommand(new PlayerSettingsCommand());
        this.addSubCommand(new ItemSettingsCommand());
        this.addSubCommand(new PrintPlayerComponentCommand());
        this.addSubCommand(new PrintItemComponentCommand());

        addAliases("comp","C");
    }
}