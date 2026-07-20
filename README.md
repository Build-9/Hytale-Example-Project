# Wojo's Quick Access Item's (Toolbelts, Slings & More)
Turn two hotbar slots into 20+! 
This mod for **Hytale** adds custom items that allow Quck Access (Quick Swapping) using a radial menu.

## User Guide
- Quick-Access Items can be made at a workbench **Tier 1**
    - There are multiple tiers of Quick-Access Items and each requires the previous tier to be made
- Holding the item and using **Right-Click** will open chest style inventory to hold items
    - NOTE: The quick swap feature may not work correctly unless they add an item into the storage at least once
- Holding a Quick-Access item and using **Left-Click** key will open a radial menu allowing the user to select a button to swap an item from the **Target Hotbar Location** with whatever the selected button shows (----, Means the QuickAccess Item has nothing in that position).
- **Enabling** the Quick-Swap feature in the _Settings_ turns the **Equipped Hotbar Position** into an equipment slot for the QuickAccess Item. If the user places their QuickAccess Item into the specified hotbar position while **Enabled**. Pressing that hotbar key will instantly open the radial menu without needing to equip the item and *Left-Click* to allow proper QuickAccess

### Description
- Add new *Quick Access Items* that are used to implement the feature.
    - Holding the item and using **Right-Click** will open chest style inventory to hold items
    - Holding the item and using **Left-Click** will open a radial menu listing items in the inventory. Selecting an item on the UI will swap whatever is in hotbar position 0 (Configurable) with whatever is in the *Quick Access Item* at that spot.
- Have different tiers and types of *Quick Accesss Items* that have different benifits and drawbacks.
- A settings page can be opened through the command line or by using the settings button in the radial menu allowing the user to set different options

### Settings
- **Equipped Position:** What *hotbar position* does the *Quick Access Item* need to be in for it to count as **Equipped**.
- **Target Position:** When selecting an item in the radial menu, what hotbar location does it swap items to.
- **Gui File:** What radial menu file do you want to see
    - This allows the player to use a menu that shows locked positions or use a smaller radial menu then possible.
- **Is Enabled:** Intercept hotbar swaps to use the hotbars *Equipped Position* as a way to open the radial menu instead of needing to **Equip and Use** the item. 

### Design Goal
The main purpose of this mod is to fix one of my major complaints I have with the inventory managemnet. That issue is that the hotbar never feels large enough for the sheet number of things you want to switch between. The original design was a Quick Access strictly for different tools/weapons. 

The current design only includes the *Unrestricted Quick Access Item* with the goal to allow more options and better configuration in the future.

### Quick Access Item Types (Only checked items are currently implemented)
- [ ] **Toolbelt:** Quick access radial item that holds only holds **Tools** (Shovel, Pickaxe, axe, hammer)
- [ ] **Builders Pouch:** Quick access radial that only holds **Blocks & Hammer**
- [ ] **Weapon Sling:** Quick access radial that only hold **Weapons**
- [ ] **Bandolier:** Quick access radial that only holds **Consumables** (Potions, Food, Bombs etc)
- [ ] **Quiver:** Quick access radial that only holds **Arrows**
- [X] **Unrestricted:** Quick access radial that can hold **Anything**

### Quick Access Item Tiers and Default Storage Capacity
- Common: 2 slots
- Uncommon: 4 slots
- Rare: 8 slots 
- Epic: 12 slots
- Legendary: 20 slots
- Debug: 24 slots

---

### Commands
#### Key Command
- `/wqa gui help` Provides a UI of various mod info 

#### All Commands (Note: most commands have default args that are not specified here)
```java
/wqa comp player    // Set your QuickAccessPlayerComponent to defaults or specified values
/wqa comp printp    // Print your QuickAccessPlayerComponent data
/wqa comp printi    // Print held items QuickAccessItemComponent data

/wqa item swap      // Swap an item from held(Higher priority) or equiped(Lower Priority) QuickAccess Item to the defined hotbar position
/wqa item print     // Print Hytale item data associated with the held itemStack

/wqa gui select     // Show the radial selection menu
/wqa gui store      // Show the container storage menu
/wqa gui settings   // Show the settings menu
/wqa gui help       // Show the help menu

// ======== Perm's Info ========
// - Default Access
/wqa item swap
/wqa gui *

// - Admin Needed
/wqa comp *
/wqa item print

```

--- 

## Code Design 
### Hytale Development Notes and Limitations
- Adding custom metadata to items is not currently supported
    - Metadata is stored on ItemStacks not on the item itself. This makes handling custom data kind of a pain.
    - My work around for this issue is to have a `ComponentID -> Item Data Class` Map  
- Custom Key Binds are not currently supported
    - Use the hotbar positions to supliment a key bind for time being.
    - Using hotbar involves async calls outside of the normal game loop and ECS structure.
    - Added a few concurent hash maps as a way to pass data from the async calls to the game loop. (It's hacky and I don't like it but it works until they add custom key binds) 
  
### Code Data Description
The plugin layout has 2 data storage locations; **QuickAccessPlayerComponent - The player** and **QuickAccessItemComponent - The Quick Access Item**.
The Item is just a data object that holds info that I was unable to add to the item json. Data in here shouldn't change unless configs are changed. The **PlayerComponent** is the only area where data does change and it gets updated when the user adjusts any settings. 

- The **QuickAccessPlayerComponent**:
    - What hotbar slot is the 'equipped' location/ what hotbar button pressed to open ui
    - Is the hotbar button enabled?
    - What Hotbar Position to Swap Items Into
    - GuiPageString (Radial Menu to displau to user)

- The **QuickAccessItemComponent**: Holds the following item into
    - Asset Info
      - Item tier (Common, Uncommon, Rare, etc)
      - Container Size
    - Config Info
      - Quick Access Size (Must be <= Container Size; likely same as Container Size but doesn't need to be)
      - Item type (Unrestricted, Toolbelt, sling, etc)
      
When a user presses the eqipped hotbar location the code checks to see if the user has QuickSwap Enabled (Hash Map Value). If so, grab the data of the items in the component and open the gui. When the user selects an item on the gui swap that item with whatever is in the defined location.

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
- Resources
  - The different resource components that are made through Hytale's Asset Editior

---

### TODO:
##### High Priority (No set order)
- [ ] Update Radial UI to look better
- [ ] Fix permissions issues - Default perms should allow player to use item
- [ ] Custom Item model
- [ ] Allow item to be placed in world & used like chest
- [ ] Add hud elements to show item buttons to user when holding Quick Access Item
- [X] All Unrestricted item tiers
- [X] Crafting reciepe design for all QuickAccessUnrestricted items

##### Low Priority (No set order)
- [ ] Allow swap item to/from Selected hotbar instead of specific value 
- [ ] Creative mode tab?
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
