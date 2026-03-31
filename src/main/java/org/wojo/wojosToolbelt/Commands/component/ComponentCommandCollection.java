package org.wojo.wojosToolbelt.Commands.component;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;

public class ComponentCommandCollection extends AbstractCommandCollection {
    public ComponentCommandCollection(){
        super("component","All Component Specific commands");

        this.addSubCommand(new PlayerSettingsCommand());
        this.addSubCommand(new PrintPlayerComponentCommand());
        this.addSubCommand(new PrintItemComponentCommand());

        addAliases("comp","C");
    }
}