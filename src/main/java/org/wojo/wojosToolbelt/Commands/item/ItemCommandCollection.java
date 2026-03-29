package org.wojo.wojosToolbelt.Commands.item;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;
import org.wojo.wojosToolbelt.Commands.component.PrintItemComponentCommand;

public class ItemCommandCollection extends AbstractCommandCollection {
    public ItemCommandCollection(){
        super("item","All Item Specific commands");

        this.addSubCommand(new PrintItemCommand());
        this.addSubCommand(new SwapItemCommand());

        addAliases("items","I");
    }
}