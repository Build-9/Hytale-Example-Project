// Move an item from a position in a players inventory (Or position in container in their inventory) to another
public class MoveItemCommand extends AbstractPlayerCommand {
    private final DefaultArg<BOOLEAN> _isSrcInContainer; // Is the inital item inside of an items container?
    private final DefaultArg<INTEGER> _srcContainerId;   // Players inventory_id the container exists in
    private final DefaultArg<INTEGER> _srcContainerPos;  // Players inventory pos of the container
    private final DefaultArg<INTEGER> _srcInvId;         // Players inventory id item is in (not used if _isSrcInContainer == true, )
    private final DefaultArg<INTEGER> _srcInvPos;        // Position of src item inside the container or in the inventory

    private final DefaultArg<BOOLEAN> _isTgtInContainer; // Is the target location inside a container
    private final DefaultArg<INTEGER> _tgtContainerId;   // Players inventory_id the container exists in
    private final DefaultArg<INTEGER> _tgtContainerPos;  // Players inventory pos of the container
    private final DefaultArg<INTEGER> _tgtInvId;         // Players inv id the target position is in
    private final DefaultArg<INTEGER> _tgtInvPos;        // Position of item in the target pos
 
    private static final String[] HANDLER_TYPES = {"delete", "move", "drop", "swap", "cancel"}
    private final DefaultArg<STRING> _tgtHandler;        // How to handle existing item in target location [del,mv,drop,swap] 
    //  NOTE: If filter blocks placemnet of item in container. 
    //  Cancel the command and tell the player
    
    MoveItemCommand() {
        super("moveItem", "Move an item from one Inventory (or ItemContainer in inventory) to another. Both Locations must be in players inventory.");
        addAliases("move", "Move", "M");

        // ------------ Source Item Values ------------
        this._isSrcInContainer = this.withDefaultArg(
            "src-inContainer","Is the source item inside of a container?",
            ArgTypes.BOOLEAN,
            true, "Source item is not in a container"
        );
        this._srcContainerId = this.withDefaultArg(
            "src-containerId","Inventory id that holds source container",
            ArgTypes.INTEGER,
            -1, "Hotbar"
        );
        this._srcContainerPos = this.withDefaultArg(
            "src-containerPos","Position of the source container in the inventory",
            ArgTypes.INTEGER,
            8, "Position 8 (0 indexed)"
        );

        this._srcInvId = this.withDefaultArg(
            "src-invId", "Inventory Id of the source item. (Not used if SrcInContainer==True)",
            ArgTypes.INTEGER,
            -1, "Hotbar"
        );
        this._srcInvPos = this.withDefaultArg(
            "src-invPos", "Position in container or pos in inventory of source item",
            ArgTypes.INTEGER,
            1, "Position 1 (0 indexed)"
        );

        // ------------ Target Item Values ------------
        this._isTgtInContainer = this.withDefaultArg(
            "tgt-inContainer", "Is the destination location inside a container?",
            ArgTypes.BOOLEAN,
            false, "Target location is not in a container"
        );
        this._tgtContainerId = this.withDefaultArg(
            "tgt-containerId","Inventory id that holds target container",
            ArgTypes.INTEGER,
            -1, "Hotbar"
        );
        this._tgtContainerPos = this.withDefaultArg(
            "tgt-containerPos","Position of the target container in the inventory",
            ArgTypes.INTEGER,
            0, "Position 0 (0 indexed, Hotbar slot 1)"
        );

        this._tgtInvId = this.withDefaultArg(
            "tgt-invId", "Inventory Id of the target location. (Not used if TgtInContainer==True)",
            ArgTypes.INTEGER,
            -1, "Hotbar"
        );
        this._tgtInvPos = this.withDefaultArg(
            "tgt-invPos", "Position in container or pos in inventory of target location",
            ArgTypes.INTEGER,
            0, "Position 0 (0 indexed, Hotbar slot 1)"
        );

        this._tgtHandler = this.withDefaultArg(
            "tgt-handle", "How to handle an item in the target location [del, drop, move, swap]",
            ArgTypes.STRING,
            "move", "If an item exists in the target location try moving it to an empty invenotry slot"
        );
    }

    @Override
    protected void execute(
        @NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store, 
        @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, 
        @NonNullDecl World world) 
    {
        boolean isSrcInContainer = commandContext.get(this._isSrcInContainer);
        Integer srcContainerId = commandContext.get(this._srcContainerId);   
        Integer srcContainerPos = commandContext.get(this._srcContainerPos);  
        Integer srcInvId = commandContext.get(this._srcInvId);
        Integer srcInvPos = commandContext.get(this._srcInvPos);

        boolean isTgtInContainer = commandContext.get(this._isTgtInContainer);
        Integer tgtContainerId = commandContext.get(this._tgtContainerId);
        Integer tgtContainerPos = commandContext.get(this._tgtContainerPos);
        Integer tgtInvId = commandContext.get(this._tgtInvId);
        Integer tgtInvPos = commandContext.get(this._tgtInvPos);
    
        String tgtHandler = commandContext.get(this._tgtHandler);

        if (!HANDLER_TYPES.contains(tgtHandler)){
            commandContext.sendMessage(Message.raw("Invalid handler type. Need one of "+HANDLER_TYPES.toString()));
        }

        Player player = store.getComponent(ref, Player.getComponentType());
        if (player != null){

        }
    }
}