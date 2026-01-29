# Wojo's Toolbelts and Quck Access Slings
Adds a few new items that, when used, allows the player to use 2 hotbar slots to swap between different weapons/items on the same hotbar location.
By default the QuickAccessItem needs to be placed in hotbar slot 9 to work. Pressing 9 will then open a GUI to select a different item.
The GUI will have a settings pannel to allow the user to change what Item is in each slot. 

## Design. 
- When picking up the toolbelt/sling it will automatically get placed into hotbar slot 9 & have tooltip.
- The player will be forced to have another item selected if 9 was initially selected.
- Item can be moved from hotbar slot 9 to stop interaction and added back to bring back interaction.
- When item placed in hotbar slot 9, Add items Component to player. When removed, remove Component from player.
- Pressing 9 will instead bring a popup for the user to change out whats in hotbar slot 1 with the selected item.

## New Items
- Crude Toolbelt
    - Holds max of 2 tools from inventory for quick access. (Update crude to be 3 after testing)

### TODO: (In no particular order)
- Tiered Slings & Toolbelts  
    - Builders Toolbelt (Blocks)
    - Weapon Slings (1 Handed, 2 Handed, Ranged, Magic)
    - Consumables Sling (Food or Weapons)
    - Possible Quick Access Items (Unofficial)
        - Crude Toolbelt (2 Tools)
        - Creative Toolbelt (10 Tools)
        - Crude Sling (2 Weapons)
        - Creative Sling (10 Weapons)
        - Builders Toolbelt (10 Blocks)
        - Consumables Sling (10 Consumables)
- Config 
    -  max & mins of item types,
    -  Multiple Items at once
    -  Default Hotbar slot selection to pick where items are placed
    -  Allow movement of QuickAccess item in the hotbar to change what button activates the GUI.
    -  Blacklist or whitelist items that can be added to the QuickAccess
- Handle Consumables
- Handle all items (Allow player to pick items that sling can hold and)
- Configurable Sling/Toolbelt (Allow server owners to make a custom item)
- Gui to
    - View Info about mod
    - Changing location of items in toolbelt
    - Change Hotbar location for toolbelt
    - Change Quickaccess GUI Styles
- Add visible Item to player model when equiped
- Animations to add/remove items from sling
- Add possible cost to swap items
    - Stamina Cost
    - Stamina Recharge Delay
    - Swap delay length (Animation)
    - Health Cost
    - Speed Reduction

## Code Design Overview
- QucickAccessComponent
    - Component that holds all the info needed
- QuickAccessSystem (Ref CHange Events System)
    - Handles Adding, Removing, and getting items from the Component whenever its updated.
- PacketFilter
    - Handle User Interaction for selecting hotbar 9, If they have component, Open the GUI & show info.
- ItemPickupEvent
    - When item is picked up add the QuickAcessComponent to the player
- QuickAccessGUI
    - GUI to swap out tool/item
- ConfigPlayerGUI
    - GUI to configure player specific settings for the mod. (Where items are stored hotbar location etc)
- ConfigAdminGUI
    - GUI to configure Admin settings for the mod. 


### Special Thanks
- Thanks to HytaleModding.dev website for some great info on how to set most of this up. [Modding Documentation Website](https://hytalemodding.dev/en)
- Thanks to TroubleDEV for some amazing youtube tutorials [TroubleDEV Youtube Link](https://www.youtube.com/channel/UC8IirsfaLXk7WFn55j1zs-g)
- Thanks to TroubleDEV's Discord to helping me through various issues. 
- Thanks to Plugin Template for code template. [Template Link](https://github.com/Build-9/Hytale-Example-Project)
