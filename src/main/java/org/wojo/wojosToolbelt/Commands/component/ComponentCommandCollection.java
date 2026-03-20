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