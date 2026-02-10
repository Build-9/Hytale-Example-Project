# Wojo's Quick Access Items
Adds a few new items that players can use to remove specific tools from the hotbar and place them in their own inventory for quick access.

## Design.
#### Description
**Wojo's Quick Access Items** is a mod that adds a new subset of items that are "equipable" in the sense that they are equpped by being placed in a specific hotbar location.
One the item is placed in that location pressing the corresponding hotbar key will open a GUI to a radial wheel allowing the player to more easily swap between specific items
instead of needing to open the full inventory. This allows the decluttering of the player inventory. 

#### Quick Access Item Types
- [ ] Toolbelt: Quick access radial item that holds only holds tools (Shovel, Pickaxe, axe, hammer)
- [ ] Builders Pouch: Quick access radial that only holds blocks & hammer
- [ ] Sling (Weapon Sling): Quick access radial that only hold weapons
- [ ] Bandolier: Quick access radial that only holds Consumables (Potions, Food, Bombs etc)
- [ ] Quiver: Quick access radial that only holds arrows
- [ ] Custom: Quick access radial that only holds specified items
- [ ] Creative: Quick access radial that can hold anything

#### Quick Access Item Tiers
- [ ] Common: 2 slots
- [ ] Uncommon: 4 slots
- [ ] Rare: 6 slots 
- [ ] Epic: 8 slots
- [ ] Legendary: 10 slots
- [ ] Mythic: 12 slots
- [ ] Creative: 20 slots??

#### GUI
- [ ] Quick Access Page: Quick access wheel showing different selectable items stored in inventory
- [ ] Quick Access Inventory: Inventory UI to allow the player to place items where desired
- [ ] Player Settings Page: Player setting page to set the following
    - [ ] What hotbar slot the Quick-Access-Item needs to be placed in to have GUI work
    - [ ] Should the swap be into the active hotbar vs predefined hotbar location?
    - [ ] What Hotbar slot the selected item will be swapped into
- [ ] Admin Settings Page (Command Only): 
    - [ ] Whitelist & Blacklist items to specific item type & tier bassed on item id's
    - [ ] Change crafting recipe's for each item type & tier
    - [ ] Interactions to modify Custom item to be whats needed
    - [ ] Customize slots available per item type & tier
    - [ ] Customize swap item cost *(Stamina-costs/stamina-regen-delay/animation-time/movement-penalty/health-cost)*
- [ ] Guide Page: Display a guide for the different aspects of the mod & commands

#### Other
- [ ] Have only 1 item equipable at a time.

## Code Design Overview
- Commands
    - Varous commands that are used for debugging, configuration, or help
- Components
    - The QuickAccessComponent is the main data class that the whole mod is built off of
- Config
    - All pre defined values that are either statically set or modifyable by admins & users 
- Events
    - Main way to trigger an effect through a player interaction. 
- Handlers
    - Logic for handling the triggered events
- Packet Adapters
    - Logic to convert player hotbar interaction to a UI button
- Systems
    - Any Component systems to verify the user is valid
- UI
    - All ui objects that a player could iteract with and view

## Progress
- [X] Add & remove QuickAccess component from player
- [ ] Add inventory item in specific location on QuickAccess item (Use Item Id)
- [ ] Add inventory item in specific location on QuickAccess item (Use Item Inventory & Position)
- [ ] Remove item from QuickAccess inventory & drop item
- [ ] Remove item from QuickAccess inventory & place in player inventory
- [ ] Swap item from QuickAccess inventory to hotbar
- [ ] QuickAccess GUI
- [ ] Invenotry GUI
- [ ] Guide GUI
- [ ] Player Settings GUI
- [ ] Admin Settings GUI

### Special Thanks
- Thanks to Hytalemodding.dev website for some great info on how to set most of this up. [Modding Documentation Website](https://hytalemodding.dev/en)
- Thanks to TroubleDEV for some amazing youtube tutorials [TroubleDEV Youtube Link](https://www.youtube.com/channel/UC8IirsfaLXk7WFn55j1zs-g)
- Thanks to TroubleDEV's Discord to helping me through various issues. 
- Thanks to Plugin Template for code template. [Template Link](https://github.com/Build-9/Hytale-Example-Project)
