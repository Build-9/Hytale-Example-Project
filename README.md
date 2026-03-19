# Wojo's Quick Access Item's (Toolbelts, Slings & More)
Adds a few new items that players can use to remove specific tools from the hotbar and place them in their own inventory while using a radial menu to access them.

## Design.
#### Description
**Wojo's Quick Access Item's** is a mod that adds a new radial menu and storage items to allow a player to quickswap the containers items into the hotbar. The new container items are "equipable" in the sense that they are equpped by being placed in a specific hotbar location.
One the item is placed in that location the user can press the corresponding hotbar key. It will open a GUI to a radial wheel allowing the player to more easily swap between specific items by opening a radial menu with fewer items instead of needing to open the full inventory. This allows the decluttering of the player inventory. 

#### Quick Access Item Types
- [ ] Toolbelt: Quick access radial item that holds only holds tools (Shovel, Pickaxe, axe, hammer)
- [ ] Builders Pouch: Quick access radial that only holds blocks & hammer
- [ ] Sling (Weapon Sling): Quick access radial that only hold weapons
- [ ] Bandolier: Quick access radial that only holds Consumables (Potions, Food, Bombs etc)
- [ ] Quiver: Quick access radial that only holds arrows
- [ ] Custom: Quick access radial that only holds specified items
- [ ] Unrestricted: Quick access radial that can hold anything

#### Quick Access Item Tiers
- [ ] Common: 2 slots
- [ ] Uncommon: 4 slots
- [ ] Rare: 6 slots 
- [ ] Epic: 8 slots
- [ ] Legendary: 10 slots
- [ ] Mythic: 12 slots
- [ ] Creative: 20 slots??

## Code Design 
### Code Description
The desing layout has 2 data the player and the item. 
- The PlayerComponent: Houses player settings like
    - What hotbar slot is the 'equipped' location / what hotbar button pressed to open ui
    - Is the hotbar button enabled?
    - Where to swap the items into
- The ItemComponent: Houses item info
    - Item type (Unrestricted, Toolbelt, sling, etc)
    - Item tier (Common, Uncommon, Rare, etc)
    - Container Size
    - Quick Access Size
    - guiPageString
When a user presses the eqipped hotbar location the code checks to see if the user care's about equipped items. If so, grab the data of the items in the component and open the gui. When the user selects an item on the gui swap that item with whatever is in the defined location.

### Code Components
- Commands
    - Varous commands that are used for debugging, configuration, or help
- Components
    - The QuickAccessPlayerComponent
    - The QuickAccessItemComponent
- Config
    - All defined values that are either statically set or modifyable by admins & users
- Events
    - The mod heavily relies on player interaction so most if not all the funtionality will be bassed off events rather then systems
        - Swap Item Event
- Handlers
    - Logic for handling the triggered events
        - Handle Swap Item Events
- Packet Adapters
    - Logic to convert player hotbar interaction to a UI button (Ideally this will be changed to better handle player button interactions)
- Systems
    - Player Component System adds a component to every player uplon joining the server and watches for changes
- UI
    - All ui objects that a player could iteract with and view


## Commands
```java
// NOTE: All open/close args can be one of the following: [o/c, Open/Close(case Insensitive), 1/0, True/False(case Insensitive)]
// WojosQuickAccess                 - wqa
//      component                       - comp  (C)
//          playerSettings                  - player   (PS)        // wqa comp player   <arg: enable, equipPos, targetPos> (Update player comp with new settings)
//          itemSettings                    - item     (IS)        // wqa comp item     <arg: tier, contSize, type, size, gui> (Update item QA comp settings)
//          printPlayer                     - printp   (PP)        // wqa comp printp   <arg: None> (Print player comp data)
//          printItem                       - printi   (PI)        // wqa comp printi   <arg: None> (Print held item's comp data)
//      item                            - item  (I)
//          swap                            - swap     (S)         // wqa item swap     <arg: qaInvId, qaInvPos, qaItemPos> (Swap Hotabar item with Item Stack in Quick Access)
//          moveItem                        - move     (M)         // wqa item move     <arg: srcInContainer?, containerId, containerPos, srcInvId, srcInvPos, tgtInContainer?, tgtContainerId, tgtContainerPos, tgtInvId, tgtInvPos, tgtHdl[del,mv,swap]> (Move an item to another location and handle existing item appropriately)
//          print                           - print    (P)         // wqa item print    <arg: invID (Default: Hotbar=-1) + invPos (Default: 0)> (print item container data & Quick access comp data
//      gui                              -guis  (G)
//          itemSelectionPage               - select   (SEL)       // wqa guis select    <arg: qaInvId, qaInvPos, (o/c)> (Open or close item selection page tied to set Quick Access Item)
//          itemStoragePage                 - store    (STO)       // wqa guis store     <arg: qaInvId, qaInvPos, (o/c)> (Open or close item storage page tied to set Quick Access Item)
//          settingsPage                    - settings (SET)       // wqa guis settings  <arg: qaInvId, qaInvPos, (o/c)> (Open or close item settings page tied to quick access item)
//          helpPage                        - help     (HEL)       // wqa guis help      <arg: (o/c)> 
```

### Special Thanks
- Thanks to Hytalemodding.dev website for some great info on how to set most of this up. [Modding Documentation Website](https://hytalemodding.dev/en)
- Thanks to TroubleDEV for some amazing youtube tutorials [TroubleDEV Youtube Link](https://www.youtube.com/channel/UC8IirsfaLXk7WFn55j1zs-g)
- Thanks to TroubleDEV's Discord to helping me through various issues. 
- Thanks to Plugin Template for code template. [Template Link](https://github.com/Build-9/Hytale-Example-Project)
