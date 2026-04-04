# Wojo's Quick Access Item's (Toolbelts, Slings & More)
Adds a few new items that players can use to remove specific tools from the hotbar and place them in their own inventory while using a radial menu to access them.

### Description
**Wojo's Quick Access Item's** is a mod that adds a new quickswap feature to hytale. It does this by adding a series of new items that do the following:
- New *Quick Access Items* are used to implement the feature.
    - Holding the item and using **Right-Click** will open chest style inventory to hold items
    - Holding the item and using **Left-Click** will open a radial menu with a smaller number of items for easy access
- A settings page can be opened through the command line or by using the settings button in the radial menu allowing the user to set the following settings
    - **Equipped Position:** What hotbar position does the item need to be in to have the item equipped
    - **Target Position:** When selecting an item in the radial menu, where should it be moved to
    - **Gui File:** What radial menu file do you want to see when left clicking
    - **Is Enabled:** Intercept hotbar swaps to use the hotbars *Equipped Position* as a way to open the radial menu. 

The main purpose of this mod is to fix one of my major complaints with the inventory by allowing the player to convert 2 hotbar slots into up to 24 different positions.


### Quick Access Item Types (Only checked items are implemented)
- [ ] Toolbelt: Quick access radial item that holds only holds tools (Shovel, Pickaxe, axe, hammer)
- [ ] Builders Pouch: Quick access radial that only holds blocks & hammer
- [ ] Sling (Weapon Sling): Quick access radial that only hold weapons
- [ ] Bandolier: Quick access radial that only holds Consumables (Potions, Food, Bombs etc)
- [ ] Quiver: Quick access radial that only holds arrows
- [X] Unrestricted: Quick access radial that can hold anything

### Quick Access Item Tiers (Only checked items are implemented)
- [ ] Common: 2 slots
- [ ] Uncommon: 4 slots
- [ ] Rare: 8 slots 
- [ ] Epic: 12 slots
- [ ] Legendary: 20 slots
- [X] Debug: 24 slots

### Commands
#### Key Command
- `/wqa gui help` Provides a UI list of various mod info 

#### All Commands (Note: most commands have default args that are not specified here)
```java
/wqa comp player    // Set your QuickAccessPlayerComponent to defaults or specified values
/wqa comp printp    // Print your QuickAccessPlayerComponent data
/wqa comp printi    // Print held items QuickAccessItemComponent data

/wqa item swap      // Swap an item from a QuickAccess Item in the hotbar to the hotbar
/wqa item print     // Print everything associated with the held itemStack

/wqa gui select     // Show the radial selection menu
/wqa gui store      // Show the container storage menu
/wqa gui settings   // show the settings menu
/wqa gui help       // Show the help menu
```

## Code Design 
### Code Description
The plugin layout has 2 data storage locations; **The player** and **The Quick Access item**. 
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
When a user presses the eqipped hotbar location the code checks to see if the user has QuickSwap Enabled. If so, grab the data of the items in the component and open the gui. When the user selects an item on the gui swap that item with whatever is in the defined location.

### Code Components
- Commands
    - Varous commands that are used for debugging, configuration, or help
- Components
    - The QuickAccessPlayerComponent (ECS component attached to the player)
    - The QuickAccessItemComponent (This is not an ecs component, its used as a wrapper to get the items Json data & some config data)
- Config
    - All statically defined values
- Events
    - Mod involves player interaction so the async nature requiures the use of events instead of a system for the swap functionality
- Handlers
    - Logic for handling the triggered events
- Interactions
    - This is the handler for when players use items. Items use interaction chains so we use this to open the UI when player is holding the item.
- Packet Adapters
    - Logic to convert player hotbar interaction to a UI button (Ideally when player keybinds get intoduced this can be replaced)
- Systems
    - Do things when something happens to an ECS component. Main use is to keep Packet Adapter working when QuickAccessPlayerComponent gets updated
- Utils
    - ECS structure states components should have no methods so these are all static helper methods that do much of the validation & sanity checks. 
- UI
    - All ui classes
- resources
  - The different resource components that are made through Hytale's Asset Editior

---

### TODO:
##### High Priority (No set order)
- [ ] Custom Item model
- [ ] Allow item to be placed in world & used like chest
- [ ] Update Radial UI to look better
- [ ] Add hud elements to show item buttons to user
- [ ] All Unrestricted item tiers
- [ ] Crafting reciepe design for all QuickAccessUnrestricted items

##### Low Priority (No set order)
- [ ] Creative mode tab settings
- [ ] Add animation to using item
- [ ] Implement other QuickAccessItemTypes
- [ ] Allow equipping items in utility slot
- [ ] Have way to *wear* QuickAccess items so others can see when player has it equipped
- [ ] Add server configurations to modify config values
  - [ ] Add configurable equip costs 
    - [ ] Swap speed
    - [ ] Move Speed while swapping
    - [ ] Stamina Cost


### Special Thanks
- **Hytalemodding.dev** website and discord needs all the praise I can give them. They are great source of info and helped me to many times to count during the development. [Modding Documentation Website](https://hytalemodding.dev/en)
- Thanks to TroubleDEV for the best early Hytale youtube tutorials [TroubleDEV Youtube Link](https://www.youtube.com/channel/UC8IirsfaLXk7WFn55j1zs-g)
- Thanks to Plugin Template for code template. [Template Link](https://github.com/Build-9/Hytale-Example-Project)
